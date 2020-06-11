package com.example.madspild.recyclerview.shoppingCart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madspild.R;
import com.example.madspild.dataStorage.shoppingCartDB.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {

  List<Product> products = new ArrayList<>();

  @NonNull
  @Override
  public ShoppingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.indkobskurv_product,parent,false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ShoppingAdapter.ViewHolder holder, int position) {
    Product currentProduct = products.get(position);
    holder.product.setText(products.get(position).getProduct());
  }

  @Override
  public int getItemCount() {
    return products.size();
  }

  public void setProducts(List<Product> products) {
    this.products = products;
    notifyDataSetChanged();
  }

  public Product getProduct(int position) {
    return products.get(position);
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    TextView product;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      product = itemView.findViewById(R.id.listProduct);
    }
  }
}
