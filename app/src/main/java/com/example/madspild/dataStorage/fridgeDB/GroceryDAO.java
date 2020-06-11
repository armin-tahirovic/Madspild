package com.example.madspild.dataStorage.fridgeDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GroceryDAO {

  @Insert
  void insert(Grocery grocery);

  @Delete
  void delete(Grocery grocery);

  @Query("DELETE FROM grocery_table")
  void deleteAllGroceries();

  @Query("SELECT * FROM grocery_table")
  LiveData<List<Grocery>> getAllGroceries();
}
