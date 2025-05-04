<<<<<<< HEAD
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
        String sql = "SELECT * FROM hoadon";
        try {          
            Connection conn = ConnectToXampp.conn;
            if (conn == null) {
                new ConnectToXampp(); // tạo kết nối nếu chưa có
                conn = ConnectToXampp.conn;
            }
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt(1));
                hd.setMaKH(rs.getInt(2));
                hd.setMaNV(rs.getInt(3));
                hd.setNgayLap(rs.getDate(4));
                hd.setTongTien(rs.getInt(5));
                danhsachhoadon.add(hd);
            }
        } catch(SQLException e) {
           e.printStackTrace();
        }
        return danhsachhoadon;
    }
    public boolean addHoaDon(HoaDon hd) {
        boolean result = false;
        try {
            String sqll = "UPDATE KhachHang SET TongChiTieu=TongChiTieu" + hd.getTongTien() + "WHERE MaKH=" + hd.getMaKH();
            Statement st = ConnectToXampp.conn.createStatement();
            st.executeUpdate(sqll);
            String sql = "INSERT INTO hoadon(MaHD, MaKH, MaNV, NgayLap, TongTien) VALUES( ?, ?, ?, ?, ?)";
            PreparedStatement prep = ConnectToXampp.conn.prepareStatement(sql);
            prep.setInt(1, hd.getMaHD());
            prep.setInt(2, hd.getMaKH());
            prep.setInt(3, hd.getMaNV());
            prep.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
            prep.setInt(5, hd.getTongTien());
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } return result;
    }
    
    public int getMaHoaDonMoiNhat() {
        try {
            String sql = "SELECT MAX(MaHD) FROM hoadon";
            Statement st = ConnectToXampp.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public ArrayList<HoaDon> getListHoaDon(Date dateMin, Date dateMax) {
        try {
            String sql = "SELECT * FROM hoadon WHERE NgayLap BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setDate(1,dateMin);
            pre.setDate(2,dateMax);
            ResultSet rs = pre.executeQuery();
            
            ArrayList<HoaDon> danhsachhoadon = new ArrayList<>();
            
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt(1));
                hd.setMaKH(rs.getInt(2));
                hd.setMaNV(rs.getInt(3));
                hd.setNgayLap(rs.getDate(4));
                hd.setTongTien(rs.getInt(5));
                danhsachhoadon.add(hd);
            }
            return danhsachhoadon;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
=======
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
        String sql = "SELECT * FROM hoadon";
        try {          
            Connection conn = ConnectToXampp.conn;
            if (conn == null) {
                new ConnectToXampp(); // tạo kết nối nếu chưa có
                conn = ConnectToXampp.conn;
            }
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt(1));
                hd.setMaKH(rs.getInt(2));
                hd.setMaNV(rs.getInt(3));
                hd.setNgayLap(rs.getDate(4));
                hd.setTongTien(rs.getInt(5));
                danhsachhoadon.add(hd);
            }
        } catch(SQLException e) {
           e.printStackTrace();
        }
        return danhsachhoadon;
    }
    public boolean addHoaDon(HoaDon hd) {
        boolean result = false;
        try {
            String sqll = "UPDATE KhachHang SET TongChiTieu=TongChiTieu" + hd.getTongTien() + "WHERE MaKH=" + hd.getMaKH();
            Statement st = ConnectToXampp.conn.createStatement();
            st.executeUpdate(sqll);
            String sql = "INSERT INTO hoadon(MaHD, MaKH, MaNV, NgayLap, TongTien) VALUES( ?, ?, ?, ?, ?)";
            PreparedStatement prep = ConnectToXampp.conn.prepareStatement(sql);
            prep.setInt(1, hd.getMaHD());
            prep.setInt(2, hd.getMaKH());
            prep.setInt(3, hd.getMaNV());
            prep.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
            prep.setInt(5, hd.getTongTien());
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } return result;
    }
    
    public int getMaHoaDonMoiNhat() {
        try {
            String sql = "SELECT MAX(MaHD) FROM hoadon";
            Statement st = ConnectToXampp.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public ArrayList<HoaDon> getListHoaDon(Date dateMin, Date dateMax) {
        try {
            String sql = "SELECT * FROM hoadon WHERE NgayLap BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setDate(1,dateMin);
            pre.setDate(2,dateMax);
            ResultSet rs = pre.executeQuery();
            
            ArrayList<HoaDon> danhsachhoadon = new ArrayList<>();
            
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt(1));
                hd.setMaKH(rs.getInt(2));
                hd.setMaNV(rs.getInt(3));
                hd.setNgayLap(rs.getDate(4));
                hd.setTongTien(rs.getInt(5));
                danhsachhoadon.add(hd);
            }
            return danhsachhoadon;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
>>>>>>> bef1f7b (commit)
