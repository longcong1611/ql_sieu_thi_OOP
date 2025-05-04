package KetNoiSQL;


import TrungGian.CTPhieuNhap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ngotr
 */
public class CTPhieuNhapSQL {
    public ArrayList<CTPhieuNhap> getListCTPhieuNhap() {
        ArrayList<CTPhieuNhap> danhsachctphieunhap = new ArrayList<>();
        String sql = "SELECT * FROM ctphieunhap";
        try {
            
            Connection conn = ConnectToXampp.conn;
            if (conn == null) {
                new ConnectToXampp(); // tạo kết nối nếu chưa có
                conn = ConnectToXampp.conn;
            }
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CTPhieuNhap ctpn = new CTPhieuNhap();
                ctpn.setMaPN(rs.getInt(1));
                ctpn.setMaSP(rs.getInt(2));
                ctpn.setSoLuong(rs.getInt(3));
                ctpn.setDonGia(rs.getInt(4));
                ctpn.setThanhTien(rs.getInt(5));
                danhsachctphieunhap.add(ctpn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhsachctphieunhap;
    }
    public ArrayList<CTPhieuNhap> getListCTPhieuNhapTheoMaPN(int maPN) {
        ArrayList<CTPhieuNhap> danhsachctphieunhap = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ctphieunhap WHERE MaPN=" + maPN;
            Statement stmt = ConnectToXampp.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CTPhieuNhap ctpn = new CTPhieuNhap();
                ctpn.setMaPN(rs.getInt(1));
                ctpn.setMaSP(rs.getInt(2));
                ctpn.setSoLuong(rs.getInt(3));
                ctpn.setDonGia(rs.getInt(4));
                ctpn.setThanhTien(rs.getInt(5));
                danhsachctphieunhap.add(ctpn);
            }
        } catch (SQLException ex) {
            return null;
        }
        return danhsachctphieunhap;
    }
    public ArrayList<CTPhieuNhap> getListCTPhieuNhapTheoMaSP(int maSP) {
        ArrayList<CTPhieuNhap> danhsachctphieunhap = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ctphieunhap WHERE MaSP=" + maSP;
            Statement stmt = ConnectToXampp.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CTPhieuNhap ctpn = new CTPhieuNhap();
                ctpn.setMaPN(rs.getInt(1));
                ctpn.setMaSP(rs.getInt(2));
                ctpn.setSoLuong(rs.getInt(3));
                ctpn.setDonGia(rs.getInt(4));
                ctpn.setThanhTien(rs.getInt(5));
                danhsachctphieunhap.add(ctpn);
            }
        } catch (SQLException ex) {
            return null;
        }
        return danhsachctphieunhap;
    }
    public boolean addCTPhieuNhap(CTPhieuNhap ctpn) {
        try {
            Connection conn = ConnectToXampp.conn;
            if (conn == null) {
                new ConnectToXampp();
                conn = ConnectToXampp.conn;
            }
            if (conn == null) {
                System.err.println("Không thể kết nối tới cơ sở dữ liệu!");
                return false;
            }

            // Kiểm tra MaPN có tồn tại không
            if (!kiemTraMaPN(ctpn.getMaPN())) {
                System.err.println("Mã PN " + ctpn.getMaPN() + " không tồn tại trong bảng phieunhap!");
                return false;
            }

            // Kiểm tra MaSP có tồn tại không
            if (!kiemTraMaSP(ctpn.getMaSP())) {
                System.err.println("Mã SP " + ctpn.getMaSP() + " không tồn tại trong bảng sanpham!");
                return false;
            }

            // Cập nhật số lượng sản phẩm
            String sqlUpdateSP = "UPDATE sanpham SET TongSL = TongSL + ? WHERE MaSP = ?";
            PreparedStatement pre = conn.prepareStatement(sqlUpdateSP);
            pre.setInt(1, ctpn.getSoLuong());
            pre.setInt(2, ctpn.getMaSP());
            pre.executeUpdate();

            // Thêm chi tiết phiếu nhập
            String sql = "INSERT INTO ctphieunhap (MaPN, MaSP, SoLuong, DonGia, ThanhTien) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, ctpn.getMaPN());
            prep.setInt(2, ctpn.getMaSP());
            prep.setInt(3, ctpn.getSoLuong());
            prep.setInt(4, ctpn.getDonGia());
            prep.setInt(5, ctpn.getThanhTien());
            int rowsAffected = prep.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                System.err.println("Không thể thêm chi tiết phiếu nhập, không có dòng nào được ảnh hưởng!");
                return false;
            }
        } catch (SQLException ex) {
            System.err.println("Lỗi SQL khi thêm chi tiết phiếu nhập: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
    
        public boolean deleteCTPhieuNhap(int maPN) {
        boolean result = false;
        try {
            String sql = "DELETE FROM ctphieunhap WHERE MaPN=" + maPN;
            Statement stmt = ConnectToXampp.conn.createStatement();
            result = stmt.executeUpdate(sql) > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
       
    public boolean deleteCTPhieuNhap(int maPN, int maSP) {
        boolean result = false;
        try {
            Connection conn = ConnectToXampp.conn;
            if (conn == null) {
                new ConnectToXampp();
                conn = ConnectToXampp.conn;
            }
            if (conn == null) {
                System.err.println("Không thể kết nối tới cơ sở dữ liệu!");
                return false;
            }

            // Lấy số lượng cũ từ ctphieunhap
            int soLuongOld = 0;
            String sqlSelect = "SELECT SoLuong FROM ctphieunhap WHERE MaPN = ? AND MaSP = ?";
            PreparedStatement psSelect = conn.prepareStatement(sqlSelect);
            psSelect.setInt(1, maPN);
            psSelect.setInt(2, maSP);
            ResultSet rs = psSelect.executeQuery();
            if (rs.next()) {
                soLuongOld = rs.getInt("SoLuong");
            } else {
                System.err.println("Không tìm thấy chi tiết phiếu nhập với MaPN=" + maPN + " và MaSP=" + maSP);
                return false;
            }

            // Cập nhật TongSL trong sanpham
            String sqlUpdateSP = "UPDATE sanpham SET TongSL = TongSL - ? WHERE MaSP = ?";
            PreparedStatement pre = conn.prepareStatement(sqlUpdateSP);
            pre.setInt(1, soLuongOld);
            pre.setInt(2, maSP);
            pre.executeUpdate();

            // Xóa chi tiết phiếu nhập
            String sqlDelete = "DELETE FROM ctphieunhap WHERE MaPN = ? AND MaSP = ?";
            PreparedStatement prep = conn.prepareStatement(sqlDelete);
            prep.setInt(1, maPN);
            prep.setInt(2, maSP);
            result = prep.executeUpdate() > 0;
            if (!result) {
                System.err.println("Không thể xóa chi tiết phiếu nhập, không có dòng nào bị ảnh hưởng!");
            }
        } catch (SQLException ex) {
            System.err.println("Lỗi SQL khi xóa chi tiết phiếu nhập: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
        return result;
    }
    public boolean updateCTPhieuNhap(int maPN, int maSPOriginal, CTPhieuNhap ctpn) {
        boolean result = false;
        try {
            Connection conn = ConnectToXampp.conn;
            if (conn == null) {
                new ConnectToXampp();
                conn = ConnectToXampp.conn;
            }

            // Lấy số lượng cũ để cập nhật số lượng sản phẩm
            int soLuongOld = 0;
            String sqlSelect = "SELECT SoLuong FROM ctphieunhap WHERE MaPN = ? AND MaSP = ?";
            PreparedStatement psSelect = conn.prepareStatement(sqlSelect);
            psSelect.setInt(1, maPN);
            psSelect.setInt(2, maSPOriginal);
            ResultSet rs = psSelect.executeQuery();
            if (rs.next()) {
                soLuongOld = rs.getInt("SoLuong");
            }

            // Cập nhật số lượng sản phẩm nếu MaSP không thay đổi
            if (maSPOriginal == ctpn.getMaSP()) {
                String sqlUpdateSP = "UPDATE sanpham SET TongSL = TongSL + ? WHERE MaSP = ?";
                PreparedStatement pre = conn.prepareStatement(sqlUpdateSP);
                pre.setInt(1, ctpn.getSoLuong() - soLuongOld);
                pre.setInt(2, ctpn.getMaSP());
                pre.executeUpdate();
            } else {
                // Nếu MaSP thay đổi, trừ số lượng cũ và cộng số lượng mới
                String sqlUpdateSPOld = "UPDATE sanpham SET TongSL = TongSL - ? WHERE MaSP = ?";
                PreparedStatement preOld = conn.prepareStatement(sqlUpdateSPOld);
                preOld.setInt(1, soLuongOld);
                preOld.setInt(2, maSPOriginal);
                preOld.executeUpdate();

                String sqlUpdateSPNew = "UPDATE sanpham SET TongSL = TongSL + ? WHERE MaSP = ?";
                PreparedStatement preNew = conn.prepareStatement(sqlUpdateSPNew);
                preNew.setInt(1, ctpn.getSoLuong());
                preNew.setInt(2, ctpn.getMaSP());
                preNew.executeUpdate();
            }

            // Cập nhật chi tiết phiếu nhập
            String sql = "UPDATE ctphieunhap SET MaPN=?, MaSP=?, SoLuong=?, DonGia=?, ThanhTien=? WHERE MaPN=? AND MaSP=?";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, ctpn.getMaPN());
            prep.setInt(2, ctpn.getMaSP());
            prep.setInt(3, ctpn.getSoLuong());
            prep.setInt(4, ctpn.getDonGia());
            prep.setInt(5, ctpn.getThanhTien());
            prep.setInt(6, maPN);
            prep.setInt(7, maSPOriginal);
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Lỗi SQL khi cập nhật chi tiết phiếu nhập: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
        return result;
    }
        public boolean kiemTraMaPN(int maPN) {
        try {
            Connection conn = ConnectToXampp.conn;
            if (conn == null) {
                new ConnectToXampp();
                conn = ConnectToXampp.conn;
            }
            if (conn == null) {
                System.err.println("Không thể kết nối tới cơ sở dữ liệu!");
                return false;
            }

            String sql = "SELECT * FROM phieunhap WHERE MaPN = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, maPN);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            System.err.println("Lỗi SQL khi kiểm tra MaPN: " + ex.getMessage());
            return false;
        }
    }
        
        public boolean kiemTraMaSP(int maSP) {
        try {
            Connection conn = ConnectToXampp.conn;
            if (conn == null) {
                new ConnectToXampp();
                conn = ConnectToXampp.conn;
            }
            if (conn == null) {
                System.err.println("Không thể kết nối tới cơ sở dữ liệu!");
                return false;
            }

            String sql = "SELECT * FROM sanpham WHERE MaSP = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, maSP);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            System.err.println("Lỗi SQL khi kiểm tra MaSP: " + ex.getMessage());
            return false;
        }
    }
}
