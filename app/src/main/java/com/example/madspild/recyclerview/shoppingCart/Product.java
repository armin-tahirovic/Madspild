package com.example.madspild.recyclerview.shoppingCart;

public class Product {

  private String productName;

  public Product(String productName) {
    this.productName = productName;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }
}
