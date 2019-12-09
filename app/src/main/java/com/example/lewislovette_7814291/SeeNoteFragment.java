package com.example.lewislovette_7814291;


import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
    private String userName;    //as only one user is accessing notes
    private ArrayList<String> userNotes;
    public static int toDeletePosition;

    private NoteModel noteModel;

    /**
     * Constructor for See Note Fragment
     */
    public SeeNoteFragment() {
        //constructor
    }

    /**
     * Removes the top bar from view
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    /**
     * Sets up users note with the note adapter and handles deleting notes.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_see_note, container, false);
        nameTitle = view.findViewById(R.id.whosNote);

        noteModel = NoteModel.getInstance();
        noteModel.setView(view);

        userName = noteModel.getEmail();

        userNotes = noteModel.getNotes();


        nameTitle.setText(userName + "'s Notes");

        /* Tests
        Log.v("COMPLEX LIST VIEW: ", "index 0 = " + notes.get(0).getNote());
        Log.v("COMPLEX LIST VIEW: ", "index 1 = " + notes.get(1).getNote());
        Log.v("COMPLEX LIST VIEW: ", "index 2 = " + notes.get(2).getNote());
        */

        listView = (ListView) view.findViewById(R.id.listViewComplex);

        //Todo: instead of 'notes' send in arraylist of all notes
        listView.setAdapter(new NoteAdapter(view.getContext(), R.layout.list_item, userNotes));

        listView.setOnItemClickListener(

                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        toDeletePosition = position;
                        Toast.makeText(view.getContext(), "You clicked the note: " + userNotes.get(position), Toast.LENGTH_LONG).show();

                        new AlertDialog.Builder(view.getContext())
                                .setTitle("Delete entry")
                                .setMessage("Are you sure you want to delete this entry?")

                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                // The dialog is automatically dismissed when a dialog button is clicked.
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Continue with delete operation
                                        Log.v("WHAT IS WHICH: ", Integer.toString(which));
                                        Log.v("WHAT IS THE POSITION TO DELETE: ", Integer.toString(toDeletePosition));
                                        noteModel.deleteNote(userNotes.get(toDeletePosition));
                                        userNotes.remove(toDeletePosition);
                                        listView.invalidateViews();
                                    }
                                })

                                // A null listener allows the button to dismiss the dialog and take no further action.
                                .setNegativeButton(android.R.string.no, null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }

                }
        );
        return view;
    }


}
