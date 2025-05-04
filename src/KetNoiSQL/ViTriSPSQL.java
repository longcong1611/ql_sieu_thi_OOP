/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KetNoiSQL;

import TrungGian.ViTriSP;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Ngoc Lan
 */
public class ViTriSPSQL {
    
    public ArrayList<ViTriSP> getListViTriSP() {
        try {
            String sql = "SELECT * FROM vitrisp";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<ViTriSP> dsvt = new ArrayList<>();
            while (rs.next()) {
                ViTriSP vt = new ViTriSP();

                vt.setMaViTri(rs.getInt(1));
                vt.setMaSP(rs.getInt(2));
                vt.setKhuVuc(rs.getString(3));
                vt.setKeSo(rs.getInt(4));
                vt.setTang(rs.getInt(5));
                vt.setMoTa(rs.getString(6));
                vt.setSoLuongHienCo(rs.getInt(7));
                vt.setNgayCapNhat(rs.getDate(8));

                dsvt.add(vt);
            }
            return dsvt;
        } catch (SQLException e) {
        }

        return null;
    }
    
    public boolean themViTri(ViTriSP vt) {
        try {
            String sql = "INSERT INTO vitrisp(MaViTri, MaSP, KhuVuc, KeSo, Tang, MoTa, SoLuongHienCo, NgayCapNhat) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setInt(1, vt.getMaViTri());
            pre.setInt(2, vt.getMaSP());
            pre.setString(3, vt.getKhuVuc());
            pre.setInt(4, vt.getKeSo());
            pre.setInt(5, vt.getTang());
            pre.setString(6, vt.getMoTa());
            pre.setInt(7, vt.getSoLuongHienCo());
            pre.setTimestamp(8, new java.sql.Timestamp(new java.util.Date().getTime()));

            pre.execute();
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public boolean xoaViTri(int maVT) {
        try {
            String sql = "DELETE FROM vitrisp WHERE MaViTri=" + maVT;
            Statement st = ConnectToXampp.conn.createStatement();
            st.execute(sql);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public boolean suaViTri(ViTriSP vt) {
        try {
            String sql = "UPDATE vitrisp SET "
                    + "MaSP=?, KhuVuc=?, KeSo=?, Tang=?, MoTa=?, SoLuongHienCo=?, NgayCapNhat=? "
                    + "WHERE MaViTri=?";
            PreparedStatement pre = ConnectToXampp.conn.prepareStatement(sql);
            pre.setInt(1, vt.getMaSP());
            pre.setString(2, vt.getKhuVuc());
            pre.setInt(3, vt.getKeSo());
            pre.setInt(4, vt.getTang());
            pre.setString(5, vt.getMoTa());
            pre.setInt(6, vt.getSoLuongHienCo());
            pre.setTimestamp(7, new java.sql.Timestamp(new java.util.Date().getTime()));
            pre.setInt(8, vt.getMaViTri());

            pre.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
