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
public class ViTriSP {
    
    private int MaViTri;
    private int MaSP;
    private String KhuVuc;
    private int KeSo;
    private int Tang;
    private String MoTa;
    private int SoLuongHienCo;
    private Date NgayCapNhat;

    public ViTriSP() {
    }

    public ViTriSP(int MaViTri, int MaSP, String KhuVuc, int KeSo, int Tang, String MoTa, int SoLuongHienCo, Date NgayCapNhat) {
        this.MaViTri = MaViTri;
        this.MaSP = MaSP;
        this.KhuVuc = KhuVuc;
        this.KeSo = KeSo;
        this.Tang = Tang;
        this.MoTa = MoTa;
        this.SoLuongHienCo = SoLuongHienCo;
        this.NgayCapNhat = NgayCapNhat;
    }

    public int getMaViTri() {
        return MaViTri;
    }

    public void setMaViTri(int MaViTri) {
        this.MaViTri = MaViTri;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int MaSP) {
        this.MaSP = MaSP;
    }

    public String getKhuVuc() {
        return KhuVuc;
    }

    public void setKhuVuc(String KhuVuc) {
        this.KhuVuc = KhuVuc;
    }

    public int getKeSo() {
        return KeSo;
    }

    public void setKeSo(int KeSo) {
        this.KeSo = KeSo;
    }

    public int getTang() {
        return Tang;
    }

    public void setTang(int Tang) {
        this.Tang = Tang;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public int getSoLuongHienCo() {
        return SoLuongHienCo;
    }

    public void setSoLuongHienCo(int SoLuongHienCo) {
        this.SoLuongHienCo = SoLuongHienCo;
    }

    public Date getNgayCapNhat() {
        return NgayCapNhat;
    }

    public void setNgayCapNhat(Date NgayCapNhat) {
        this.NgayCapNhat = NgayCapNhat;
    }
    
    
}

