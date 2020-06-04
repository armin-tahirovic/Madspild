package com.example.madspild.network;

public class FoodResponse {

  private int id;
  private String title;
  private String image;

  public Food getFood() {
    return new Food(id, title, image);
  }
}
