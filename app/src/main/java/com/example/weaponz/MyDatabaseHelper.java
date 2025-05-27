package com.example.weaponz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import androidx.annotation.Nullable;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String databaseName = "SignLog.db";
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, "SignLog.db", null, 2);
    }
    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table users(username TEXT, email TEXT, phone TEXT, password TEXT, PRIMARY KEY(email))");
        MyDatabase.execSQL("create Table user_dates(email TEXT, creation_date TEXT, PRIMARY KEY(email))"); // New table for storing
        // creation dates
    }
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists user_dates");
        onCreate(MyDB);
    }
    public Boolean insertData(String username, String email, String phone, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("password", password);
        long result = MyDatabase.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            ContentValues dateContentValues = new ContentValues();
            dateContentValues.put("email", email);
            dateContentValues.put("creation_date", new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
            MyDatabase.insert("user_dates", null, dateContentValues);
            return true;
        }
    }
    public Boolean checkUserExists(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});
        if(cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }

    public String getCreationDate(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select creation_date from user_dates where email = ?", new String[]{email});
        if(cursor.moveToFirst()) {
            return cursor.getString(0);
        } else {
            return null;
        }
    }


    public String getUsername(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select username from users where email = ?", new String[]{email});
        if(cursor.moveToFirst()) {
            return cursor.getString(0);
        } else {
            return null;
        }
    }
    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }

    public Boolean updateUserData(String username, String email, String phone, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("password", password);
        int result = MyDatabase.update("users", contentValues, "email = ?", new String[]{email});
        return result > 0;
    }

    public Boolean deleteUser(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        return MyDatabase.delete("users", "email = ?", new String[]{email}) > 0;
    }
}
