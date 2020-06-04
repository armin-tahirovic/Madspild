package com.example.madspild.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.madspild.DatePickerFragment;
import com.example.madspild.R;
import com.example.madspild.dataStorage.Note;
import com.example.madspild.dataStorage.NoteViewModel;
import com.example.madspild.network.Food;
import com.example.madspild.network.FoodViewModel;
import com.example.madspild.recyclerview.koleskab.Groceries;
import com.example.madspild.recyclerview.koleskab.KoleskabAdapter;

import java.util.ArrayList;
import java.util.List;

public class Koleskab extends Fragment {

  EditText groceryName;
  EditText groceryDate;
  ImageView imageView;
  RecyclerView fridge;
  KoleskabAdapter groceriesAdapter;
  List<Groceries> groceries = new ArrayList<>();
  NoteViewModel noteViewModel;
  FoodViewModel foodViewModel;

  public Koleskab() {
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_koleskab,container,false);
    Button addGroceries = (Button) view.findViewById(R.id.tilføjVare);
    Button deleteGroceries = (Button) view.findViewById(R.id.sletVare);
    imageView = view.findViewById(R.id.imageAPI);
    fridge = view.findViewById(R.id.køleskabsVare);
    fridge.setLayoutManager(new LinearLayoutManager(view.getContext()));
    fridge.hasFixedSize();
    groceryName = view.findViewById(R.id.navnVare);
    groceryDate = view.findViewById(R.id.datePicker);
    groceriesAdapter = new KoleskabAdapter(groceries);
    fridge.setAdapter(groceriesAdapter);

    // Data storage <<<VIRKER IKKE>>>
    noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);


    foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);
    foodViewModel.getFood().observe(getViewLifecycleOwner(), new Observer<Food>() {
      @Override
      public void onChanged(Food food) {
        Glide.with(Koleskab.this).load(food.getImage()).into(imageView);
      }
    });


    addGroceries.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        groceries.add(new Groceries(groceryName.getText().toString(), groceryDate.getText().toString()));
        noteViewModel.insert(new Note(groceryName.getText().toString(), groceryDate.getText().toString()));
        groceriesAdapter.notifyDataSetChanged();
      }
    });

    deleteGroceries.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        groceries.removeAll(groceries);
        noteViewModel.deleteAllNotes();
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
