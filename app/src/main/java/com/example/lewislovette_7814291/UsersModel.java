package com.example.lewislovette_7814291;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class UsersModel {
    //Dealing with user data
    private String email;
    private String password;
    private Bitmap profilePic;

    private View view;
    DatabaseHandler db;

    private static final UsersModel oneInstance = new UsersModel();

    /**
     * This is used to get a single instance of the class - implements the Singleton Pattern.
     * @return single instance of the class
     */
    public static UsersModel getInstance() {
        return oneInstance;
    }

    /**
     * Class constructor, only used by Singleton when first instantiating class.
     */
    public UsersModel(){

    }

    /**
     * Sets the private view variable within this class as well as instatiating the DatabaseHandler.
     * @param view - only param for setView.
     */
    public void setView(View view){
        this.view = view;
        db = new DatabaseHandler(view.getContext());
    }

    /**
     * Checks if the user has a profile picture
     * @return True if the user has a picture
     */
    public boolean hasPicture(){

        boolean temp = db.hasPicture(this.email);

        return temp;
    }

    /**
     * Gets the profile picture of the current user as Bitmap.
     * @return profilePic - the profile pic as a Bitmap
     */
    public Bitmap getProfilePic() {

        //Todo: return profile pic from db
        byte[] blob = db.getPicture(email);    //retrieve profile pic for specific user


        ByteArrayInputStream imageStream = new ByteArrayInputStream(blob);
        Bitmap blobToBitmap = BitmapFactory.decodeStream(imageStream);

        if(blobToBitmap != null){
            Log.v("BITMAP", "!= null:");
        }

        profilePic = blobToBitmap;

        return profilePic;
    }

    /**
     * Stores the users profile picture
     * @param profilePic - a Bitmap of the users profile pic
     */
    public void setProfilePic(Bitmap profilePic) {
        this.profilePic = profilePic;

        //Todo: fix error when saving image to database
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        profilePic.compress(Bitmap.CompressFormat.PNG, 100, output);

        //converting to blob to pass to db;
        byte[] toBlob = output.toByteArray();

        Log.v("userM", "email = " + this.email);
        Log.v("userM", "password = " + this.password);

        db.setPicture(toBlob, this.email);
    }

    public void addUser(String email, String password){
        setEmail(email);
        setPassword(password);

        db.addUser(email, password);
    }

    public boolean exists(){

        return true;
    }

    /**
     * Gets the email stored in the instance of this class.
     * @return String email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email within the instance of this class to the current user.
     * @param email - the email of the user that is currently signed in.
     */
    public void setEmail(String email) {
        this.email = email;

        //would then get data from db about this user (if it exists)
    }

    /**
     * Gets the password stored in the instance of this class.
     * @return String password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password within the instance of this class to the current user.
     * @param password - the email of the user that is currently signed in.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
