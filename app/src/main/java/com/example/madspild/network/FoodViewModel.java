package com.example.madspild.network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class FoodViewModel extends ViewModel {

  FoodRepository repository;

  public FoodViewModel() {
    repository = FoodRepository.getInstance();
  }

  public LiveData<Food> getFood() {
    return repository.getFood();
  }

  public void updateFood(String s) {
    repository.updateFood(s);
  }
}
