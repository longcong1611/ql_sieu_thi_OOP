/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KetNoiSQL;

import TrungGian.KhachHang;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Ngoc Lan
 */
public class KhachHangSQL {
    
    public ArrayList<KhachHang> getListKhachHang() {
        try {
            String sql = "SELECT * FROM khachhang";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<KhachHang> dskh = new ArrayList<>();
            while (rs.next()) {
                KhachHang kh = new KhachHang();

                kh.setMaKH(rs.getInt(1));
                kh.setTenKH(rs.getString(2));
                kh.setSDT(rs.getString(3));
                kh.setGioiTinh(rs.getString(4));

                dskh.add(kh);
            }
            return dskh;
        } catch (SQLException e) {
        }

        return null;
    }
    
    public boolean themKhachHang(KhachHang kh) {
        try {
            String sql = "INSERT INTO khachhang(MaKH, TenKH, SDT, GioiTinh ) "
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setInt(1, kh.getMaKH());
            pre.setString(2, kh.getTenKH());
            pre.setString(3, kh.getSDT());
            pre.setString(4, kh.getGioiTinh());

            pre.execute();
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public boolean xoaKhachHang(int maKH) {
        try {
            String sql = "DELETE FROM khachhang WHERE MaKH=" + maKH;
            Statement st = ConnectToXampp.conn.createStatement();
            st.execute(sql);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public boolean suaKhachHang(KhachHang kh) {
        try {
            String sql = "UPDATE khachhang SET "
                    + "TenKH=?, SDT=?, GioiTinh=? "
                    + "WHERE MaKH=?";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setString(1, kh.getTenKH());
            pre.setString(2, kh.getSDT());
            pre.setString(3, kh.getGioiTinh());
            pre.setInt(4, kh.getMaKH());

            pre.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
