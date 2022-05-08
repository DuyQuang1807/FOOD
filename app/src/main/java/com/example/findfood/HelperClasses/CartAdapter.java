package com.example.findfood.HelperClasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findfood.R;
import com.example.findfood.local.LocalStorage;
import com.example.findfood.model.Order;
import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    ArrayList<Order> cartList;
    Context context;

    String _subtotal, _price, _quantity, PhanTramGiam, GiaGiam, TongTienThanhToan;
    LocalStorage localStorage;
    Gson gson;
    double PhanTramGiamD, GiaGiamD, TongTienThanhToanD, subtotalD;

    public CartAdapter(ArrayList<Order> cartList, Context context) {
        this.cartList = cartList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView;

        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_cart, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        decimalFormat.applyPattern("#,###,###,###");
        final DecimalFormat decimalFormatCustom = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        decimalFormat.applyPattern("#");
        final Order cart = cartList.get(position);
        localStorage = new LocalStorage(context);
        gson = new Gson();
        holder.title.setText(cart.getFood().getTenSanPham());
        holder.attribute.setText(cart.getFood().getMatheloai());

        holder.quantity.setText(String.valueOf(decimalFormat.format(cart.getSoluongmua())));
        holder.price.setText(String.valueOf(decimalFormat.format(cart.getFood().getGiaTien())));
        subtotalD = (cart.getSoluongmua() * cart.getFood().getGiaTien());
        _subtotal = String.valueOf(decimalFormat.format(cart.getSoluongmua() * cart.getFood().getGiaTien()));

        holder.subTotal.setText(_subtotal);

        // Đây là đoạn mới thêm, lỗi thì chịu

        PhanTramGiamD = (cart.getFood().getKhuyenMai());
        holder.txtPhanTramGiam.setText(String.valueOf(decimalFormatCustom.format(PhanTramGiamD) + "%"));

        GiaGiamD = (cart.getSoluongmua() * (cart.getFood().getGiaTien() * ((cart.getFood().getKhuyenMai()) * 0.01)));
        holder.txtGiaGiam.setText(String.valueOf((decimalFormat.format(GiaGiamD))));

        holder.txtsub_totalTongTienThanhToan.setText(_subtotal);
        holder.txtGiaGiamTongTienThanhToan.setText(decimalFormat.format(GiaGiamD));

        TongTienThanhToanD = subtotalD - GiaGiamD;
        holder.txtTongTienThanhToan.setText(String.valueOf(decimalFormat.format(TongTienThanhToanD)));
        Picasso.get()
                .load(cart.getFood().getAnh())
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

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cartList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cartList.size());
                Gson gson = new Gson();
                String cartStr = gson.toJson(cartList);
                Log.d("CART", cartStr);
                localStorage.setCart(cartStr);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        ProgressBar progressBar;
        CardView cardView;
        TextView offer, currency, price, quantity, attribute, addToCart, subTotal, txtPhanTramGiam, txtGiaGiam, txtTongTienThanhToan, txtsub_totalTongTienThanhToan, txtGiaGiamTongTienThanhToan;
        TextView plus, minus;
        Button delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.product_image);
            title = itemView.findViewById(R.id.product_title);
            progressBar = itemView.findViewById(R.id.progressbar);
            quantity = itemView.findViewById(R.id.quantity);
            currency = itemView.findViewById(R.id.product_currency);
            attribute = itemView.findViewById(R.id.product_attribute);
            minus = itemView.findViewById(R.id.quantity_minus);
            txtPhanTramGiam = itemView.findViewById(R.id.txtPhanTramGiam);
            delete = itemView.findViewById(R.id.cart_delete);
            txtGiaGiam = itemView.findViewById(R.id.txtGiaGiam);
            txtTongTienThanhToan = itemView.findViewById(R.id.txtTongTienThanhToan);
            txtsub_totalTongTienThanhToan = itemView.findViewById(R.id.txtsub_totalTongTienThanhToan);
            txtGiaGiamTongTienThanhToan = itemView.findViewById(R.id.txtGiaGiamTongTienThanhToan);
            subTotal = itemView.findViewById(R.id.sub_total);
            price = itemView.findViewById(R.id.product_price);
        }
    }
}