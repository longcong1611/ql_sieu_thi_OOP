<<<<<<< HEAD
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package XuLy;

import KetNoiSQL.ThongKeSQL;
import TrungGian.ThongKe;
import java.util.List;

/**
 *
 * @author DELL-3520
 */
public class ThongKeXuLy {
    ThongKeSQL tkDAL = new ThongKeSQL();
    public List<ThongKe> getThongKeTheoNgay(java.sql.Date tuNgay, java.sql.Date denNgay){
        return tkDAL.getThongKeTheoNgay(tuNgay, denNgay);
    }
    public List<ThongKe> getThongKeTheoTen(String tenNV){
        return tkDAL.getThongKeTheoTen(tenNV);
    }
    public List<ThongKe> getThongKeSanPham(java.sql.Date tuNgay, java.sql.Date denNgay, String tenLoai){
        return tkDAL.getThongKeSanPham(tuNgay, denNgay, tenLoai);
    }
}
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package XuLy;

import KetNoiSQL.ThongKeSQL;
import TrungGian.ThongKe;
import java.util.List;

/**
 *
 * @author DELL-3520
 */
public class ThongKeXuLy {
    ThongKeSQL tkDAL = new ThongKeSQL();
    public List<ThongKe> getThongKeTheoNgay(java.sql.Date tuNgay, java.sql.Date denNgay){
        return tkDAL.getThongKeTheoNgay(tuNgay, denNgay);
    }
    public List<ThongKe> getThongKeTheoTen(String tenNV){
        return tkDAL.getThongKeTheoTen(tenNV);
    }
    public List<ThongKe> getThongKeSanPham(java.sql.Date tuNgay, java.sql.Date denNgay, String tenLoai){
        return tkDAL.getThongKeSanPham(tuNgay, denNgay, tenLoai);
    }
}
>>>>>>> bef1f7b (commit)
