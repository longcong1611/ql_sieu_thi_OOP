package XuLy;

import TrungGian.CTPhieuNhap;
import TrungGian.PhieuNhap;
import GiaoDien.Thongbao;
import KetNoiSQL.CTPhieuNhapSQL;
import KetNoiSQL.PhieuNhapSQL;
import java.util.ArrayList;

/**
 *
 * @author ngotr
 */
public class CTPhieuNhapXuLy {
    private ArrayList<CTPhieuNhap> listPhieuNhap = null;
    private CTPhieuNhapSQL ctPhieuNhapSQL = new CTPhieuNhapSQL();

    public CTPhieuNhapXuLy() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listPhieuNhap = ctPhieuNhapSQL.getListCTPhieuNhap();
    }

    public ArrayList<CTPhieuNhap> getListPhieuNhap() {
        if (listPhieuNhap == null) {
            docDanhSach();
        }
        return listPhieuNhap;
    }
    
    public ArrayList<CTPhieuNhap> getListPhieuNhap(String maPN) {
        ArrayList<CTPhieuNhap> dsct = new ArrayList<>();
        int ma = Integer.parseInt(maPN);
        
        for(CTPhieuNhap ct: listPhieuNhap) {
            if(ct.getMaPN() == ma) {
                dsct.add(ct);
            }
        }
        
        return dsct;
    }

    public boolean luuCTPhieuNhap(CTPhieuNhap ctpn) {
        return ctPhieuNhapSQL.addCTPhieuNhap(ctpn);
    }
    
    public boolean suaCTPhieuNhap(int maPN, CTPhieuNhap ctpn) {
        boolean result = ctPhieuNhapSQL.updateCTPhieuNhap(maPN, ctpn);
        if (result) {
            int tongTien = 0;
            ArrayList<CTPhieuNhap> ds = ctPhieuNhapSQL.getListCTPhieuNhapTheoMaPN(maPN);
            for (CTPhieuNhap ct : ds) {
                tongTien += ct.getThanhTien();
            }

            PhieuNhapSQL pnSQL = new PhieuNhapSQL();
            PhieuNhap pn = pnSQL.getPhieuNhap(maPN);
            if (pn != null) {
                pn.setTongTien(tongTien);
                pnSQL.updatePhieuNhap(maPN, pn);
            }

            docDanhSach();
        } else {
            new Thongbao("Lỗi khi sửa chi tiết phiếu nhập!", Thongbao.ERROR_DIALOG);
        }
        return result;
    }
    
    public boolean xoaCTPhieuNhap(int maPN, int maSP) {
    boolean result = ctPhieuNhapSQL.deleteCTPhieuNhap(maPN, maSP);
    if (result) {
        int tongTien = 0;
        ArrayList<CTPhieuNhap> ds = ctPhieuNhapSQL.getListCTPhieuNhapTheoMaPN(maPN);
        for (CTPhieuNhap ct : ds) {
            tongTien += ct.getThanhTien();
        }

        PhieuNhapSQL pnSQL = new PhieuNhapSQL();
        PhieuNhap pn = pnSQL.getPhieuNhap(maPN);
        if (pn != null) {
            pn.setTongTien(tongTien);
            pnSQL.updatePhieuNhap(maPN, pn);
        }

        docDanhSach();
    } else {
        new Thongbao("Xóa chi tiết phiếu nhập thất bại!", Thongbao.ERROR_DIALOG);
    }
    return result;
    }
        public void themCTPhieuNhap(String MaPN, String MaSP, String SoLuong, String DonGia, String ThanhTien) {
            
            CTPhieuNhap ctpn = new CTPhieuNhap();
            ctpn.setMaPN(Integer.parseInt(MaPN));
            ctpn.setMaSP(Integer.parseInt(MaSP));
            ctpn.setSoLuong(Integer.parseInt(SoLuong));
            ctpn.setDonGia(Integer.parseInt(DonGia));
            ctpn.setThanhTien(Integer.parseInt(ThanhTien));

            ctPhieuNhapSQL.addCTPhieuNhap(ctpn);
        }

        public boolean themCTPhieuNhap(CTPhieuNhap ctpn) {
        boolean result = ctPhieuNhapSQL.addCTPhieuNhap(ctpn);
        if (result) {
            int maPN = ctpn.getMaPN();
            int tongTien = 0;
            ArrayList<CTPhieuNhap> ds = ctPhieuNhapSQL.getListCTPhieuNhapTheoMaPN(maPN);
            for (CTPhieuNhap ct : ds) {
                tongTien += ct.getThanhTien();
            }

            PhieuNhapSQL pnSQL = new PhieuNhapSQL();
            PhieuNhap pn = pnSQL.getPhieuNhap(maPN);
            if (pn != null) {
                pn.setTongTien(tongTien);
                pnSQL.updatePhieuNhap(maPN, pn);
            }

            docDanhSach();
        }
        return result;
    }
}
