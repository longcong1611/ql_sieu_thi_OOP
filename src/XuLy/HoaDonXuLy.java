package XuLy;

import TrungGian.HoaDon;
import KetNoiSQL.HoaDonSQL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author ngotr
 */
public class HoaDonXuLy {
    private ArrayList<HoaDon> listHoaDon;
    private HoaDonSQL hoaDonSQL = new HoaDonSQL();
    
    public HoaDonXuLy() {
        listHoaDon = new ArrayList<>();
        docDanhSach(); // Nạp dữ liệu ngay khi khởi tạo
    }

    void docDanhSach() {
        listHoaDon = hoaDonSQL.getListHoaDon();
    }

    public ArrayList<HoaDon> getListHoaDon() {
        if (listHoaDon == null) {
            listHoaDon = new ArrayList<>();
            docDanhSach();
        }
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
        docDanhSach();
    }

    public int getMaHoaDonMoiNhat() {
        return hoaDonSQL.getMaHoaDonMoiNhat();
    }

    public HoaDon getHoaDon(String maHD) {
        if (listHoaDon == null || listHoaDon.isEmpty()) {
            docDanhSach(); // Nạp lại dữ liệu nếu danh sách trống
        }
        int ma = Integer.parseInt(maHD);
        for (HoaDon hd : listHoaDon) {
            if (hd.getMaHD() == ma) {
                return hd;
            }
        }
        return null;
    }

    public ArrayList<HoaDon> getListHoaDonTheoGia(String min, String max) {
        ArrayList<HoaDon> danhsachhoadon = new ArrayList<>();
        try {
            int minPrice = Integer.parseInt(min.trim());
            int maxPrice = Integer.parseInt(max.trim());
            if (minPrice > maxPrice) {
                JOptionPane.showMessageDialog(null, "Giá tối thiểu phải nhỏ hơn hoặc bằng giá tối đa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return danhsachhoadon;
            }
            for (HoaDon hd : listHoaDon) {
                if (hd.getTongTien() >= minPrice && hd.getTongTien() <= maxPrice)
                    danhsachhoadon.add(hd);
            }
            return danhsachhoadon;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Hãy nhập khoảng giá hợp lệ (số nguyên)!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return danhsachhoadon;
        }
    }

    public ArrayList<HoaDon> getListHoaDonTheoNgay(String min, String max) {
        ArrayList<HoaDon> danhsachhoadon = new ArrayList<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false); // Không cho phép ngày không hợp lệ (ví dụ: 31/04/2025)
            Date minDate = sdf.parse(min.trim());
            Date maxDate = sdf.parse(max.trim());

            if (minDate.after(maxDate)) {
                JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải trước ngày kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return danhsachhoadon;
            }

            java.sql.Date dateMin = new java.sql.Date(minDate.getTime());
            java.sql.Date dateMax = new java.sql.Date(maxDate.getTime());

            danhsachhoadon = hoaDonSQL.getListHoaDon(dateMin, dateMax);
            if (danhsachhoadon == null) {
                return new ArrayList<>();
            }
            return danhsachhoadon;
        } catch (java.text.ParseException e) {
            JOptionPane.showMessageDialog(null, "Định dạng ngày không hợp lệ (dd/MM/yyyy)!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return danhsachhoadon;
        }
    }

    public void addHoaDon(String MaHD, String maKH, String MaNV, String TongTien) {
        HoaDon cthd = new HoaDon();

        cthd.setMaHD(Integer.parseInt(MaHD));
        cthd.setMaKH(Integer.parseInt(maKH));
        cthd.setMaNV(Integer.parseInt(MaNV));
        cthd.setTongTien(Integer.parseInt(TongTien));
        docDanhSach();

        hoaDonSQL.addHoaDon(cthd);
    }

    public boolean themHoaDon(HoaDon hd) {
        boolean result = hoaDonSQL.addHoaDon(hd);
        if (result) {
            docDanhSach(); // Cập nhật danh sách hóa đơn
            JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm hóa đơn thất bại! Vui lòng kiểm tra dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    public void capNhatTongTien(int maHD, int tongTien) {
        HoaDon hd = getHoaDon(String.valueOf(maHD));
        if (hd != null) {
            hd.setTongTien(tongTien);
            hoaDonSQL.updateHoaDon(maHD, hd);
            docDanhSach(); // Cập nhật danh sách sau khi cập nhật
        }
    }
}