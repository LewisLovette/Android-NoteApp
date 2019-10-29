package com.example.lewislovette_7814291;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeeNoteFragment extends Fragment {

    private View view;

    private ListView listView;
    String userName;    //as only one user is accessing notes
    ArrayList<String> userNote;
    public static int[] deleteIcon = {
            R.drawable.ic_delete_black_24dp,
    };
    private ArrayList<NoteModel> notes = new ArrayList<>();


    public SeeNoteFragment() {

        //testing atm - though you would pass name to the DB and it would retrieve all the notes for that person

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_see_note, container, false);

        userName = "Lewis";

        userNote = new ArrayList<>();
        userNote.add("Just a list");
        userNote.add("Of Note");
        userNote.add("And such");

        generateNotes();

        Log.v("COMPLEX LIST VIEW: ", "index 0 = " + notes.get(0).getNote());
        Log.v("COMPLEX LIST VIEW: ", "index 1 =" + notes.get(1).getNote());
        Log.v("COMPLEX LIST VIEW: ", "index 2 =" + notes.get(2).getNote());


        listView = (ListView) view.findViewById(R.id.listViewComplex);
        listView.setAdapter(new NoteAdapter(view.getContext(), R.layout.list_item, notes));
        listView.setOnItemClickListener(

                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Toast.makeText(view.getContext(), "You clicked " + notes.get(position), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        return view;
    }

    private void generateNotes() {

        for (int i = 0; i < userNote.size(); i++) {
            notes.add(new NoteModel(userName, userNote.get(i)));
        }
    }

    public void onButtonClick(View v){
        startActivity(new Intent(view.getContext(), SeeNoteFragment.class));
    }




}
