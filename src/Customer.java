import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Scanner;

public class Customer extends User implements CustomerInterface{
    public Customer(){}
    public Customer(String userName, String email, String password, Long phoneNumber, int userID, int rating) {
        super(userName, email, password, phoneNumber, userID, rating);
    }
    Connection conn = DataBaseConnect.ConnectDB();
    @Override
    public void requestDrive(int userID) {
        int ID = userID;
        int rideID =0;
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter your current location : ");
        String Src = in.nextLine();
        System.out.println("Please enter your Destination : ");
        String Des = in.nextLine();
        String countQuery = "Select Count(*) AS total from rides";
        String insertQuery = "INSERT into rides values(?,?,?,?)";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(countQuery);
            if(rs.next()){
                rideID = rs.getInt("total");
            }
            PreparedStatement insertPreparedStatement = conn.prepareStatement(insertQuery);
            insertPreparedStatement.setInt(1,rideID);
            insertPreparedStatement.setInt(2,ID);
            insertPreparedStatement.setString(3,Src);
            insertPreparedStatement.setString(4,Des);
            insertPreparedStatement.execute();

        }catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void register() {

    }
}
