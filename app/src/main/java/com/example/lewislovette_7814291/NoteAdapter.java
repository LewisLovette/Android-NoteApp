package com.example.lewislovette_7814291;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
            //TextView textViewNote = (TextView) v.findViewById(R.id.textViewNote);

            textViewNote.setText(notes.get(position).getNote());
            //textViewNote.setText(notes.get(position).getNote());

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return v;
    }

}
