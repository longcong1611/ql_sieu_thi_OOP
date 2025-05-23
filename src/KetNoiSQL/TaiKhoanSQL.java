package KetNoiSQL;

import Controller.DangNhapController;
import TrungGian.TaiKhoan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TaiKhoanSQL {
    public boolean themTaiKhoan(int maNV, String tenDangNhap, String phanQuyen) {
        try {
            String sql = "INSERT INTO taikhoan(MaNV, TenDangNhap, MatKhau, PhanQuyen) "
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setInt(1, maNV);
            pre.setString(2, tenDangNhap);
            pre.setString(3, tenDangNhap);
            pre.setString(4, phanQuyen);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }
    
    public boolean kiemTraTrungTenDangNhap(String tenDangNhap) {
        try {
            String sql = "SELECT * FROM taikhoan WHERE TenDangNhap = '" + tenDangNhap + "'";
            Statement st = ConnectToXampp.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getTenDangNhapTheoMa(int maNV) {
        try {
            String sql = "SELECT TenDangNhap FROM TaiKhoan WHERE MaNV=" + maNV;
            Statement st = ConnectToXampp.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return "";
    }

    public String getQuyenTheoMa(int maNV) {
        try {
            String sql = "SELECT Quyen FROM TaiKhoan WHERE MaNV=" + maNV;
            Statement st = ConnectToXampp.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return "";
    }
    
    public boolean doiMatKhau(String matKhauCu, String matKhauMoi) {
        try {
            String sql = "UPDATE TaiKhoan SET MatKhau=? WHERE MaNV=? AND MatKhau=?";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setString(1, matKhauMoi);
            pre.setInt(2, DangNhapController.taiKhoanLogin.getMaNV());
            pre.setString(3, matKhauCu);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }    

    public ArrayList<TaiKhoan> timTaiKhoan(String tuKhoa) {
        ArrayList<TaiKhoan> danhSach = new ArrayList<>();
        try {
            String sql = "SELECT * FROM taikhoan WHERE TenDangNhap LIKE ? OR MaNV LIKE ?";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setString(1, "%" + tuKhoa + "%");
            pre.setString(2, "%" + tuKhoa + "%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setMaNV(rs.getInt("MaNV"));
                tk.setTenDangNhap(rs.getString("TenDangNhap"));
                tk.setMatKhau(rs.getString("MatKhau"));
                tk.setQuyen(rs.getString("PhanQuyen"));
                danhSach.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSach;
    }
    
    public void docDanhSach() {
        try {
            String sql = "SELECT * FROM taikhoan";
            Statement st = ConnectToXampp.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean updateTaiKhoan(int maNV, String tenDangNhap, String matKhau, String quyen) {
        try {
            String sql = "UPDATE taikhoan SET TenDangNhap = ?, MatKhau = ?, PhanQuyen=? WHERE MaNV = ?";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setString(1, tenDangNhap);
            pre.setString(2, matKhau);
            pre.setInt(3, maNV);
            pre.setString(4, quyen);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }
    
    public ArrayList<TaiKhoan> getDanhSachTaiKhoan() {
        ArrayList<TaiKhoan> danhSach = new ArrayList<>();
        try {
            String sql = "SELECT * FROM taikhoan";
            Statement st = ConnectToXampp.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setMaNV(rs.getInt("MaNV"));
                tk.setTenDangNhap(rs.getString("TenDangNhap"));
                tk.setMatKhau(rs.getString("MatKhau"));
                tk.setQuyen(rs.getString("PhanQuyen"));
                danhSach.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    public boolean deleteTaiKhoan(int maNV) {
        boolean result = false;
        try {
            String sql = "DELETE FROM taikhoan WHERE MaNV=?";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setInt(1, maNV);
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
    
}
