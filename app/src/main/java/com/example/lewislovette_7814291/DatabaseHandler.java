package com.example.lewislovette_7814291;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

/**
 * Responsible for traffic in/out of the database
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    Cursor cursor;

    /**
     * DatabaseHandler constructor
     * @param context - of the current activity
     */
    public DatabaseHandler(Context context){
        super(context, "NoteAppDatabase", null, 1);
    }

    /**
     *  Creates tables 'userDetails, userNotes' if they do not exist
     * @param db - takes in the database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS userDetails (email, password, profilePic BLOB)");    //BLOB is for byte[] of picture
        db.execSQL("CREATE TABLE IF NOT EXISTS userNotes (email, notes)");
    }

    /**
     * onUpgrade
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Adds a user to the database if non existent.
     * @param email - current users email
     * @param password  - current users password
     */
    public void addUser(String email, String password) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues contentValues = new ContentValues();

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
            Log.v("dbhelper", "user inserted successfully");
        } else {
            Log.v("dbhelper", "failed to insert user");
        }
        sqLiteDatabase.close();
    }

    /**
     * Adds a user to the database if non existent.
     * @param email - current users email
     * @param password  - current users password
     * @return empty if user exists
     */
    public boolean userExists(String email, String password) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String query = "select * from userDetails where email = ? and password = ?";
        cursor = sqLiteDatabase.rawQuery(query, new String[]{email, password});


        if(cursor == null){
            Log.v("dbhelper", "user does not exist");
            return false;
        }


        if(cursor.moveToFirst()) {
            Log.v("dbhelper", "user exists, cursor = " + cursor.getString(cursor.getColumnIndex("email")));
            return true;
        }

        Log.v("dbhelper", "user does not exist");

        sqLiteDatabase.close();

        return false;
    }

    /**
     * Adds a note to the database associated with the current user
     * @param email - email of the current user
     * @param note - the note the user wants to add
     */
    public void addNote(String email, String note) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("email", email);
        contentValues.put("notes", note);

        long result = sqLiteDatabase.insert("userNotes", null, contentValues);

        if (result > 0) {
            Log.v("dbhelper", "note inserted successfully");
        } else {
            Log.v("dbhelper", "failed to insert note");
        }
        sqLiteDatabase.close();
    }

    /**
     * Gets all of a users notes into an ArrayList of Strings
     * @param email - the current users email
     * @return ArrayList<String> containing all of the users notes
     */
    public ArrayList<String> getNotes(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ArrayList<String> notes = new ArrayList<String>();

        String query = "select * from userNotes where email = ?";
        cursor = sqLiteDatabase.rawQuery(query, new String[] {email});

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            notes.add(cursor.getString(cursor.getColumnIndex("notes")));
            cursor.moveToNext();
        }
        sqLiteDatabase.close();

        return notes;
    }

    /**
     * Sets the users profile picture when they take one
     * @param blob
     * @param email - the current users email
     */
    public void setPicture(byte[] blob, String email){
        Log.v("dbhelper", "inserting new picture");
        Log.v("dbhelper", "email = " + email);
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues contentValues = new ContentValues();


        //updating a users profile picture
        contentValues.put("profilePic", blob);
        sqLiteDatabase.update("userDetails", contentValues,
                "email = '" + email + "'", null);


        Log.v("dbhelper", "picture successfully updated for user " + email );

        sqLiteDatabase.close();
    }

    /**
     * Gets the users profile picture
     * @param email - the current users email
     * @return blob of the users picture
     */
    public byte[] getPicture(String email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        byte[] blob;

        String query = "select profilePic from userDetails where email = ?";
        cursor = sqLiteDatabase.rawQuery(query, new String[] {email});

        cursor.moveToFirst();
        blob = cursor.getBlob(0);


        return blob;
    }

    /**
     * Checks if the user has taken a picture of their profile
     * @param email - the current users email
     * @return true or false for if user has a picture
     */
    public boolean hasPicture(String email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String query = "select profilePic from userDetails where email = ?";
        cursor = sqLiteDatabase.rawQuery(query, new String[] {email});

        cursor.moveToFirst();
        if(cursor.getBlob(0) != null){
            Log.v("dbhelper", "blob = true");
            return true;
        }

        Log.v("dbhelper", "cursor = false");
        return false;


    }

    /**
     * Deletes a users specific note from the database
     * @param email - current users email
     * @param note - users note to delete
     */
    public void deleteNote(String email, String note){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        Log.v("dbhelper", "deleting note: " + note);


        //deleting from db
        sqLiteDatabase.delete("userNotes","email = '" + email + "' and notes = '" + note + "'",
                null);

        Log.v("dbhelper", "note successfully deleted");


    }

}
