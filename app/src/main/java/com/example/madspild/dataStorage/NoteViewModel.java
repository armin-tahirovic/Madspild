package com.example.madspild.dataStorage;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

  private NoteRepository repository;

  public NoteViewModel(Application application) {
    super(application);
    repository = NoteRepository.getInstance(application);
  }

  public LiveData<List<Note>> getAllNotes() {
    return repository.getAllNotes();
  }

  public void insert(final Note note) {
    repository.insert(note);
  }

  public void deleteAllNotes() {
    repository.deleteAllNotes();
  }
}
