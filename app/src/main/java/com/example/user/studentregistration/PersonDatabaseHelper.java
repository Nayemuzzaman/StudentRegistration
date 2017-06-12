package com.example.user.studentregistration;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PersonDatabaseHelper {

    private static final String TAG = PersonDatabaseHelper.class.getSimpleName();

    // database configuration
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mydatabase.db";

    // table configuration
    private static final String TABLE_NAME = "person_table";         // Table name
    private static final String PERSON_TABLE_COLUMN_ID = "_id";     // id required for cursor
    private static final String PERSON_TABLE_COLUMN_NAME = "person_name";
    private static final String PERSON_TABLE_COLUMN_Email = "person_email";
    private static final String PERSON_TABLE_COLUMN_Phone = "person_phone";
    private static final String PERSON_TABLE_COLUMN_Address = "person_address";

    private DatabaseOpenHelper openHelper;
    private SQLiteDatabase database;


    public PersonDatabaseHelper(Context aContext) {

        openHelper = new DatabaseOpenHelper(aContext);
        database = openHelper.getWritableDatabase();
    }

    public void insertData (String aPersonName, String aPersonEmail, String aPersonPhone,  String aPersonAddress) {

        // Content values error

        ContentValues contentValues = new ContentValues();

        contentValues.put(PERSON_TABLE_COLUMN_NAME, aPersonName);
        contentValues.put(PERSON_TABLE_COLUMN_Email, aPersonEmail);
        contentValues.put(PERSON_TABLE_COLUMN_Phone, aPersonPhone);
        contentValues.put(PERSON_TABLE_COLUMN_Address, aPersonAddress);

        database.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor getAllData () {

        String buildSQL = "SELECT * FROM " + TABLE_NAME;

        Log.d(TAG, "getAllData SQL: " + buildSQL);

        return database.rawQuery(buildSQL, null);
    }

// database

    private class DatabaseOpenHelper extends SQLiteOpenHelper {

        public DatabaseOpenHelper(Context aContext) {
            super(aContext, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            // Create table

            String buildSQL = "CREATE TABLE " + TABLE_NAME + "( " + PERSON_TABLE_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    PERSON_TABLE_COLUMN_NAME + " TEXT, " + PERSON_TABLE_COLUMN_Email + " TEXT, " + PERSON_TABLE_COLUMN_Phone + " TEXT, " + PERSON_TABLE_COLUMN_Address+ " TEXT )";

            Log.d(TAG, "onCreate SQL: " + buildSQL);

            sqLiteDatabase.execSQL(buildSQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            // Upgrade table

            String buildSQL = "DROP TABLE IF EXISTS " + TABLE_NAME;

            Log.d(TAG, "onUpgrade SQL: " + buildSQL);

            sqLiteDatabase.execSQL(buildSQL);       // drop table

            onCreate(sqLiteDatabase);               // Create table
        }
    }
}