package com.example.madspild.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

  private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
    .baseUrl("https://api.spoonacular.com")
    .addConverterFactory(GsonConverterFactory.create());

  private static Retrofit retrofit = retrofitBuilder.build();
  private static FoodAPI foodAPI = retrofit.create(FoodAPI.class);

  public static FoodAPI getFoodAPI() {
    return foodAPI;
  }
}
