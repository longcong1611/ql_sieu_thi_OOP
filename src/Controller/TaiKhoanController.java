package Controller;

import TrungGian.TaiKhoan;
import KetNoiSQL.TaiKhoanSQL;
import Bean.Dialog;
import java.util.ArrayList;

public class TaiKhoanController {
    private TaiKhoanSQL taiKhoanSQL = new TaiKhoanSQL();
    
    public String getTenDangNhapTheoMa(String ma) {
        int maNV = Integer.parseInt(ma);
        return taiKhoanSQL.getTenDangNhapTheoMa(maNV);
    }
    
    public String getQuyenTheoMa(String ma) {
        int maNV = Integer.parseInt(ma);
        return taiKhoanSQL.getQuyenTheoMa(maNV);
    }
    
    public boolean kiemTraTrungTenDangNhap(String tenDangNhap) {
        return taiKhoanSQL.kiemTraTrungTenDangNhap(tenDangNhap);
    }
    
    public boolean themTaiKhoan(String ma, String tenDangNhap, String phanQuyen){
        int maNV = Integer.parseInt(ma);
        if (tenDangNhap.trim().equals("")){
            Dialog dlg = new Dialog("Không được để trống tên đăng nhập!",Dialog.ERROR_DIALOG);
            return false;
        }
        if (kiemTraTrungTenDangNhap(tenDangNhap)) {
            Dialog dlg = new Dialog("Tên đăng nhập đã có người sử dụng!",Dialog.WARNING_DIALOG);
            return false;
        }
        boolean flag = taiKhoanSQL.themTaiKhoan(maNV, tenDangNhap, phanQuyen);
        if (flag) {
            new Dialog("Cấp tài khoản thành công! Mật khẩu là" + tenDangNhap, Dialog.SUCCESS_DIALOG);
        } else {
            new Dialog("Cấp tài khoản thất bại! Tài khoản đã tồn tại", Dialog.ERROR_DIALOG);
        }
        return flag;
    }
    
    public boolean doiMatKhau(String matKhauCu, String matKhauMoi, String nhapLaiMatKhau){
        if(!matKhauMoi.equals(nhapLaiMatKhau)) {
            new Dialog("Mật khẩu mới không khớp!", Dialog.ERROR_DIALOG);
            return false;
        }
        boolean flag = taiKhoanSQL.doiMatKhau(matKhauCu, matKhauMoi);
        if (flag) {
            new Dialog("Đổi thành công!", Dialog.SUCCESS_DIALOG);
        } else {
            new Dialog("Mật khẩu cũ không đúng!", Dialog.ERROR_DIALOG);
        }
        return flag;
    }
    public ArrayList<TaiKhoan> timTaiKhoan(String tuKhoa) {
        return taiKhoanSQL.timTaiKhoan(tuKhoa);
    }
    
    public void docDanhSach() {
        taiKhoanSQL.docDanhSach();
    }
    
    public boolean updateTaiKhoan(String maTK, String tenDangNhap, String matKhau) {
        int ma = Integer.parseInt(maTK);
        if (tenDangNhap.trim().equals("")) {
            new Dialog("Không được để trống tên đăng nhập!", Dialog.ERROR_DIALOG);
            return false;
        }
        if (matKhau.trim().equals("")) {
            new Dialog("Không được để trống mật khẩu!", Dialog.ERROR_DIALOG);
            return false;
        }
        // Kiểm tra trùng tên đăng nhập với tài khoản khác
        String currentTenDangNhap = taiKhoanSQL.getTenDangNhapTheoMa(ma);
        if (!tenDangNhap.equals(currentTenDangNhap) && kiemTraTrungTenDangNhap(tenDangNhap)) {
            new Dialog("Tên đăng nhập đã có người sử dụng!", Dialog.WARNING_DIALOG);
            return false;
        }
        boolean flag = taiKhoanSQL.updateTaiKhoan(ma, tenDangNhap, matKhau);
        if (flag) {
            new Dialog("Cập nhật tài khoản thành công!", Dialog.SUCCESS_DIALOG);
        } else {
            new Dialog("Cập nhật tài khoản thất bại!", Dialog.ERROR_DIALOG);
        }
        return flag;
    }
    
    public ArrayList<TaiKhoan> getDanhSachTaiKhoan() {
        return taiKhoanSQL.getDanhSachTaiKhoan();
    }
}
