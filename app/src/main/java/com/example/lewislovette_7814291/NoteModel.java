package com.example.lewislovette_7814291;

import android.view.View;

public class NoteModel {
    //Deals with notes
    private View view;
    DatabaseHandler db;

    String name = "Lewis";
    String note;
    public NoteModel(String name, String note) {
        this.name = name;
        this.note = note;
    }

    /*
    public NoteModel(View view) {
        this.view = view;
        db = new DatabaseHandler(view.getContext());
    }
    */

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
        //db.addNote(this); not needed for this branch
    }
}
