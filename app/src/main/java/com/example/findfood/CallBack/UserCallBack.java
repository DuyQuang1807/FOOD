package com.example.findfood.CallBack;

import com.example.findfood.model.User;

import java.util.ArrayList;

public interface UserCallBack {
    void onSuccess(ArrayList<User> lists);
    void onError(String message);
}
