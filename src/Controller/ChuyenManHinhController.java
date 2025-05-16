package Controller;

import Bean.DanhMucBean;
import Bean.Dialog;
import TrungGian.PhanQuyen;
import Controller.PhanQuyenController;
import GiaoDien.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChuyenManHinhController {
    private JPanel root;
    private String kindSelected = "";
    private List<DanhMucBean> listItem = null;
    private final PhanQuyen quyen;
    private final Map<String, JPanel> panelMap = new HashMap<>();

    public ChuyenManHinhController(JPanel jpnRoot) {
        this.root = jpnRoot;
        this.quyen = PhanQuyenController.quyenTk;

        if (quyen == null) {
            System.err.println("Không tìm thấy thông tin phân quyền!");
        }

        initPanelMap();
    }

    private void initPanelMap() {
        panelMap.put("Trangchu", new TrangChuJPanel());
        panelMap.put("Sanpham", new SanPhamJPanel());
        panelMap.put("Khachhang", new KhachHangJPanel());
        panelMap.put("Vitrisanpham", new ViTriSPJPanel());
        panelMap.put("Nhaphang", new PhieuNhapJPanel());
        panelMap.put("Khuyenmai", new KhuyenMaiJPanel());
        panelMap.put("Hoadon", new HoaDonJPanel());
        panelMap.put("Thongke", new ThongkeJPanel());
        panelMap.put("Nhanvien", new NhanVienJPanel());
    }

    public void setView(JPanel jpnItem, JLabel jlbItem) {
        kindSelected = "Trangchu";
        changeBackground(jpnItem, jlbItem, Color.GREEN);
        setPanel(new TrangChuJPanel());
    }

    public void setEvent(List<DanhMucBean> listItem) {
        this.listItem = listItem;
        for (DanhMucBean item : listItem) {
            item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }

    private class LabelEvent extends MouseAdapter {
        private final String kind;
        private final JPanel jpnItem;
        private final JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if ("Thoat".equals(kind)) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thoát?", "Xác nhận thoát", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) System.exit(0);
                return;
            }

            if (!hasPermission(kind)) {
                new Dialog("Bạn không có quyền truy cập vào phần này!", Dialog.ERROR_DIALOG);
                return;
            }

            JPanel panel = panelMap.getOrDefault(kind, new TrangChuJPanel());
            setPanel(panel);
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            changeBackground(jpnItem, jlbItem, Color.GREEN);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            changeBackground(jpnItem, jlbItem, Color.GREEN);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                changeBackground(jpnItem, jlbItem, Color.WHITE);
            }
        }
    }

    private void setPanel(JPanel panel) {
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(panel);
        root.validate();
        root.repaint();
    }

    private void setChangeBackground(String kind) {
        for (DanhMucBean item : listItem) {
            Color color = item.getKind().equalsIgnoreCase(kind) ? Color.GREEN : Color.WHITE;
            changeBackground(item.getJpn(), item.getJlb(), color);
        }
    }

    private void changeBackground(JPanel panel, JLabel label, Color color) {
        panel.setBackground(color);
        label.setBackground(color);
    }

    private boolean hasPermission(String kind) {
        if ("Nhanvien".equalsIgnoreCase(kind)) {
            return quyen.getQlNhanVien() == 1;
        }
        return true;
    }
}
