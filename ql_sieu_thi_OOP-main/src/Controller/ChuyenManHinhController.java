
package Controller;

import Bean.DanhMucBean;
import GiaoDien.KhachHangJPanel;
import GiaoDien.SanPhamJPanel;
import GiaoDien.TrangChuJPanel;
import GiaoDien.ViTriSPJPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.w3c.dom.Node;

public class ChuyenManHinhController {
    private JPanel root;
    private String kindSelected = "";
    private List<DanhMucBean> listItem = null; 

    public ChuyenManHinhController(JPanel jpnroot) {
        this.root = jpnroot;
    }
    
    public void setView(JPanel jpnItem, JLabel jlbItem){
        kindSelected = "Trang chu";
        jpnItem.setBackground(Color.green);
        jlbItem.setBackground(Color.green);
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new TrangChuJPanel());
        root.validate();
        root.repaint();
    }
    public void setEvent(List<DanhMucBean> listItem){
        this.listItem = listItem;
        for(DanhMucBean Item : listItem){ 
            Item.getJlb().addMouseListener(new LabelEvent(Item.getKind(), Item.getJpn(), Item.getJlb()));
        }       
    }       
     class LabelEvent implements MouseListener{
         private JPanel node ;
         private String kind ;
         
         private JPanel jpnItem;
         private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "Trangchu":
                    node = new TrangChuJPanel();
                    break;
                case "Sanpham":
                    node = new SanPhamJPanel();
                    break;
                case "Khachhang":
                    node = new KhachHangJPanel();
                    break;
                case "Vitrisanpham":
                    node = new ViTriSPJPanel();
                    break;
                    default:
                     node = new TrangChuJPanel();   
                        break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(Color.GREEN);
            jlbItem.setBackground(Color.GREEN);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(Color.GREEN);
            jlbItem.setBackground(Color.GREEN);
        }

        @Override
        public void mouseExited(MouseEvent e){ 
            if(kindSelected.equalsIgnoreCase(kind)){
                jpnItem.setBackground(Color.WHITE);
                jlbItem.setBackground(Color.WHITE);
        }
        } 
            private void setChangeBackground(String kind){
                for(DanhMucBean item : listItem ){
                    if(item.getKind().equalsIgnoreCase(kind)){
                        item.getJpn().setBackground(Color.GREEN);
                        item.getJlb().setBackground(Color.GREEN);
                }else{
                        item.getJpn().setBackground(Color.WHITE);
                        item.getJlb().setBackground(Color.WHITE);
                    }
            }
     }

}
}
