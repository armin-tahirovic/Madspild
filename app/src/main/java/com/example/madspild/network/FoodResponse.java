package com.example.madspild.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodResponse {

  @SerializedName("products")
  private List<Food> foodList;

  public List<Food> getFood() {
    return foodList;
  }
}
