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

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    View view;
    Button photoButton;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;
    UsersModel usersModel;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        photoButton = view.findViewById(R.id.photoButton);
        imageView = view.findViewById(R.id.profilePicView);
        usersModel = new UsersModel(view);

        if(usersModel.getProfilePic() == null) {
            Log.v("GETTING PROFILE PIC", "NOT FOUND");
        }

        imageView.setImageBitmap(usersModel.getProfilePic());

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



        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);

            //sending to model to be saved
            usersModel.setProfilePic(imageBitmap);
        }
    }


}
