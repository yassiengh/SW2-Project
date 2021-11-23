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

    }
}
