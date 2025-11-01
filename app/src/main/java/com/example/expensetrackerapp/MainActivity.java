package com.example.expensetrackerapp;

import android.app.Activity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import com.example.expensetrackerapp.R;

public class MainActivity extends AppCompatActivity{

    TextView mainBalance, totalExpense, addExpense, showExpense, totalIncome, addIncome, showIncome;

    ExpenseSQLite sqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sqlite = new ExpenseSQLite(this);

        mainBalance = findViewById(R.id.mainBalance);
        totalExpense = findViewById(R.id.totalExpense);
        addExpense = findViewById(R.id.addExpense);
        showExpense = findViewById(R.id.expenseShow);
        totalIncome = findViewById(R.id.totalIncome);
        addIncome = findViewById(R.id.addIncome);
        showIncome = findViewById(R.id.incomeShow);

        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddActivity.EXPENSE = true;
                startActivity(new Intent(MainActivity.this,AddActivity.class));
            }
        });

        addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddActivity.EXPENSE = false;
                startActivity(new Intent(MainActivity.this,AddActivity.class));
            }
        });

        showExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerViewActivity.REC_VIEW = true;
                startActivity(new Intent(MainActivity.this,RecyclerViewActivity.class));
                showData();
            }
        });

        showIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerViewActivity.REC_VIEW = false;
                startActivity(new Intent(MainActivity.this,RecyclerViewActivity.class));
                showData();
            }
        });

    }

    public void showData(){
        totalExpense.setText("BDT: "+sqlite.showExpense());
        totalIncome.setText("BDT: "+sqlite.showIncome());

        double balance = sqlite.showIncome() - sqlite.showExpense();
        mainBalance.setText("BDT: "+balance);
    }

    @Override
    protected void onResume(){
        showData();
        super.onResume();
    }
}