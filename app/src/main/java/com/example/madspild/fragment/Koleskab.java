package com.example.madspild.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.madspild.DatePickerFragment;
import com.example.madspild.R;
import com.example.madspild.dataStorage.fridgeDB.Grocery;
import com.example.madspild.dataStorage.fridgeDB.GroceryViewModel;
import com.example.madspild.network.Food;
import com.example.madspild.network.FoodViewModel;
import com.example.madspild.recyclerview.koleskab.KoleskabAdapter;

import java.util.List;

public class Koleskab extends Fragment {

  EditText groceryName;
  EditText groceryDate;
  ImageView imageView;
  RecyclerView fridge;
  GroceryViewModel groceryViewModel;
  FoodViewModel foodViewModel;

  public Koleskab() {
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_koleskab,container,false);
    Button addGroceries = (Button) view.findViewById(R.id.tilføjVare);
    Button deleteGroceries = (Button) view.findViewById(R.id.sletVare);

    fridge = view.findViewById(R.id.køleskabsVare);
    fridge.setLayoutManager(new LinearLayoutManager(view.getContext()));
    fridge.hasFixedSize();
    final KoleskabAdapter groceriesAdapter = new KoleskabAdapter();
    fridge.setAdapter(groceriesAdapter);

    groceryName = view.findViewById(R.id.navnVare);
    groceryDate = view.findViewById(R.id.datePicker);
    imageView = view.findViewById(R.id.imageAPI);

    // API
    foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);
    foodViewModel.getFood().observe(getViewLifecycleOwner(), new Observer<Food>() {
      @Override
      public void onChanged(Food food) {
        Glide.with(Koleskab.this).load(food.getImage()).into(imageView);
        System.out.println("<<< Hvad får jeg ud: " + food.getImage() + " >>>");
      }
    });

    // Data storage
    groceryViewModel = new ViewModelProvider(this).get(GroceryViewModel.class);
    groceryViewModel.getAllGroceries().observe(getViewLifecycleOwner(), new Observer<List<Grocery>>() {
      @Override
      public void onChanged(List<Grocery> groceries) {
        groceriesAdapter.setGroceries(groceries);
      }
    });

    new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
      @Override
      public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
      }

      @Override
      public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        groceryViewModel.delete(groceriesAdapter.getGrocery(viewHolder.getAdapterPosition()));
      }
    }).attachToRecyclerView(fridge);

    addGroceries.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        groceryViewModel.insert(new Grocery(groceryName.getText().toString(), groceryDate.getText().toString()));
        foodViewModel.updateFood(groceryName.getText().toString(),"1","7ec3abdad26c4257906ff07da86f9c19");
      }
    });

    deleteGroceries.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        groceryViewModel.deleteAllGroceries();
        groceriesAdapter.notifyDataSetChanged();
      }
    });

    final FragmentManager fm = ((AppCompatActivity)getActivity()).getSupportFragmentManager();

    groceryDate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AppCompatDialogFragment datePicker = new DatePickerFragment();
        datePicker.setTargetFragment(Koleskab.this, 2);
        datePicker.show(fm, "date picker");
      }
    });

    return view;
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
      String date = data.getStringExtra("date");
      groceryDate.setText(date);
    }
  }
}
