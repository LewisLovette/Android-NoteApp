package com.example.lewislovette_7814291;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class UsersModel {
    //Dealing with user data
    private String name;
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
        byte[] blob = db.getPicture(name);    //retrieve profile pic for specific user

        ByteArrayInputStream imageStream = new ByteArrayInputStream(blob);
        Bitmap blobToBitmap = BitmapFactory.decodeStream(imageStream);

        return blobToBitmap;
    }

    public void setProfilePic(Bitmap profilePic) {
        this.profilePic = profilePic;

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        profilePic.compress(Bitmap.CompressFormat.PNG, 100, out);

        //converting to blob to pass to db;
        byte[] toBlob = out.toByteArray();

        db.setPicture(toBlob);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
