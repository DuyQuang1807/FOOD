package com.example.findfood.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.findfood.DangKyActivity;
import com.example.findfood.DangNhapActivity;
import com.example.findfood.HelperClasses.SliderAdapter;
import com.example.findfood.MainActivity;
import com.example.findfood.R;
import com.example.findfood.Common.begin;
import com.example.findfood.model.ScreenItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class intro extends AppCompatActivity {

    private ViewPager screenPager;
    SliderAdapter sliderAdapter;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0;
    Button btnGetStarted;
    Animation btnAnim;
    TextView tvSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*-------------------- Make the activity on full screeen --------------------*/

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*-------------------- END make the activity on full screeen --------------------*/


        /*-------------------- Kiểm tra xem activy này đã được mở hay chưa --------------------*/

        if (restorePrefData()) {
            Intent mainActivity = new Intent(getApplicationContext(), DangNhapActivity.class);
            startActivity(mainActivity);
            finish();
        }

        /*-------------------- END kiểm tra xem activy này đã được mở hay chưa --------------------*/

        setContentView(R.layout.activity_intro);

        /*-------------------- Khởi tạo views --------------------*/

        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);
        tvSkip = findViewById(R.id.tv_skip);

        /*-------------------- END khởi tạo views --------------------*/


        /*-------------------- Điền dữ liệu vào màn hình --------------------*/

        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("FindFood", "Ứng dụng tìm kiếm vào giao đồ ăn thông minh", R.drawable.a1));
        mList.add(new ScreenItem("Gọi đâu có đó", "Tìm kiếm quán ăn, và đặt đồ ăn toàn quốc với tốc độ bàn thờ", R.drawable.a3));
        mList.add(new ScreenItem("Ăn uống thả ga, không lo về giá", "Giá cả không là vấn đề", R.drawable.a2));

        /*-------------------- END điền dữ liệu vào màn hình --------------------*/


        /*-------------------- Cài đặt viewpage --------------------*/

        screenPager = findViewById(R.id.screen_viewpager);
        sliderAdapter = new SliderAdapter(this, mList);
        screenPager.setAdapter(sliderAdapter);

        /*-------------------- END cài đặt viewpage --------------------*/


        /*-------------------- Cài đặt tablayout cho viewpage --------------------*/

        tabIndicator.setupWithViewPager(screenPager);

        /*-------------------- END cài đặt tablayout cho viewpage --------------------*/


        /*-------------------- Cài đặt button tiếp theo --------------------*/

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenPager.getCurrentItem();
                if (position < mList.size()) {
                    position++;
                    screenPager.setCurrentItem(position);
                }
                if (position == mList.size() - 1) { // Khi đến màn hình cuối
                    // TODO : Hiện nút bắt đầu, ẩn indicator và button tiếp theo
                    loaddLastScreen();
                }
            }
        });

        /*-------------------- END cài đặt button tiếp theo --------------------*/


        /*-------------------- Cài đặt tablayout và lắng nghe sự thay đổi --------------------*/

        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size() - 1) {
                    loaddLastScreen();
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        /*-------------------- END cài đặt tablayout và lắng nghe sự thay đổi --------------------*/


        /*-------------------- Cài đặt sự kiện lắng nghe cho button bắt đầu --------------------*/

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở màn hình đăng nhập
                Intent mainActivity = new Intent(getApplicationContext(), DangNhapActivity.class);
                startActivity(mainActivity);
                // Lưu lại biến boolean vào bộ nhớ để lần sau khi người dùng mở app
                // Chúng ta sẽ biết họ đã vào introActivity rồi
                // Gọi hàm lưu biến boolean
                savePrefsData();
                finish();
            }
        });

        /*-------------------- END cài đặt sự kiện lắng nghe cho button bắt đầu --------------------*/


        /*-------------------- Cài đặt sự kiện lắng nghe cho button skip --------------------*/

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenPager.setCurrentItem(mList.size());
            }
        });

        /*-------------------- END cài đặt sự kiện lắng nghe cho button skip --------------------*/
    }

    /*-------------------- Cài đặt hàm lưu biến boolean --------------------*/

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend", false);
        return isIntroActivityOpnendBefore;
    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend", true);
        editor.commit();
    }

    /*-------------------- END cài đặt hàm lưu biến boolean --------------------*/


    /*-------------------- Hiện nút bắt đầu và ẩn indicator và button tiếp theo --------------------*/

    private void loaddLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        btnGetStarted.setAnimation(btnAnim);
    }

    /*-------------------- Hiện nút bắt đầu và ẩn indicator và button tiếp theo --------------------*/

}