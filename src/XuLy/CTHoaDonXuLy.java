package XuLy;

import TrungGian.CTHoaDon;
import KetNoiSQL.CTHoaDonSQL;
import TrungGian.HoaDon;
import java.util.ArrayList;

/**
 *
 * @author ngotr
 */
public class CTHoaDonXuLy {
    private ArrayList<CTHoaDon> listCTHoaDon;
    private CTHoaDonSQL ctHDSQL = new CTHoaDonSQL();
    private HoaDonXuLy hdXuLy = new HoaDonXuLy();

    public CTHoaDonXuLy() {
        docListCTHoaDon();
    }

    public void docListCTHoaDon() {
        this.listCTHoaDon = ctHDSQL.getListCTHoaDon();
    }

    public ArrayList<CTHoaDon> getListCTHoaDon() {
        return listCTHoaDon;
    }

    public ArrayList<CTHoaDon> getListCTHoaDonTheoMaHD(String maHD) {
        int ma = Integer.parseInt(maHD);
        ArrayList<CTHoaDon> dsct = new ArrayList<>();

        for (CTHoaDon cthd : listCTHoaDon) {
            if (cthd.getMaHD() == ma)
                dsct.add(cthd);
        }

        return dsct;
    }

    public void addCTHoaDon(String MaHD, String maSP, String soLuong, String donGia, String thanhTien) {

        donGia = donGia.replace(",","");
        thanhTien = thanhTien.replace(",", "");

        CTHoaDon cthd = new CTHoaDon();

        cthd.setMaHD(Integer.parseInt(MaHD));
        cthd.setMaSP(Integer.parseInt(maSP));
        cthd.setDonGia(Integer.parseInt(donGia));
        cthd.setSoLuong(Integer.parseInt(soLuong));
        cthd.setThanhTien(Integer.parseInt(thanhTien));

        ctHDSQL.addCTHoaDon(cthd);
    }
}
