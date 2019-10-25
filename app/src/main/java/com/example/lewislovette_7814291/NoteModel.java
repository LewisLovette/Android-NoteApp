package com.example.lewislovette_7814291;

import android.view.View;

public class NoteModel {
    //Deals with notes
    private View view;

    String name;
    String note;

    public NoteModel(View view) {
        this.view = view;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
        DatabaseHandler db = new DatabaseHandler(view.getContext());
        db.addNote(this);
    }
}
