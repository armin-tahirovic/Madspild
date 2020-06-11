package com.example.madspild.dataStorage.fridgeDB;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.madspild.dataStorage.FoodDatabase;

import java.util.List;

public class GroceryRepository {

  private GroceryDAO groceryDAO;
  private static GroceryRepository instance;
  private LiveData<List<Grocery>> allGroceries;

  private GroceryRepository(Application application) {
    FoodDatabase database = FoodDatabase.getInstance(application);
    groceryDAO = database.groceryDAO();
    allGroceries = groceryDAO.getAllGroceries();
  }

  public static synchronized GroceryRepository getInstance(Application application) {
    if (instance == null) {
      instance = new GroceryRepository(application);
    }
    return instance;
  }

  public LiveData<List<Grocery>> getAllGroceries() {
    return allGroceries;
  }

  public void insert(Grocery grocery) {
    new InsertGroceriesAsync(groceryDAO).execute(grocery);
  }

  public void delete(Grocery grocery) {
    new DeleteGroceryAsync(groceryDAO).execute(grocery);
  }

  public void deleteAllGroceries() {
    new DeleteAllGroceriesAsync(groceryDAO).execute();
  }

  private static class InsertGroceriesAsync extends AsyncTask<Grocery, Void, Void> {
    private GroceryDAO groceryDAO;

    private InsertGroceriesAsync(GroceryDAO groceryDAO) {
      this.groceryDAO = groceryDAO;
    }

    @Override
    protected Void doInBackground(Grocery... groceries) {
      groceryDAO.insert(groceries[0]);
      return null;
    }
  }

  private static class DeleteGroceryAsync extends AsyncTask<Grocery, Void, Void> {
    private GroceryDAO groceryDAO;

    private DeleteGroceryAsync(GroceryDAO groceryDAO) {
      this.groceryDAO = groceryDAO;
    }

    @Override
    protected Void doInBackground(Grocery... groceries) {
      groceryDAO.delete(groceries[0]);
      return null;
    }
  }

  private static class DeleteAllGroceriesAsync extends AsyncTask<Void, Void, Void> {
    private GroceryDAO groceryDAO;

    private DeleteAllGroceriesAsync(GroceryDAO groceryDAO) {
      this.groceryDAO = groceryDAO;
    }

    @Override
    protected Void doInBackground(Void... voids) {
      groceryDAO.deleteAllGroceries();
      return null;
    }
  }
}
