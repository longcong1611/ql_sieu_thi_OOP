package XuLy;

import TrungGian.KhuyenMai;
import KetNoiSQL.KhuyenMaiSQL;
import GiaoDien.Thongbao;
import java.util.ArrayList;
import java.util.Date;

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
            new Thongbao("Hãy nhập tên chương trình khuyến mãi!", Thongbao.ERROR_DIALOG);
            return false;
        }
        if (ngayBD.compareTo(ngayKT) > 0 || ngayBD.compareTo(ngayKT) == 0) {
            new Thongbao("Ngày kết thúc không hợp lệ!", Thongbao.ERROR_DIALOG);
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
            new Thongbao("Hãy nhập số nguyên hợp lệ!", Thongbao.ERROR_DIALOG);
            return false;
        }
        if (flag) {
            new Thongbao("Thêm mới thành công!", Thongbao.SUCCESS_DIALOG);
        } else {
            new Thongbao("Thêm mới thất bại!", Thongbao.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean suaKhuyenMai(String MaKM, String TenKM, String MaSP, String UuDai, Date ngayBD, Date ngayKT) {
        TenKM = TenKM.trim();
        UuDai = UuDai.replace("%", "");
        if (MaKM.equals("")) {
            new Thongbao("Chưa chọn mã để sửa!", Thongbao.ERROR_DIALOG);
            return false;
        }
        if (TenKM.equals("")) {
            new Thongbao("Hãy nhập tên chương trình khuyến mãi!", Thongbao.ERROR_DIALOG);
            return false;
        }
        if (ngayBD.compareTo(ngayKT) > 0 || ngayBD.compareTo(ngayKT) == 0) {
            new Thongbao("Ngày kết thúc không hợp lệ!", Thongbao.ERROR_DIALOG);
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
            new Thongbao("Hãy nhập số nguyên hợp lệ!", Thongbao.ERROR_DIALOG);
            return false;
        }
        if (flag) {
            new Thongbao("Sửa thành công!", Thongbao.SUCCESS_DIALOG);
        } else {
            new Thongbao("Sửa thất bại!", Thongbao.ERROR_DIALOG);
        }
        return flag;
    }
    public boolean xoaKhuyenMai(String MaKM) {
        if (MaKM.equals("")) {
            new Thongbao("Chưa chọn mã để xóa!", Thongbao.ERROR_DIALOG);
            return false;
        }
        boolean flag = false;
        try {
            int maKM = Integer.parseInt(MaKM);
            flag = khuyenmaiSQL.xoaKhuyenMai(maKM);
        } catch (Exception e) {
            new Thongbao("Lỗi không xác định!", Thongbao.ERROR_DIALOG);
            e.printStackTrace();
            return false;
        }
        if (flag) {
            new Thongbao("Xóa thành công!", Thongbao.SUCCESS_DIALOG);
        } else {
            new Thongbao("Xóa thất bại!", Thongbao.ERROR_DIALOG);
        }
        return flag;
    }
}
