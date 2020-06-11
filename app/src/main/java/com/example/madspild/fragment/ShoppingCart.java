package com.example.madspild.fragment;


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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madspild.R;
import com.example.madspild.dataStorage.shoppingCartDB.Product;
import com.example.madspild.dataStorage.shoppingCartDB.ProductViewModel;
import com.example.madspild.recyclerview.shoppingCart.ShoppingAdapter;

import java.util.List;

public class ShoppingCart extends Fragment {

  EditText addText;
  RecyclerView shoppingCart;
  List<Product> productSC;
  ProductViewModel productViewModel;

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
    final ShoppingAdapter shoppingAdapter = new ShoppingAdapter();
    shoppingCart.setAdapter(shoppingAdapter);

    addText = view.findViewById(R.id.edProduct);
    productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
    productViewModel.getAllProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
      @Override
      public void onChanged(@Nullable List<Product> products) {
            shoppingAdapter.setProducts(products);
      }
    });

    new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
      @Override
      public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
      }

      @Override
      public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        productViewModel.delete(shoppingAdapter.getProduct(viewHolder.getAdapterPosition()));
      }
    }).attachToRecyclerView(shoppingCart);

    addProduct.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        productViewModel.insert(new Product(addText.getText().toString()));
      }
    });

    removeProduct.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        productViewModel.deleteAllProducts();
        shoppingAdapter.notifyDataSetChanged();
      }
    });

    return view;
  }
}
