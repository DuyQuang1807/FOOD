package com.example.findfood.CallBack;

import com.example.findfood.model.ChatUserStore;

import java.util.ArrayList;

public interface ChatUserStoreCallBack {
    void onSuccess(ArrayList<ChatUserStore> lists);
    void onError(String message);
}
