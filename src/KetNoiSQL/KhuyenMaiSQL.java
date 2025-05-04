<<<<<<< HEAD
package KetNoiSQL;

import KetNoiSQL.ConnectToXampp;
import TrungGian.KhuyenMai;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ngotr
 */
public class KhuyenMaiSQL {

    public ArrayList<KhuyenMai> getDanhSachKhuyenMai() {
        ArrayList<KhuyenMai> danhSach = new ArrayList<>();
            
        String sql = "SELECT * FROM khuyenmai";

        try {
            Connection conn = ConnectToXampp.conn;
            if (conn == null) {
                new ConnectToXampp(); // tạo kết nối nếu chưa có
                conn = ConnectToXampp.conn;
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setMaKM(rs.getInt("MaKM"));
                km.setMaSP(rs.getInt("MaSP"));
                km.setTenKM(rs.getString("TenKM"));
                km.setUuDai(rs.getInt("UuDai"));
                km.setNgayBD(rs.getDate("NgayBD"));
                km.setNgayKT(rs.getDate("NgayKT"));
                danhSach.add(km);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSach;
    }

    public boolean themKhuyenMai(KhuyenMai khuyenmai) {
        try {
            String sql = "INSERT INTO khuyenmai(MaKM, TenKM, MaSP, UuDai, NgayBD, NgayKT) "
                       + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setInt(1, khuyenmai.getMaKM());
            pre.setString(2, khuyenmai.getTenKM());
            pre.setInt(3, khuyenmai.getMaSP());
            pre.setInt(4, khuyenmai.getUuDai());
            pre.setTimestamp(5, new Timestamp(khuyenmai.getNgayBD().getTime()));
            pre.setTimestamp(6, new Timestamp(khuyenmai.getNgayKT().getTime()));

            return pre.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean suaKhuyenMai(KhuyenMai khuyenmai) {
        try {
            String sql = "UPDATE khuyenmai SET MaKM=?, TenKM=?, MaSP=?, UuDai=?, NgayBD=?, NgayKT=? "
                       + "WHERE MaKM=?";

            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setInt(1, khuyenmai.getMaKM());
            pre.setString(2, khuyenmai.getTenKM());
            pre.setInt(3, khuyenmai.getMaSP());
            pre.setInt(4, khuyenmai.getUuDai());
            pre.setTimestamp(5, new Timestamp(khuyenmai.getNgayBD().getTime()));
            pre.setTimestamp(6, new Timestamp(khuyenmai.getNgayKT().getTime()));
            pre.setInt(7, khuyenmai.getMaKM());

            return pre.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaKhuyenMai(int MaKM) {
        try {
            String sql = "DELETE FROM khuyenmai WHERE MaKM=" + MaKM;
            Statement st = ConnectToXampp.conn.createStatement();
            return st.executeUpdate(sql) > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
=======
package KetNoiSQL;

import KetNoiSQL.ConnectToXampp;
import TrungGian.KhuyenMai;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ngotr
 */
public class KhuyenMaiSQL {

    public ArrayList<KhuyenMai> getDanhSachKhuyenMai() {
        ArrayList<KhuyenMai> danhSach = new ArrayList<>();
            
        String sql = "SELECT * FROM khuyenmai";

        try {
            Connection conn = ConnectToXampp.conn;
            if (conn == null) {
                new ConnectToXampp(); // tạo kết nối nếu chưa có
                conn = ConnectToXampp.conn;
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setMaKM(rs.getInt("MaKM"));
                km.setMaSP(rs.getInt("MaSP"));
                km.setTenKM(rs.getString("TenKM"));
                km.setUuDai(rs.getInt("UuDai"));
                km.setNgayBD(rs.getDate("NgayBD"));
                km.setNgayKT(rs.getDate("NgayKT"));
                danhSach.add(km);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSach;
    }

    public boolean themKhuyenMai(KhuyenMai khuyenmai) {
        try {
            String sql = "INSERT INTO khuyenmai(MaKM, TenKM, MaSP, UuDai, NgayBD, NgayKT) "
                       + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setInt(1, khuyenmai.getMaKM());
            pre.setString(2, khuyenmai.getTenKM());
            pre.setInt(3, khuyenmai.getMaSP());
            pre.setInt(4, khuyenmai.getUuDai());
            pre.setTimestamp(5, new Timestamp(khuyenmai.getNgayBD().getTime()));
            pre.setTimestamp(6, new Timestamp(khuyenmai.getNgayKT().getTime()));

            return pre.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean suaKhuyenMai(KhuyenMai khuyenmai) {
        try {
            String sql = "UPDATE khuyenmai SET MaKM=?, TenKM=?, MaSP=?, UuDai=?, NgayBD=?, NgayKT=? "
                       + "WHERE MaKM=?";

            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setInt(1, khuyenmai.getMaKM());
            pre.setString(2, khuyenmai.getTenKM());
            pre.setInt(3, khuyenmai.getMaSP());
            pre.setInt(4, khuyenmai.getUuDai());
            pre.setTimestamp(5, new Timestamp(khuyenmai.getNgayBD().getTime()));
            pre.setTimestamp(6, new Timestamp(khuyenmai.getNgayKT().getTime()));
            pre.setInt(7, khuyenmai.getMaKM());

            return pre.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaKhuyenMai(int MaKM) {
        try {
            String sql = "DELETE FROM khuyenmai WHERE MaKM=" + MaKM;
            Statement st = ConnectToXampp.conn.createStatement();
            return st.executeUpdate(sql) > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
>>>>>>> bef1f7b (commit)
