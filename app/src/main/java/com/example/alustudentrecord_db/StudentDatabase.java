package com.example.alustudentrecord_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class StudentDatabase extends SQLiteOpenHelper {

    // Naming the database and the version
    public static final String databaseName = "cohort1";
    public static final int databaseVersion = 1;
    public static final String TABLE_NAME = "students";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    public StudentDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " +TABLE_NAME+ "( "+NAME+"TEXT"+EMAIL+"TEXT"+PASSWORD+"TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

// @Override
// public SQLiteDatabase getReadableDatabase() {
// return super.getReadableDatabase();
// }

// @Override
// public SQLiteDatabase getWritableDatabase() {
// return super.getWritableDatabase();
// }

    public void trialSetData(String name, String email, String password){
        SQLiteDatabase data_s = getWritableDatabase();

        ContentValues creeateContent = new ContentValues();

        creeateContent.put(NAME, name);
        creeateContent.put(EMAIL,email);
        creeateContent.put(PASSWORD, password);

        data_s.insert(TABLE_NAME, null, creeateContent);
    }

}