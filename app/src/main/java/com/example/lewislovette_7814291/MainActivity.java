package com.example.lewislovette_7814291;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    public String currentUser;
    private Button login;
    private Button signup;

    /**
     * Handles redirect to login or signup pages
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.startLogin);
        signup = findViewById(R.id.startSignup);


        login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LoginPage.class);
                startActivity(intent);
                finish();
            }

        });

        signup.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SignupPage.class);
                startActivity(intent);
                finish();
            }

        });


    }
}
