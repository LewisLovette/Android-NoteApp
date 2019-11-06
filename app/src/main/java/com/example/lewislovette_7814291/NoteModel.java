package com.example.lewislovette_7814291;

import android.view.View;

import java.util.ArrayList;

public class NoteModel {
    //Deals with notes
    DatabaseHandler db;

    String email;
    ArrayList<String> note;

    private static final NoteModel oneInstance = new NoteModel();

    public static NoteModel getInstance() {
        return oneInstance;
    }

    private NoteModel() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

        //should then set all notes with 'getNote'
    }

    public ArrayList<String> getNotes() {
        return note;
    }

    public void addNote(String note) {
        this.note.add(note);
        //db.addNote(name, note); not needed for this branch
    }

    public void deleteNote(int noteToDelete) {
        this.note.remove(noteToDelete);

        //should then delete # from database
    }
}
