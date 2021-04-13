package com.example.swe_graphicalpasswordauthenticator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME ="ScramblerPass.db";
    public static final String STRING_TABLE ="stringTable";
    public static final String IMAGE_TABLE ="imageTable";

    public static final String COLUMN_1 ="ID"; // id to number each entry in the table, it's also set as the primary key (eg: 1, 2, 3, ...)
    public static final String COLUMN_2 ="username";
    public static final String COLUMN_3 ="stringPasswd";
    public static final String IMAGE_COLUMN ="imagePasswd";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE stringTable (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, stringPasswd TEXT)"); // create String Input table
        sqLiteDatabase.execSQL("CREATE TABLE imageTable (ID INTEGER PRIMARY  KEY AUTOINCREMENT, imagePasswd TEXT)"); // create image table

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { // drop the table if the table already exist exists
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + STRING_TABLE);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + IMAGE_TABLE);

        onCreate(sqLiteDatabase);
    }

    public long addUser(String user, String stringPasswd){ // add user and password to the database
        SQLiteDatabase db = this.getWritableDatabase(); //
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user); // put username in contentValue
        contentValues.put("stringPasswd",stringPasswd); // put password in contentValues
        long res = db.insert("stringTable",null,contentValues); // put the value contain the username and the password into the table
        db.close();
        return  res;
    }



    public long addImagePasswd(String imagePasswd){ // password to the database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("imagePasswd",imagePasswd); // put password in contentValues
        long res = db.insert("imageTable",null,contentValues); // put the value contain the username and the password into the table
        db.close();
        return  res;
    }

    public boolean checkUser(String username, String stringPasswd){ // check if user and password exist in the database
        String[] columns = { COLUMN_1 }; // set a string array of columns with the incrementing id
        SQLiteDatabase db = getReadableDatabase();
        String selection = COLUMN_2 + "=?" + " and " + COLUMN_3 + "=?";
        String[] selectionArgs = { username, stringPasswd };
        Cursor cursor = db.query(STRING_TABLE,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true; // return true if user exists
        else
            return  false; // return false if user doesn't exist
    }

    public boolean checkUsername(String username){ // check if user exists in the database
        String[] columns = { COLUMN_1 }; // set a string array of columns with the incrementing id
        SQLiteDatabase db = getReadableDatabase();
        String selection = COLUMN_2 + "=?";
        String[] selectionArgs = { username };
        Cursor cursor = db.query(STRING_TABLE,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true; // return true if user exists
        else
            return  false; // return false if user doesn't exist
    }


    public boolean checkImagePasswd(String imagePasswd){ // check if user exists in the database
        String[] columns = { COLUMN_1 }; // set a string array of columns with the incrementing id
        SQLiteDatabase db = getReadableDatabase();
        String selection = IMAGE_COLUMN + "=?";
        String[] selectionArgs = { imagePasswd };
        Cursor cursor = db.query(IMAGE_TABLE,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true; // return true if user exists
        else
            return  false; // return false if user doesn't exist
    }
}
