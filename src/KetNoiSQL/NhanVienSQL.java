<<<<<<< HEAD
package KetNoiSQL;

import Bean.NhanVien;
import Bean.Dialog;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanVienSQL {
    public ArrayList<NhanVien> getDanhSachNhanVien() {
        try {
            String sql = "SELECT * FROM nhanvien";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
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
        }

        return null;
    }
    
    public NhanVien getNhanVien(int maNV) {
        NhanVien nv = null;
        try {
            String sql = "SELECT * FROM nhanvien WHERE MaNV=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(0, maNV);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                nv = new NhanVien();
                nv.setMaNV(rs.getInt(1));
                nv.setHoVaTen(rs.getString(2));
                nv.setGioiTinh(rs.getString(3));
                nv.setSoDienThoai(rs.getString(4));
                nv.setChucVu(rs.getString(5));
                nv.setDiaChi(rs.getString(6));
            }
        } catch (SQLException e) {
            return null;
        }

        return nv;
    }

    public boolean updateNhanVien(NhanVien nv) {
        boolean result = false;
        try {
            String sql = "UPDATE nhanvien SET TenNV=?, GioiTinh=?, ChucVu=? WHERE MaNV=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, nv.getHoVaTen());
            pre.setString(2, nv.getGioiTinh());
            pre.setString(3, nv.getSoDienThoai());
            pre.setString(4, nv.getChucVu());
            pre.setString(5, nv.getDiaChi());
            pre.setInt(6, nv.getMaNV());
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean deleteNhanVien(int maNV) {
        boolean result = false;
        try {
            String sql = "DELETE FROM nhanvien WHERE MaNV=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, maNV);
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean themNhanVien(NhanVien nv) {
        boolean result = false;
        try {
            String sql = "INSERT INTO NhanVien(Ho, Ten, GioiTinh, ChucVu) " +
                    "VALUES(?, ?, ?, ?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, nv.getHoVaTen());
            pre.setString(2, nv.getGioiTinh());
            pre.setString(3, nv.getSoDienThoai());
            pre.setString(4, nv.getChucVu());
            pre.setString(5, nv.getDiaChi());
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }    


}
=======
package KetNoiSQL;

import Bean.NhanVien;
import Bean.Dialog;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanVienSQL {
    public ArrayList<NhanVien> getDanhSachNhanVien() {
        try {
            String sql = "SELECT * FROM nhanvien";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
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
        }

        return null;
    }
    
    public NhanVien getNhanVien(int maNV) {
        NhanVien nv = null;
        try {
            String sql = "SELECT * FROM nhanvien WHERE MaNV=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(0, maNV);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                nv = new NhanVien();
                nv.setMaNV(rs.getInt(1));
                nv.setHoVaTen(rs.getString(2));
                nv.setGioiTinh(rs.getString(3));
                nv.setSoDienThoai(rs.getString(4));
                nv.setChucVu(rs.getString(5));
                nv.setDiaChi(rs.getString(6));
            }
        } catch (SQLException e) {
            return null;
        }

        return nv;
    }

    public boolean updateNhanVien(NhanVien nv) {
        boolean result = false;
        try {
            String sql = "UPDATE nhanvien SET TenNV=?, GioiTinh=?, ChucVu=? WHERE MaNV=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, nv.getHoVaTen());
            pre.setString(2, nv.getGioiTinh());
            pre.setString(3, nv.getSoDienThoai());
            pre.setString(4, nv.getChucVu());
            pre.setString(5, nv.getDiaChi());
            pre.setInt(6, nv.getMaNV());
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean deleteNhanVien(int maNV) {
        boolean result = false;
        try {
            String sql = "DELETE FROM nhanvien WHERE MaNV=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, maNV);
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean themNhanVien(NhanVien nv) {
        boolean result = false;
        try {
            String sql = "INSERT INTO NhanVien(Ho, Ten, GioiTinh, ChucVu) " +
                    "VALUES(?, ?, ?, ?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, nv.getHoVaTen());
            pre.setString(2, nv.getGioiTinh());
            pre.setString(3, nv.getSoDienThoai());
            pre.setString(4, nv.getChucVu());
            pre.setString(5, nv.getDiaChi());
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }    


}
>>>>>>> bef1f7b (commit)
