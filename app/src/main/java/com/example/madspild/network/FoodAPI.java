package com.example.madspild.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FoodAPI {

  @GET("food/products/search?query={title}")
  Call<FoodResponse> getFood(@Path("title") String title);
}
