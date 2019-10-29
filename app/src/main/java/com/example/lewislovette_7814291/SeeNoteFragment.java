package com.example.lewislovette_7814291;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeeNoteFragment extends Fragment {

    private View view;
    private TextView nameTitle;

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

        nameTitle = view.findViewById(R.id.whosNote);

        userName = "Lewis";

        userNote = new ArrayList<>();
        userNote.add("Just a list | seeing if note wraps | seeing if note wraps | seeing if note wraps | seeing if note wraps | seeing if note wraps | seeing if note wraps | seeing if note wraps | seeing if note wraps ");
        userNote.add("Of Notes");
        userNote.add("And such");

        generateNotes();

        nameTitle.setText(userName + "'s Notes");

        /* Tests
        Log.v("COMPLEX LIST VIEW: ", "index 0 = " + notes.get(0).getNote());
        Log.v("COMPLEX LIST VIEW: ", "index 1 = " + notes.get(1).getNote());
        Log.v("COMPLEX LIST VIEW: ", "index 2 = " + notes.get(2).getNote());
        */

        listView = (ListView) view.findViewById(R.id.listViewComplex);
        listView.setAdapter(new NoteAdapter(view.getContext(), R.layout.list_item, notes));
        listView.setOnItemClickListener(

                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //as button doesn't work but we know which view it is, set a popup to delete
                        //pass the view to the delete and if yes then delete
                        ImageButton deleteButton = view.findViewById(R.id.deleteButton);
                        TextView textInfo = view.findViewById(R.id.textViewNote);
                        Log.v("BUTTON ID", Integer.toString(deleteButton.getId()));
                        Log.v("TEXT FOR VIEWS", textInfo.getText().toString());
                        Toast.makeText(view.getContext(), "You clicked the note: " + notes.get(position), Toast.LENGTH_LONG).show();

                    }

                }
        );
        return view;
    }

    private void deleteNote(View view){

    }

    private void generateNotes() {

        for (int i = 0; i < userNote.size(); i++) {
            notes.add(new NoteModel(userName, userNote.get(i)));
        }
    }

}
