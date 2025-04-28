package QuanLySieuThi.TrungGian;

import java.util.Date;

/**
 *
 * @author ngotr
 */
public class PhieuNhap {
    private String MaPN;
    private String MaNV;
    private String NCC;
    private Date NgayLap;
    private int TongTien;

    public PhieuNhap() {
    }

    public PhieuNhap(String MaPN, String MaNV, String NCC, Date NgayLap, int TongTien) {
        this.MaPN = MaPN;
        this.MaNV = MaNV;
        this.NCC = NCC;
        this.NgayLap = NgayLap;
        this.TongTien = TongTien;
    }

    public String getMaPN() {
        return MaPN;
    }

    public void setMaPN(String MaPN) {
        this.MaPN = MaPN;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getNCC() {
        return NCC;
    }

    public void setNCC(String NCC) {
        this.NCC = NCC;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Date NgayLap) {
        this.NgayLap = NgayLap;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }
    
}
