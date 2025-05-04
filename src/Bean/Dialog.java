package Bean;

import Main.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.*;

public class Dialog extends JDialog {
    private String content;
    private int type;
    public final static int ERROR_DIALOG = 1;
    public final static int SUCCESS_DIALOG = 2;
    public final static int WARNING_DIALOG = 3;

    public Dialog(String content, int type) {
        Main.changLNF("Windows");
        this.content = content;
        this.type = type;
        addControls();
        addEvents();
        showWindow();
    }

    JPanel pnMain, pnHeader, pnTop, pnBottom, pnButtonFooter, pnCenter;
    JLabel lblIcon, lblContent, lblClose;
    JButton btnOK, btnCancel;
    final ImageIcon iconError = new ImageIcon("Images/iconError.png");
    final ImageIcon iconSuccess = new ImageIcon("Images/iconCheck.png");
    final ImageIcon iconWarning = new ImageIcon("Images/iconWarning.png");
    
    private void addControls() {
        Container con = getContentPane();
        con.setLayout(new BorderLayout());

        // Main panel
        pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.setBackground(Color.WHITE);
        pnMain.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));

        // Header panel with close icon
        pnHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnHeader.setPreferredSize(new Dimension(400, 25));
        ImageIcon closeIcon = new ImageIcon("Images/iconClose.png");
        Image scaledCloseIcon = closeIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        lblClose = new JLabel(new ImageIcon(scaledCloseIcon));
        lblClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnHeader.add(lblClose);

        // Set background and icon based on type
        Color backgroundHeader = Color.WHITE;
        Image scaledDialogIcon = null;
        lblIcon = new JLabel(); // Khởi tạo mặc định
        switch (type) {
            case ERROR_DIALOG:
                backgroundHeader = new Color(211,211,211);
                scaledDialogIcon = iconError.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                lblIcon.setIcon(new ImageIcon(scaledDialogIcon));
                break;
            case SUCCESS_DIALOG:
                backgroundHeader = new Color(211,211,211);
                scaledDialogIcon = iconSuccess.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                lblIcon.setIcon(new ImageIcon(scaledDialogIcon));
                break;
            case WARNING_DIALOG:
                backgroundHeader = new Color(211,211,211);
                scaledDialogIcon = iconWarning.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                lblIcon.setIcon(new ImageIcon(scaledDialogIcon));
                break;
            default:
                backgroundHeader = Color.GRAY;
                break;
        }
        pnHeader.setBackground(backgroundHeader);

        // Center panel to hold icon and content
        pnCenter = new JPanel();
        pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
        pnCenter.setBackground(Color.WHITE);

        // Top panel for icon
        pnTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnTop.setBackground(Color.WHITE);
        pnTop.add(lblIcon);

        // Bottom panel for content
        pnBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnBottom.setBackground(Color.WHITE);
        lblContent = new JLabel(content);
        lblContent.setFont(new Font("", Font.PLAIN, 18));
        lblContent.setHorizontalAlignment(JTextField.CENTER);
        lblContent.setForeground(Color.BLACK);
        lblContent.setText("<html><div style='text-align: center; width:300px'>" + content + "</div></html>");
        pnBottom.add(lblContent);

        // Add top and bottom to center panel
        pnCenter.add(pnTop);
        pnCenter.add(pnBottom);

        // Button and footer panel
        pnButtonFooter = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnButtonFooter.setBackground(Color.WHITE);
        btnOK = new JButton("OK");
        btnOK.setPreferredSize(new Dimension(80, 30));
        pnButtonFooter.add(btnOK);
        if (type == WARNING_DIALOG) {
            btnCancel = new JButton("Cancel");
            btnCancel.setPreferredSize(new Dimension(80, 30));
            pnButtonFooter.add(btnCancel);
        }

        // Add panels to main
        pnMain.add(pnHeader, BorderLayout.NORTH);
        pnMain.add(pnCenter, BorderLayout.CENTER);
        pnMain.add(pnButtonFooter, BorderLayout.SOUTH);

        con.add(pnMain);
    }

    private void addEvents() {
        lblClose.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                closeDialog();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeDialog();
                action = OK_OPTION;
            }
        });

        if (btnCancel != null) {
            btnCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    closeDialog();
                    action = CANCEL_OPTION;
                }
            });
        }

        pnHeader.addMouseMotionListener(new MouseMotionListener() {
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

    int xMouse = 0, yMouse = 0;

    private void Move(int x, int y) {
        this.setLocation(x - xMouse, y - yMouse);
    }

    private int action;
    public final static int OK_OPTION = 1;
    public final static int CANCEL_OPTION = 2;

    private void closeDialog() {
        this.setVisible(false);
    }

    public int getAction() {
        return action;
    }

    private void showWindow() {
        this.setUndecorated(true);
        this.setSize(400, 250);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setModal(true);
        this.setBackground(Color.WHITE);
        this.setVisible(true);
        getRootPane().setDefaultButton(btnOK);
    }
}