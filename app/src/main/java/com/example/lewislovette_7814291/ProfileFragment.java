package com.example.lewislovette_7814291;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
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

    public ProfileFragment() {
        // Required empty public constructor
    }

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
