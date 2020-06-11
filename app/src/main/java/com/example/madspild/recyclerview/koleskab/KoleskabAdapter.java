package com.example.madspild.recyclerview.koleskab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madspild.R;
import com.example.madspild.dataStorage.fridgeDB.Grocery;

import java.util.ArrayList;
import java.util.List;

public class KoleskabAdapter extends RecyclerView.Adapter<KoleskabAdapter.ViewHolder> {

  List<Grocery> groceries = new ArrayList<>();

  @NonNull
  @Override
  public KoleskabAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.koleskab_vare,parent,false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull KoleskabAdapter.ViewHolder holder, int position) {
    holder.groceryName.setText(groceries.get(position).getGrocery());
    holder.groceryDate.setText(groceries.get(position).getDate());
  }

  @Override
  public int getItemCount() {
    return groceries.size();
  }

  public void setGroceries(List<Grocery> groceries) {
    this.groceries = groceries;
    notifyDataSetChanged();
  }

  public Grocery getGrocery(int position) {
    return groceries.get(position);
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    TextView groceryName;
    TextView groceryDate;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      groceryName = itemView.findViewById(R.id.groceryName);
      groceryDate = itemView.findViewById(R.id.groceryDate);
    }
  }
}
