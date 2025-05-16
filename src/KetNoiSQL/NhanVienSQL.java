package KetNoiSQL;

import TrungGian.NhanVien;
import Bean.Dialog;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanVienSQL {
    public ArrayList<NhanVien> getDanhSachNhanVien() {
        try {
            if (ConnectToXampp.conn == null || ConnectToXampp.conn.isClosed()) {
                System.err.println("Database connection is null or closed");
                return null;
            }
            String sql = "SELECT * FROM nhanvien";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<NhanVien> dssv = new ArrayList<>();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getInt(1));
                nv.setHoVaTen(rs.getString(2));
                nv.setGioiTinh(rs.getString(3));
                nv.setSoDienThoai(rs.getString(4));
                nv.setChucVu(rs.getString(5));
                nv.setDiaChi(rs.getString(6));
                dssv.add(nv);
            }
            return dssv;
        } catch (SQLException e) {
            System.err.println("SQLException in getDanhSachNhanVien: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public NhanVien getNhanVien(int maNV) {
        NhanVien nv = null;
        try {
            if (ConnectToXampp.conn == null || ConnectToXampp.conn.isClosed()) {
                System.err.println("Database connection is null or closed");
                return null;
            }
            String sql = "SELECT * FROM nhanvien WHERE MaNV=?";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setInt(1, maNV);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                nv = new NhanVien();
                nv.setMaNV(rs.getInt(1));
                nv.setHoVaTen(rs.getString(2));
                nv.setGioiTinh(rs.getString(3));
                nv.setSoDienThoai(rs.getString(4));
                nv.setChucVu(rs.getString(5));
                nv.setDiaChi(rs.getString(6));
            }
        } catch (SQLException e) {
            System.err.println("SQLException in getNhanVien: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return nv;
    }

    public boolean updateNhanVien(NhanVien nv) {
        try {
            if (ConnectToXampp.conn == null || ConnectToXampp.conn.isClosed()) {
                System.err.println("Database connection is null or closed");
                return false;
            }
            String sql = "UPDATE nhanvien SET TenNV=?, GioiTinh=?, SDT=?, ChucVu=?, DiaChi=? WHERE MaNV=?";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setString(1, nv.getHoVaTen());
            pre.setString(2, nv.getGioiTinh());
            pre.setString(3, nv.getSoDienThoai());
            pre.setString(4, nv.getChucVu());
            pre.setString(5, nv.getDiaChi());
            pre.setInt(6, nv.getMaNV());
            boolean result = pre.executeUpdate() > 0;
            if (result && !ConnectToXampp.conn.getAutoCommit()) {
                ConnectToXampp.conn.commit();
            }
            return result;
        } catch (SQLException e) {
            System.err.println("SQLException in updateNhanVien: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteNhanVien(int maNV) {
        try {
            if (ConnectToXampp.conn == null || ConnectToXampp.conn.isClosed()) {
                System.err.println("Database connection is null or closed");
                return false;
            }
            String sql = "DELETE FROM nhanvien WHERE MaNV=?";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setInt(1, maNV);
            boolean result = pre.executeUpdate() > 0;
            if (result && !ConnectToXampp.conn.getAutoCommit()) {
                ConnectToXampp.conn.commit();
            }
            return result;
        } catch (SQLException e) {
            System.err.println("SQLException in deleteNhanVien: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean themNhanVien(NhanVien nv) {
        try {
            if (ConnectToXampp.conn == null || ConnectToXampp.conn.isClosed()) {
                System.err.println("Database connection is null or closed");
                return false;
            }
            // Lấy giá trị MaNV lớn nhất
            String maxSql = "SELECT MAX(MaNV) FROM nhanvien";
            PreparedStatement maxPre = ConnectToXampp.conn.prepareStatement(maxSql);
            ResultSet rs = maxPre.executeQuery();
            int newMaNV = 1; // Bắt đầu từ 1 nếu bảng rỗng
            if (rs.next() && rs.getInt(1) > 0) {
                newMaNV = rs.getInt(1) + 1;
            }
            System.out.println("Generated new MaNV: " + newMaNV);

            // Thêm nhân viên với MaNV
            String sql = "INSERT INTO nhanvien(MaNV, TenNV, GioiTinh, SDT, ChucVu, DiaChi) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setInt(1, newMaNV);
            pre.setString(2, nv.getHoVaTen());
            pre.setString(3, nv.getGioiTinh());
            pre.setString(4, nv.getSoDienThoai());
            pre.setString(5, nv.getChucVu());
            pre.setString(6, nv.getDiaChi());
            boolean result = pre.executeUpdate() > 0;
            if (result && !ConnectToXampp.conn.getAutoCommit()) {
                ConnectToXampp.conn.commit();
            }
            return result;
        } catch (SQLException e) {
            System.err.println("SQLException in themNhanVien: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}