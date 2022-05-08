package com.example.findfood.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findfood.CallBack.UserCallBack;
import com.example.findfood.Databases.DatabaseUser;
import com.example.findfood.MainActivity;
import com.example.findfood.R;
import com.example.findfood.model.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Objects;

public class CheckRoleActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseUser databaseUser;
    com.airbnb.lottie.LottieAnimationView warning;
    TextView banned;
    ImageView logo;
    String trangThai;
    String check = "active";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_role);
        warning = findViewById(R.id.animation_view);
        banned = findViewById(R.id.tv_banned);
        logo = findViewById(R.id.image_owl);
        mAuth = FirebaseAuth.getInstance();

        String idUser = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        databaseUser = new DatabaseUser(getApplicationContext());
        databaseUser.getAll(new UserCallBack() {
            @Override
            public void onSuccess(ArrayList<User> lists) {
                for (int i = 0; i < lists.size(); i++) {
                    if (lists.get(i).getToken() != null && lists.get(i).getToken().equalsIgnoreCase(idUser)) {
                        trangThai = lists.get(i).getTrangThai();
                    }
                }
                if (trangThai.equalsIgnoreCase(check)) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    logo.setVisibility(View.GONE);
                    warning.setVisibility(View.VISIBLE);
                    banned.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    @Override
    public void onBackPressed() {
    }

}