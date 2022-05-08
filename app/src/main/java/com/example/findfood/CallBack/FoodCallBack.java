package com.example.findfood.CallBack;

import com.example.findfood.model.Food;

import java.util.ArrayList;

public interface FoodCallBack {
    void onSuccess(ArrayList<Food> lists);
    void onError(String message);
}
