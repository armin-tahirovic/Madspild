package com.example.madspild.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madspild.R;
import com.example.madspild.dataStorage.Note;
import com.example.madspild.dataStorage.NoteViewModel;
import com.example.madspild.recyclerview.shoppingCart.Product;
import com.example.madspild.recyclerview.shoppingCart.ShoppingAdapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCart extends Fragment {

  EditText addText;
  RecyclerView shoppingCart;
  ShoppingAdapter shoppingAdapter;
  List<Product> products = new ArrayList<>();
  private NoteViewModel noteViewModel;

  public ShoppingCart() {
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_indkobskurv,container,false);
    Button addProduct = (Button) view.findViewById(R.id.addProduct);
    Button removeProduct = (Button) view.findViewById(R.id.deleteProduct);
    shoppingCart = view.findViewById(R.id.rv);
    shoppingCart.setLayoutManager(new LinearLayoutManager(view.getContext()));
    shoppingCart.hasFixedSize();
    addText = view.findViewById(R.id.edProduct);
    shoppingAdapter = new ShoppingAdapter(products);
    shoppingCart.setAdapter(shoppingAdapter);
    noteViewModel = ViewModelProviders.of(requireActivity()).get(NoteViewModel.class);

    addProduct.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        products.add(new Product(addText.getText().toString()));
        noteViewModel.insert(new Note(addText.getText().toString(), "Today"));
        shoppingAdapter.notifyDataSetChanged();
      }
    });

    removeProduct.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        products.removeAll(products);
        noteViewModel.deleteAllNotes();
        shoppingAdapter.notifyDataSetChanged();
      }
    });

    return view;
  }
}
