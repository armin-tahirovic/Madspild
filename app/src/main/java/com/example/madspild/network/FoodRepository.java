package com.example.madspild.network;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodRepository {

  private static FoodRepository instance;
  private MutableLiveData<Food> food;

  private FoodRepository() {
    food = new MutableLiveData<>();
  }

  public static synchronized FoodRepository getInstance() {
    if (instance == null) {
      instance = new FoodRepository();
    }
    return instance;
  }

  public LiveData<Food> getFood() {
    return food;
  }

  public void updateFood(String title) {
    FoodAPI foodAPI = ServiceGenerator.getFoodAPI();
    Call<FoodResponse> call = foodAPI.getFood(title);
    call.enqueue(new Callback<FoodResponse>() {
      @Override
      public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
        if (response.code() == 200) {
          food.setValue(response.body().getFood());
        }
      }

      @Override
      public void onFailure(Call<FoodResponse> call, Throwable t) {
        Log.i("Retrofit","Something went wrong");
      }
    });
  }
}
