package com.example.madspild.dataStorage.fridgeDB;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class GroceryViewModel extends AndroidViewModel {

  private GroceryRepository repository;

  public GroceryViewModel(Application application) {
    super(application);
    repository = GroceryRepository.getInstance(application);
  }

  public LiveData<List<Grocery>> getAllGroceries() {
    return repository.getAllGroceries();
  }

  public void insert(Grocery grocery) {
    repository.insert(grocery);
  }

  public void delete(Grocery grocery) {
    repository.delete(grocery);
  }

  public void deleteAllGroceries() {
    repository.deleteAllGroceries();
  }
}
