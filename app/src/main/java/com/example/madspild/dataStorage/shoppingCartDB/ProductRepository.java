package com.example.madspild.dataStorage.shoppingCartDB;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.madspild.dataStorage.FoodDatabase;

import java.util.List;

public class ProductRepository {

  private ProductDAO productDAO;
  private static ProductRepository instance;
  private LiveData<List<Product>> allProducts;

  private ProductRepository(Application application) {
    FoodDatabase database = FoodDatabase.getInstance(application);
    productDAO = database.productDAO();
    allProducts = productDAO.getAllProducts();
  }

  public static synchronized ProductRepository getInstance(Application application) {
    if (instance == null)
      instance = new ProductRepository(application);
    return instance;
  }

  public LiveData<List<Product>> getAllProducts() {
    return allProducts;
  }

  public void insert(Product product) {
    new InsertProductsAsync(productDAO).execute(product);
  }

  public void delete(Product product) {
    new DeleteAsync(productDAO).execute(product);
  }

  public void deleteAllProducts() {
    new DeleteAllProductsAsync(productDAO).execute();
  }

  private static class InsertProductsAsync extends AsyncTask<Product, Void, Void> {
    private ProductDAO productDAO;

    private InsertProductsAsync(ProductDAO productDAO) {
      this.productDAO = productDAO;
    }

    @Override
    protected Void doInBackground(Product... products) {
      productDAO.insert(products[0]);
      return null;
    }
  }


  private static class DeleteAllProductsAsync extends AsyncTask<Void, Void, Void> {
    private ProductDAO productDAO;

    private DeleteAllProductsAsync(ProductDAO productDAO) {
      this.productDAO = productDAO;
    }

    @Override
    protected Void doInBackground(Void... voids) {
      productDAO.deleteAllProducts();
      return null;
    }
  }

  private static class DeleteAsync extends AsyncTask<Product, Void, Void> {
    private ProductDAO productDAO;

    private DeleteAsync(ProductDAO productDAO) {
      this.productDAO = productDAO;
    }

    @Override
    protected Void doInBackground(Product... products) {
      productDAO.delete(products[0]);
      return null;
    }
  }
}
