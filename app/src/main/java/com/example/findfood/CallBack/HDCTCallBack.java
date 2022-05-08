package com.example.findfood.CallBack;

import com.example.findfood.model.HDCT;

import java.util.ArrayList;

public interface HDCTCallBack {
    void onSuccess(ArrayList<HDCT> lists);
    void onError(String message);
}
