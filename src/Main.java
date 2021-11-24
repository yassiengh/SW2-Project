// import DataBaseConnect;

import java.awt.HeadlessException;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    public static void main(String arg[]) {
        DataBaseConnect dataBaseConnect = new DataBaseConnect();
        Connection conn = DataBaseConnect.ConnectDB();

        String username = "yassien";
        String email = "yassiengyahoo.com";
        String password = "password";
        int phonenumber = 0000;
        int Rating = 5;
        String type = "admin";


        /*try{
            String query = "INSERT INTO user"+ " VALUES (?,?,?,?,?,?,?)";
          //  String query = "DELETE from user";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1,3);
            preparedStatement.setString(2,username);
            preparedStatement.setString(3,email);
            preparedStatement.setString(4,password);
            preparedStatement.setInt(5,phonenumber);
            preparedStatement.setString(6,type);
            preparedStatement.setInt(7,Rating);

            preparedStatement.execute();

        }catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, e);
        }*/

//        Admin a = new Admin();
//        a.ViewPendingDriverRegistration();

        SuggestionHandler sh = new SuggestionHandler();
        sh.ViewRidesInFavArea("obour");
        sh.offerPrice(1, 40);
    }
}
