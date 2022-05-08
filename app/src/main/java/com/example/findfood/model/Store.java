package com.example.findfood.model;

public class Store {
    private String email;
    private String pass;
    private String name;
    private String phone;
    private String image;
    private String diaChi;
    private String status;
    private String tenCuaHang;
    private String tokenstore;
    private String thoiGianMoCua;
    private String thoiGianDongCua;

    public Store() {
    }

    public Store(String email, String pass, String name, String phone, String image, String diaChi, String status, String tenCuaHang, String tokenstore, String thoiGianMoCua, String thoiGianDongCua) {
        this.email = email;
        this.pass = pass;
        this.name = name;
        this.phone = phone;
        this.image = image;
        this.diaChi = diaChi;
        this.status = status;
        this.tenCuaHang = tenCuaHang;
        this.tokenstore = tokenstore;
        this.thoiGianMoCua = thoiGianMoCua;
        this.thoiGianDongCua = thoiGianDongCua;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTenCuaHang() {
        return tenCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        this.tenCuaHang = tenCuaHang;
    }

    public String getTokenstore() {
        return tokenstore;
    }

    public void setTokenstore(String tokenstore) {
        this.tokenstore = tokenstore;
    }

    public String getThoiGianMoCua() {
        return thoiGianMoCua;
    }

    public void setThoiGianMoCua(String thoiGianMoCua) {
        this.thoiGianMoCua = thoiGianMoCua;
    }

    public String getThoiGianDongCua() {
        return thoiGianDongCua;
    }

    public void setThoiGianDongCua(String thoiGianDongCua) {
        this.thoiGianDongCua = thoiGianDongCua;
    }
}