package com.example.findfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.findfood.HelperClasses.XacNhanAdapter;
import com.example.findfood.View.User.QuanLyDonHangActivity;
import com.example.findfood.model.HDCT;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GiaoDichActivity extends AppCompatActivity {
    XacNhanAdapter xacnhanAdapter;
    ArrayList<HDCT> hdctArrayList;
    RecyclerView rcvxacnhan;
    ImageView back1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dich);

        back1 = findViewById(R.id.back1);
        rcvxacnhan = findViewById(R.id.rcvxacnhan);
        hdctArrayList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvxacnhan.setLayoutManager(linearLayoutManager);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("HDCT");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    HDCT hdct = dataSnapshot.getValue(HDCT.class);
                    if (hdct.getIdUser().equalsIgnoreCase(user.getUid())){
                        hdctArrayList.add(hdct);
                        xacnhanAdapter = new XacNhanAdapter(hdctArrayList,GiaoDichActivity.this);
                        rcvxacnhan.setAdapter(xacnhanAdapter);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuanLyDonHangActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}