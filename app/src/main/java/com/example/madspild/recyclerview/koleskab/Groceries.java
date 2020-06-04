package com.example.madspild.recyclerview.koleskab;

public class Groceries {

  private String groceryName;
  private String groceryDate;

  public Groceries(String groceryName, String groceryDate) {
    this.groceryName = groceryName;
    this.groceryDate = groceryDate;
  }

  public String getGroceryName() {
    return groceryName;
  }

  public void setGroceryName(String groceryName) {
    this.groceryName = groceryName;
  }

  public String getGroceryDate() {
    return groceryDate;
  }

  public void setGroceryDate(String groceryDate) {
    this.groceryDate = groceryDate;
  }
}
