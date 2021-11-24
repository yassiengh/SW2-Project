import java.sql.*;
import java.util.logging.Logger;

public abstract class User {
    String userName;
    String email;
    String password;
    Long phoneNumber;
    int userID;
    int rating;

    public User(){}
    public User(String userName,String email,String password,Long phoneNumber,int userID,int rating){
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
        this.userID = userID;
    }

    public abstract void register();

    public void login(String email,String password){
        ResultSet rs;
        PreparedStatement ps2;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/uber","root","");

            String query2="SELECT * FROM `user` WHERE `PW`=? and `Email`=?";
            ps2 = con.prepareStatement(query2);
            ps2.getResultSet();
            ps2.setString(1, password);
            ps2.setString(2, email);

            rs =ps2.executeQuery();

            if(rs.next()){
                System.out.println("login succesffully");
            }else{
                System.out.println("22221ly");
            }} catch (ClassNotFoundException ex) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
