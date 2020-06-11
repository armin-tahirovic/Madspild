package com.example.madspild.network;

public class FoodResponse {

  private Products products;

  public Food getFood() {
    return new Food(products.id, products.title, products.image);
  }

  private class Products {
    private int id;
    private String title;
    private String image;
  }
}
