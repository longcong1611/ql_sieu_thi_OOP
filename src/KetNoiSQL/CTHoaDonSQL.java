package KetNoiSQL;

import TrungGian.CTHoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ngotr
 */
public class CTHoaDonSQL {
    public ArrayList<CTHoaDon> getListCTHoaDon() {
        ArrayList<CTHoaDon> danhsachcthoadon = new ArrayList<>();
        
        String sql = "SELECT * FROM cthoadon";
        try {
            Connection conn = ConnectToXampp.conn;
            if (conn == null) {
                new ConnectToXampp(); // tạo kết nối nếu chưa có
                conn = ConnectToXampp.conn;
            }
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                CTHoaDon cthoadon = new CTHoaDon();
                cthoadon.setMaHD(rs.getInt(1));
                cthoadon.setMaSP(rs.getInt(2));
                cthoadon.setSoLuong(rs.getInt(3));
                cthoadon.setDonGia(rs.getInt(4));
                cthoadon.setThanhTien(rs.getInt(5));
                danhsachcthoadon.add(cthoadon);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return danhsachcthoadon;
    }

    public ArrayList<CTHoaDon> getListCTHoaDonTheoMaHD(int maHD) {
        ArrayList<CTHoaDon> danhsachcthoadon = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cthoadon WHERE MaHD="+maHD;
            Statement stmt = ConnectToXampp.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                CTHoaDon cthoadon = new CTHoaDon();
                cthoadon.setMaHD(rs.getInt(1));
                cthoadon.setMaSP(rs.getInt(2));
                cthoadon.setSoLuong(rs.getInt(3));
                cthoadon.setDonGia(rs.getInt(4));
                cthoadon.setThanhTien(rs.getInt(5));
                danhsachcthoadon.add(cthoadon);
            }
        } catch(SQLException ex) {
            return null;
        }
        return danhsachcthoadon;
    }

    public ArrayList<CTHoaDon> getListCTHoaDonTheoMaSP(int maSP) {
        ArrayList<CTHoaDon> danhsachcthoadon = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cthoadon WHERE MaSP="+maSP;
            Statement stmt = ConnectToXampp.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                CTHoaDon cthoadon = new CTHoaDon();
                cthoadon.setMaHD(rs.getInt(1));
                cthoadon.setMaSP(rs.getInt(2));
                cthoadon.setSoLuong(rs.getInt(3));
                cthoadon.setDonGia(rs.getInt(4));
                cthoadon.setThanhTien(rs.getInt(5));
                danhsachcthoadon.add(cthoadon);
            }
        } catch(SQLException ex) {
            return null;
        }
        return danhsachcthoadon;
    }

    public boolean addCTHoaDon(CTHoaDon cthd) {
        boolean result = false;
        try {
            String sql = "INSERT INTO cthoadon VALUES(?,?,?,?,?)";
            PreparedStatement prep = ConnectToXampp.conn.prepareStatement(sql);
            prep.setInt(1, cthd.getMaHD());
            prep.setInt(2, cthd.getMaSP());
            prep.setInt(3, cthd.getSoLuong());
            prep.setInt(4, cthd.getDonGia());
            prep.setInt(5, cthd.getThanhTien());
            result = prep.executeUpdate() > 0;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
    }

    public boolean deleteCTHoaDon(int maHD, int maSP) {
        boolean result = false;
        try {
            String sql = "DELETE FROM cthoadon WHERE MaHD="+maHD+" AND MaSP="+maSP;
            Statement stmt = ConnectToXampp.conn.createStatement();
            result = stmt.executeUpdate(sql) > 0;
        } catch(SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean deleteCTHoaDon(int maHD) {
        boolean result = false;
        try {
            String sql = "DELETE FROM cthoadon WHERE MaHD="+maHD;
            Statement stmt = ConnectToXampp.conn.createStatement();
            result = stmt.executeUpdate(sql) > 0;
        } catch(SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean updateCTHoaDon(int maHD, int maSP, CTHoaDon cthd) {
        boolean result = false;
        try {
            String sql = "UPDATE cthoadon SET MaHD=?, MaSP=?, SoLuong=?, DonGia=? ThanhTien=? "
                    + "WHERE MaHD=? AND MaSP=?";
            PreparedStatement prep = ConnectToXampp.conn.prepareStatement(sql);
            prep.setInt(1, cthd.getMaHD());
            prep.setInt(2, cthd.getMaSP());
            prep.setInt(3, cthd.getSoLuong());
            prep.setInt(4, cthd.getDonGia());
            prep.setInt(5, cthd.getThanhTien());
            prep.setInt(6, maHD);
            prep.setInt(7, maSP);
            result = prep.executeUpdate() > 0;
        } catch(SQLException ex) {
            return false;
        }
        return result;
    }
}
