package TrungGian;

import java.util.Date;

/**
 *
 * @author ngotr
 */
public class HoaDon {
    private String MaHD;
    private String MaKH;
    private String MaNV;
    private Date NgayLap;
    private int TongTien;

    public HoaDon() {
    }

    public HoaDon(String MaHD, String MaKH, String MaNV, Date NgayLap, int TongTien) {
        this.MaHD = MaHD;
        this.MaKH = MaKH;
        this.MaNV = MaNV;
        this.NgayLap = NgayLap;
        this.TongTien = TongTien;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
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
