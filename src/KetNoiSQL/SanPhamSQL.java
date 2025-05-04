/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KetNoiSQL;

import TrungGian.SanPham;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Ngoc Lan
 */



public class SanPhamSQL {
    
    public ArrayList<SanPham> getListSanPham() {
        try {
            String sql = "SELECT * FROM sanpham";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<SanPham> dssp = new ArrayList<>();
            while (rs.next()) {
                SanPham sp = new SanPham();

                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setLoaiSP(rs.getString(3));
                sp.setGiaBan(rs.getInt(4));
                sp.setGiaNhap(rs.getInt(5));
                sp.setTongSL(rs.getInt(6));
                sp.setNhaCungCap(rs.getString(7));
                sp.setHSD(rs.getDate(8));

                dssp.add(sp);
            }
            return dssp;
        } catch (SQLException e) {
        }

        return null;
    }
    
    public boolean themSanPham(SanPham sp) {
        try {
            String sql = "INSERT INTO SanPham(MaSP, TenSP, LoaiSP, GiaBan, GiaNhap, TongSL, NhaCungCap, HSD) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setInt(1, sp.getMaSP());
            pre.setString(2, sp.getTenSP());
            pre.setString(3, sp.getLoaiSP());
            pre.setInt(4, sp.getGiaBan());
            pre.setInt(5, sp.getGiaNhap());
            pre.setInt(6, sp.getTongSL());
            pre.setString(7, sp.getNhaCungCap());
            pre.setTimestamp(8, new java.sql.Timestamp(new java.util.Date().getTime()));

            pre.execute();
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public boolean xoaSanPham(int maSP) {
        try {
            String sql = "DELETE FROM sanpham WHERE MaSP=" + maSP;
            Statement st = ConnectToXampp.conn.createStatement();
            st.execute(sql);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public boolean suaSanPham(SanPham sp) {
        try {
            String sql = "UPDATE SanPham SET "
                    + "TenSP=?, LoaiSP=?, GiaBan=?, GiaNhap=?, TongSL=?, NhaCungCap=?, HSD=? "
                    + "WHERE MaSP=?";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setString(1, sp.getTenSP());
            pre.setString(2, sp.getLoaiSP());
            pre.setInt(3, sp.getGiaBan());
            pre.setInt(4, sp.getGiaNhap());
            pre.setInt(5, sp.getTongSL());
            pre.setString(6, sp.getNhaCungCap());
            pre.setTimestamp(7, new java.sql.Timestamp(new java.util.Date().getTime()));
            pre.setInt(8, sp.getMaSP());

            pre.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public SanPham getSanPhamByMaSP(int maSP) {
    try {
        String sql = "SELECT * FROM sanpham WHERE MaSP = ?";
        PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
        pre.setInt(1, maSP);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            SanPham sp = new SanPham();
            sp.setMaSP(rs.getInt(1));
            sp.setTenSP(rs.getString(2));
            sp.setLoaiSP(rs.getString(3));
            sp.setGiaBan(rs.getInt(4));
            sp.setGiaNhap(rs.getInt(5));
            sp.setTongSL(rs.getInt(6));
            sp.setNhaCungCap(rs.getString(7));
            sp.setHSD(rs.getDate(8));
            return sp;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
}
