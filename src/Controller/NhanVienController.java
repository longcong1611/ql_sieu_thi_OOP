package Controller;

import Bean.Dialog;
import Bean.NhanVien;
import Controller.NhanVienController;
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
    
    public ArrayList<NhanVien> getDanhNhanVien(){
        if(this.listNhanVien == null)
            docDanhSach();
        return this.listNhanVien;
    }
    
    public boolean themNhanVien(String hoVaTen, String gioiTinh, String soDienThoai, String chucVu, String diaChi){
        hoVaTen = hoVaTen.trim();
        chucVu = chucVu.trim();
        if (hoVaTen.equals("")){
            new Dialog("Họ và tên không được để trống!", Dialog.ERROR_DIALOG);
            return false;
        }
        if (chucVu.equals("")){
            new Dialog("Chức vụ không được để trống", Dialog.ERROR_DIALOG);
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
            new Dialog("Thêm thất bại!", Dialog.ERROR_DIALOG);
        } else {
            new Dialog("Thêm thành công!", Dialog.SUCCESS_DIALOG);
        }
        return flag;
    }
    
    public boolean updateNhanVien(String ma, String hoVaTen, String gioiTinh, String soDienThoai, String chucVu, String diaChi){
        int maNV = Integer.parseInt(ma);
        hoVaTen = hoVaTen.trim();
        chucVu = chucVu.trim();
        if (hoVaTen.equals("")){
            new Dialog("Họ và tên không được để trống!", Dialog.ERROR_DIALOG);
            return false;
        }
        if (chucVu.equals("")){
            new Dialog("Chức vụ không được để trống", Dialog.ERROR_DIALOG);
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
            new Dialog("Cập nhật thất bại!", Dialog.ERROR_DIALOG);
        } else {
            new Dialog("Cập nhật thành công!", Dialog.SUCCESS_DIALOG);
        }
        return flag;
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
                } else {
                    new Dialog("Xoá thất bại!", Dialog.ERROR_DIALOG);
                }
            }
            return flag;
        } catch (Exception e) {
            new Dialog("Chưa chọn nhân viên!", Dialog.ERROR_DIALOG);
        }
        return false;
    }    
    
    public ArrayList<NhanVien> timNhanVien(String tuKhoa) {
        tuKhoa = tuKhoa.toLowerCase();
        ArrayList<NhanVien> dsnv = new ArrayList<>();
        for (NhanVien nv : listNhanVien) {
            if (nv.getHoVaTen().toLowerCase().contains(tuKhoa) || nv.getSoDienThoai().toLowerCase().contains(tuKhoa) ||
                    nv.getGioiTinh().toLowerCase().contains(tuKhoa) || nv.getChucVu().toLowerCase().contains(tuKhoa) || nv.getDiaChi().toLowerCase().contains(tuKhoa)) {
                dsnv.add(nv);
            }
        }
        return dsnv;
    }
    
    public ArrayList<NhanVien> getDanhSachNhanVien() {
        if (this.listNhanVien == null)
            docDanhSach();
        return this.listNhanVien;
    }
}
