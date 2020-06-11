package com.example.madspild.dataStorage.shoppingCartDB;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

  private ProductRepository repository;

  public ProductViewModel(Application application) {
    super(application);
    repository = ProductRepository.getInstance(application);
  }

  public LiveData<List<Product>> getAllProducts() {
    return repository.getAllProducts();
  }

  public void insert(Product product) {
    repository.insert(product);
  }

  public void delete(Product product) {
    repository.delete(product);
  }

  public void deleteAllProducts() {
    repository.deleteAllProducts();
  }
}
