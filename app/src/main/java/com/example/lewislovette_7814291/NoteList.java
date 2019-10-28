package com.example.lewislovette_7814291;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class NoteList extends AppCompatActivity {

    private ListView listView;
    private String[] userName;
    private String[] userNote;
    public static int[] deleteIcon = {
            R.drawable.ic_delete_black_24dp,
    };
    private ArrayList<NoteModel> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        userName = getResources().getStringArray(R.array.userName);
        userNote = getResources().getStringArray(R.array.userNote);
        generateNote();
    }

    private void generateNote() {

        for (int i = 0; i < deleteIcon.length; i++) {
            notes.add(new NoteModel(userName[i], userNote[i]));
        }
    }

}
