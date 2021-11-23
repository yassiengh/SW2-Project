import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.sql.*;
import java.util.Scanner;

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

    }

    @Override
    public void register() {

    }

    @Override
    public void banAccount(int userID) {

    }
}
