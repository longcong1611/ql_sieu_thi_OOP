package KetNoiSQL;

import TrungGian.TaiKhoan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DangNhapSQL {
    public TaiKhoan dangNhap(TaiKhoan tk) {
        try {
            String sql = "SELECT * FROM taikhoan WHERE TenDangNhap=? AND MatKhau=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, tk.getTenDangNhap());
            pre.setString(2, tk.getMatKhau());
            ResultSet rs = pre.executeQuery();
            TaiKhoan tkLogin = null;
            if (rs.next()) {
                tkLogin = tk;
                tkLogin.setMaNV(rs.getInt("MaNV"));
                tkLogin.setQuyen(rs.getString("PhanQuyen"));
            }
            return tkLogin;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tk;
    }
}
