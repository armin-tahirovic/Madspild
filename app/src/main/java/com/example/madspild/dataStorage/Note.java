package com.example.madspild.dataStorage;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "grocery_table")
public class Note {
  @PrimaryKey(autoGenerate = true)
  private int id;
  private String grocery;
  private String date;

  public Note(String grocery, String date) {
    this.grocery = grocery;
    this.date = date;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getGrocery() {
    return grocery;
  }

  public void setGrocery(String grocery) {
    this.grocery = grocery;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }
}
