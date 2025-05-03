package KetNoiSQL;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ngoc Lan
 */
public class ConnectToXampp {
    public static Connection conn = null;
    static {
        try{
        String url = "jdbc:mysql://localhost:3307/quanlysieuthi";
        String username = "root";
        String password = "";
        conn = DriverManager.getConnection(url, username, password);
        System.out.println("Kết nối SQL thành công");
        System.out.println(conn.getCatalog());
        } catch (SQLException ex) {
            Logger.getLogger(ConnectToXampp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
