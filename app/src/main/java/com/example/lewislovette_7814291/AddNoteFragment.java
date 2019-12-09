package com.example.lewislovette_7814291;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddNoteFragment extends Fragment {
    private final int REQ_CODE = 100;

    private NoteModel noteModel;

    //Setting up for views in the fragment
    private View view;
    private Button saveNoteButton;
    private Button speak;
    private TextView noteToSave;

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
        speak = (Button) view.findViewById(R.id.speak);
        noteToSave = (TextView) view.findViewById(R.id.addNoteText);


        //Binding events and sending data to model layer
        saveNoteButton.setOnClickListener(new View.OnClickListener() {  //Sending the text to save to NoteModel when 'save' is clicked.
            public void onClick(View v) {
                Toast.makeText(getContext(), "Note Saved", Toast.LENGTH_LONG).show();
                noteModel.addNote(noteToSave.getText().toString());

            }
        });


        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak");
                try {
                    startActivityForResult(intent, REQ_CODE);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getContext(),
                            "Sorry your device not supported",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    noteToSave.setText(result.get(0).toString());
                }
                break;
            }
        }
    }



}
