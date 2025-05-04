package KetNoiSQL;


import TrungGian.PhieuNhap;
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
public class PhieuNhapSQL {
    public ArrayList<PhieuNhap> getListPhieuNhap() {
        ArrayList<PhieuNhap> danhsachphieunhap = new ArrayList<>();
        String sql = "SELECT * FROM phieunhap";
        try {
            
            Connection conn = ConnectToXampp.conn;
            if (conn == null) {
                new ConnectToXampp(); // tạo kết nối nếu chưa có
                conn = ConnectToXampp.conn;
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                PhieuNhap pn = new PhieuNhap();
                pn.setMaPN(rs.getInt(1));
                pn.setMaNV(rs.getInt(2));
                pn.setNhaCungCap(rs.getString(3));
                pn.setNgayLap(rs.getDate(4));
                pn.setTongTien(rs.getInt(5));
                danhsachphieunhap.add(pn);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return danhsachphieunhap;
    }
    public boolean themPhieuNhap(PhieuNhap pn) {
        boolean result = false;
        try {
            String sql = "INSERT INTO phieunhap(MaPN, MaNV, NCC, NgayLap, TongTien) "
                    + "VALUES(?, ?,?,?,?)";
            PreparedStatement prep = ConnectToXampp.conn.prepareStatement(sql);
            prep.setInt(1, pn.getMaPN());
            prep.setInt(2, pn.getMaNV());
            prep.setString(3, pn.getNhaCungCap());
            prep.setDate(4, new java.sql.Date(pn.getNgayLap().getTime()));
            prep.setInt(5, 0);
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
    }
    public PhieuNhap getPhieuNhap(int maPN) {
        PhieuNhap pn = null;
        try {
            String sql = "SELECT * FROM phieunhap WHERE MaPN = ?";
            PreparedStatement stmt = ConnectToXampp.conn.prepareStatement(sql);
            stmt.setInt(1, maPN);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pn = new PhieuNhap();
                pn.setMaPN(rs.getInt(1));
                pn.setMaNV(rs.getInt(2));
                pn.setNhaCungCap(rs.getString(3));
                pn.setNgayLap(rs.getDate(4));
                pn.setTongTien(rs.getInt(5));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return pn;
    }
    public boolean deletePhieuNhap(int maPN) {
        boolean result = false;
        try {
            String sql = "DELETE FROM phieunhap WHERE MaPN = ?";
            PreparedStatement stmt = ConnectToXampp.conn.prepareStatement(sql);
            stmt.setInt(1, maPN);
            result = stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
    }
    
    public boolean updatePhieuNhap(int maPN, PhieuNhap pn) {
        boolean result = false;
        try {
            String sql = "UPDATE phieunhap SET MaPN=?, MaNV=?, NCC=?, NgayLap=?, TongTien=? "
                    + "WHERE MaPN=" + maPN;
            PreparedStatement prep = ConnectToXampp.conn.prepareStatement(sql);
            prep.setInt(1, pn.getMaPN());
            prep.setInt(2, pn.getMaNV());
            prep.setString(3, pn.getNhaCungCap());
            prep.setDate(4, new java.sql.Date(pn.getNgayLap().getTime()));
            prep.setInt(5, pn.getTongTien());
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
    }
    public int getLastID() {
        try {
            String sql = "SELECT MAX(maPN) FROM phieunhap";
            Statement st = ConnectToXampp.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
        public boolean kiemTraMaNV(int maNV) {
        try {
            Connection conn = ConnectToXampp.conn;
            if (conn == null) {
                new ConnectToXampp();
                conn = ConnectToXampp.conn;
            }
            if (conn == null) {
                System.err.println("Không thể kết nối tới cơ sở dữ liệu!");
                return false;
            }

            String sql = "SELECT * FROM nhanvien WHERE MaNV = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, maNV);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Nếu có dòng dữ liệu, MaNV tồn tại
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
