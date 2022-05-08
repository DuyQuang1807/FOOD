package com.example.findfood.HelperClasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findfood.FoodProfileActivity;
import com.example.findfood.R;
import com.example.findfood.model.Food;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class FoodProfileAdapter extends RecyclerView.Adapter<FoodProfileAdapter.MyViewHolder> {

    ArrayList<Food> categoryList;
    Context context;
    String Tag;

    public FoodProfileAdapter(ArrayList<Food> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemfoodprofile, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        decimalFormat.applyPattern("#,###,###,###");
        Food categories = categoryList.get(position);
        holder.title.setText(categories.getTenSanPham());
        holder.txtdiachi.setText(categories.getDiaChi());
        holder.txtTrangThaiActivity.setText(categories.getTrangThai());
        holder.txtgia.setText(String.valueOf(decimalFormat.format(categories.getGiaTien())+" VNĐ"));
        Picasso.get()
                .load(categories.getAnh())
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d("Error : ", e.getMessage());
                    }
                });


        holder.cardView1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("img", categories.getAnh());
                intent.putExtra("gia", decimalFormat.format(categories.getGiaTien())+"\t VNĐ");
                intent.putExtra("namefood", categories.getTenSanPham());
                intent.putExtra("idfood","Id: "+categories.getIdfood());
                intent.putExtra("idstore",categories.getIdCuaHang());
                intent.putExtra("diachi",categories.getDiaChi());
                intent.putExtra("sl",categories.getSoLuong()+"");
                intent.putExtra("matl",categories.getMatheloai());
                intent.putExtra("status",categories.getStatus());
                intent.putExtra("trangThai", categories.getTrangThai());
                intent.putExtra("mota",categories.getMota());
                intent.putExtra("idfoodcheck",categories.getIdfood()+"");
                intent.putExtra("tokenstore",categories.getTokenstore());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title,txtdiachi,txtgia, txtTrangThaiActivity;
        ProgressBar progressBar;
        CardView cardView,cardView1,card_view4;
        LinearLayout line1;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgfood);
            title = itemView.findViewById(R.id.txtnamefood);
            progressBar = itemView.findViewById(R.id.progressbar);
            txtdiachi = itemView.findViewById(R.id.txtdiachi);
            txtgia = itemView.findViewById(R.id.txtgia);
            txtTrangThaiActivity = itemView.findViewById(R.id.txtTrangThaiActivity);
            line1 = itemView.findViewById(R.id.line1);
            cardView1 = itemView.findViewById(R.id.cardview1);

        }
    }
}
