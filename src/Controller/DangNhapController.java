<<<<<<< HEAD
package Controller;

import Bean.PhanQuyen;
import TrungGian.TaiKhoan;
import Bean.Dialog;
import KetNoiSQL.DangNhapSQL;

import java.io.*;
import javax.swing.*;

public class DangNhapController {
    private final static int WRONG_ERROR = 2;
    public static TaiKhoan taiKhoanLogin = null;

    public TaiKhoan getTaiKhoanDangNhap(String user, String password, boolean selected) {
        int validationResult = kiemTraDangNhap(user, password);
        if (validationResult == WRONG_ERROR) {
            showDialog("Sai thông tin đăng nhập!", Dialog.ERROR_DIALOG);
            return null;
        }

        TaiKhoan account = taiKhoanLogin;
        PhanQuyenController phanQuyenController = new PhanQuyenController();
        phanQuyenController.kiemTraQuyen(account.getQuyen());
        xuLyGhiNhoDangNhap(user, password, selected);
        showDialog("Đăng nhập thành công!", Dialog.SUCCESS_DIALOG);
        return account;
    }

    public String getTaiKhoanGhiNho() {
        File file = new File("remember.dat");
        if (!file.exists() || file.length() == 0) {
            return "";
        }
        try (FileInputStream fis = new FileInputStream("remember.dat");
             BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
            String line = br.readLine();
            return line != null ? line : "";
        } catch (IOException e) {
            System.err.println("Error reading remember.dat: " + e.getMessage());
            return "";
        }
    }

    private void xuLyGhiNhoDangNhap(String user, String password, boolean selected) {
        if (!selected) {
            // Xóa file nếu không chọn ghi nhớ
            File file = new File("remember.dat");
            if (file.exists()) {
                file.delete();
            }
            return;
        }

        try (FileWriter fw = new FileWriter("remember.dat");
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(user + " | " + password);
            bw.flush();
        } catch (IOException e) {
            System.err.println("Error writing to remember.dat: " + e.getMessage());
        }
    }

    private int kiemTraDangNhap(String user, String password) {
        TaiKhoan tk = new TaiKhoan();
        tk.setTenDangNhap(user);
        tk.setMatKhau(password);

        DangNhapSQL dangNhapSQL = new DangNhapSQL();
        TaiKhoan account = dangNhapSQL.dangNhap(tk);
        if (account != null) {
            taiKhoanLogin = account;
            return 0;
        } else {
            taiKhoanLogin = null;
            return WRONG_ERROR;
        }
    }

    private void showDialog(String message, int dialogType) {
        SwingUtilities.invokeLater(() -> {
            Dialog dialog = new Dialog(message, dialogType);
            dialog.setVisible(true);
        });
    }
=======
package Controller;

import TrungGian.PhanQuyen;
import TrungGian.TaiKhoan;
import Bean.Dialog;
import KetNoiSQL.DangNhapSQL;

import java.io.*;
import javax.swing.*;

public class DangNhapController {
    private final static int WRONG_ERROR = 2;
    public static TaiKhoan taiKhoanLogin = null;

    public TaiKhoan getTaiKhoanDangNhap(String user, String password, boolean selected) {
        int validationResult = kiemTraDangNhap(user, password);
        if (validationResult == WRONG_ERROR) {
            showDialog("Sai thông tin đăng nhập!", Dialog.ERROR_DIALOG);
            return null;
        }

        TaiKhoan account = taiKhoanLogin;
        PhanQuyenController phanQuyenController = new PhanQuyenController();
        phanQuyenController.kiemTraQuyen(account.getQuyen());
        xuLyGhiNhoDangNhap(user, password, selected);
        showDialog("Đăng nhập thành công!", Dialog.SUCCESS_DIALOG);
        return account;
    }

    public String getTaiKhoanGhiNho() {
        File file = new File("remember.dat");
        if (!file.exists() || file.length() == 0) {
            return "";
        }
        try (FileInputStream fis = new FileInputStream("remember.dat");
             BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
            String line = br.readLine();
            return line != null ? line : "";
        } catch (IOException e) {
            System.err.println("Error reading remember.dat: " + e.getMessage());
            return "";
        }
    }

    private void xuLyGhiNhoDangNhap(String user, String password, boolean selected) {
        if (!selected) {
            // Xóa file nếu không chọn ghi nhớ
            File file = new File("remember.dat");
            if (file.exists()) {
                file.delete();
            }
            return;
        }

        try (FileWriter fw = new FileWriter("remember.dat");
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(user + " | " + password);
            bw.flush();
        } catch (IOException e) {
            System.err.println("Error writing to remember.dat: " + e.getMessage());
        }
    }

    private int kiemTraDangNhap(String user, String password) {
        TaiKhoan tk = new TaiKhoan();
        tk.setTenDangNhap(user);
        tk.setMatKhau(password);

        DangNhapSQL dangNhapSQL = new DangNhapSQL();
        TaiKhoan account = dangNhapSQL.dangNhap(tk);
        if (account != null) {
            taiKhoanLogin = account;
            return 0;
        } else {
            taiKhoanLogin = null;
            return WRONG_ERROR;
        }
    }

    private void showDialog(String message, int dialogType) {
        SwingUtilities.invokeLater(() -> {
            Dialog dialog = new Dialog(message, dialogType);
            dialog.setVisible(true);
        });
    }
>>>>>>> bef1f7b (commit)
}