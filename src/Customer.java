import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        Scanner in = new Scanner(System.in);
        System.out.println("Please Enter these data below..");
        System.out.println("username");
        String username =in.nextLine();
        System.out.println("Password");
        String password =in.nextLine();
        System.out.println("Email");
        String email=in.nextLine();
        System.out.println("Type");
        String type=in.nextLine();
        System.out.println("Phone Number");
        long phoneNumber=in.nextInt();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/uber","root","");
            Statement stmt=con.createStatement();
            String query2="select `Email` From user where email=?";
            PreparedStatement ps2 = con.prepareStatement(query2);
            ps2.setString(1, email);
            ResultSet result =(ResultSet) ps2.executeQuery();
            if(!result.next()){
                String query = " insert into user (`UserName`, `PW`, `Email`, `PN`, `Type`,`Rating`)values (?, ?, ?, ?,?,?)";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString (1, username);
                preparedStmt.setString (2, password);
                preparedStmt.setString(3, email);
                preparedStmt.setLong(4, phoneNumber);
                preparedStmt.setString(5, type);
                preparedStmt.setInt(6, 0);
                preparedStmt.execute();


            }else{
                System.out.println("Please register again with another email");
            }



        }   catch (ClassNotFoundException ex) {
            Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
