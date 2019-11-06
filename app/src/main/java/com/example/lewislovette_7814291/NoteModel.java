package com.example.lewislovette_7814291;

import android.arch.lifecycle.MutableLiveData;
import android.view.View;

import java.util.ArrayList;

public class NoteModel {
    //Deals with notes
    DatabaseHandler db;
    String email;
    ArrayList<String> noteList = new ArrayList<>();

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

        //Todo: then should pull all notes connected to this email into noteList
    }

    public ArrayList<String> getNotes() {

        return noteList;
    }

    public void addNote(String note) {
        noteList.add(note);

        //Todo: should only add to db and then the get note should get all notes
        //db.addNote(name, note); not needed for this branch
    }

    public void deleteNote(int noteToDelete) {
        noteList.remove(noteToDelete);

        //Todo: then delete # from database
    }

}
