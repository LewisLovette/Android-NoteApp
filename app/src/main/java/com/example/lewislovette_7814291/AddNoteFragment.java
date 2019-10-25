package com.example.lewislovette_7814291;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddNoteFragment extends Fragment {
    private View view;
    Button saveNoteButton;


    public AddNoteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_add_note, container, false);
        saveNoteButton = (Button) view.findViewById(R.id.saveButton);

        //Binding events
        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getContext(), "Note Saved", Toast.LENGTH_LONG).show();
            }
        });

        return view;

    }




}
