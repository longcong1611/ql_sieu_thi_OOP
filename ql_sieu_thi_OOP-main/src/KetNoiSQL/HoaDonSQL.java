package KetNoiSQL;

import TrungGian.HoaDon;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ngotr
 */
public class HoaDonSQL {
    public ArrayList<HoaDon> getListHoaDon() {
        ArrayList<HoaDon> danhsachhoadon = new ArrayList<>();
        try {
            String sql = "SELECT * FROM hoadon";
            Statement stmt = ConnectToXampp.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString(1));
                hd.setMaKH(rs.getString(2));
                hd.setMaNV(rs.getString(3));
                hd.setNgayLap(rs.getDate(4));
                hd.setTongTien(rs.getInt(5));
                danhsachhoadon.add(hd);
            }
        } catch(SQLException ex) {
            return null;
        }
        return danhsachhoadon;
    }
    public boolean addHoaDon(HoaDon hd) {
        boolean result = false;
        try {
            String sqll = "UPDATE KhachHang SET TongChiTieu=TongChiTieu" + hd.getTongTien() + "WHERE MaKH=" + hd.getMaKH();
            Statement st = ConnectToXampp.conn.createStatement();
            st.executeUpdate(sqll);
            String sql = "INSERT INTO hoadon(MaKH, MaNV, NgayLap, TongTien) VALUES(?, ?, ?, ?)";
            PreparedStatement prep = ConnectToXampp.conn.prepareStatement(sql);
            prep.setString(1, hd.getMaKH());
            prep.setString(2, hd.getMaNV());
            prep.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
            prep.setInt(4, hd.getTongTien());
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } return result;
    }
    
}
