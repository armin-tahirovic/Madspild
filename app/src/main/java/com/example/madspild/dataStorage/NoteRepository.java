package com.example.madspild.dataStorage;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

  private NoteDAO noteDAO;
  private static NoteRepository instance;
  private LiveData<List<Note>> allNotes;

  private NoteRepository(Application application) {
    NoteDatabase database = NoteDatabase.getInstance(application);
    noteDAO = database.noteDAO();
    allNotes = noteDAO.getAllNotes();
  }

  public static synchronized NoteRepository getInstance(Application application) {
    if (instance == null)
      instance = new NoteRepository(application);
    return instance;
  }

  public LiveData<List<Note>> getAllNotes() {
    return allNotes;
  }

  public void insert(Note note) {
    new InsertNoteAsync(noteDAO).execute(note);
  }

  public void deleteAllNotes() {
    new DeleteAllNotesAsync(noteDAO).execute();
  }

  private static class InsertNoteAsync extends AsyncTask<Note, Void, Void> {
    private NoteDAO noteDAO;

    private InsertNoteAsync(NoteDAO noteDAO) {
      this.noteDAO = noteDAO;
    }

    @Override
    protected Void doInBackground(Note... notes) {
      noteDAO.insert(notes[0]);
      return null;
    }
  }


  private static class DeleteAllNotesAsync extends AsyncTask<Void, Void, Void> {
    private NoteDAO noteDAO;

    private DeleteAllNotesAsync(NoteDAO noteDAO) {
      this.noteDAO = noteDAO;
    }

    @Override
    protected Void doInBackground(Void... voids) {
      noteDAO.deleteAllGroceries();
      return null;
    }
  }
}
