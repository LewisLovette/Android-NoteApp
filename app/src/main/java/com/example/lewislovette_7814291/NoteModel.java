package com.example.lewislovette_7814291;

import android.arch.lifecycle.MutableLiveData;
import android.view.View;

import java.util.ArrayList;

public class NoteModel {
    //Deals with notes
    String email;
    ArrayList<String> noteList = new ArrayList<>();

    private View view;
    DatabaseHandler db;

    private static final NoteModel oneInstance = new NoteModel();

    /**
     * This is used to get a single instance of the class - implements the Singleton Pattern.
     * @return single instance of the class
     */
    public static NoteModel getInstance() {
        return oneInstance;
    }

    /**
     * Class constructor, only used by Singleton when first instantiating class.
     */
    private NoteModel() {

    }

    /**
     * Sets the private view variable within this class as well as instatiating the DatabaseHandler.
     * @param view - only param for setView.
     */
    public void setView(View view){
        this.view = view;
        db = new DatabaseHandler(view.getContext());
    }

    /**
     * Gets the email stored in the instance of this class.
     * @return String email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email within the instance of this class to the current user.
     * @param email - the email of the user that is currently signed in.
     */
    public void setEmail(String email) {
        this.email = email;

        //Todo: then should pull all notes connected to this email into noteList
    }

    /**
     * Gets all of the notes corresponding to the current user.
     * @return noteList - an array of notes.
     */
    public ArrayList<String> getNotes() {

        return noteList;
    }

    /**
     * Adds a newly created note to the database.
     * @param note - new note created by the user
     */
    public void addNote(String note) {
        noteList.add(note);

        //Todo: should only add to db and then the get note should get all notes
        //db.addNote(name, note); not needed for this branch
    }

    /**
     * Deletes a selected note
     * @param noteToDelete - position of the note to be deleted.
     */
    public void deleteNote(int noteToDelete) {
        noteList.remove(noteToDelete);

        //Todo: then delete # from database
    }

}
