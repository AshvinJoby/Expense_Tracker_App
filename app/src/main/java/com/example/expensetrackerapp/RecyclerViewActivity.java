package com.example.expensetrackerapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    ExpenseAdapter adapter;
    ArrayList<ExpenseModel> arrayList = new ArrayList<>();
    ExpenseSQLite sqlite;
    public static boolean REC_VIEW = true;
    TextView recyTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyTv = findViewById(R.id.recyTv);
        recyclerview = findViewById(R.id.recyclerView);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        sqlite = new ExpenseSQLite(this);

        loadData();
    }

    public void loadData(){
        Cursor cursor = null;

        if (REC_VIEW){
            cursor = sqlite.showExpenseRecyclerView();
            recyTv.setText("Expense List");
        } else {
            cursor = sqlite.showIncomeRecyclerView();
            recyTv.setText("Income List");
        }

        if (cursor!=null && cursor.getCount()>0){
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String buy = cursor.getString(1);
                String reason = cursor.getString(2);
                arrayList.add(new ExpenseModel(id,buy,reason));
            }

            adapter = new ExpenseAdapter(arrayList,RecyclerViewActivity.this);
            recyclerview.setAdapter(adapter);
        }
    }
}