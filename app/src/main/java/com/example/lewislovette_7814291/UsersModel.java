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

    public UsersModel(View view){
        this.view = view;
        db = new DatabaseHandler(view.getContext());
    }

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

    public void setProfilePic(Bitmap profilePic) {
        this.profilePic = profilePic;

        //Todo: fix error when saving image to database
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        profilePic.compress(Bitmap.CompressFormat.PNG, 100, output);

        //converting to blob to pass to db;
        byte[] toBlob = output.toByteArray();

        Log.v("user model", "email = " + this.email);
        Log.v("user model", "password = " + this.password);

        //db.setPicture(toBlob, this.email, this.password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

        //would then get data from db about this user (if it exists)
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
