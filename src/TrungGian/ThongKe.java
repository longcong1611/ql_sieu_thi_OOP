<<<<<<< HEAD
package TrungGian;

import java.util.Date;

/**
 * DTO ThongKe: chứa thông tin doanh thu theo hóa đơn
 */
public class ThongKe {
    private Date NgayLap;
    private int MaHD;
    private int TongTien;
    private int MaNV;
    private String TenNV;
    private int MaSP;
    private String TenSP;
    private int TongNhap, soluongBanra, soluongConlai;
    
    public ThongKe(Date NgayLap, int MaHD, int TongTien, int MaNV, String TenNV) {
        this.NgayLap = NgayLap;
        this.MaHD = MaHD;
        this.TongTien = TongTien;
        this.MaNV = MaNV;
        this.TenNV = TenNV;
    }
    public ThongKe(Date NgayLap, int MaSP, String TenSP, int TongNhap, int soluongBanra, int soluongConlai ){
        this.NgayLap = NgayLap;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.TongNhap = TongNhap;
        this.soluongBanra = soluongBanra;
        this.soluongConlai = soluongConlai;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Date NgayLap) {
        this.NgayLap = NgayLap;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
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

    public int getTongNhap() {
        return TongNhap;
    }

    public void setTongNhap(int TongNhap) {
        this.TongNhap = TongNhap;
    }

    public int getSoluongBanra() {
        return soluongBanra;
    }

    public void setSoluongBanra(int soluongBanra) {
        this.soluongBanra = soluongBanra;
    }

    public int getSoluongConlai() {
        return soluongConlai;
    }

    public void setSoluongConlai(int soluongConlai) {
        this.soluongConlai = soluongConlai;
    }
    
    /**
     * Trả về tên nhân viên (dùng chung getter)
     */
    public String getNhanVienLap() {
        return TenNV;
    }

    public void setNhanVienLap(String TenNV) {
        this.TenNV = TenNV;
    }
}
=======
package TrungGian;

import java.util.Date;

/**
 * DTO ThongKe: chứa thông tin doanh thu theo hóa đơn
 */
public class ThongKe {
    private Date NgayLap;
    private int MaHD;
    private int TongTien;
    private int MaNV;
    private String TenNV;
    private int MaSP;
    private String TenSP;
    private int TongNhap, soluongBanra, soluongConlai;
    
    public ThongKe(Date NgayLap, int MaHD, int TongTien, int MaNV, String TenNV) {
        this.NgayLap = NgayLap;
        this.MaHD = MaHD;
        this.TongTien = TongTien;
        this.MaNV = MaNV;
        this.TenNV = TenNV;
    }
    public ThongKe(Date NgayLap, int MaSP, String TenSP, int TongNhap, int soluongBanra, int soluongConlai ){
        this.NgayLap = NgayLap;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.TongNhap = TongNhap;
        this.soluongBanra = soluongBanra;
        this.soluongConlai = soluongConlai;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Date NgayLap) {
        this.NgayLap = NgayLap;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
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

    public int getTongNhap() {
        return TongNhap;
    }

    public void setTongNhap(int TongNhap) {
        this.TongNhap = TongNhap;
    }

    public int getSoluongBanra() {
        return soluongBanra;
    }

    public void setSoluongBanra(int soluongBanra) {
        this.soluongBanra = soluongBanra;
    }

    public int getSoluongConlai() {
        return soluongConlai;
    }

    public void setSoluongConlai(int soluongConlai) {
        this.soluongConlai = soluongConlai;
    }
    
    /**
     * Trả về tên nhân viên (dùng chung getter)
     */
    public String getNhanVienLap() {
        return TenNV;
    }

    public void setNhanVienLap(String TenNV) {
        this.TenNV = TenNV;
    }
}
>>>>>>> bef1f7b (commit)
