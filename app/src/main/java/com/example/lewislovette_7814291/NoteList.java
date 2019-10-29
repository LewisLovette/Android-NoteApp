package com.example.lewislovette_7814291;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NoteList extends AppCompatActivity {

    private ListView listView;
    String userName;    //as only one user is accessing notes
    ArrayList<String> userNote;
    public static int[] deleteIcon = {
            R.drawable.ic_delete_black_24dp,
    };
    private ArrayList<NoteModel> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);


        //testing atm - though you would pass name to the DB and it would retrieve all the notes for that person
        userName = "Lewis";

        userNote = new ArrayList<>();
        userNote.add("Just a list");
        userNote.add("Of Note");
        userNote.add("And such");

        generateNotes();

        Log.v("COMPLEX LIST VIEW: ", "index 0 = " + notes.get(0).getNote());
        Log.v("COMPLEX LIST VIEW: ", "index 1 =" + notes.get(1).getNote());
        Log.v("COMPLEX LIST VIEW: ", "index 2 =" + notes.get(2).getNote());


        listView = (ListView) findViewById(R.id.listViewComplex);
        listView.setAdapter(new NoteAdapter(this, R.layout.list_item, notes));
        listView.setOnItemClickListener(

                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Toast.makeText(getBaseContext(), "You clicked " + notes.get(position), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void generateNotes() {

        for (int i = 0; i < userNote.size(); i++) {
            notes.add(new NoteModel(userName, userNote.get(i)));
        }
    }

    public void onButtonClick(View v){
        startActivity(new Intent(this, NoteList.class));
    }

}
