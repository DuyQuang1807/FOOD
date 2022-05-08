package com.example.findfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.findfood.CallBack.FoodCallBack;
import com.example.findfood.Databases.DatabaseFood;
import com.example.findfood.HelperClasses.FoodAdapter;
import com.example.findfood.model.Food;

import java.util.ArrayList;

public class FoodProductActivity extends AppCompatActivity {
    ArrayList<Food> foodArrayList;
    DatabaseFood databaseFood;
    FoodAdapter foodAdapter;
    RecyclerView rcvfood;
    ImageView backDSMA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_product);

        rcvfood = findViewById(R.id.rcvfood);
        backDSMA = findViewById(R.id.backDSMA);
        Intent getdata = getIntent();
        String matl = getdata.getStringExtra("matl");
        databaseFood = new DatabaseFood(FoodProductActivity.this);
        foodArrayList = new ArrayList<>();
        foodAdapter = new FoodAdapter(foodArrayList,FoodProductActivity.this);
        GridLayoutManager idLayoutManager = new GridLayoutManager(FoodProductActivity.this,2);
        rcvfood.setLayoutManager(idLayoutManager);
        rcvfood.setHasFixedSize(true);
        rcvfood.setAdapter(foodAdapter);

        databaseFood.getAll(new FoodCallBack() {
            @Override
            public void onSuccess(ArrayList<Food> lists) {
                foodArrayList.clear();
                for (int i =0;i<lists.size();i++){
                    if (lists.get(i).getMatheloai().equalsIgnoreCase(matl)){
                        foodArrayList.add(lists.get(i));
                        foodAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onError(String message) {

            }
        });

        backDSMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }
}