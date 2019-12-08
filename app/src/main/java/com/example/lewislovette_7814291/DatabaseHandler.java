package com.example.lewislovette_7814291;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.ByteArrayOutputStream;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(Context context){
        super(context, "NoteAppDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS userDetails (email, password, profilePic BLOB)");    //BLOB is for byte[] of picture
        db.execSQL("CREATE TABLE IF NOT EXISTS userNotes (name, notes)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Adds a user to the database if non existent.
     * @param email
     * @param password
     * @return empty if user exists
     */
    public void addUser(String email, String password) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        Cursor cursor;
        String query = "select * from userDetails where email = ?";
        cursor = sqLiteDatabase.rawQuery(query, new String[] {email});

        if(cursor.moveToFirst()) {
            Log.v("dbhelper", "user exists, cursor = " + cursor.getString(cursor.getColumnIndex("email")));
            return;
        }


        Log.v("dbhelper", "adding - result = " + cursor.getCount());

        contentValues.put("email", email);
        contentValues.put("password", password);

        long result = sqLiteDatabase.insert("userDetails", null, contentValues);

        if (result > 0) {
            Log.v("dbhelper", "inserted successfully");
        } else {
            Log.v("dbhelper", "failed to insert");
        }
        sqLiteDatabase.close();
    }

    public void addNote(String name, String note) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("notes", note);

        long result = sqLiteDatabase.insert("UserNotes", null, contentValues);

        if (result > 0) {
            Log.v("dbhelper", "inserted successfully");
        } else {
            Log.v("dbhelper", "failed to insert");
        }
        sqLiteDatabase.close();
    }

    public void getNotes(NoteModel userNote) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        //Todo: get notes from db
        //contentValues.put("name", userNote.getName());
        //contentValues.put("notes", userNote.getNote());

        long result = sqLiteDatabase.insert("UserNotes", null, contentValues);

        if (result > 0) {
            Log.v("dbhelper", "inserted successfully");
        } else {
            Log.v("dbhelper", "failed to insert");
        }
        sqLiteDatabase.close();
    }

    public void setPicture(byte[] blob, String email){
        Log.v("dbhelper", "inserting new picture");
        Log.v("dbhelper", "email = " + email);
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues contentValues = new ContentValues();


        //updating a users profile picture
        contentValues.put("profilePic", blob);
        sqLiteDatabase.update("userDetails", contentValues,
                "email = '" + email + "'", null);


        //String query = "update userDetails set profilePic = '" + blob + "' where email = ?";
        //sqLiteDatabase.rawQuery(query, new String[]{email});

        Log.v("dbhelper", "picture succesffuly updated for user " + email );


        sqLiteDatabase.close();
    }

    public byte[] getPicture(String userEmail){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        byte[] blob;
        Cursor cursor;
        userEmail = NULL;

        String sql = "SELECT profilePic FROM userDetails";


        //selection args example:  https://stackoverflow.com/questions/10598137/rawqueryquery-selectionargs
        cursor = sqLiteDatabase.rawQuery(sql, new String[] {});

        if(cursor.getCount() <= 0){
            Log.v("Cursor", "has no data");
        }

        cursor.moveToFirst();
        blob = cursor.getBlob(0);


        return blob;
    }
}
