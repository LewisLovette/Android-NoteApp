package com.example.lewislovette_7814291;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<NoteModel> {

    private int resource;
    private ArrayList<NoteModel> notes;
    private Context context;

    public NoteAdapter(Context context, int resource, ArrayList<NoteModel> notes) {
        super(context, resource, notes);
        this.resource = resource;
        this.notes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        try {
            if (v == null) {
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = layoutInflater.inflate(resource, parent, false);
            }

            TextView textViewNote = (TextView) v.findViewById(R.id.textViewNote);

            //Todo: get # of notes and display them
            //textViewNote.setText(notes.get(position).getNote());

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


        /*
        back = v.findViewById(R.id.listLayout);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "I@M A", Toast.LENGTH_SHORT).show();
            }
        });
        //binding button so can delete specified notes
        deleteButton = (ImageButton) v.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hello code here
            }
        });
        */


        return v;
    }

}
