package com.example.lewislovette_7814291;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lewislovette_7814291.AddNoteFragment;
import com.example.lewislovette_7814291.ProfileFragment;
import com.example.lewislovette_7814291.R;
import com.example.lewislovette_7814291.SeeNoteFragment;


public class NavigationScreen extends AppCompatActivity {


    final Fragment addNoteFragment = new AddNoteFragment();
    final Fragment seeNoteFragment = new SeeNoteFragment();
    final Fragment profileFragment = new ProfileFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = addNoteFragment;
    Button addNoteButton = (Button) findViewById(R.id.saveButton);
    EditText addNoteText = (EditText) findViewById(R.id.addNoteText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_screen);



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.main_container, profileFragment, "3").hide(profileFragment).commit();
        fm.beginTransaction().add(R.id.main_container, seeNoteFragment, "2").hide(seeNoteFragment).commit();
        fm.beginTransaction().add(R.id.main_container,addNoteFragment, "1").commit();

        //adding listeners to fragments (as we don't destroy fragments it means that they can all be within the onCreate();
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                addNoteFragment.setNote((String) addNoteText.getText());
            }
        });
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fm.beginTransaction().hide(active).show(addNoteFragment).commit();
                    active = addNoteFragment;
                    return true;

                case R.id.navigation_dashboard:
                    fm.beginTransaction().hide(active).show(seeNoteFragment).commit();
                    active = seeNoteFragment;
                    return true;

                case R.id.navigation_notifications:
                    fm.beginTransaction().hide(active).show(profileFragment).commit();
                    active = profileFragment;
                    return true;
            }
            return false;
        }
    };
}
