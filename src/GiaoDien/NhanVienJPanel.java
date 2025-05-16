package GiaoDien;

import Bean.Dialog;
import Controller.NhanVienController;
import Controller.PhanQuyenController;
import Controller.TaiKhoanController;
import TrungGian.NhanVien;
import TrungGian.PhanQuyen;
import TrungGian.TaiKhoan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingUtilities;

class BangNV extends JTable {
    public BangNV(DefaultTableModel model) {
        super(model);
        custom();
    }

    private void custom() {
        this.setDefaultEditor(Object.class, null);
        this.setRowHeight(30);
        this.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        this.getTableHeader().setBackground(new Color(224, 224, 224));
        this.getTableHeader().setForeground(Color.BLACK);
        this.getTableHeader().setReorderingAllowed(false);
        this.setGridColor(new Color(230, 230, 230));
        this.setShowGrid(true);
        ((DefaultTableCellRenderer) this.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setSelectionBackground(new Color(100, 150, 220));
        this.setSelectionForeground(Color.WHITE);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < this.getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}

public class NhanVienJPanel extends JPanel {
    private PhanQuyenController phanQuyenController = new PhanQuyenController();
    private NhanVienController nhanVienController = new NhanVienController();
    private TaiKhoanController taiKhoanController = new TaiKhoanController();
    
    JTabbedPane tabbedPane;
    JTextField txtMaNV, txtHoVaTen, txtSoDienThoai, txtChucVu, txtDiaChi, txtTimNV;
    JComboBox<String> cmbGioiTinh;
    BangNV tblNhanVien;
    DefaultTableModel dtmNhanVien;
    JButton btnResetNV, btnThemNV, btnSuaNV, btnXoaNV, btnTimNV, btnCapTaiKhoanTK;

    JTextField txtMaTK, txtTenDangNhap, txtTimTK;
    JComboBox<String> cmbQuyen;
    BangNV tblTaiKhoan;
    DefaultTableModel dtmTaiKhoan;
    JButton btnResetTK, btnThemTK, btnSuaTK, btnXoaTK, btnTimTK;

    JCheckBox ckbNhapHang, ckbQLSanPham, ckbQLNhanVien, ckbQLKhachHang, ckbThongKe;
    JButton btnThemQuyen, btnSuaQuyen, btnXoaQuyen;
    JComboBox<String> cmbQuyenPQ;

    final Color colorPanel = new Color(247, 247, 247);

    public NhanVienJPanel() {
        System.out.println("Initializing NhanVienJPanel");
        addControls();
        addEvents();
    }
        
    private void addControls() {
        Font font = new Font("Tahoma", Font.PLAIN, 16);
        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);

        // Tạo JTabbedPane
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Nhân viên", null);
        tabbedPane.addTab("Tài khoản", null);
        tabbedPane.addTab("Quyền", null);
        
        // PANEL NHÂN VIÊN
        JPanel pnNhanVien = new TransparentPanel();
        pnNhanVien.setLayout(new BoxLayout(pnNhanVien, BoxLayout.Y_AXIS));

        // Tiêu đề
        JPanel pnTitleNV = new TransparentPanel();
        JLabel lblTitleNV = new JLabel("<html><h1>Quản Lý Nhân Viên</h1></html>");
        pnTitleNV.add(lblTitleNV);
        pnNhanVien.add(pnTitleNV);

        // Trường nhập liệu
        JPanel pnTextNV = new TransparentPanel();
        pnTextNV.setLayout(new BoxLayout(pnTextNV, BoxLayout.Y_AXIS));

        JLabel lblMaNV = new JLabel("Mã Nhân viên");
        JLabel lblHoVaTen = new JLabel("Họ và tên");
        JLabel lblSoDienThoai = new JLabel("Số điện thoại");
        JLabel lblGioiTinh = new JLabel("Giới tính");
        JLabel lblChucVu = new JLabel("Chức vụ");
        JLabel lblDiaChi = new JLabel("Địa chỉ");

        lblMaNV.setFont(font);
        lblHoVaTen.setFont(font);
        lblSoDienThoai.setFont(font);
        lblGioiTinh.setFont(font);
        lblChucVu.setFont(font);
        lblDiaChi.setFont(font);

        txtMaNV = new JTextField(20);
        txtMaNV.setEditable(false);
        txtHoVaTen = new JTextField(20);
        txtSoDienThoai = new JTextField(20);
        txtChucVu = new JTextField(20);
        txtDiaChi = new JTextField(20);
        cmbGioiTinh = new JComboBox<>();
        cmbGioiTinh.addItem("Chọn giới tính");
        cmbGioiTinh.addItem("Nam");
        cmbGioiTinh.addItem("Nữ");

        txtMaNV.setFont(font);
        txtHoVaTen.setFont(font);
        txtSoDienThoai.setFont(font);
        txtChucVu.setFont(font);
        txtDiaChi.setFont(font);
        cmbGioiTinh.setFont(font);

        JPanel pnMaNV = new TransparentPanel();
        pnMaNV.add(lblMaNV);
        pnMaNV.add(txtMaNV);
        pnTextNV.add(pnMaNV);

        JPanel pnHoVaTen = new TransparentPanel();
        pnHoVaTen.add(lblHoVaTen);
        pnHoVaTen.add(txtHoVaTen);
        pnTextNV.add(pnHoVaTen);

        JPanel pnSoDienThoai = new TransparentPanel();
        pnSoDienThoai.add(lblSoDienThoai);
        pnSoDienThoai.add(txtSoDienThoai);
        pnTextNV.add(pnSoDienThoai);

        JPanel pnGioiTinh = new TransparentPanel();
        pnGioiTinh.add(lblGioiTinh);
        pnGioiTinh.add(cmbGioiTinh);
        pnTextNV.add(pnGioiTinh);

        JPanel pnChucVu = new TransparentPanel();
        pnChucVu.add(lblChucVu);
        pnChucVu.add(txtChucVu);
        pnTextNV.add(pnChucVu);

        JPanel pnDiaChi = new TransparentPanel();
        pnDiaChi.add(lblDiaChi);
        pnDiaChi.add(txtDiaChi);
        pnTextNV.add(pnDiaChi);

        Dimension lblSizeNV = lblMaNV.getPreferredSize();
        lblMaNV.setPreferredSize(lblSizeNV);
        lblHoVaTen.setPreferredSize(lblSizeNV);
        lblSoDienThoai.setPreferredSize(lblSizeNV);
        lblGioiTinh.setPreferredSize(lblSizeNV);
        lblChucVu.setPreferredSize(lblSizeNV);
        lblDiaChi.setPreferredSize(lblSizeNV);
        cmbGioiTinh.setPreferredSize(txtChucVu.getPreferredSize());

        pnNhanVien.add(pnTextNV);

        // Tìm kiếm
        JPanel pnTimNV = new TransparentPanel();
        JLabel lblTimNV = new JLabel("Từ khóa tìm");
        lblTimNV.setFont(font);
        txtTimNV = new JTextField(20);
        txtTimNV.setFont(font);
        pnTimNV.add(lblTimNV);
        pnTimNV.add(txtTimNV);
        pnNhanVien.add(pnTimNV);
        lblTimNV.setPreferredSize(lblSizeNV);

        // Nút bấm
        JPanel pnButtonNV = new TransparentPanel();
        btnResetNV = new JButton("Refresh");
        btnThemNV = new JButton("Thêm");
        btnSuaNV = new JButton("Sửa");
        btnXoaNV = new JButton("Xóa");
        btnTimNV = new JButton("Tìm kiếm");
        btnCapTaiKhoanTK = new JButton("Cấp tài khoản");
        
        btnResetNV.setFont(font);
        btnThemNV.setFont(font);
        btnSuaNV.setFont(font);
        btnXoaNV.setFont(font);
        btnTimNV.setFont(font);
        btnCapTaiKhoanTK.setFont(font);

        pnButtonNV.add(btnResetNV);
        pnButtonNV.add(btnThemNV);
        pnButtonNV.add(btnSuaNV);
        pnButtonNV.add(btnXoaNV);
        pnButtonNV.add(btnTimNV);
        pnButtonNV.add(btnCapTaiKhoanTK);
        
        Dimension btnSizeNV = new Dimension(150, 30);
        btnResetNV.setPreferredSize(btnSizeNV);
        btnThemNV.setPreferredSize(btnSizeNV);
        btnSuaNV.setPreferredSize(btnSizeNV);
        btnXoaNV.setPreferredSize(btnSizeNV);
        btnTimNV.setPreferredSize(btnSizeNV);
        btnCapTaiKhoanTK.setPreferredSize(btnSizeNV);

        pnNhanVien.add(pnButtonNV);

        // Bảng nhân viên
        JPanel pnTableNV = new TransparentPanel(new BorderLayout());
        dtmNhanVien = new DefaultTableModel();
        dtmNhanVien.addColumn("Mã NV");
        dtmNhanVien.addColumn("Họ và tên");
        dtmNhanVien.addColumn("Giới tính");
        dtmNhanVien.addColumn("Số điện thoại");
        dtmNhanVien.addColumn("Chức vụ");
        dtmNhanVien.addColumn("Địa chỉ");
        tblNhanVien = new BangNV(dtmNhanVien);
        JScrollPane scrTblNhanVien = new JScrollPane(tblNhanVien);
        pnTableNV.add(scrTblNhanVien, BorderLayout.CENTER);
        pnNhanVien.add(pnTableNV);

        // PANEL TÀI KHOẢN
        JPanel pnTaiKhoan = new TransparentPanel();
        pnTaiKhoan.setLayout(new BoxLayout(pnTaiKhoan, BoxLayout.Y_AXIS));

        // Tiêu đề
        JPanel pnTitleTK = new TransparentPanel();
        JLabel lblTitleTK = new JLabel("<html><h1>Quản Lý Tài Khoản</h1></html>");
        pnTitleTK.add(lblTitleTK);
        pnTaiKhoan.add(pnTitleTK);

        // Trường nhập liệu
        JPanel pnTextTK = new TransparentPanel();
        pnTextTK.setLayout(new BoxLayout(pnTextTK, BoxLayout.Y_AXIS));

        JLabel lblMaTK = new JLabel("Mã Nhân viên");
        JLabel lblTenDangNhap = new JLabel("Tên đăng nhập");
        JLabel lblQuyen = new JLabel("Quyền");

        lblMaTK.setFont(font);
        lblTenDangNhap.setFont(font);
        lblQuyen.setFont(font);

        txtMaTK = new JTextField(20);
        txtMaTK.setEditable(false);
        txtTenDangNhap = new JTextField(20);
        cmbQuyen = new JComboBox<>();

        txtMaTK.setFont(font);
        txtTenDangNhap.setFont(font);
        cmbQuyen.setFont(font);

        JPanel pnMaTK = new TransparentPanel();
        pnMaTK.add(lblMaTK);
        pnMaTK.add(txtMaTK);
        pnTextTK.add(pnMaTK);

        JPanel pnTenDangNhap = new TransparentPanel();
        pnTenDangNhap.add(lblTenDangNhap);
        pnTenDangNhap.add(txtTenDangNhap);
        pnTextTK.add(pnTenDangNhap);

        JPanel pnQuyen = new TransparentPanel();
        pnQuyen.add(lblQuyen);
        pnQuyen.add(cmbQuyen);
        pnTextTK.add(pnQuyen);

        Dimension lblSizeTK = new Dimension(120, lblMaTK.getPreferredSize().height);
        lblMaTK.setPreferredSize(lblSizeTK);
        lblTenDangNhap.setPreferredSize(lblSizeTK);
        lblQuyen.setPreferredSize(lblSizeTK);
        cmbQuyen.setPreferredSize(txtTenDangNhap.getPreferredSize());

        pnTaiKhoan.add(pnTextTK);

        // Tìm kiếm
        JPanel pnTimTK = new TransparentPanel();
        JLabel lblTimTK = new JLabel("Từ khóa tìm");
        lblTimTK.setFont(font);
        txtTimTK = new JTextField(20);
        txtTimTK.setFont(font);
        pnTimTK.add(lblTimTK);
        pnTimTK.add(txtTimTK);
        pnTaiKhoan.add(pnTimTK);
        lblTimTK.setPreferredSize(lblSizeTK);

        // Nút bấm
        JPanel pnButtonTK = new TransparentPanel();
        btnResetTK = new JButton("Refresh");
        btnThemTK = new JButton("Thêm");
        btnSuaTK = new JButton("Sửa");
        btnXoaTK = new JButton("Xóa");
        btnTimTK = new JButton("Tìm kiếm");

        btnResetTK.setFont(font);
        btnThemTK.setFont(font);
        btnSuaTK.setFont(font);
        btnXoaTK.setFont(font);
        btnTimTK.setFont(font);

        pnButtonTK.add(btnResetTK);
        pnButtonTK.add(btnThemTK);
        pnButtonTK.add(btnSuaTK);
        pnButtonTK.add(btnXoaTK);
        pnButtonTK.add(btnTimTK);

        Dimension btnSizeTK = new Dimension(120, 30);
        btnResetTK.setPreferredSize(btnSizeTK);
        btnThemTK.setPreferredSize(btnSizeTK);
        btnSuaTK.setPreferredSize(btnSizeTK);
        btnXoaTK.setPreferredSize(btnSizeTK);
        btnTimTK.setPreferredSize(btnSizeTK);

        pnTaiKhoan.add(pnButtonTK);

        // Bảng tài khoản
        JPanel pnTableTK = new TransparentPanel(new BorderLayout());
        dtmTaiKhoan = new DefaultTableModel();
        dtmTaiKhoan.addColumn("Mã nhân viên");
        dtmTaiKhoan.addColumn("Tên đăng nhập");
        dtmTaiKhoan.addColumn("Mật khẩu");
        dtmTaiKhoan.addColumn("Quyền");
        tblTaiKhoan = new BangNV(dtmTaiKhoan);
        JScrollPane scrTblTaiKhoan = new JScrollPane(tblTaiKhoan);
        pnTableTK.add(scrTblTaiKhoan, BorderLayout.CENTER);
        pnTaiKhoan.add(pnTableTK);

        // PANEL PHÂN QUYỀN
        JPanel pnPhanQuyen = new TransparentPanel();
        pnPhanQuyen.setLayout(new BoxLayout(pnPhanQuyen, BoxLayout.Y_AXIS));

        // Tiêu đề
        JPanel pnTitlePQ = new TransparentPanel();
        JLabel lblTitlePQ = new JLabel("<html><h1>Quản Lý Phân Quyền</h1></html>");
        pnTitlePQ.add(lblTitlePQ);
        pnPhanQuyen.add(pnTitlePQ);

        // Nhóm quyền
        JPanel pnCmbQuyen = new TransparentPanel();
        JLabel lblCmbQuyen = new JLabel("Nhóm quyền");
        lblCmbQuyen.setFont(font);
        cmbQuyenPQ = new JComboBox<>();
        cmbQuyenPQ.setFont(font);
        pnCmbQuyen.add(lblCmbQuyen);
        pnCmbQuyen.add(cmbQuyenPQ);
        pnPhanQuyen.add(pnCmbQuyen);

        // Checkbox quyền
        ckbNhapHang = new JCheckBox("Quản lý nhập hàng");
        ckbQLSanPham = new JCheckBox("Quản lý sản phẩm");
        ckbQLNhanVien = new JCheckBox("Quản lý nhân viên");
        ckbQLKhachHang = new JCheckBox("Quản lý khách hàng");
        ckbThongKe = new JCheckBox("Quản lý thống kê");

        ckbNhapHang.setFont(font);
        ckbQLSanPham.setFont(font);
        ckbQLNhanVien.setFont(font);
        ckbQLKhachHang.setFont(font);
        ckbThongKe.setFont(font);

        JPanel pnCheckNhapHang = new TransparentPanel();
        pnCheckNhapHang.add(ckbNhapHang);
        pnPhanQuyen.add(pnCheckNhapHang);

        JPanel pnCheckQLSanPham = new TransparentPanel();
        pnCheckQLSanPham.add(ckbQLSanPham);
        pnPhanQuyen.add(pnCheckQLSanPham);

        JPanel pnCheckQLNhanVien = new TransparentPanel();
        pnCheckQLNhanVien.add(ckbQLNhanVien);
        pnPhanQuyen.add(pnCheckQLNhanVien);

        JPanel pnCheckQLKhachHang = new TransparentPanel();
        pnCheckQLKhachHang.add(ckbQLKhachHang);
        pnPhanQuyen.add(pnCheckQLKhachHang);

        JPanel pnCheckThongKe = new TransparentPanel();
        pnCheckThongKe.add(ckbThongKe);
        pnPhanQuyen.add(pnCheckThongKe);

        Dimension ckbSize = ckbQLKhachHang.getPreferredSize();
        cmbQuyenPQ.setPreferredSize(ckbSize);
        ckbNhapHang.setPreferredSize(ckbSize);
        ckbQLSanPham.setPreferredSize(ckbSize);
        ckbQLNhanVien.setPreferredSize(ckbSize);
        ckbQLKhachHang.setPreferredSize(ckbSize);
        ckbThongKe.setPreferredSize(ckbSize);

        // Nút bấm
        JPanel pnButtonPQ = new TransparentPanel();
        btnThemQuyen = new JButton("Thêm quyền");
        btnSuaQuyen = new JButton("Sửa quyền");
        btnXoaQuyen = new JButton("Xóa quyền");

        btnThemQuyen.setFont(font);
        btnSuaQuyen.setFont(font);
        btnXoaQuyen.setFont(font);

        pnButtonPQ.add(btnThemQuyen);
        pnButtonPQ.add(btnSuaQuyen);
        pnButtonPQ.add(btnXoaQuyen);

        btnSuaQuyen.setPreferredSize(btnThemQuyen.getPreferredSize());
        btnXoaQuyen.setPreferredSize(btnThemQuyen.getPreferredSize());

        pnPhanQuyen.add(pnButtonPQ);

        // Gán panel vào tab
        tabbedPane.setComponentAt(0, pnNhanVien);
        tabbedPane.setComponentAt(1, pnTaiKhoan);
        tabbedPane.setComponentAt(2, pnPhanQuyen);

        this.add(tabbedPane, BorderLayout.CENTER);

        // Tải dữ liệu
        loadDataTblNhanVien();
        loadDataTblTaiKhoan();
        loadDataCmbQuyen();
    }
    
    private void addEvents() {
        // Sự kiện cho tab Nhân viên
        tblNhanVien.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblNhanVien();
            }
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });

        btnTimNV.addActionListener(e -> xuLyTimKiemNhanVien());
        btnThemNV.addActionListener(e -> xuLyThemNhanVien());
        btnSuaNV.addActionListener(e -> xuLySuaNhanVien());
        btnXoaNV.addActionListener(e -> xuLyXoaNhanVien());
        btnResetNV.addActionListener(e -> resetFormNhanVien());
        btnCapTaiKhoanTK.addActionListener(e -> xuLyCapTaiKhoan());

        // Sự kiện cho tab Tài khoản
        tblTaiKhoan.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblTaiKhoan();
            }
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });

        btnTimTK.addActionListener(e -> xuLyTimKiemTaiKhoan());
        btnThemTK.addActionListener(e -> xuLyThemTaiKhoan());
        btnSuaTK.addActionListener(e -> xuLySuaTaiKhoan());
        btnXoaTK.addActionListener(e -> xuLyXoaTaiKhoan());
        btnResetTK.addActionListener(e -> resetFormTaiKhoan());

        // Sự kiện cho tab Phân quyền
        cmbQuyenPQ.addActionListener(e -> xuLyHienThiChiTietQuyen());
        btnThemQuyen.addActionListener(e -> xuLyThemQuyen());
        btnSuaQuyen.addActionListener(e -> xuLySuaQuyen());
        btnXoaQuyen.addActionListener(e -> xuLyXoaQuyen());
    }

    private void resetFormNhanVien() {
        txtMaNV.setText("");
        txtHoVaTen.setText("");
        txtSoDienThoai.setText("");
        txtChucVu.setText("");
        txtDiaChi.setText("");
        txtTimNV.setText("");
        cmbGioiTinh.setSelectedIndex(0);
        loadDataTblNhanVien();
    }

    private void resetFormTaiKhoan() {
        txtMaTK.setText("");
        txtTenDangNhap.setText("");
        txtTimTK.setText("");
        cmbQuyen.setSelectedIndex(-1);
        loadDataTblTaiKhoan();
    }

    private void xuLyXoaQuyen() {
        if (cmbQuyenPQ.getSelectedIndex() < 1) {
            new Dialog("Chưa chọn nhóm quyền để xóa!", Dialog.ERROR_DIALOG);
            return;
        }
        Dialog dlg = new Dialog("Bạn có chắc chắn muốn xóa?", Dialog.WARNING_DIALOG);
        if (dlg.getAction() == Dialog.CANCEL_OPTION) {
            return;
        }
        String tenQuyen = cmbQuyenPQ.getSelectedItem() + "";
        boolean flag = phanQuyenController.xoaQuyen(tenQuyen);
        if (flag) {
            loadDataCmbQuyen();
        }
    }

    private void xuLyThemQuyen() {
        String tenQuyen = JOptionPane.showInputDialog(this, "Nhập tên quyền");
        if (tenQuyen == null || tenQuyen.trim().isEmpty()) {
            new Dialog("Tên quyền không được để trống!", Dialog.ERROR_DIALOG);
            return;
        }
        boolean flag = phanQuyenController.themQuyen(tenQuyen);
        if (flag) {
            loadDataCmbQuyen();
        }
    }

    private void xuLySuaQuyen() {
        if (cmbQuyenPQ.getSelectedIndex() < 1) {
            new Dialog("Chưa chọn nhóm quyền để sửa!", Dialog.ERROR_DIALOG);
            return;
        }
        String tenQuyen = cmbQuyenPQ.getSelectedItem() + "";
        int nhapHang = ckbNhapHang.isSelected() ? 1 : 0;
        int sanPham = ckbQLSanPham.isSelected() ? 1 : 0;
        int nhanVien = ckbQLNhanVien.isSelected() ? 1 : 0;
        int khachHang = ckbQLKhachHang.isSelected() ? 1 : 0;
        int thongKe = ckbThongKe.isSelected() ? 1 : 0;

        boolean flag = phanQuyenController.suaQuyen(tenQuyen, nhapHang, sanPham, nhanVien, khachHang, thongKe);
        if (flag) {
            loadDataCmbQuyen();
        }
    }

    private void xuLyHienThiChiTietQuyen() {
        ArrayList<PhanQuyen> dsq = phanQuyenController.getListQuyen();
        if (dsq == null) {
            System.out.println("Danh sách quyền rỗng hoặc không tải được");
            return;
        }
        PhanQuyen phanQuyen = new PhanQuyen();
        for (PhanQuyen pq : dsq) {
            if (pq.getQuyen().equals(cmbQuyenPQ.getSelectedItem())) {
                phanQuyen.setQuyen(pq.getQuyen());
                phanQuyen.setNhapHang(pq.getNhapHang());
                phanQuyen.setQlSanPham(pq.getQlSanPham());
                phanQuyen.setQlNhanVien(pq.getQlNhanVien());
                phanQuyen.setQlKhachHang(pq.getQlKhachHang());
                phanQuyen.setThongKe(pq.getThongKe());
                break;
            }
        }
        ckbNhapHang.setSelected(phanQuyen.getNhapHang() == 1);
        ckbQLSanPham.setSelected(phanQuyen.getQlSanPham() == 1);
        ckbQLNhanVien.setSelected(phanQuyen.getQlNhanVien() == 1);
        ckbQLKhachHang.setSelected(phanQuyen.getQlKhachHang() == 1);
        ckbThongKe.setSelected(phanQuyen.getThongKe() == 1);
    }

    private void loadDataCmbQuyen() {
        phanQuyenController.docDanhSachQuyen();
        ArrayList<PhanQuyen> dsq = phanQuyenController.getListQuyen();
        System.out.println("loadDataCmbQuyen: dsq = " + (dsq == null ? "null" : dsq.size() + " items"));
        cmbQuyen.removeAllItems();
        cmbQuyenPQ.removeAllItems();
        cmbQuyenPQ.addItem("Chọn quyền");
        if (dsq != null) {
            for (PhanQuyen pq : dsq) {
                cmbQuyen.addItem(pq.getQuyen());
                cmbQuyenPQ.addItem(pq.getQuyen());
            }
        }
    }

    private void xuLyCapTaiKhoan() {
        if (txtMaNV.getText().trim().isEmpty()) {
            new Dialog("Hãy chọn nhân viên!", Dialog.ERROR_DIALOG);
            return;
        }
        new Dialog("Chuyển sang tab 'Tài khoản' để cấp tài khoản!", Dialog.WARNING_DIALOG);
        tabbedPane.setSelectedIndex(1);
        txtMaTK.setText(txtMaNV.getText());
    }

    private void xuLyThemTaiKhoan() {
        String maNV = txtMaTK.getText();
        String tenDangNhap = txtTenDangNhap.getText();
        String quyen = cmbQuyen.getSelectedItem() != null ? cmbQuyen.getSelectedItem() + "" : "";
        
        if (maNV.trim().isEmpty()) {
            new Dialog("Hãy chọn nhân viên!", Dialog.ERROR_DIALOG);
            return;
        }
        if (tenDangNhap.trim().isEmpty()) {
            new Dialog("Vui lòng nhập tên đăng nhập!", Dialog.ERROR_DIALOG);
            return;
        }
        if (quyen.isEmpty()) {
            new Dialog("Vui lòng chọn quyền!", Dialog.ERROR_DIALOG);
            return;
        }
        
        boolean flag = taiKhoanController.themTaiKhoan(maNV, tenDangNhap, quyen);
        if (flag) {
            loadDataTblTaiKhoan();
            resetFormTaiKhoan();
        }
    }

    private void xuLySuaTaiKhoan() {
        String maTK = txtMaTK.getText();
        String tenDangNhap = txtTenDangNhap.getText();
        String quyen = cmbQuyen.getSelectedItem() + "";
        
        if (maTK.trim().isEmpty()) {
            new Dialog("Hãy chọn tài khoản!", Dialog.ERROR_DIALOG);
            return;
        }
        if (tenDangNhap.trim().isEmpty()) {
            new Dialog("Vui lòng nhập tên đăng nhập!", Dialog.ERROR_DIALOG);
            return;
        }
        
        boolean flag = taiKhoanController.updateTaiKhoan(maTK, tenDangNhap, tenDangNhap, quyen);
        if (flag) {
            loadDataTblTaiKhoan();
            resetFormTaiKhoan();
        }
    }

    private void xuLyXoaTaiKhoan() {
        String maTK = txtMaTK.getText();
        if (maTK.trim().isEmpty()) {
            new Dialog("Hãy chọn tài khoản!", Dialog.ERROR_DIALOG);
            return;
        }
        Dialog dlg = new Dialog("Bạn có chắc chắn muốn xóa?", Dialog.WARNING_DIALOG);
        if (dlg.getAction() == Dialog.CANCEL_OPTION) {
            return;
        }
        boolean flag = taiKhoanController.xoaTaiKhoan(maTK);
        if (flag) {
            loadDataTblTaiKhoan();
            resetFormTaiKhoan();
        }
    }

    private void xuLyTimKiemTaiKhoan() {
        String keyword = txtTimTK.getText();
        ArrayList<TaiKhoan> dstk = taiKhoanController.timTaiKhoan(keyword);
        dtmTaiKhoan.setRowCount(0);
        for (TaiKhoan tk : dstk) {
            Vector vec = new Vector();
            vec.add(tk.getMaNV());
            vec.add(tk.getTenDangNhap());
            vec.add(tk.getMatKhau());
            vec.add(tk.getQuyen());
            dtmTaiKhoan.addRow(vec);
        }
    }

    private void xuLyThemNhanVien() {
        if (cmbGioiTinh.getSelectedIndex() == 0) {
            new Dialog("Hãy chọn giới tính!", Dialog.ERROR_DIALOG);
            return;
        }
        String hoVaTen = txtHoVaTen.getText();
        String soDienThoai = txtSoDienThoai.getText();
        String gioiTinh = cmbGioiTinh.getSelectedItem() + "";
        String chucVu = txtChucVu.getText();
        String diaChi = txtDiaChi.getText();

        if (nhanVienController.themNhanVien(hoVaTen, gioiTinh, soDienThoai, chucVu, diaChi)) {
            SwingUtilities.invokeLater(() -> {
                loadDataTblNhanVien();
                resetFormNhanVien();
                tblNhanVien.revalidate();
                tblNhanVien.repaint();
            });
        }
    }

    private void xuLySuaNhanVien() {
        if (cmbGioiTinh.getSelectedIndex() == 0) {
            new Dialog("Hãy chọn giới tính!", Dialog.ERROR_DIALOG);
            return;
        }
        String ma = txtMaNV.getText();
        String hoVaTen = txtHoVaTen.getText();
        String soDienThoai = txtSoDienThoai.getText();
        String gioiTinh = cmbGioiTinh.getSelectedItem() + "";
        String chucVu = txtChucVu.getText();
        String diaChi = txtDiaChi.getText();

        if (nhanVienController.updateNhanVien(ma, hoVaTen, gioiTinh, soDienThoai, chucVu, diaChi)) {
            SwingUtilities.invokeLater(() -> {
                loadDataTblNhanVien();
                resetFormNhanVien();
                tblNhanVien.revalidate();
                tblNhanVien.repaint();
            });
        }
    }
    
    private void xuLyXoaNhanVien() {
        int row = tblNhanVien.getSelectedRow();
        if (row == -1) {
            new Dialog("Hãy chọn nhân viên để xóa!", Dialog.ERROR_DIALOG);
            return;
        }
        String ma = txtMaNV.getText();
        if (nhanVienController.xoaNhanVien(ma)) {
            SwingUtilities.invokeLater(() -> {
                loadDataTblNhanVien();
                resetFormNhanVien();
                tblNhanVien.revalidate();
                tblNhanVien.repaint();
            });
        }
    }
    
    private void xuLyTimKiemNhanVien() {
        String keyword = txtTimNV.getText();
        ArrayList<NhanVien> dsnv = nhanVienController.timNhanVien(keyword);
        dtmNhanVien.setRowCount(0);
        for (NhanVien nv : dsnv) {
            Vector vec = new Vector();
            vec.add(nv.getMaNV());
            vec.add(nv.getHoVaTen());
            vec.add(nv.getGioiTinh());
            vec.add(nv.getSoDienThoai());
            vec.add(nv.getChucVu());
            vec.add(nv.getDiaChi());
            dtmNhanVien.addRow(vec);
        }
        dtmNhanVien.fireTableDataChanged();
        tblNhanVien.revalidate();
        tblNhanVien.repaint();
    }

    private void xuLyClickTblNhanVien() {
        int row = tblNhanVien.getSelectedRow();
        if (row > -1) {
            txtMaNV.setText(tblNhanVien.getValueAt(row, 0) + "");
            txtHoVaTen.setText(tblNhanVien.getValueAt(row, 1) + "");
            txtSoDienThoai.setText(tblNhanVien.getValueAt(row, 3) + "");
            txtChucVu.setText(tblNhanVien.getValueAt(row, 4) + "");
            txtDiaChi.setText(tblNhanVien.getValueAt(row, 5) + "");
            String gioiTinh = tblNhanVien.getValueAt(row, 2) + "";
            if (gioiTinh.equals("Nam")) {
                cmbGioiTinh.setSelectedIndex(1);
            } else if (gioiTinh.equals("Nữ")) {
                cmbGioiTinh.setSelectedIndex(2);
            } else {
                cmbGioiTinh.setSelectedIndex(0);
            }
        }
    }
    private void xuLyClickTblTaiKhoan() {
        int row = tblTaiKhoan.getSelectedRow();
        if (row > -1) {
            txtMaTK.setText(tblTaiKhoan.getValueAt(row, 0) + "");
            txtTenDangNhap.setText(tblTaiKhoan.getValueAt(row, 1) + "");
            String quyen = tblTaiKhoan.getValueAt(row, 3) + "";
            cmbQuyen.setSelectedItem(quyen);
        }
    }

    private void loadDataTblNhanVien() {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Loading data for tblNhanVien...");
            dtmNhanVien.setRowCount(0);
            ArrayList<NhanVien> dsnv = nhanVienController.getDanhSachNhanVien();
            System.out.println("Danh sách nhân viên: " + (dsnv == null ? "null" : dsnv.size() + " items"));
            if (dsnv != null) {
                for (NhanVien nv : dsnv) {
                    Vector vec = new Vector();
                    vec.add(nv.getMaNV());
                    vec.add(nv.getHoVaTen());
                    vec.add(nv.getGioiTinh());
                    vec.add(nv.getSoDienThoai());
                    vec.add(nv.getChucVu());
                    vec.add(nv.getDiaChi());
                    dtmNhanVien.addRow(vec);
                    System.out.println("Added row: " + vec);
                }
            } else {
                System.out.println("Không tải được danh sách nhân viên!");
            }
            dtmNhanVien.fireTableDataChanged();
            System.out.println("Table row count after loading: " + tblNhanVien.getRowCount());
            tblNhanVien.revalidate();
            tblNhanVien.repaint();
        });
    }

    private void loadDataTblTaiKhoan() {
        dtmTaiKhoan.setRowCount(0);
        ArrayList<TaiKhoan> dstk = taiKhoanController.getDanhSachTaiKhoan();
        if (dstk != null) {
            for (TaiKhoan tk : dstk) {
                Vector vec = new Vector();
                vec.add(tk.getMaNV());
                vec.add(tk.getTenDangNhap());
                vec.add(tk.getMatKhau());
                vec.add(tk.getQuyen());
                dtmTaiKhoan.addRow(vec);
            }
        }
        dtmTaiKhoan.fireTableDataChanged();
        tblTaiKhoan.revalidate();
        tblTaiKhoan.repaint();
    }
}