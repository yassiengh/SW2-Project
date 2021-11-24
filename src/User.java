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
        // code goes here
    }
}
