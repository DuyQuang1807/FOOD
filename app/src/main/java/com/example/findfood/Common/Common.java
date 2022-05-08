package com.example.findfood.Common;

import com.example.findfood.model.User;
import com.squareup.okhttp.Request;

public class Common {
    public static User currentUser;

    public  static  final String UPDATE = "Update";
    public  static  final String Delete = "Delete";

    public static final int PICK_IMAGE_REQUEST = 71;

    public static String convertCodeToStatus (String code) {
        if (code.equals("0"))
            return "Địa điểm";
        else if (code.equals("1"))
            return "Đang đến";
        else
            return "Đang giao";
    }


}
