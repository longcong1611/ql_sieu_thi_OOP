package Main;

import GiaoDien.DangNhapGUI;
import GiaoDien.MainJFrame;
import KetNoiSQL.ConnectToXampp;

public class Main {
    public static void main(String[] args){
        new ConnectToXampp();
        
        changLNF("Nimbus");
        DangNhapGUI login = new DangNhapGUI();
        login.showWindow();
    }
    public static void changLNF(String nameLNF){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (nameLNF.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        }

    }
}
