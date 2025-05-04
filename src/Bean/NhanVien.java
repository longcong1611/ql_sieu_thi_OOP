package Bean;

public class NhanVien {
    
    private int maNV;
    private String hoVaTen;
    private String gioiTinh;
    private String soDienThoai;
    private String chucVu;
    private String diaChi;
    public NhanVien() {
        
    }

    public NhanVien(int maNV, String hoVaTen, String gioiTinh, String chucVu) {
        this.maNV = maNV;
        this.hoVaTen = hoVaTen;
        this.soDienThoai = soDienThoai;
        this.gioiTinh = gioiTinh;
        this.chucVu = chucVu;
        this.diaChi = diaChi;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
       
}
