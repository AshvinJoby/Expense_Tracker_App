package com.example.expensetrackerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class ExpenseSQLite extends SQLiteOpenHelper {

    public ExpenseSQLite(@Nullable Context context){
        super(context,"expense_tracker",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
    database.execSQL("create table expense(id INTEGER primary key autoincrement, amount double, reason text, time double)");
        database.execSQL("create table income(id INTEGER primary key autoincrement, amount double, reason text, time double)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL("drop table if exists expense");
        database.execSQL("drop table if exists income");
    }

    public void addExpense(double amount, String reason){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("amount",amount);
        values.put("reason",reason);
        values.put("time",System.currentTimeMillis());
        database.insert("expense",null,values);
    }

    public void addIncome(double amount, String reason){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("amount",amount);
        values.put("reason",reason);
        values.put("time",System.currentTimeMillis());
        database.insert("income",null,values);
    }

    public double showExpense(){
        double totalExpense = 0;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from expense",null);

        if (cursor!=null && cursor.getCount()>0){
            while(cursor.moveToNext()){
                double expense = cursor.getDouble(1);
                totalExpense = totalExpense + expense;
            }
        }
        return totalExpense;
    }

    public double showIncome(){
        double totalIncome = 0;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from income",null);

        if (cursor!=null && cursor.getCount()>0){
            while(cursor.moveToNext()){
                double income = cursor.getDouble(1);
                totalIncome = totalIncome + income;
            }
        }
        return totalIncome;
    }

    public Cursor showExpenseRecyclerView(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from expense",null);
        return cursor;
    }

    public Cursor showIncomeRecyclerView(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from income",null);
        return cursor;
    }

}

