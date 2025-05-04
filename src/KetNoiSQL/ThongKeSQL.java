// ThongKeSQL.java (package KetNoiSQL)
package KetNoiSQL;

import static KetNoiSQL.ConnectToXampp.conn;
import TrungGian.ThongKe;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp thực thi truy vấn thống kê doanh thu theo ngày
 */
public class ThongKeSQL {
    /**
     * Trả về danh sách doanh thu của từng hóa đơn trong khoảng ngày
     * @param tuNgay  java.util.Date ngày bắt đầu
     * @param denNgay java.util.Date ngày kết thúc
     * @return List<ThongKe> (ngày, mã hóa đơn, tổng tiền, mã NV, tên NV)
     */
    public List<ThongKe> getThongKeTheoNgay(java.util.Date tuNgay, java.util.Date denNgay) {
        List<ThongKe> list = new ArrayList<>();
        String sql =
            "SELECT hd.NgayLap, hd.MaHD, SUM(ct.SoLuong * ct.DonGia) AS TongTien, " +
            "       nv.MaNV, nv.TenNV " +
            "FROM hoadon hd " +
            "JOIN cthoadon ct ON hd.MaHD = ct.MaHD " +
            "JOIN nhanvien nv ON hd.MaNV = nv.MaNV " +
            "WHERE hd.NgayLap BETWEEN ? AND ? " +
            "GROUP BY hd.NgayLap, hd.MaHD, nv.MaNV, nv.TenNV " +
            "ORDER BY hd.NgayLap";
        try (PreparedStatement ps = ConnectToXampp.conn.prepareStatement(sql)) {
            // truyền tham số kiểu DATE
            ps.setDate(1, new java.sql.Date(tuNgay.getTime()));
            ps.setDate(2, new java.sql.Date(denNgay.getTime()));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new ThongKe(
                        rs.getDate("NgayLap"),
                        rs.getInt("MaHD"),
                        rs.getInt("TongTien"),
                        rs.getInt("MaNV"),
                        rs.getString("TenNV")
                    ));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list; // luôn trả về list, không null
    }

    public List<ThongKe> getThongKeTheoTen(String tenNV){
        List<ThongKe> list = new ArrayList<>();
        String sql =
            "SELECT hd.NgayLap, hd.MaHD, SUM(ct.SoLuong * ct.DonGia) AS TongTien, " +
            "       nv.MaNV, nv.TenNV " +
            "FROM hoadon hd " +
            "JOIN cthoadon ct ON hd.MaHD = ct.MaHD " +
            "JOIN nhanvien nv ON hd.MaNV = nv.MaNV " +
            "WHERE nv.TenNV = ? " +
            "GROUP BY hd.NgayLap, hd.MaHD, nv.MaNV, nv.TenNV " +
            "ORDER BY hd.NgayLap";
        try (PreparedStatement ps = ConnectToXampp.conn.prepareStatement(sql)) {
            ps.setString(1, tenNV);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new ThongKe(
                        rs.getDate("NgayLap"),
                        rs.getInt("MaHD"),
                        rs.getInt("TongTien"),
                        rs.getInt("MaNV"),
                        rs.getString("TenNV")
                    ));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
}
        
public List<ThongKe> getThongKeSanPham(java.util.Date tuNgay, java.util.Date denNgay, String tenLoai) {
    List<ThongKe> list = new ArrayList<>();
    String sql = 
    "SELECT " +
    "    '0000-00-00' AS NgayLap, " +
    "    sp.MaSP, " +
    "    sp.TenSP, " +
    "    sp.TongSL AS SoLuongConLai, " +

    "    IFNULL(( " +
    "        SELECT SUM(cthd.SoLuong) " +
    "        FROM cthoadon cthd " +
    "        JOIN hoadon hd ON cthd.MaHD = hd.MaHD " +
    "        WHERE cthd.MaSP = sp.MaSP AND hd.NgayLap BETWEEN ? AND ? " +
    "    ), 0) AS SoLuongBanRa, " +

    "    IFNULL(( " +
    "        SELECT SUM(ctnh.SoLuong) " +
    "        FROM ctphieunhap ctnh " +
    "        JOIN phieunhap pnh ON ctnh.MaPN = pnh.MaPN " +
    "        WHERE ctnh.MaSP = sp.MaSP AND pnh.NgayLap BETWEEN ? AND ? " +
    "    ), 0) AS TongNhap " +

    "FROM sanpham sp " +
    "WHERE sp.LoaiSP = ?";




    try (PreparedStatement ps = ConnectToXampp.conn.prepareStatement(sql)) {
        
            ps.setDate(1, new java.sql.Date(tuNgay.getTime())); // từ ngày bán
            ps.setDate(2, new java.sql.Date(denNgay.getTime())); // đến ngày bán
            ps.setDate(3, new java.sql.Date(tuNgay.getTime())); // từ ngày nhập
            ps.setDate(4, new java.sql.Date(denNgay.getTime())); // đến ngày nhập
            ps.setString(5, tenLoai); // loại sản phẩm từ jComboBox2
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new ThongKe(
                    null,
                    rs.getInt("MaSP"),
                    rs.getString("TenSP"),
                    rs.getInt("TongNhap"),
                    rs.getInt("SoLuongBanRa"),
                    rs.getInt("SoLuongConLai")
                ));
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return list;
}

}