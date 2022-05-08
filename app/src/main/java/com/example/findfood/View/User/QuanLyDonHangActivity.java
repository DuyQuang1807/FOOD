package com.example.findfood.View.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findfood.GiaoDichActivity;
import com.example.findfood.R;
import com.example.findfood.ThanhToanActivity;
import com.example.findfood.View.CartActivity;
import com.example.findfood.View.HistoryActivity;

public class QuanLyDonHangActivity extends AppCompatActivity {

    public static ImageView backqldh;
    TextView txtTrangThaiDonHang,txtDonHang,txtGioHang, txtHistory, txtQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_don_hang);

        backqldh = findViewById(R.id.backqldh);
        txtQRCode = findViewById(R.id.txtQRCode);
        txtDonHang = findViewById(R.id.txtDonHang);
        txtHistory = findViewById(R.id.txtHistory);
        txtGioHang = findViewById(R.id.txtGioHang);
        txtTrangThaiDonHang = findViewById(R.id.txtTrangThaiDonHang);


        backqldh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TrangCaNhan.class);
                startActivity(intent);
            }
        });

        txtQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), testQRCode.class);
                startActivity(intent);
            }
        });

        txtHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGiaoDich = new Intent(getApplicationContext(), GiaoDichActivity.class);
                startActivity(iGiaoDich);
            }
        });

        txtTrangThaiDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iLichSu = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(iLichSu);
            }
        });
        txtDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iDonHang = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(iDonHang);
            }
        });
        txtGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(getApplicationContext(), ThanhToanActivity.class);
                startActivity(iGioHang);
            }
        });
    }
}