import java.sql.*;
import javax.swing.*;

public class DataBaseConnect {

    public static Connection ConnectDB(){

        String url = "jdbc:mysql://localhost:3306/uber";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
            //connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

}
