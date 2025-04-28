package TrungGian;

/**
 *
 * @author ngotr
 */
public class CTHoaDon {
    private String MaHD;
    private String MaSP;
    private int SoLuong;
    private int DonGia;
    private int ThanhTien;
    
    public CTHoaDon() {
    }
    public CTHoaDon(String MaHD, String MaSP, int SoLuong, int DonGia, int ThanhTien) {
        this.MaHD= MaHD;
        this.MaSP= MaSP;
        this.SoLuong= SoLuong;
        this.DonGia= DonGia;
        this.ThanhTien= ThanhTien;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
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
