<<<<<<< HEAD
package GiaoDien;


import TrungGian.KhuyenMai;
import XuLy.KhuyenMaiXuLy;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.LayoutManager;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author ngotr
 */

    class TransparentPanel extends JPanel {

    public TransparentPanel() {
        this.setOpaque(false);
    }
    
    public TransparentPanel(LayoutManager layout) {
        this.setLayout(layout);
    }

}

    class Bang extends JTable {

    public Bang(DefaultTableModel model) {
        super(model);
        custom();
    }

    private void custom() {
        // Không cho chỉnh sửa trực tiếp trên bảng
        this.setDefaultEditor(Object.class, null);

        // Font và chiều cao dòng
        this.setRowHeight(30);
        this.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        this.getTableHeader().setBackground(new Color(224, 224, 224));
        this.getTableHeader().setForeground(Color.BLACK);
        this.getTableHeader().setReorderingAllowed(false); // không cho kéo đổi cột

        // Viền bảng
        this.setGridColor(new Color(230, 230, 230));
        this.setShowGrid(true);

        // Canh giữa header
        ((DefaultTableCellRenderer) this.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        // Chỉ chọn 1 dòng tại 1 thời điểm
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Màu khi chọn dòng
        this.setSelectionBackground(new Color(100, 150, 220));
        this.setSelectionForeground(Color.WHITE);

        // Canh giữa tất cả ô (nếu muốn)
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < this.getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}

public class KhuyenMaiJPanel extends JPanel {
    
    public KhuyenMaiJPanel() {
        addControls();
        addEvents();
    }
    

    private KhuyenMaiXuLy khuyenmaiXuLy = new KhuyenMaiXuLy();
    final Color colorPanel = new Color(247, 247, 247);

    JButton  btnThem, btnSua, btnXoa;
    JTextField txtMa, txtTen, txtMaSP, txtUuDai;
    Bang tblKhuyenMai;
    DefaultTableModel dtmKhuyenMai;
    JDateChooser dateBD, dateKT;
    

    private void addControls() {
        Font font = new Font("Tahoma", Font.PLAIN, 16);
        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);

        int w = 1030;
        int h = 844;

        //=================MAIN PANEL=================
        //=====TITLE====
        JPanel pnMain = new TransparentPanel();
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));

        JPanel pnTitle = new TransparentPanel();
        JLabel lblTitle = new JLabel("<html><h1>Quản Lý Khuyến Mãi</h1></html>");
        pnTitle.add(lblTitle);
        pnMain.add(pnTitle);

        //========TEXTFIELD=========
        JPanel pnTextField = new TransparentPanel();
        pnTextField.setLayout(new BoxLayout(pnTextField, BoxLayout.Y_AXIS));

        JLabel lblMa, lblTen, lblMaSP, lblUuDai, lblNgayBD, lblNgayKT;
        lblMa = new JLabel("Mã Khuyến mãi");
        lblTen = new JLabel("Tên khuyến mãi");
        lblMaSP = new JLabel("Mã sản phẩm");
        lblUuDai = new JLabel("Ưu đãi");
        lblNgayBD = new JLabel("Ngày bắt đầu");
        lblNgayKT = new JLabel("Ngày kết thúc");

        lblMa.setFont(font);
        lblTen.setFont(font);
        lblMaSP.setFont(font);
        lblUuDai.setFont(font);
        lblNgayBD.setFont(font);
        lblNgayKT.setFont(font);

        txtMa = new JTextField(20);
        txtTen = new JTextField(20);
        txtMaSP = new JTextField(20);
        txtUuDai = new JTextField(20);
        dateBD = new JDateChooser();
        dateBD.setDateFormatString("dd/MM/yyyy");
        dateKT = new JDateChooser();
        dateKT.setDateFormatString("dd/MM/yyyy");

        txtMa.setEditable(true);
        dateBD.getCalendarButton().setPreferredSize(new Dimension(32, 32));
        dateBD.getCalendarButton().setIcon(new ImageIcon("image/icons8_calendar_25_20px.png"));
        dateKT.getCalendarButton().setPreferredSize(dateBD.getCalendarButton().getPreferredSize());
        dateKT.getCalendarButton().setIcon(dateBD.getCalendarButton().getIcon());

        txtMa.setFont(font);
        txtTen.setFont(font);
        txtMaSP.setFont(font);
        txtUuDai.setFont(font);
        dateBD.setFont(font);
        dateKT.setFont(font);

        JPanel pnMa = new TransparentPanel();
        pnMa.add(lblMa);
        pnMa.add(txtMa);
        pnTextField.add(pnMa);

        JPanel pnTen = new TransparentPanel();
        pnTen.add(lblTen);
        pnTen.add(txtTen);
        pnTextField.add(pnTen);

        JPanel pnMaSP = new TransparentPanel();
        pnMaSP.add(lblMaSP);
        pnMaSP.add(txtMaSP);
        pnTextField.add(pnMaSP);

        JPanel pnUuDai = new TransparentPanel();
        pnUuDai.add(lblUuDai);
        pnUuDai.add(txtUuDai);
        pnTextField.add(pnUuDai);

        JPanel pnNgayBD = new TransparentPanel();
        pnNgayBD.add(lblNgayBD);
        pnNgayBD.add(dateBD);
        pnTextField.add(pnNgayBD);

        JPanel pnNgayKT = new TransparentPanel();
        pnNgayKT.add(lblNgayKT);
        pnNgayKT.add(dateKT);
        pnTextField.add(pnNgayKT);

        pnMain.add(pnTextField);

        Dimension lblSize = lblTen.getPreferredSize();
        lblMa.setPreferredSize(lblSize);
        lblTen.setPreferredSize(lblSize);
        lblMaSP.setPreferredSize(lblSize);
        lblUuDai.setPreferredSize(lblSize);
        lblNgayBD.setPreferredSize(lblSize);
        lblNgayKT.setPreferredSize(lblSize);
        dateBD.setPreferredSize(txtUuDai.getPreferredSize());
        dateKT.setPreferredSize(txtUuDai.getPreferredSize());

        //==========BUTTON PANEL===============
        JPanel pnButton = new TransparentPanel();
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnThem.setFont(font);
        btnSua.setFont(font);
        btnXoa.setFont(font);
        pnButton.add(btnThem);
        pnButton.add(btnSua);
        pnButton.add(btnXoa);
        pnMain.add(pnButton);
        btnSua.setPreferredSize(btnThem.getPreferredSize());
        btnXoa.setPreferredSize(btnThem.getPreferredSize());

        //======================TABLE======================
        JPanel pnTable = new TransparentPanel(new BorderLayout());
        dtmKhuyenMai = new DefaultTableModel();
        dtmKhuyenMai.addColumn("Mã KM");
        dtmKhuyenMai.addColumn("Tên KM");
        dtmKhuyenMai.addColumn("Mã SP");
        dtmKhuyenMai.addColumn("Ưu đãi");
        dtmKhuyenMai.addColumn("Ngày bắt đầu");
        dtmKhuyenMai.addColumn("Ngày kết thúc");
        dtmKhuyenMai.addColumn("Tình trạng");

        tblKhuyenMai = new Bang(dtmKhuyenMai);
        JScrollPane scrTblKhuyenMai = new JScrollPane(tblKhuyenMai);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblKhuyenMai.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblKhuyenMai.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        tblKhuyenMai.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        tblKhuyenMai.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);

        TableColumnModel columnModelBanHang = tblKhuyenMai.getColumnModel();
        columnModelBanHang.getColumn(0).setPreferredWidth(24);
        columnModelBanHang.getColumn(1).setPreferredWidth(189);
        columnModelBanHang.getColumn(2).setPreferredWidth(66);
        columnModelBanHang.getColumn(3).setPreferredWidth(56);
        columnModelBanHang.getColumn(4).setPreferredWidth(81);
        columnModelBanHang.getColumn(5).setPreferredWidth(81);
        columnModelBanHang.getColumn(6).setPreferredWidth(92);

        pnTable.add(scrTblKhuyenMai, BorderLayout.CENTER);
        pnMain.add(pnTable);

        this.add(pnMain, BorderLayout.CENTER);

        loadDataTblKhuyenMai();
    }

    private void addEvents() {
        tblKhuyenMai.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblKhuyenMai();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemKhuyenMai();
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaKhuyenMai();
            }
        });
        
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xulyXoaKhuyenMai();
            }
        });
    }

    private void loadDataTblKhuyenMai() {
        dtmKhuyenMai.setRowCount(0);
        khuyenmaiXuLy.docDanhSach();
        ArrayList<KhuyenMai> danhsach = khuyenmaiXuLy.getDanhSachKhuyenMai();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (KhuyenMai gg : danhsach) {
            Vector vec = new Vector();
            vec.add(gg.getMaKM());
            vec.add(gg.getTenKM());
            vec.add(gg.getMaSP());
            vec.add(gg.getUuDai());
            vec.add(sdf.format(gg.getNgayBD()));
            vec.add(sdf.format(gg.getNgayKT()));

            Date now = new Date();
            if (gg.getNgayBD().before(now) && gg.getNgayKT().after(now)) {
                vec.add("Có hiệu lực");
            } else {
                vec.add("Không hiệu lực");
            }

            dtmKhuyenMai.addRow(vec);
        }
    }

    private void xuLyClickTblKhuyenMai() {
        int row = tblKhuyenMai.getSelectedRow();
        if (row > -1) {
            String ma = tblKhuyenMai.getValueAt(row, 0) + "";
            String ten = tblKhuyenMai.getValueAt(row, 1) + "";
            String masp = tblKhuyenMai.getValueAt(row, 2) + "";
            String uudai = tblKhuyenMai.getValueAt(row, 3) + "";
            String start = tblKhuyenMai.getValueAt(row, 4) + "";
            String end = tblKhuyenMai.getValueAt(row, 5) + "";

            java.util.Date ngayBD = new Date();
            java.util.Date ngayKT = new Date();
            try {
                ngayBD = new SimpleDateFormat("dd/MM/yyyy").parse(start);
                ngayKT = new SimpleDateFormat("dd/MM/yyyy").parse(end);
            } catch (Exception e) {
            }

            txtMa.setText(ma);
            txtTen.setText(ten);
            txtMaSP.setText(masp);
            txtUuDai.setText(uudai);
            dateBD.setDate(ngayBD);
            dateKT.setDate(ngayKT);
        }
    }

    private void xuLyThemKhuyenMai() {
        boolean flag = khuyenmaiXuLy.themKhuyenMai(
                txtMa.getText(),
                txtTen.getText(), 
                txtMaSP.getText(), 
                txtUuDai.getText(), 
                dateBD.getDate(), 
                dateKT.getDate());
        if (flag)
            loadDataTblKhuyenMai();
    }

    private void xuLySuaKhuyenMai() {
        boolean flag = khuyenmaiXuLy.suaKhuyenMai(
                txtMa.getText(), 
                txtTen.getText(), 
                txtMaSP.getText(), 
                txtUuDai.getText(), 
                dateBD.getDate(), 
                dateKT.getDate());
        if (flag)
            loadDataTblKhuyenMai();
    }
    private void xulyXoaKhuyenMai() {
        int row = tblKhuyenMai.getSelectedRow();
        if (row != -1) {
            String ma = txtMa.getText();
            boolean flag = khuyenmaiXuLy.xoaKhuyenMai(ma);
            if (flag) {
                loadDataTblKhuyenMai();
           }
        }
    }
}
=======
package GiaoDien;


import TrungGian.KhuyenMai;
import XuLy.KhuyenMaiXuLy;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.LayoutManager;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author ngotr
 */

    class TransparentPanel extends JPanel {

    public TransparentPanel() {
        this.setOpaque(false);
    }
    
    public TransparentPanel(LayoutManager layout) {
        this.setLayout(layout);
    }

}

    class Bang extends JTable {

    public Bang(DefaultTableModel model) {
        super(model);
        custom();
    }

    private void custom() {
        // Không cho chỉnh sửa trực tiếp trên bảng
        this.setDefaultEditor(Object.class, null);

        // Font và chiều cao dòng
        this.setRowHeight(30);
        this.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        this.getTableHeader().setBackground(new Color(224, 224, 224));
        this.getTableHeader().setForeground(Color.BLACK);
        this.getTableHeader().setReorderingAllowed(false); // không cho kéo đổi cột

        // Viền bảng
        this.setGridColor(new Color(230, 230, 230));
        this.setShowGrid(true);

        // Canh giữa header
        ((DefaultTableCellRenderer) this.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        // Chỉ chọn 1 dòng tại 1 thời điểm
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Màu khi chọn dòng
        this.setSelectionBackground(new Color(100, 150, 220));
        this.setSelectionForeground(Color.WHITE);

        // Canh giữa tất cả ô (nếu muốn)
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < this.getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}

public class KhuyenMaiJPanel extends JPanel {
    
    public KhuyenMaiJPanel() {
        addControls();
        addEvents();
    }
    

    private KhuyenMaiXuLy khuyenmaiXuLy = new KhuyenMaiXuLy();
    final Color colorPanel = new Color(247, 247, 247);

    JButton  btnThem, btnSua, btnXoa;
    JTextField txtMa, txtTen, txtMaSP, txtUuDai;
    Bang tblKhuyenMai;
    DefaultTableModel dtmKhuyenMai;
    JDateChooser dateBD, dateKT;
    

    private void addControls() {
        Font font = new Font("Tahoma", Font.PLAIN, 16);
        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);

        int w = 1030;
        int h = 844;

        //=================MAIN PANEL=================
        //=====TITLE====
        JPanel pnMain = new TransparentPanel();
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));

        JPanel pnTitle = new TransparentPanel();
        JLabel lblTitle = new JLabel("<html><h1>Quản Lý Khuyến Mãi</h1></html>");
        pnTitle.add(lblTitle);
        pnMain.add(pnTitle);

        //========TEXTFIELD=========
        JPanel pnTextField = new TransparentPanel();
        pnTextField.setLayout(new BoxLayout(pnTextField, BoxLayout.Y_AXIS));

        JLabel lblMa, lblTen, lblMaSP, lblUuDai, lblNgayBD, lblNgayKT;
        lblMa = new JLabel("Mã Khuyến mãi");
        lblTen = new JLabel("Tên khuyến mãi");
        lblMaSP = new JLabel("Mã sản phẩm");
        lblUuDai = new JLabel("Ưu đãi");
        lblNgayBD = new JLabel("Ngày bắt đầu");
        lblNgayKT = new JLabel("Ngày kết thúc");

        lblMa.setFont(font);
        lblTen.setFont(font);
        lblMaSP.setFont(font);
        lblUuDai.setFont(font);
        lblNgayBD.setFont(font);
        lblNgayKT.setFont(font);

        txtMa = new JTextField(20);
        txtTen = new JTextField(20);
        txtMaSP = new JTextField(20);
        txtUuDai = new JTextField(20);
        dateBD = new JDateChooser();
        dateBD.setDateFormatString("dd/MM/yyyy");
        dateKT = new JDateChooser();
        dateKT.setDateFormatString("dd/MM/yyyy");

        txtMa.setEditable(true);
        dateBD.getCalendarButton().setPreferredSize(new Dimension(32, 32));
        dateBD.getCalendarButton().setIcon(new ImageIcon("image/icons8_calendar_25_20px.png"));
        dateKT.getCalendarButton().setPreferredSize(dateBD.getCalendarButton().getPreferredSize());
        dateKT.getCalendarButton().setIcon(dateBD.getCalendarButton().getIcon());

        txtMa.setFont(font);
        txtTen.setFont(font);
        txtMaSP.setFont(font);
        txtUuDai.setFont(font);
        dateBD.setFont(font);
        dateKT.setFont(font);

        JPanel pnMa = new TransparentPanel();
        pnMa.add(lblMa);
        pnMa.add(txtMa);
        pnTextField.add(pnMa);

        JPanel pnTen = new TransparentPanel();
        pnTen.add(lblTen);
        pnTen.add(txtTen);
        pnTextField.add(pnTen);

        JPanel pnMaSP = new TransparentPanel();
        pnMaSP.add(lblMaSP);
        pnMaSP.add(txtMaSP);
        pnTextField.add(pnMaSP);

        JPanel pnUuDai = new TransparentPanel();
        pnUuDai.add(lblUuDai);
        pnUuDai.add(txtUuDai);
        pnTextField.add(pnUuDai);

        JPanel pnNgayBD = new TransparentPanel();
        pnNgayBD.add(lblNgayBD);
        pnNgayBD.add(dateBD);
        pnTextField.add(pnNgayBD);

        JPanel pnNgayKT = new TransparentPanel();
        pnNgayKT.add(lblNgayKT);
        pnNgayKT.add(dateKT);
        pnTextField.add(pnNgayKT);

        pnMain.add(pnTextField);

        Dimension lblSize = lblTen.getPreferredSize();
        lblMa.setPreferredSize(lblSize);
        lblTen.setPreferredSize(lblSize);
        lblMaSP.setPreferredSize(lblSize);
        lblUuDai.setPreferredSize(lblSize);
        lblNgayBD.setPreferredSize(lblSize);
        lblNgayKT.setPreferredSize(lblSize);
        dateBD.setPreferredSize(txtUuDai.getPreferredSize());
        dateKT.setPreferredSize(txtUuDai.getPreferredSize());

        //==========BUTTON PANEL===============
        JPanel pnButton = new TransparentPanel();
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnThem.setFont(font);
        btnSua.setFont(font);
        btnXoa.setFont(font);
        pnButton.add(btnThem);
        pnButton.add(btnSua);
        pnButton.add(btnXoa);
        pnMain.add(pnButton);
        btnSua.setPreferredSize(btnThem.getPreferredSize());
        btnXoa.setPreferredSize(btnThem.getPreferredSize());

        //======================TABLE======================
        JPanel pnTable = new TransparentPanel(new BorderLayout());
        dtmKhuyenMai = new DefaultTableModel();
        dtmKhuyenMai.addColumn("Mã KM");
        dtmKhuyenMai.addColumn("Tên KM");
        dtmKhuyenMai.addColumn("Mã SP");
        dtmKhuyenMai.addColumn("Ưu đãi");
        dtmKhuyenMai.addColumn("Ngày bắt đầu");
        dtmKhuyenMai.addColumn("Ngày kết thúc");
        dtmKhuyenMai.addColumn("Tình trạng");

        tblKhuyenMai = new Bang(dtmKhuyenMai);
        JScrollPane scrTblKhuyenMai = new JScrollPane(tblKhuyenMai);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblKhuyenMai.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblKhuyenMai.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        tblKhuyenMai.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        tblKhuyenMai.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);

        TableColumnModel columnModelBanHang = tblKhuyenMai.getColumnModel();
        columnModelBanHang.getColumn(0).setPreferredWidth(24);
        columnModelBanHang.getColumn(1).setPreferredWidth(189);
        columnModelBanHang.getColumn(2).setPreferredWidth(66);
        columnModelBanHang.getColumn(3).setPreferredWidth(56);
        columnModelBanHang.getColumn(4).setPreferredWidth(81);
        columnModelBanHang.getColumn(5).setPreferredWidth(81);
        columnModelBanHang.getColumn(6).setPreferredWidth(92);

        pnTable.add(scrTblKhuyenMai, BorderLayout.CENTER);
        pnMain.add(pnTable);

        this.add(pnMain, BorderLayout.CENTER);

        loadDataTblKhuyenMai();
    }

    private void addEvents() {
        tblKhuyenMai.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblKhuyenMai();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemKhuyenMai();
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaKhuyenMai();
            }
        });
        
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xulyXoaKhuyenMai();
            }
        });
    }

    private void loadDataTblKhuyenMai() {
        dtmKhuyenMai.setRowCount(0);
        khuyenmaiXuLy.docDanhSach();
        ArrayList<KhuyenMai> danhsach = khuyenmaiXuLy.getDanhSachKhuyenMai();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (KhuyenMai gg : danhsach) {
            Vector vec = new Vector();
            vec.add(gg.getMaKM());
            vec.add(gg.getTenKM());
            vec.add(gg.getMaSP());
            vec.add(gg.getUuDai());
            vec.add(sdf.format(gg.getNgayBD()));
            vec.add(sdf.format(gg.getNgayKT()));

            Date now = new Date();
            if (gg.getNgayBD().before(now) && gg.getNgayKT().after(now)) {
                vec.add("Có hiệu lực");
            } else {
                vec.add("Không hiệu lực");
            }

            dtmKhuyenMai.addRow(vec);
        }
    }

    private void xuLyClickTblKhuyenMai() {
        int row = tblKhuyenMai.getSelectedRow();
        if (row > -1) {
            String ma = tblKhuyenMai.getValueAt(row, 0) + "";
            String ten = tblKhuyenMai.getValueAt(row, 1) + "";
            String masp = tblKhuyenMai.getValueAt(row, 2) + "";
            String uudai = tblKhuyenMai.getValueAt(row, 3) + "";
            String start = tblKhuyenMai.getValueAt(row, 4) + "";
            String end = tblKhuyenMai.getValueAt(row, 5) + "";

            java.util.Date ngayBD = new Date();
            java.util.Date ngayKT = new Date();
            try {
                ngayBD = new SimpleDateFormat("dd/MM/yyyy").parse(start);
                ngayKT = new SimpleDateFormat("dd/MM/yyyy").parse(end);
            } catch (Exception e) {
            }

            txtMa.setText(ma);
            txtTen.setText(ten);
            txtMaSP.setText(masp);
            txtUuDai.setText(uudai);
            dateBD.setDate(ngayBD);
            dateKT.setDate(ngayKT);
        }
    }

    private void xuLyThemKhuyenMai() {
        boolean flag = khuyenmaiXuLy.themKhuyenMai(
                txtMa.getText(),
                txtTen.getText(), 
                txtMaSP.getText(), 
                txtUuDai.getText(), 
                dateBD.getDate(), 
                dateKT.getDate());
        if (flag)
            loadDataTblKhuyenMai();
    }

    private void xuLySuaKhuyenMai() {
        boolean flag = khuyenmaiXuLy.suaKhuyenMai(
                txtMa.getText(), 
                txtTen.getText(), 
                txtMaSP.getText(), 
                txtUuDai.getText(), 
                dateBD.getDate(), 
                dateKT.getDate());
        if (flag)
            loadDataTblKhuyenMai();
    }
    private void xulyXoaKhuyenMai() {
        int row = tblKhuyenMai.getSelectedRow();
        if (row != -1) {
            String ma = txtMa.getText();
            boolean flag = khuyenmaiXuLy.xoaKhuyenMai(ma);
            if (flag) {
                loadDataTblKhuyenMai();
           }
        }
    }
}
>>>>>>> bef1f7b (commit)
