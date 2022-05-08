package com.example.findfood.View.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.findfood.DangNhapActivity;
//import com.example.findfood.HelperClasses.UsersAdapter;
import com.example.findfood.CallBack.UserCallBack;
import com.example.findfood.DangNhapActivity;
import com.example.findfood.Databases.DatabaseUser;
import com.example.findfood.EditProfile;
import com.example.findfood.GiaoDichActivity;
import com.example.findfood.MainActivity;
import com.example.findfood.MapsActivity;
import com.example.findfood.R;
import com.example.findfood.ThanhToanActivity;
import com.example.findfood.View.CartActivity;
import com.example.findfood.View.ChatActivity;
import com.example.findfood.View.FavoriteActivity;
import com.example.findfood.View.HistoryActivity;
import com.example.findfood.View.MapActivity;
import com.example.findfood.View.ThayDoiMatKhauActivity;
import com.example.findfood.model.User;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TrangCaNhan extends AppCompatActivity {
    public static ImageView back;
    private Switch darkModeSwitch;
    RelativeLayout edtEditProfile;
    ImageView profileCircleImageView;
    TextView usernameTextView, email, txtlogout,txteditprofile,txtchangepassword, txtmap, qldh;
    TextView txtYeuThich,map,txtVersion,txtMesenger;
    DatabaseUser databaseUser;
    FirebaseUser firebaseUser;

    FirebaseAuth fAuth;
    FirebaseDatabase fData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_ca_nhan);

        back = findViewById(R.id.back);
        txtVersion = findViewById(R.id.txtVersion);
        edtEditProfile = findViewById(R.id.edtEditProfile);
        qldh = findViewById(R.id.qldh);
        txtmap = findViewById(R.id.txtmap);
        txtMesenger = findViewById(R.id.txtMesenger);
        txtYeuThich = findViewById(R.id.txtYeuThich);
        profileCircleImageView = findViewById(R.id.profileCircleImageView);
        usernameTextView = findViewById(R.id.usernameTextView);
        email= findViewById(R.id.email);
        txtlogout = findViewById(R.id.txtlogout);
        txtchangepassword = findViewById(R.id.txtchangepassword);
        txteditprofile = findViewById(R.id.txteditprofile);
        darkModeSwitch = findViewById(R.id.darkModeSwitch);
        fAuth = FirebaseAuth.getInstance();
        fData = FirebaseDatabase.getInstance();
        databaseUser = new DatabaseUser(getApplicationContext());
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseUser.getAll(new UserCallBack() {
            @Override
            public void onSuccess(ArrayList<User> lists) {
                for (int i =0 ; i<lists.size();i++){
                    if (lists.get(i).getToken()!=null && lists.get(i).getToken().equalsIgnoreCase(firebaseUser.getUid())){
                        email.setText(lists.get(i).getEmail());
                        usernameTextView.setText(lists.get(i).getName());
                        Picasso.get()
                                .load(lists.get(i).getImage()).into(profileCircleImageView);
                    }
                }
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getApplicationContext(), "Đang phát triển, dự kiến khả dụng : CDIO 4", Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        txtmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MapActivity.class));
            }
        });
//        map.setVisibility(View.GONE);

        darkModeSwitch.setVisibility(View.GONE);

        edtEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iProfile = new Intent(getApplicationContext(), EditProfile.class);
                startActivity(iProfile);
            }
        });

        txteditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iProfile = new Intent(getApplicationContext(), EditProfile.class);
                startActivity(iProfile);
            }
        });

        txtchangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iQuenPass = new Intent(getApplicationContext(), ThayDoiMatKhauActivity.class);
                startActivity(iQuenPass);
                finish();
            }
        });

//        txteditprofile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent iProfile = new Intent(getApplicationContext(), EditProfile.class);
//                startActivity(iProfile);
//            }
//        });

        txtMesenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iMess = new Intent(getApplicationContext(), ChatActivity.class);
                startActivity(iMess);
            }
        });
        qldh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iQLDH = new Intent(getApplicationContext(),QuanLyDonHangActivity.class);
                startActivity(iQLDH);
            }
        });
        txtYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iYeuThich = new Intent(getApplicationContext(), FavoriteActivity.class);
                startActivity(iYeuThich);
            }
        });

        txtlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                startActivity(new Intent(getApplicationContext(), DangNhapActivity.class));
                finish();
            }
        });

        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        txtVersion.setText( getString(R.string.phienban) + " " +packageInfo.versionName);
    }

}