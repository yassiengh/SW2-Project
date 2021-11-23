public class Customer extends User implements CustomerInterface{

    public Customer(String userName, String email, String password, Long phoneNumber, int userID, int rating) {
        super(userName, email, password, phoneNumber, userID, rating);
    }

    @Override
    public void requestDrive(String Src, String Des) {

    }

    @Override
    public void register() {

    }
}
