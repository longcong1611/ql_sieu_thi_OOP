package Controller;

import Bean.Dialog;
import TrungGian.NhanVien;
import KetNoiSQL.NhanVienSQL;

import java.util.ArrayList;

public class NhanVienController {
    private NhanVienSQL nhanVienSQL = new NhanVienSQL();
    private ArrayList<NhanVien> listNhanVien = null;

    public NhanVienController() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listNhanVien = nhanVienSQL.getDanhSachNhanVien();
    }

    public ArrayList<NhanVien> getDanhSachNhanVien() {
        if (this.listNhanVien == null || this.listNhanVien.isEmpty()) {
            docDanhSach();
        }
        return this.listNhanVien;
    }

    public boolean themNhanVien(String hoVaTen, String gioiTinh, String soDienThoai, String chucVu, String diaChi) {
        hoVaTen = hoVaTen.trim();
        chucVu = chucVu.trim();
        soDienThoai = soDienThoai.trim();
        if (hoVaTen.isEmpty()) {
            new Dialog("Họ và tên không được để trống!", Dialog.ERROR_DIALOG);
            return false;
        }
        if (chucVu.isEmpty()) {
            new Dialog("Chức vụ không được để trống!", Dialog.ERROR_DIALOG);
            return false;
        }
        if (soDienThoai.isEmpty()) {
            new Dialog("Số điện thoại không được để trống!", Dialog.ERROR_DIALOG);
            return false;
        }
        if (!soDienThoai.matches("\\d{10,11}")) {
            new Dialog("Số điện thoại phải là 10 hoặc 11 chữ số!", Dialog.ERROR_DIALOG);
            return false;
        }
        NhanVien nv = new NhanVien();
        nv.setHoVaTen(hoVaTen);
        nv.setGioiTinh(gioiTinh);
        nv.setSoDienThoai(soDienThoai);
        nv.setChucVu(chucVu);
        nv.setDiaChi(diaChi);
        boolean flag = nhanVienSQL.themNhanVien(nv);
        if (!flag) {
            new Dialog("Thêm thất bại! Kiểm tra số điện thoại không trùng lặp hoặc lỗi cơ sở dữ liệu.", Dialog.ERROR_DIALOG);
        } else {
            new Dialog("Thêm thành công!", Dialog.SUCCESS_DIALOG);
            docDanhSach();
        }
        return flag;
    }

    public boolean updateNhanVien(String ma, String hoVaTen, String gioiTinh, String soDienThoai, String chucVu, String diaChi) {
        try {
            int maNV = Integer.parseInt(ma);
            hoVaTen = hoVaTen.trim();
            chucVu = chucVu.trim();
            soDienThoai = soDienThoai.trim();
            if (hoVaTen.isEmpty()) {
                new Dialog("Họ và tên không được để trống!", Dialog.ERROR_DIALOG);
                return false;
            }
            if (chucVu.isEmpty()) {
                new Dialog("Chức vụ không được để trống!", Dialog.ERROR_DIALOG);
                return false;
            }
            if (soDienThoai.isEmpty()) {
                new Dialog("Số điện thoại không được để trống!", Dialog.ERROR_DIALOG);
                return false;
            }
            if (!soDienThoai.matches("\\d{10,11}")) {
                new Dialog("Số điện thoại phải là 10 hoặc 11 chữ số!", Dialog.ERROR_DIALOG);
                return false;
            }
            NhanVien nv = new NhanVien();
            nv.setMaNV(maNV);
            nv.setHoVaTen(hoVaTen);
            nv.setGioiTinh(gioiTinh);
            nv.setSoDienThoai(soDienThoai);
            nv.setChucVu(chucVu);
            nv.setDiaChi(diaChi);
            boolean flag = nhanVienSQL.updateNhanVien(nv);
            if (!flag) {
                new Dialog("Cập nhật thất bại! Kiểm tra mã nhân viên tồn tại hoặc số điện thoại không trùng lặp.", Dialog.ERROR_DIALOG);
            } else {
                new Dialog("Cập nhật thành công!", Dialog.SUCCESS_DIALOG);
                docDanhSach();
            }
            return flag;
        } catch (NumberFormatException e) {
            new Dialog("Mã nhân viên không hợp lệ!", Dialog.ERROR_DIALOG);
            return false;
        } catch (Exception e) {
            System.err.println("Exception in updateNhanVien: " + e.getMessage());
            e.printStackTrace();
            new Dialog("Lỗi khi cập nhật nhân viên: " + e.getMessage(), Dialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean xoaNhanVien(String ma) {
        try {
            int maNV = Integer.parseInt(ma);
            Dialog dlg = new Dialog("Bạn có chắc chắn muốn xoá?", Dialog.WARNING_DIALOG);
            boolean flag = false;
            if (dlg.getAction() == Dialog.OK_OPTION) {
                flag = nhanVienSQL.deleteNhanVien(maNV);
                if (flag) {
                    new Dialog("Xoá thành công!", Dialog.SUCCESS_DIALOG);
                    docDanhSach();
                } else {
                    new Dialog("Xoá thất bại!", Dialog.ERROR_DIALOG);
                }
            }
            return flag;
        } catch (Exception e) {
            new Dialog("Chưa chọn nhân viên!", Dialog.ERROR_DIALOG);
            return false;
        }
    }

    public ArrayList<NhanVien> timNhanVien(String tuKhoa) {
        tuKhoa = tuKhoa.toLowerCase();
        ArrayList<NhanVien> dsnv = new ArrayList<>();
        for (NhanVien nv : getDanhSachNhanVien()) {
            if (nv.getHoVaTen().toLowerCase().contains(tuKhoa) || nv.getSoDienThoai().toLowerCase().contains(tuKhoa) ||
                nv.getGioiTinh().toLowerCase().contains(tuKhoa) || nv.getChucVu().toLowerCase().contains(tuKhoa) || nv.getDiaChi().toLowerCase().contains(tuKhoa)) {
                dsnv.add(nv);
            }
        }
        return dsnv;
    }
}