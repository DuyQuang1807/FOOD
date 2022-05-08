package com.example.findfood;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.findfood.Databases.DatabaseUser;
import com.example.findfood.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;

public class DangKyActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    public EditText edtHoTen,edtEmail,edtPass,edtConfPass,edtDate,edtPhone;
    RadioButton rdbNam, rdbNu, rdbKhac;
    String gioiTinh = "";
    Button btnDangKy;
    TextView btnDangNhap;
    public ImageButton buttonDate;
    FirebaseFirestore fStore;
    ProgressBar progressBar;

    public int Year;
    public int Month;
    public int dayOfMonth;
    public int yearCurrent;
    private FirebaseAuth mAuth;
    private DatabaseUser DatabaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        edtHoTen = findViewById(R.id.fullName);
        edtEmail = findViewById(R.id.Email);
        edtPass = findViewById(R.id.password);
        edtConfPass = findViewById(R.id.confPassword);
        edtPhone = findViewById(R.id.edtPhone);
        rdbNam = findViewById(R.id.rdbNam);
        rdbNu = findViewById(R.id.rdbNu);
        rdbKhac = findViewById(R.id.rdbKhac);
        btnDangKy = findViewById(R.id.btnOTP);
        btnDangNhap = findViewById(R.id.btnDangNhap);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        progressBar = findViewById(R.id.progressBar);

        this.edtDate = (EditText) this.findViewById(R.id.editText_date);
        this.buttonDate = (ImageButton) this.findViewById(R.id.button_date);

            //Hàm Date
        this.buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSelectDate();
            }
        });
        final Calendar c = Calendar.getInstance();
        this.Year = c.get(Calendar.YEAR);
        this.yearCurrent = c.get(Calendar.YEAR);
        this.Month = c.get(Calendar.MONTH);
        this.dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

            //Hàm check validation
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email    = edtEmail.getText().toString().trim();
                String password       = edtPass.getText().toString().trim();
                String confpassword    = edtConfPass.getText().toString();
                final String hoten = edtHoTen.getText().toString().trim();
                final String phone = edtPhone.getText().toString().trim();
                final String ngaysinh = edtDate.getText().toString().trim();

                if (TextUtils.isEmpty(hoten)){
                    edtHoTen.setError("Bắt buộc");
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    edtEmail.setError("Bắt buộc");
                    return;
                }
                if (!email.matches("^[a-zA-Z][a-z0-9_\\.]{4,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$")) {
                    edtEmail.setError("Email không hợp lệ.");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    edtPass.setError("Bắt buộc");
                    return;
                }

                if(password.length() < 6){
                    edtPass.setError("Mật khẩu phải lớn hơn 6 ký tự");
                    return;
                }

                if (TextUtils.isEmpty(confpassword)){
                    edtConfPass.setError("Bắt buộc");
                    return;
                }

                if (!password.equals(confpassword)){
                    edtConfPass.setError("Mật khẩu không khớp");
                }

                if (TextUtils.isEmpty(phone)){
                    edtPhone.setError("Bắt buộc");
                    return;
                }

                if (rdbNam.isChecked()){
                    gioiTinh = "Nam";
                }
                if (rdbNu.isChecked()){
                    gioiTinh = "Nữ";
                }
                if (rdbKhac.isChecked()) {
                    gioiTinh = "Khác";
                }

                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getApplicationContext(), "Đăng ký Thất Bại." ,
                                            Toast.LENGTH_SHORT).show();
                                } else {

                                    DatabaseUser = new DatabaseUser(getApplicationContext());
                                    User user = new User(email,password,hoten,phone,"null","null",ngaysinh,gioiTinh,mAuth.getUid(), "active");

                                    DatabaseUser.insert(user);
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getApplicationContext(), "Đăng Ký Thành Công.", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),DangNhapActivity.class));
                                    finish();

                                }
                            }
                        });
            }
        });


        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DangNhapActivity.class));
            }
        });

    }

    private void buttonSelectDate() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                edtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                Year = year;
                Month = monthOfYear;
                if (Year > yearCurrent){
                    Toast.makeText(getApplicationContext(), "Sai định dạng ngày/tháng/năm", Toast.LENGTH_SHORT).show();
                }
            }
        };
        DatePickerDialog datePickerDialog = null;
        datePickerDialog = new DatePickerDialog(this, dateSetListener, Year, Month, dayOfMonth);
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        datePickerDialog.show();
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }


}