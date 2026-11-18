// Holds the four values the user entered on the form
public class UserRegistration {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;



    // Saves the four values from the form
    public UserRegistration(String username, String password, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }



    // Returns the username
    public String getUsername() { return username; }
    // Returns the password
    public String getPassword() { return password; }
    // Returns the email
    public String getEmail() { return email; }
    // Returns the phone number
    public String getPhoneNumber() { return phoneNumber; }


}
