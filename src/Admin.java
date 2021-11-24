import javax.swing.*;
import java.sql.*;

public class Admin extends User implements  AdminInterface{


    public Admin() {
        super();
    }

    public Admin(String userName, String email, String password, Long phoneNumber, int userID, int rating) {
        super(userName, email, password, phoneNumber, userID, rating);
    }

    @Override
    public void verifyForms(int userID) {

    }

    @Override
    public void ViewPendingDriverRegistration() {
        Connection conn = DataBaseConnect.ConnectDB();
        String query = "SELECT user.userID, userName, phoneNumber, license, email,favouriteArea from user, registrationform where user.userID = registrationform.userID";
        try {
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getInt("userID"));
                System.out.println(rs.getString("userName"));
                System.out.println(rs.getInt("phoneNumber"));
                System.out.println(rs.getString("email"));
                System.out.println(rs.getString("license"));
                System.out.println(rs.getString("favouriteArea"));
                System.out.println();
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void register() {

    }

    @Override
    public void banAccount(int userID) {

    }
}
