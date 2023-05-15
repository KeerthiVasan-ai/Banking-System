package com.keerthi77459.bankmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Statement;
import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {

        super(context, "bank.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE accounts(name TEXT,account_number TEXT primary Key,email TEXT,amount TEXT)");
        DB.execSQL("create table transfers(sender TEXT,receiver TEXT,amount TEXT)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {

    }

    public Boolean insertdata(String name,String ano,String email){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("account_number",ano);
        contentValues.put("email",email);
        contentValues.put("amount","30000");
        long result = DB.insert("accounts",null,contentValues);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor returnName(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT name FROM accounts",null);
        return cursor;
    }

    public Boolean updatedata(String name,String amount){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("amount",amount);
        long result = DB.update("accounts",contentValues,"name=?",new String[] {name});
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM accounts",null);
        return cursor;
    }

    public Cursor getName(String condition){
        SQLiteDatabase DB = getReadableDatabase();

        String Query = "SELECT amount FROM accounts WHERE " + condition;
        return DB.rawQuery(Query,null);
    }


    public Boolean transactions(String sender,String receiver,String amount){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sender",sender);
        contentValues.put("receiver",receiver);
        contentValues.put("amount",amount);
        long result = DB.insert("transfers",null,contentValues);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

}
