package com.example.lewislovette_7814291;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Responsible for the  {@link LoginPage} view
 */
public class LoginPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    //TextView statusText;
    private EditText email;
    private EditText password;
    private Button login;
    private Button back;

    private NoteModel noteModel;
    private UsersModel usersModel;

    public String emailUser;
    public String passwordUser;
    String TAG =  "Firebase Auth";

    /**
     * Handles login and back buttons, sets variables.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //statusText = findViewById(R.id.statusText);

        email = findViewById(R.id.emailInput);
        password = findViewById(R.id.passwordInput);
        login = findViewById(R.id.login);
        back = findViewById(R.id.back);

        //Setting up models for the user
        View view  = findViewById(android.R.id.content);

        noteModel = NoteModel.getInstance();
        noteModel.setView(view);
        usersModel = UsersModel.getInstance();
        usersModel.setView(view);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

        login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                signIn(email.getText().toString(), password.getText().toString());

            }
        });

        back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

        });

    }

    /**
     * Sets up firebase connection
     */
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    /**
     *  Handles sign in with Firebase and Locally if no internet.
     * @param email - current users email
     * @param password  - current users password
     */
    private void signIn(final String email, String password) {
        Log.v(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        this.emailUser = email;
        this.passwordUser = password;

        usersModel.setEmail(email);
        usersModel.setPassword(password);
        noteModel.setEmail((email));

        Log.v("user model", "email = " + email);
        Log.v("user model", "password = " + password);

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.v(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                            usersModel.addUser(emailUser, passwordUser);

                            //If login is successful, go to navigation page
                            Intent intent = new Intent(getBaseContext(), NavigationScreen.class);
                            startActivity(intent);
                            finish();

                        } else {
                            /*
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getBaseContext(), "Authentication failed. Trying locally",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                            */

                            if(usersModel.exists()) {
                                Intent intent = new Intent(getBaseContext(), NavigationScreen.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(getBaseContext(), "Firebase & Local Login Failed",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }

                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            //statusText.setText("AUTH FAILED");
                        }
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });

    }

    /**
     * Information on user sign in
     * @param user
     */
    private void updateUI(FirebaseUser user) {

        if (user != null) {
            Log.v(TAG, "Signed in Successfully");
        } else {
            Log.v(TAG, "Failed to Signed in");
            //Open activity
        }
    }

    /**
     * Validates email and password information
     * @return
     */
    private boolean validateForm() {
        boolean valid = true;

        String email = this.email.getText().toString();
        if (TextUtils.isEmpty(email)) {
            this.email.setError("Required.");
            valid = false;
        } else {
            this.email.setError(null);
        }

        String password = this.password.getText().toString();
        if (TextUtils.isEmpty(password)) {
            this.password.setError("Required.");
            valid = false;
        } else {
            this.password.setError(null);
        }

        return valid;
    }

}
