import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SuggestionHandler implements SuggestionHandlerInterface{
    @Override
    public void ViewRidesInFavArea(String FavouriteArea) {
        Connection conn = DataBaseConnect.ConnectDB();
        String query = "SELECT rideID, user.userID, src, des, userName from rides, user " +
                "where rides.userID = user.userID AND src = \"" + FavouriteArea + "\"";
        try {
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getInt("rideID"));
                System.out.println(rs.getInt("userID"));
                System.out.println(rs.getString("userName"));
                System.out.println(rs.getString("src"));
                System.out.println(rs.getString("des"));
                System.out.println();
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void offerPrice(int rideID, int price) {
        Connection conn = DataBaseConnect.ConnectDB();
        String query = "UPDATE rides set offeredPrice = " + price + " where rideID = " + rideID;
        try {
            Statement st = conn.createStatement();
            st.execute(query);
            System.out.println("price offered\n");
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
