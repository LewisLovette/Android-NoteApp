package com.example.lewislovette_7814291;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    View view;
    Button photoButton;
    Button mapButton;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;
    UsersModel usersModel;
    TextView userEmail;

    /**
     * Constructor for profile fragment
     */
    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Removes the top bar from view
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    /**
     * Sets the profile to users data, allows for pictures and google maps access
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        photoButton = view.findViewById(R.id.photoButton);
        mapButton = view.findViewById(R.id.mapBack);
        imageView = view.findViewById(R.id.profilePicView);
        userEmail = view.findViewById(R.id.userEmail);

        usersModel = UsersModel.getInstance();
        usersModel.setView(view);

        userEmail.setText(usersModel.getEmail());

        if(usersModel.hasPicture()) {
            imageView.setImageBitmap(usersModel.getProfilePic());
        }
        //Todo: set profile pic for specific user (if it exists otherwise set default)
        /*
        //setting profile pic if available
        if(usersModel.getProfilePic() == null) {
            Log.v("GETTING PROFILE PIC", "NOT FOUND");
        }
        else {
            imageView.setImageBitmap(usersModel.getProfilePic());
        }
        */


        imageView.setOnClickListener(new View.OnClickListener() {

                 public void onClick(View v) {
                     imageView.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.shake));
                 }
             });

        photoButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), MapsActivity.class);
                startActivity(intent);
                //don't finish on navigation screen.
            }

        });


        return view;
    }

    /**
     * Saves the users image taken within the camera app
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);

            //sending to model to be saved
            //Todo: Fix setting blob in db
            usersModel.setProfilePic(imageBitmap);
        }
    }


}
