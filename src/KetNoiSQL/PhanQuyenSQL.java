package KetNoiSQL;

import TrungGian.PhanQuyen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PhanQuyenSQL {
    public ArrayList<PhanQuyen> getListQuyen(){
        try {
            String sql = "SELECT * FROM quyen";
            Statement st = ConnectToXampp.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<PhanQuyen> dspq = new ArrayList<>();
            while (rs.next()) {
                PhanQuyen phanQuyen = new PhanQuyen();
                phanQuyen.setQuyen(rs.getString(1));
                phanQuyen.setNhapHang(rs.getInt(2));
                phanQuyen.setQlSanPham(rs.getInt(3));
                phanQuyen.setQlNhanVien(rs.getInt(4));
                phanQuyen.setQlKhachHang(rs.getInt(5));
                phanQuyen.setThongKe(rs.getInt(6));
                dspq.add(phanQuyen);
            }
            return dspq;
        } catch (Exception e) {            
        }
        return null;
    }
    
    public PhanQuyen getQuyen(String quyen) {
        try {
            String sql = "SELECT * FROM quyen WHERE Quyen ='" + quyen + "'";
            Statement st = ConnectToXampp.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                PhanQuyen phanQuyen = new PhanQuyen();
                phanQuyen.setQuyen(quyen);
                phanQuyen.setNhapHang(rs.getInt(2));
                phanQuyen.setQlSanPham(rs.getInt(3));
                phanQuyen.setQlNhanVien(rs.getInt(4));
                phanQuyen.setQlKhachHang(rs.getInt(5));
                phanQuyen.setThongKe(rs.getInt(6));
                return phanQuyen;
            }
        } catch (Exception e) {
        }
        return null;
    }
    public boolean themQuyen(PhanQuyen phanQuyen) {
        try {
            String sql = "INSERT INTO quyen VALUES (?,?,?,?,?,?)";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setString(1, phanQuyen.getQuyen());
            pre.setInt(2, phanQuyen.getNhapHang());
            pre.setInt(3, phanQuyen.getQlSanPham());
            pre.setInt(4, phanQuyen.getQlNhanVien());
            pre.setInt(5, phanQuyen.getQlKhachHang());
            pre.setInt(6, phanQuyen.getThongKe());
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }
    
    public boolean suaQuyen(PhanQuyen phanQuyen) {
        try {
            String sql = "UPDATE quyen SET NhapHang=?,QLSanPham=?,QLNhanVien=?,QLKhachHang=?,ThongKe=? WHERE Quyen=?";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setInt(1, phanQuyen.getNhapHang());
            pre.setInt(2, phanQuyen.getQlSanPham());
            pre.setInt(3, phanQuyen.getQlNhanVien());
            pre.setInt(4, phanQuyen.getQlKhachHang());
            pre.setInt(5, phanQuyen.getThongKe());
            pre.setString(6, phanQuyen.getQuyen());
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }
    public boolean xoaQuyen(String phanQuyen) {
        try {
            String sql1 = "UPDATE TaiKhoan SET PhanQuyen='Default' WHERE PhanQuyen='" + phanQuyen + "'";
            Statement st1 = ConnectToXampp.conn.createStatement();
            st1.executeUpdate(sql1);
            String sql = "DELETE FROM quyen WHERE Quyen='" + phanQuyen + "'";
            Statement st = ConnectToXampp.conn.createStatement();
            return st.executeUpdate(sql) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }    
}
