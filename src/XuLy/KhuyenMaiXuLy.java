package XuLy;

import TrungGian.KhuyenMai;
import KetNoiSQL.KhuyenMaiSQL;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author ngotr
 */
public class KhuyenMaiXuLy {
    private ArrayList<KhuyenMai> listKhuyenMai = null;
    private KhuyenMaiSQL khuyenmaiSQL = new KhuyenMaiSQL();

    public KhuyenMaiXuLy() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listKhuyenMai = khuyenmaiSQL.getDanhSachKhuyenMai();
    }

    public ArrayList<KhuyenMai> getDanhSachKhuyenMai() {
        if (this.listKhuyenMai == null)
            docDanhSach();
        return this.listKhuyenMai;
    }

    public boolean themKhuyenMai(String MaKM, String TenKM, String MaSP, String UuDai, Date ngayBD, Date ngayKT) {
        TenKM = TenKM.trim();
        UuDai = UuDai.replace("%", "");
        if (TenKM.equals("")) {
            JOptionPane.showMessageDialog(null, "Hãy nhập tên chương trình khuyến mãi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (ngayBD.compareTo(ngayKT) > 0 || ngayBD.compareTo(ngayKT) == 0) {
            JOptionPane.showMessageDialog(null, "Ngày kết thúc không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean flag = false;
        try {
            int uudaiKM = Integer.parseInt(UuDai);
            int masp = Integer.parseInt(MaSP);
            int makm = Integer.parseInt(MaKM);
            
            KhuyenMai khuyenmai = new KhuyenMai();
            khuyenmai.setMaKM(makm);
            khuyenmai.setTenKM(TenKM);
            khuyenmai.setMaSP(masp);
            khuyenmai.setUuDai(uudaiKM);
            khuyenmai.setNgayBD(ngayBD);
            khuyenmai.setNgayKT(ngayKT);

            flag = khuyenmaiSQL.themKhuyenMai(khuyenmai);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hãy nhập số nguyên hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (flag) {
            JOptionPane.showMessageDialog(null, "Thêm mới thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm mới thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return flag;
    }

    public boolean suaKhuyenMai(String MaKM, String TenKM, String MaSP, String UuDai, Date ngayBD, Date ngayKT) {
        TenKM = TenKM.trim();
        UuDai = UuDai.replace("%", "");
        if (MaKM.equals("")) {
            JOptionPane.showMessageDialog(null, "Chưa chọn mã để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (TenKM.equals("")) {
            JOptionPane.showMessageDialog(null, "Hãy nhập tên chương trình khuyến mãi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (ngayBD.compareTo(ngayKT) > 0 || ngayBD.compareTo(ngayKT) == 0) {
            JOptionPane.showMessageDialog(null, "Ngày kết thúc không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean flag = false;
        try {
            int maKM = Integer.parseInt(MaKM);
            int uudaiKM = Integer.parseInt(UuDai);
            int maSP = Integer.parseInt(MaSP);

            KhuyenMai khuyenmai = new KhuyenMai();
            khuyenmai.setMaKM(maKM);
            khuyenmai.setTenKM(TenKM);
            khuyenmai.setMaSP(maSP);
            khuyenmai.setUuDai(uudaiKM);
            khuyenmai.setNgayBD(ngayBD);
            khuyenmai.setNgayKT(ngayKT);

            flag = khuyenmaiSQL.suaKhuyenMai(khuyenmai);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hãy nhập số nguyên hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (flag) {
            JOptionPane.showMessageDialog(null, "Sửa thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Sửa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return flag;
    }

    public boolean xoaKhuyenMai(String MaKM) {
        if (MaKM.equals("")) {
            JOptionPane.showMessageDialog(null, "Chưa chọn mã để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean flag = false;
        try {
            int maKM = Integer.parseInt(MaKM);
            flag = khuyenmaiSQL.xoaKhuyenMai(maKM);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi không xác định!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
        if (flag) {
            JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return flag;
    }
}