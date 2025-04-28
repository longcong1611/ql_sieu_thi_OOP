package TrungGian;

/**
 *
 * @author ngotr
 */
public class CTPhieuNhap {
    private String MaPN;
    private String MaSP;
    private int SoLuong;
    private int DonGia;
    private int ThanhTien;

    public CTPhieuNhap() {
    }

    public CTPhieuNhap(String MaPN, String MaSP, int SoLuong, int DonGia, int ThanhTien) {
        this.MaPN = MaPN;
        this.MaSP = MaSP;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.ThanhTien = ThanhTien;
    }

    public String getMaPN() {
        return MaPN;
    }

    public void setMaPN(String MaPN) {
        this.MaPN = MaPN;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getDonGia() {
        return DonGia;
    }

    public void setDonGia(int DonGia) {
        this.DonGia = DonGia;
    }

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
    
}
