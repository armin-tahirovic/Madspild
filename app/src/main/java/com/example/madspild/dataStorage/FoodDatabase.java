package com.example.madspild.dataStorage;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.madspild.dataStorage.fridgeDB.Grocery;
import com.example.madspild.dataStorage.fridgeDB.GroceryDAO;
import com.example.madspild.dataStorage.shoppingCartDB.ProductDAO;
import com.example.madspild.dataStorage.shoppingCartDB.Product;

@Database(entities = {Product.class, Grocery.class}, version = 1)
public abstract class FoodDatabase extends RoomDatabase {
  private static FoodDatabase instance;
  public abstract ProductDAO productDAO();
  public abstract GroceryDAO groceryDAO();

  public static synchronized FoodDatabase getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context.getApplicationContext(),
        FoodDatabase.class, "food_database")
        .fallbackToDestructiveMigration()
        .build();
    }
    return instance;
  }
}
