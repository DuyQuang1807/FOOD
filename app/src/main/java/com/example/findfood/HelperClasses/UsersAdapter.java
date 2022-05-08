//package com.example.findfood.HelperClasses;
//
//import android.app.Activity;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//
//import com.example.findfood.R;
//import com.example.findfood.model.User;
//
//import org.jetbrains.annotations.Nullable;
//
//public class UsersAdapter extends ArrayAdapter<User> {
//    Activity context;
//    int resource;
//    public UsersAdapter(Activity context, int resource) {
//        super(context, resource);
//        this.context=context;
//        this.resource=resource;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View custom=context.getLayoutInflater().inflate(resource,null);
//        TextView txtId=custom.findViewById(R.id.txtId);
//        TextView txtName=custom.findViewById(R.id.txtName);
//        TextView txtNgaySinh=custom.findViewById(R.id.txtNgaySinh);
//        TextView txtPhone=custom.findViewById(R.id.txtPhone);
//        TextView txtEmail=custom.findViewById(R.id.txtEmail);
//        TextView txtGioiTinh=custom.findViewById(R.id.txtGioiTinh);
//        User user=getItem(position);
////        txtId.setText(user.getUserId());xxxxx``
//        txtName.setText(user.getHoten());
//        txtNgaySinh.setText(user.getNgaysinh());
//        txtEmail.setText(user.getEmail());
//        txtPhone.setText(user.getPhone());
//        txtEmail.setText(user.getGioitinh());
//        return custom;
//    }
//}
