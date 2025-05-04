<<<<<<< HEAD
package XuLy;

import TrungGian.HoaDon;
import KetNoiSQL.HoaDonSQL;
import GiaoDien.Thongbao;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author ngotr
 */
public class HoaDonXuLy {
    private ArrayList<HoaDon> listHoaDon;
    private HoaDonSQL hoaDonSQL = new HoaDonSQL();

    public ArrayList<HoaDon> getListHoaDon() {
        listHoaDon = hoaDonSQL.getListHoaDon();
        return listHoaDon;
    }

    public void luuHoaDon(int maKH, String nhanVien, int tongTien) {
        HoaDon hd = new HoaDon();
        String[] arrNV = nhanVien.split(" - ");
        int maNV = Integer.parseInt(arrNV[0]);
        hd.setMaNV(maNV);
        hd.setMaKH(maKH);
        hd.setTongTien(tongTien);

        hoaDonSQL.addHoaDon(hd);
    }

    public int getMaHoaDonMoiNhat() {
        return hoaDonSQL.getMaHoaDonMoiNhat();
    }

    public HoaDon getHoaDon(String maHD) {
        int ma = Integer.parseInt(maHD);
        for (HoaDon hd : listHoaDon) {
            if (hd.getMaHD() == ma)
                return hd;
        }
        return null;
    }

    public ArrayList<HoaDon> getListHoaDonTheoGia(String min, String max) {
        try {
            int minPrice = Integer.parseInt(min);
            int maxPrice = Integer.parseInt(max);
            ArrayList<HoaDon> danhsachhoadon = new ArrayList<>();
            for (HoaDon hd : listHoaDon) {
                if (hd.getTongTien() > minPrice && hd.getTongTien() < maxPrice)
                    danhsachhoadon.add(hd);
            }
            return danhsachhoadon;
        } catch (Exception e) {
            new Thongbao("Hãy nhập khoảng giá hợp lệ", Thongbao.ERROR_DIALOG);
        }
        return null;
    }

    public ArrayList<HoaDon> getListHoaDonTheoNgay(String min, String max) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date minDate = sdf.parse(min);
            Date maxDate = sdf.parse(max);

            java.sql.Date dateMin = new java.sql.Date(minDate.getTime());
            java.sql.Date dateMax = new java.sql.Date(maxDate.getTime());

            ArrayList<HoaDon> danhsachhoadon = hoaDonSQL.getListHoaDon(dateMin, dateMax);
            return danhsachhoadon;
        } catch (Exception e) {
            new Thongbao("Hãy nhập khoảng ngày hợp lệ!", Thongbao.ERROR_DIALOG);
        }
        return null;
    }
    public void addHoaDon(String MaHD, String maKH, String MaNV, String TongTien) {


        HoaDon cthd = new HoaDon();

        cthd.setMaHD(Integer.parseInt(MaHD));
        cthd.setMaKH(Integer.parseInt(maKH));
        cthd.setMaNV(Integer.parseInt(MaNV));
        cthd.setTongTien(Integer.parseInt(TongTien));

        hoaDonSQL.addHoaDon(cthd);
    }
}
=======
package XuLy;

import TrungGian.HoaDon;
import KetNoiSQL.HoaDonSQL;
import GiaoDien.Thongbao;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author ngotr
 */
public class HoaDonXuLy {
    private ArrayList<HoaDon> listHoaDon;
    private HoaDonSQL hoaDonSQL = new HoaDonSQL();

    public ArrayList<HoaDon> getListHoaDon() {
        listHoaDon = hoaDonSQL.getListHoaDon();
        return listHoaDon;
    }

    public void luuHoaDon(int maKH, String nhanVien, int tongTien) {
        HoaDon hd = new HoaDon();
        String[] arrNV = nhanVien.split(" - ");
        int maNV = Integer.parseInt(arrNV[0]);
        hd.setMaNV(maNV);
        hd.setMaKH(maKH);
        hd.setTongTien(tongTien);

        hoaDonSQL.addHoaDon(hd);
    }

    public int getMaHoaDonMoiNhat() {
        return hoaDonSQL.getMaHoaDonMoiNhat();
    }

    public HoaDon getHoaDon(String maHD) {
        int ma = Integer.parseInt(maHD);
        for (HoaDon hd : listHoaDon) {
            if (hd.getMaHD() == ma)
                return hd;
        }
        return null;
    }

    public ArrayList<HoaDon> getListHoaDonTheoGia(String min, String max) {
        try {
            int minPrice = Integer.parseInt(min);
            int maxPrice = Integer.parseInt(max);
            ArrayList<HoaDon> danhsachhoadon = new ArrayList<>();
            for (HoaDon hd : listHoaDon) {
                if (hd.getTongTien() > minPrice && hd.getTongTien() < maxPrice)
                    danhsachhoadon.add(hd);
            }
            return danhsachhoadon;
        } catch (Exception e) {
            new Thongbao("Hãy nhập khoảng giá hợp lệ", Thongbao.ERROR_DIALOG);
        }
        return null;
    }

    public ArrayList<HoaDon> getListHoaDonTheoNgay(String min, String max) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date minDate = sdf.parse(min);
            Date maxDate = sdf.parse(max);

            java.sql.Date dateMin = new java.sql.Date(minDate.getTime());
            java.sql.Date dateMax = new java.sql.Date(maxDate.getTime());

            ArrayList<HoaDon> danhsachhoadon = hoaDonSQL.getListHoaDon(dateMin, dateMax);
            return danhsachhoadon;
        } catch (Exception e) {
            new Thongbao("Hãy nhập khoảng ngày hợp lệ!", Thongbao.ERROR_DIALOG);
        }
        return null;
    }
    public void addHoaDon(String MaHD, String maKH, String MaNV, String TongTien) {


        HoaDon cthd = new HoaDon();

        cthd.setMaHD(Integer.parseInt(MaHD));
        cthd.setMaKH(Integer.parseInt(maKH));
        cthd.setMaNV(Integer.parseInt(MaNV));
        cthd.setTongTien(Integer.parseInt(TongTien));

        hoaDonSQL.addHoaDon(cthd);
    }
}
>>>>>>> bef1f7b (commit)
