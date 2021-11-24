import java.sql.SQLException;

public interface AdminInterface {

        public void verifyForms(int userID);
        public void ViewPendingDriverRegistration() throws SQLException;
        public void register();
        public void banAccount(int userID);

}
