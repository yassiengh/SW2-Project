import java.sql.*;
import java.util.Scanner;
import java.util.logging.Logger;

public class Driver extends User{

    String license;
    Long nationalID;
    boolean verified;
    String favouriteArea;

    public Driver(String userName, String email, String password, Long phoneNumber,
                  int userID, int rating,Long nationalID,boolean verified, String favouriteArea) {
        super(userName, email, password, phoneNumber, userID, rating);
        this.license = license;
        this.nationalID = nationalID;
        this.verified = verified;
        this.favouriteArea = favouriteArea;

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
            System.out.println("lisence");
            String licence=in.nextLine();
            System.out.println("nationalID");
            String nationalID=in.nextLine();
            System.out.println("Phone Number");
            long phoneNumber=in.nextInt();

            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/uber","root","");
                Statement stmt=con.createStatement();
                Statement stmt3=con.createStatement();
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
                    preparedStmt.setInt(5, 2);
                    preparedStmt.setInt(6, 0);

                    preparedStmt.execute();



                    String Q2="INSERT INTO `registrationform`(`License`, `FavouriteArea`, `nationalID`, `Email`) VALUES (?,?,?,?)";
                    PreparedStatement stmt2 = con.prepareStatement(Q2);
                    stmt2.setString (1, licence);
                    stmt2.setString (2, "Under-update");
                    stmt2.setString(3, nationalID);
                    stmt2.setString(4, email);
                    stmt2.execute();
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
}
