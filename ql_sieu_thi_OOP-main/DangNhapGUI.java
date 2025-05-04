package GiaoDien;

import Main.Main;
import Bean.Dialog;
import TrungGian.TaiKhoan;
import Bean.ImagePanel;
import Controller.DangNhapController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DangNhapGUI extends JFrame {
    public DangNhapGUI() {
        Main.changLNF("Windows"); 
        this.setTitle("Đăng nhập");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        addControls();
        xuLyTaiKhoanDaGhiNho();
        addEvents();
    }
    
    private JLabel btnExit;
    private JButton btnLogin;
    private JTextField txtUser;
    private JPasswordField txtPassword;
    private JPanel pnMain, pnCenter, pnSouth;
    private JCheckBox ckbRemember;
    
    private void xuLyTaiKhoanDaGhiNho() {
        DangNhapController dangNhapController = new DangNhapController();
        String line = dangNhapController.getTaiKhoanGhiNho();
        if (line != null && !line.trim().isEmpty()) {
            try {
                String[] arr = line.split(" \\| ");
                if (arr.length == 3) {
                    ckbRemember.setSelected(true);
                    txtUser.setText(arr[0]);
                    txtPassword.setText(arr[2]);
                    txtUser.requestFocus();
                } else {
                    throw new Exception("Định dạng tài khoản không hợp lệ");
                }
            } catch (Exception e) {
                txtUser.setText("");
                txtPassword.setText("");
                txtUser.requestFocus();
            }
        } else {
            txtUser.setText("");
            txtPassword.setText("");
            txtUser.requestFocus();
        }
    }

    private void addControls() {
        Container con = getContentPane();
        con.setLayout(new BorderLayout(10, 10));

        pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout(20, 20));
        pnMain.setBackground(Color.LIGHT_GRAY);
        pnMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel pnTop = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnTop.setOpaque(false);
        ImageIcon originalIcon = new ImageIcon("Images/iconClose.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        btnExit = new JLabel(new ImageIcon(scaledImage));        
        btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnTop.add(btnExit);

        pnCenter = new JPanel();
        pnCenter.setLayout(new GridBagLayout());
        pnCenter.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;        
        
        Font fontTXT = new Font("Arial", Font.BOLD, 18);
        
        JLabel lblUser = new JLabel("Tên đăng nhập:");
        lblUser.setForeground(Color.BLACK);
        lblUser.setFont(fontTXT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnCenter.add(lblUser, gbc);
        
        txtUser = new JTextField();
        txtUser.setBackground(new Color(250, 250, 250));
        txtUser.setForeground(Color.BLACK);
        txtUser.setFont(fontTXT);
        txtUser.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        pnCenter.add(txtUser, gbc);

        JLabel lblPassword = new JLabel("Mật khẩu:");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setFont(fontTXT);
        gbc.gridx = 0;
        gbc.gridy = 2;
        pnCenter.add(lblPassword, gbc);
        
        txtPassword = new JPasswordField();
        txtPassword.setEchoChar('•');
        txtPassword.setBackground(new Color(250, 250, 250));
        txtPassword.setForeground(Color.BLACK);
        txtPassword.setFont(fontTXT);
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        gbc.gridx = 0;
        gbc.gridy = 3;
        pnCenter.add(txtPassword, gbc);

        ckbRemember = new JCheckBox("Ghi nhớ đăng nhập");
        ckbRemember.setFont(new Font("Arial", Font.PLAIN, 16));
        ckbRemember.setOpaque(false);
        ckbRemember.setForeground(Color.BLACK);
        ckbRemember.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 4;
        pnCenter.add(ckbRemember, gbc);

        pnSouth = new JPanel();
        pnSouth.setOpaque(false);
        btnLogin = new JButton("Đăng nhập");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 18));
        btnLogin.setBackground(new Color(0, 120, 215));
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.setFocusPainted(false);
        pnSouth.add(btnLogin);        

        pnMain.add(pnTop, BorderLayout.NORTH);
        pnMain.add(pnCenter, BorderLayout.CENTER);
        pnMain.add(pnSouth, BorderLayout.SOUTH);   
        
        con.add(pnMain);
    }

    private void addEvents() {
        moveFrame();
        btnExit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyThoat();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon hoverIcon = new ImageIcon("Images/iconClose-hover.png");
                Image scaledHoverImage = hoverIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
                btnExit.setIcon(new ImageIcon(scaledHoverImage));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon defaultIcon = new ImageIcon("Images/iconClose.png");
                Image scaledDefaultImage = defaultIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
                btnExit.setIcon(new ImageIcon(scaledDefaultImage));
            }
        });

        txtUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtPassword.requestFocus();
            }
        });

        txtPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyDangNhap();
            }
        });

        btnLogin.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyDangNhap();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogin.setBackground(new Color(45, 105, 205));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnLogin.setBackground(new Color(0, 120, 215));
            }
        });
    }

    int xMouse = 0, yMouse = 0;

    private void moveFrame() {
        pnMain.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                Move(x, y);
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });
    }

    private void Move(int x, int y) {
        this.setLocation(x - xMouse, y - yMouse);
    }

    private void xuLyThoat() {
        System.exit(0);
    }

    private void xuLyDangNhap() {
        String username = txtUser.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            String message = "Vui lòng nhập ";
            if (username.isEmpty()) message += "tên đăng nhập";
            if (username.isEmpty() && password.isEmpty()) message += " và ";
            if (password.isEmpty()) message += "mật khẩu";
            message += "!";
            new Dialog(message, Dialog.ERROR_DIALOG);
            if (username.isEmpty()) txtUser.requestFocus();
            else txtPassword.requestFocus();
            return;
        }

        DangNhapController dangNhapController = new DangNhapController();
        TaiKhoan tk = dangNhapController.getTaiKhoanDangNhap(username, password, ckbRemember.isSelected());
        if (tk != null) {
            this.dispose();
            MainJFrame gui = new MainJFrame();
            gui.showWindow();
        }
    }

    public void showWindow() {
        Image icon = Toolkit.getDefaultToolkit().getImage("Image/iconApp.png");
        this.setIconImage(icon);
        this.setVisible(true);
    }
}