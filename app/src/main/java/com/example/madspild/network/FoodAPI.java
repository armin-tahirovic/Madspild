package com.example.madspild.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FoodAPI {

  @GET("search")
  Call<FoodResponse> getFood(
    @Query("query") String title,
    @Query("number") String number,
    @Query("apiKey") String key);
}
