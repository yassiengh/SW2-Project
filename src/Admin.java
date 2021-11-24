import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin extends User implements  AdminInterface{

    Connection conn = DataBaseConnect.ConnectDB();

    Admin(){}
    public Admin(String userName, String email, String password, Long phoneNumber, int userID, int rating) {
        super(userName, email, password, phoneNumber, userID, rating);
    }

    @Override
    public void verifyForms(int userID) {
            String query = "SELECT * from registrationform form WHERE userID = "+userID;
            String favourtieArea = "";
            String license = "";
            try{
                String dropQuery = "Delete from registrationform Where userID = "+userID;
                PreparedStatement dropPreparedStatement = conn.prepareStatement(dropQuery);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                if(rs.next()){
                    favourtieArea = rs.getString("favouriteArea");
                    license = rs.getString("license");
                }
                System.out.println(favourtieArea);
                System.out.println(license);
                Scanner in = new Scanner(System.in);
                System.out.println("Enter your decision ( approve or deny ) : ");
                String decision = in.nextLine();
                if(decision.equalsIgnoreCase("deny")){
                    dropPreparedStatement.execute();
                    System.out.println("Form Denied");
                }else if(decision.equalsIgnoreCase("approve")){
                    String AddQuery = "INSERT into driver Values (?,?,?)";
                    PreparedStatement AddPreparedStatement = conn.prepareStatement(AddQuery);
                    AddPreparedStatement.setInt(1,userID);
                    AddPreparedStatement.setString(2,favourtieArea);
                    AddPreparedStatement.setString(3,license);
                    AddPreparedStatement.execute();
                    dropPreparedStatement.execute();
                    System.out.println("Driver approved");
                }else{
                    System.out.println("not a valid Decision");

                }

            }catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, e);
            }
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

    @Override
    public void banAccount(int userID) {

    }
}
