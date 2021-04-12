package com.example.swe_graphicalpasswordauthenticator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLDatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "ScramblerApp.db";
    private static final int DATABASE_VERSION = 1;
    public static final String USER_TABLE = "USER_TABLE";
    public static final String COL_USER = "COL_USER";
    public static final String COL_PASS = "COL_PASS";
    private SQLiteDatabase database;

    public SQLDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // called the first time the db is accessed
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + USER_TABLE + " (" + COL_USER + " STRING PRIMARY KEY, " + COL_PASS + " STRING)";

        db.execSQL(createUserTable);
    }

    // called when the db version is changed
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean registerUser(UserTableModel userModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // Check for DUPLICATE usernames here?????

        cv.put(COL_USER, userModel.getUsername());
        cv.put(COL_PASS, userModel.getPassword());

        long insert = db.insert(DATABASE_NAME, null, cv);
        if (insert == -1){
            // if Negative value, it falied to insert into DB
            return false;
        }
        else {
            // if Positive value, it succesfully placed into DB
            return true;
        }
    }
}
