package XuLy;


import TrungGian.PhieuNhap;
import KetNoiSQL.PhieuNhapSQL;
import GiaoDien.Thongbao;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        this.listPhieuNhap=phieunhapSQL.getListPhieuNhap();
    }
    
    public ArrayList<PhieuNhap> getListPhieuNhap() {
        if (listPhieuNhap == null) {
            docDanhSach();
        }
        return listPhieuNhap;
    }

    public boolean themPhieuNhap(String NhaCungCap, String nhanVien, int tongTien) {
        String[] NV = nhanVien.split(" - ");

        int maNV = Integer.parseInt(NV[0]);

        PhieuNhap pn = new PhieuNhap();
        pn.setNhaCungCap(NhaCungCap);
        pn.setMaNV(maNV);
        pn.setTongTien(tongTien);

        return phieunhapSQL.themPhieuNhap(pn);
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
                new Thongbao("Hãy nhập khoảng giá phù hợp!", Thongbao.ERROR_DIALOG);
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
            new Thongbao("Hãy nhập số nguyên cho khoảng giá!", Thongbao.ERROR_DIALOG);
        }
        return null;
    }

    public ArrayList<PhieuNhap> getListPhieuNhapTheoNgay(String tuNgay, String denNgay) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
            
            Date min = sdf.parse(tuNgay);
            Date max = sdf.parse(denNgay);
            if (max.before(min)) {
                new Thongbao("Hãy nhập khoảng ngày phù hợp!", Thongbao.ERROR_DIALOG);
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
            new Thongbao("Hãy nhập ngày hợp lệ (dd/MM/yyy)!", Thongbao.ERROR_DIALOG);
        }
        return null;
    }
    public boolean xoaPhieuNhap (String MaPN) {
        if (MaPN.equals("")) {
            new Thongbao("Chưa chọn mã để xóa!", Thongbao.ERROR_DIALOG);
            return false;
        }
        boolean flag = false;
        try {
            int maPN = Integer.parseInt(MaPN);
            flag = phieunhapSQL.deletePhieuNhap(maPN);
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
