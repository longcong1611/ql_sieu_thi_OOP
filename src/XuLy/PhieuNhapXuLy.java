package XuLy;

import TrungGian.PhieuNhap;
import KetNoiSQL.PhieuNhapSQL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author ngotr
 */
public class PhieuNhapXuLy {
    private PhieuNhapSQL phieunhapSQL = new PhieuNhapSQL();
    private ArrayList<PhieuNhap> listPhieuNhap = null;
    
    public PhieuNhapXuLy(){
        docDanhSach();
    }
    
    public void docDanhSach() {
        this.listPhieuNhap = phieunhapSQL.getListPhieuNhap();
    }
    
    public ArrayList<PhieuNhap> getListPhieuNhap() {
        if (listPhieuNhap == null) {
            docDanhSach();
        }
        return listPhieuNhap;
    }

    public void themPhieuNhap(String MaPN, String maNV, String NhaCungCap, Date Ngaylap, String TongTien) {
        PhieuNhap pn = new PhieuNhap();
        try {
            pn.setMaPN(Integer.parseInt(MaPN));
            pn.setMaNV(Integer.parseInt(maNV));
            pn.setNhaCungCap(NhaCungCap);
            pn.setNgayLap(Ngaylap);
            pn.setTongTien(Integer.parseInt(TongTien));
            themPhieuNhap(pn);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ! Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int getLastID() {
        return phieunhapSQL.getLastID();
    }

    public PhieuNhap timPhieuNhap(String maPN) {
        int ma = Integer.parseInt(maPN);
        for (PhieuNhap pn : listPhieuNhap) {
            if (pn.getMaPN() == ma) {
                return pn;
            }
        }
        return null;
    }

    public ArrayList<PhieuNhap> getListPhieuNhapTheoGia(String giaThap, String giaCao) {
        try {
            int min = Integer.parseInt(giaThap);
            int max = Integer.parseInt(giaCao);
            if (max < min) {
                JOptionPane.showMessageDialog(null, "Hãy nhập khoảng giá phù hợp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            ArrayList<PhieuNhap> result = new ArrayList<>();
            for (PhieuNhap pn : listPhieuNhap) {
                if (pn.getTongTien() <= max && pn.getTongTien() >= min) {
                    result.add(pn);
                }
            }
            return result;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hãy nhập số nguyên cho khoảng giá!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public ArrayList<PhieuNhap> getListPhieuNhapTheoNgay(String tuNgay, String denNgay) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
            
            Date min = sdf.parse(tuNgay);
            Date max = sdf.parse(denNgay);
            if (max.before(min)) {
                JOptionPane.showMessageDialog(null, "Hãy nhập khoảng ngày phù hợp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            ArrayList<PhieuNhap> result = new ArrayList<>();
            for (PhieuNhap pn : listPhieuNhap) {
                if (pn.getNgayLap().after(min) && pn.getNgayLap().before(max)) {
                    result.add(pn);
                }
            }
            return result;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hãy nhập ngày hợp lệ (dd/MM/yyy)!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public boolean xoaPhieuNhap(String MaPN) {
        if (MaPN.equals("")) {
            JOptionPane.showMessageDialog(null, "Chưa chọn mã để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean flag = false;
        try {
            int maPN = Integer.parseInt(MaPN);
            flag = phieunhapSQL.deletePhieuNhap(maPN);
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

    public void capNhatTongTien(int maPN, int tongTienMoi) {
        PhieuNhap pn = phieunhapSQL.getPhieuNhap(maPN);
        if (pn != null) {
            pn.setTongTien(tongTienMoi);
            boolean result = phieunhapSQL.updatePhieuNhap(maPN, pn);
            if (result) {
                docDanhSach();
            }
        }
    }

    public boolean themPhieuNhap(PhieuNhap pn) {
        // Kiểm tra MaPN đã tồn tại
        if (timPhieuNhap(String.valueOf(pn.getMaPN())) != null) {
            JOptionPane.showMessageDialog(null, "Mã PN " + pn.getMaPN() + " đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra MaNV có tồn tại trong bảng nhanvien không
        if (!phieunhapSQL.kiemTraMaNV(pn.getMaNV())) {
            JOptionPane.showMessageDialog(null, "Mã NV " + pn.getMaNV() + " không tồn tại trong bảng nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra độ dài của NhaCungCap
        if (pn.getNhaCungCap() != null && pn.getNhaCungCap().length() > 50) {
            JOptionPane.showMessageDialog(null, "Nhà cung cấp vượt quá độ dài cho phép (50 ký tự)!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra ngày lập không null và hợp lệ
        if (pn.getNgayLap() == null) {
            JOptionPane.showMessageDialog(null, "Ngày lập không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Gọi tầng SQL để thêm phiếu nhập
        boolean result = phieunhapSQL.themPhieuNhap(pn);
        if (result) {
            docDanhSach(); // Cập nhật lại danh sách
        }
        return result;
    }
}