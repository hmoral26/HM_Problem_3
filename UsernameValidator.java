import java.util.Map;


// Checks that the username is not empty and is at least 5 characters
public class UsernameValidator implements Validator {
    private Validator nextValidator;


    // Links this validator to the next one
    @Override
    public void setNextValidator(Validator nextValidator) {
        this.nextValidator = nextValidator;
    }


    // Checks the username and continues to the next validator
    @Override
    public boolean validate(UserRegistration user, Map<String, String> errors) {
        String username = user.getUsername();
        boolean valid = true;
        if (username == null || username.trim().length() < 5) {
            errors.put("username", "Username must be at least 5 characters.");
            valid = false;
        }
        boolean nextValid = (nextValidator == null) || nextValidator.validate(user, errors);
        return valid && nextValid;


    }


}
