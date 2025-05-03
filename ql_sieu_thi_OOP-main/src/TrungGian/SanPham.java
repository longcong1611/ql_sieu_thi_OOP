/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TrungGian;

import java.util.Date;

/**
 *
 * @author Ngoc Lan
 */
public class SanPham {
    
    private int MaSP;
    private String TenSP;
    private String LoaiSP;
    private int GiaBan;
    private int GiaNhap;
    private int TongSL;
    private String NhaCungCap;
    private Date HSD;

    public SanPham() {
    }

    public SanPham(int MaSP, String TenSP, String LoaiSP, int GiaBan, int GiaNhap, int TongSL, String NhaCungCap, Date HSD) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.LoaiSP = LoaiSP;
        this.GiaBan = GiaBan;
        this.GiaNhap = GiaNhap;
        this.TongSL = TongSL;
        this.NhaCungCap = NhaCungCap;
        this.HSD = HSD;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getLoaiSP() {
        return LoaiSP;
    }

    public void setLoaiSP(String LoaiSP) {
        this.LoaiSP = LoaiSP;
    }

    public int getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(int GiaBan) {
        this.GiaBan = GiaBan;
    }

    public int getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(int GiaNhap) {
        this.GiaNhap = GiaNhap;
    }

    public int getTongSL() {
        return TongSL;
    }

    public void setTongSL(int TongSL) {
        this.TongSL = TongSL;
    }

    public String getNhaCungCap() {
        return NhaCungCap;
    }

    public void setNhaCungCap(String NhaCungCap) {
        this.NhaCungCap = NhaCungCap;
    }

    public Date getHSD() {
        return HSD;
    }

    public void setHSD(Date HSD) {
        this.HSD = HSD;
    }

    
}

