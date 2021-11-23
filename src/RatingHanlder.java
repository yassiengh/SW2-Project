import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Scanner;

public class RatingHanlder implements RatingHanlderInterface {

    Connection conn = DataBaseConnect.ConnectDB();

    @Override
    public void rate(int raterID , int ratedID) {
        int rating;
        double sum = 0;
        double count = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("How would you rate your ride from 1 to 5 ?");
        while(true){
            rating = in.nextInt();
            if(rating >= 0 && rating <=5){
                break;
            }else{
                System.out.println("Please enter a valid rating : ");
            }
        }
        int rateSrcID = raterID;
        int rateDesID = ratedID;
        String insertQuery = "INSERT INTO ratings VALUES(?,?,?)";
        String updateQuery = "UPDATE user SET rating = ? Where userID = ?";
        String sumQuery = "SELECT SUM(rating) AS sum from ratings WHERE ratedID = "+rateDesID;
        String countQuery = "SELECT COUNT(*) AS count from ratings WHERE ratedID = "+rateDesID;
        try{
            // insert fe table el ratings
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
            insertStatement.setInt(1,rateSrcID);
            insertStatement.setInt(2,rateDesID);
            insertStatement.setInt(3,rating);

            insertStatement.execute();
            // get info to update rating el user
            Statement st = conn.createStatement();
            ResultSet rs1 = st.executeQuery(sumQuery);
            if(rs1.next()){
                sum = rs1.getInt("sum");
            }
            ResultSet rs2 = st.executeQuery(countQuery);
            if(rs2.next()){
                count = rs2.getInt("count");
            }
            System.out.println(sum);
            System.out.println(count);
            double newRating = sum / count;
            newRating = (double) Math.round(newRating*100)/100;
            System.out.println(newRating);
            //update user rating
            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
            updateStatement.setDouble(1,newRating);
            updateStatement.setInt(2,rateDesID);
            updateStatement.execute();

        }catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
