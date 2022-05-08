package com.example.findfood.CallBack;

import com.example.findfood.model.Categories;

import java.util.ArrayList;

public interface CategoriesCallBack {
    void onSuccess(ArrayList<Categories> lists);
    void onError(String message);
}
