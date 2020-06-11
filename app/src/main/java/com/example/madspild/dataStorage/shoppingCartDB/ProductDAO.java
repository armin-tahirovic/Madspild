package com.example.madspild.dataStorage.shoppingCartDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDAO {

  @Insert
  void insert(Product product);

  @Delete
  void delete(Product product);

  @Query("DELETE FROM product_table")
  void deleteAllProducts();

  @Query("SELECT * FROM product_table")
  LiveData<List<Product>> getAllProducts();
}
