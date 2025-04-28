package QuanLySieuThi.TrungGian;

import java.util.Date;

/**
 *
 * @author ngotr
 */
public class KhuyenMai {
    private String MaKM;
    private String MaSP;
    private String TenKM;
    private int UuDai;
    private Date NgayBD;
    private Date ngayKT;

    public KhuyenMai() {
    }

    public KhuyenMai(String MaKM, String MaSP, String TenKM, int UuDai, Date NgayBD, Date ngayKT) {
        this.MaKM = MaKM;
        this.MaSP = MaSP;
        this.TenKM = TenKM;
        this.UuDai = UuDai;
        this.NgayBD = NgayBD;
        this.ngayKT = ngayKT;
    }

    public String getMaKM() {
        return MaKM;
    }

    public void setMaKM(String MaKM) {
        this.MaKM = MaKM;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenKM() {
        return TenKM;
    }

    public void setTenKM(String TenKM) {
        this.TenKM = TenKM;
    }

    public int getUuDai() {
        return UuDai;
    }

    public void setUuDai(int UuDai) {
        this.UuDai = UuDai;
    }

    public Date getNgayBD() {
        return NgayBD;
    }

    public void setNgayBD(Date NgayBD) {
        this.NgayBD = NgayBD;
    }

    public Date getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(Date ngayKT) {
        this.ngayKT = ngayKT;
    }
    
}
