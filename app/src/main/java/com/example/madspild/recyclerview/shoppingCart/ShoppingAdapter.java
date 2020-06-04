package com.example.madspild.recyclerview.shoppingCart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madspild.R;

import java.util.List;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {

  List<Product> products;

  public ShoppingAdapter(List<Product> products) {
    this.products = products;
  }

  @NonNull
  @Override
  public ShoppingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.indkobskurv_product,parent,false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ShoppingAdapter.ViewHolder holder, int position) {
    holder.product.setText(products.get(position).getProductName());
  }

  @Override
  public int getItemCount() {
    return products.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    TextView product;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      product = itemView.findViewById(R.id.listProduct);
    }
  }
}
