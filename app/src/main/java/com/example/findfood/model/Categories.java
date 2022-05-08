package com.example.findfood.model;

public class Categories {
    private String matheloai;
    private String tenDanhMuc;
    private String moTa;
    private String anh;
    private String trangThai;
    private String idDanhMuc;

    public Categories() {
    }

    public Categories(String matheloai, String tenDanhMuc, String moTa, String anh, String trangThai, String idDanhMuc) {
        this.matheloai = matheloai;
        this.tenDanhMuc = tenDanhMuc;
        this.moTa = moTa;
        this.anh = anh;
        this.trangThai = trangThai;
        this.idDanhMuc = idDanhMuc;
    }

    public String getMatheloai() {
        return matheloai;
    }

    public void setMatheloai(String matheloai) {
        this.matheloai = matheloai;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getIdDanhMuc() {
        return idDanhMuc;
    }

    public void setIdDanhMuc(String idDanhMuc) {
        this.idDanhMuc = idDanhMuc;
    }
}

