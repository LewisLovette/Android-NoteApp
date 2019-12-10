package com.example.lewislovette_7814291;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

import org.junit.Test;

public class NoteModelTest {

  @Test
  public void getInstance() {
    NoteModel noteModel = NoteModel.getInstance();

    assertEquals(noteModel.getEmail(), null);
  }

  @Test
  public void setView() {}

  @Test
  public void getEmail() {
    NoteModel noteModel = NoteModel.getInstance();
    noteModel.setEmail("testemail@email.com");

    assertEquals(noteModel.getEmail(), "testemail@email.com");
  }

  @Test
  public void setEmail() {
    NoteModel noteModel = NoteModel.getInstance();
    noteModel.setEmail("testemail@email.com");

    assertEquals(noteModel.getEmail(), "testemail@email.com");
  }

  @Test
  public void getNotes() {}

  @Test
  public void addNote() {}

  @Test
  public void deleteNote() {}
}