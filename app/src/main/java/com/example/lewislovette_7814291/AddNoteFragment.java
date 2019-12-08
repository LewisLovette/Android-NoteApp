package com.example.lewislovette_7814291;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddNoteFragment extends Fragment {

    NoteModel noteModel;

    //Setting up for views in the fragment
    private View view;
    Button saveNoteButton;
    TextView noteToSave;

    public AddNoteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_add_note, container, false);

        noteModel = NoteModel.getInstance();
        noteModel.setView(view);

        //Setting buttons to view.
        saveNoteButton = (Button) view.findViewById(R.id.saveButton);
        noteToSave = (TextView) view.findViewById(R.id.addNoteText);


        //Binding events and sending data to model layer
        saveNoteButton.setOnClickListener(new View.OnClickListener() {  //Sending the text to save to NoteModel when 'save' is clicked.
            public void onClick(View v) {
                Toast.makeText(getContext(), "Note Saved", Toast.LENGTH_LONG).show();
                noteModel.addNote(noteToSave.getText().toString());

            }
        });

        return view;

    }




}
