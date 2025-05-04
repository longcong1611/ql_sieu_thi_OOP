package TrungGian;

import java.util.Date;

/**
 *
 * @author ngotr
 */
public class PhieuNhap {
    private int MaPN;
    private int MaNV;
    private String NhaCungCap;
    private Date NgayLap;
    private int TongTien;

    public PhieuNhap() {
    }

    public PhieuNhap(int MaPN, int MaNV, String NhaCungCap, Date NgayLap, int TongTien) {
        this.MaPN = MaPN;
        this.MaNV = MaNV;
        this.NhaCungCap = NhaCungCap;
        this.NgayLap = NgayLap;
        this.TongTien = TongTien;
    }

    public int getMaPN() {
        return MaPN;
    }

    public void setMaPN(int MaPN) {
        this.MaPN = MaPN;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public String getNhaCungCap() {
        return NhaCungCap;
    }

    public void setNhaCungCap(String NhaCungCap) {
        this.NhaCungCap = NhaCungCap;
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
