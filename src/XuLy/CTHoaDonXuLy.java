package XuLy;

import TrungGian.CTHoaDon;
import KetNoiSQL.CTHoaDonSQL;
import TrungGian.HoaDon;
import KetNoiSQL.SanPhamSQL;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ngotr
 */
public class CTHoaDonXuLy {
    private ArrayList<CTHoaDon> listCTHoaDon;
    private CTHoaDonSQL ctHDSQL = new CTHoaDonSQL();
    private HoaDonXuLy hdXuLy = new HoaDonXuLy();
    private SanPhamSQL sanPhamSQL = new SanPhamSQL();

    public CTHoaDonXuLy() {
        listCTHoaDon = new ArrayList<>();
        docListCTHoaDon();
    }

    public void docListCTHoaDon() {
        this.listCTHoaDon = ctHDSQL.getListCTHoaDon();
    }

    public ArrayList<CTHoaDon> getListCTHoaDon() {
        if (listCTHoaDon == null) {
            listCTHoaDon = new ArrayList<>();
            docListCTHoaDon();
        }
        return listCTHoaDon;
    }

    public ArrayList<CTHoaDon> getListCTHoaDonTheoMaHD(String maHD) {
        int ma = Integer.parseInt(maHD);
        ArrayList<CTHoaDon> dsct = new ArrayList<>();
        for (CTHoaDon cthd : getListCTHoaDon()) {
            if (cthd.getMaHD() == ma) {
                dsct.add(cthd);
            }
        }
        return dsct;
    }

    public void addCTHoaDon(String MaHD, String maSP, String soLuong, String donGia, String thanhTien) {
        donGia = donGia.replace(",", "");
        thanhTien = thanhTien.replace(",", "");

        CTHoaDon cthd = new CTHoaDon();
        cthd.setMaHD(Integer.parseInt(MaHD));
        cthd.setMaSP(Integer.parseInt(maSP));
        cthd.setDonGia(Integer.parseInt(donGia));
        cthd.setSoLuong(Integer.parseInt(soLuong));
        cthd.setThanhTien(Integer.parseInt(thanhTien));

        ctHDSQL.addCTHoaDon(cthd);
        docListCTHoaDon(); // Cập nhật danh sách sau khi thêm
    }

    public boolean themCTHoaDon(CTHoaDon cthd) {
        // Kiểm tra mã hóa đơn tồn tại
        HoaDon hd = hdXuLy.getHoaDon(String.valueOf(cthd.getMaHD()));
        if (hd == null) {
            JOptionPane.showMessageDialog(null, "Mã HD " + cthd.getMaHD() + " không tồn tại trong bảng hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra mã sản phẩm tồn tại
        if (!kiemTraSanPham(cthd.getMaSP())) {
            JOptionPane.showMessageDialog(null, "Mã SP " + cthd.getMaSP() + " không tồn tại trong bảng sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra số lượng và đơn giá
        if (cthd.getSoLuong() <= 0) {
            JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (cthd.getDonGia() <= 0) {
            JOptionPane.showMessageDialog(null, "Đơn giá phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Tính thành tiền
        cthd.setThanhTien(cthd.getSoLuong() * cthd.getDonGia());

        // Thêm chi tiết hóa đơn
        boolean result = ctHDSQL.addCTHoaDon(cthd);
        if (result) {
            // Tính lại tổng tiền của hóa đơn
            int maHD = cthd.getMaHD();
            int tongTien = 0;
            ArrayList<CTHoaDon> ds = getListCTHoaDonTheoMaHD(String.valueOf(maHD));
            for (CTHoaDon ct : ds) {
                tongTien += ct.getThanhTien();
            }
            hdXuLy.capNhatTongTien(maHD, tongTien); // Cập nhật tổng tiền trong hóa đơn

            docListCTHoaDon(); // Làm mới danh sách chi tiết hóa đơn
            hdXuLy.docDanhSach(); // Làm mới danh sách hóa đơn để đồng bộ
        } else {
        }
        return result;
    }

    private boolean kiemTraSanPham(int maSP) {
        return sanPhamSQL.getSanPhamByMaSP(maSP) != null;
    }
}