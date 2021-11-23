public class Admin extends User implements  AdminInterface{


    public Admin(String userName, String email, String password, Long phoneNumber, int userID, int rating) {
        super(userName, email, password, phoneNumber, userID, rating);
    }

    @Override
    public void verifyForms(int userID) {

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
