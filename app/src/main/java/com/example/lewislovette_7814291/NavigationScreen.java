package com.example.lewislovette_7814291;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Handles navigation bar
 */
public class NavigationScreen extends AppCompatActivity {


    final Fragment addNoteFragment = new AddNoteFragment();
    final Fragment seeNoteFragment = new SeeNoteFragment();
    final Fragment profileFragment = new ProfileFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = addNoteFragment;

    /**
     * Sets up fragments to be used within the navigation bar
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_screen);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.main_container, profileFragment, "3").hide(profileFragment).commit();
        fm.beginTransaction().add(R.id.main_container, seeNoteFragment, "2").hide(seeNoteFragment).commit();
        fm.beginTransaction().add(R.id.main_container,addNoteFragment, "1").commit();

    }

    /**
     * Switches between fragments depending on navigation pressed. Defaults to Add Note Fragment
     */
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
                    fm.beginTransaction().remove(seeNoteFragment).commit();
                    Fragment seeNoteFragment = new SeeNoteFragment();
                    fm.beginTransaction().add(R.id.main_container, seeNoteFragment, "2").commit();
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
