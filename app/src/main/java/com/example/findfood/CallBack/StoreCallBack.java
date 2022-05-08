package com.example.findfood.CallBack;

import com.example.findfood.model.Store;

import java.util.ArrayList;

public interface StoreCallBack {
    void onSuccess(ArrayList<Store> lists);
    void onError(String message);
}
