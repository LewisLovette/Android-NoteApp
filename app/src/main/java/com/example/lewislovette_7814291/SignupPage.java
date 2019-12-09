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

public class SignupPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView statusText;
    private TextView detailText;
    private EditText email;
    private EditText password;
    public String emailUser;
    public String passwordUser;
    private Button signup;
    private Button back;

    private NoteModel noteModel;
    private UsersModel usersModel;

    private String TAG = "Firebase Auth";

    /**
     * Handles signup form and back button
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        email = findViewById(R.id.emailInput);
        password = findViewById(R.id.passwordInput);
        signup = findViewById(R.id.signup);
        back = findViewById(R.id.back);
        mAuth = FirebaseAuth.getInstance();

        //Setting up models for the user
        View view  = findViewById(android.R.id.content);

        noteModel = NoteModel.getInstance();
        noteModel.setView(view);
        usersModel = UsersModel.getInstance();
        usersModel.setView(view);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

        signup.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                createAccount(email.getText().toString(), password.getText().toString());
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
     * Handles connection to firebase
     */
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    /**
     * Creates an account locally and with firebase.
     * @param email
     * @param password
     */
    private void createAccount(final String email, String password) {
        Log.v(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        Log.v("user model", "email = " + email);
        Log.v("user model", "password = " + password);
        usersModel.setEmail(email);
        usersModel.setPassword(password);
        noteModel.setEmail(email);
        emailUser = email;
        passwordUser = password;

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.v(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                            usersModel.addUser(emailUser, passwordUser);

                            //If signup is successful, go to navigation page
                            Intent intent = new Intent(getBaseContext(), NavigationScreen.class);
                            startActivity(intent);
                            finish();

                        } else {
                            if(usersModel.exists()) {
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getBaseContext(), "User Already exists",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);

                            }
                            else{
                                usersModel.addUser(emailUser, passwordUser);
                                Log.v(TAG, "Firebase connection failed. Local user added");
                                Intent intent = new Intent(getBaseContext(), NavigationScreen.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        // [START_EXCLUDE]
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }

    /**
     * Information on if user successfully signed up
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
     * Valides email and password information
     * @return boolean valid
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
