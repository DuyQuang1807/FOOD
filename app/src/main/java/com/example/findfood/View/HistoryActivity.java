package com.example.findfood.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findfood.HelperClasses.Tabadapter;
import com.example.findfood.R;
import com.example.findfood.View.User.QuanLyDonHangActivity;
import com.example.findfood.View.User.TrangCaNhan;
import com.google.android.material.tabs.TabLayout;

public class HistoryActivity extends AppCompatActivity {
    private Tabadapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ImageView back;
    TextView titletoolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        tabLayout = findViewById(R.id.tabLayout);
        back = findViewById(R.id.back);
        titletoolbar = findViewById(R.id.toolbar_title);
        viewPager = findViewById(R.id.viewPager);

        adapter = new Tabadapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentGDThanhCong(), "Chờ Shipper");
        adapter.addFragment(new FragmentGDThatBai(), "Chờ Cửa Hàng");
        adapter.addFragment(new FragmentDangGiao(), "Đang giao");
        adapter.addFragment(new FragmentDaGiao(), "Đã giao");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), QuanLyDonHangActivity.class);
                startActivity(i);
            }
        });

    }
}