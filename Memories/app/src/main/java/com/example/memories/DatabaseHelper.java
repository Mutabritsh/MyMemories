package com.example.memories;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "memories.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_table";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_IMAGE = "image";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_LOCATION + " TEXT, "
                + COLUMN_IMAGE + " BLOB)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
@SuppressLint("RestrictedApi")
public void insertData(Data data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
    values.put("title", data.getTitle());
        values.put("description", data.getDescription());
        values.put("location", data.getLocation());
        values.put("image", data.getImage());

    long insert = db.insert(TABLE_NAME, null, values);
    if (insert != -1) {
        Log.e(TAG, "insertData: memory saved");
    } else {
        Log.e(TAG, "insertData: failed to save memory");
    }

    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);


    }
}


//    public boolean insertData(String title, String description, String location, byte[] image) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_TITLE, title);
//        values.put(COLUMN_DESCRIPTION, description);
//        values.put(COLUMN_LOCATION, location);
//        values.put(COLUMN_IMAGE, image);
//
//        long result = db.insert(TABLE_NAME, null, values);
//        db.close();
//        return result != -1;


