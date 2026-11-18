import java.util.Map;


// Checks that the password is at least 8 characters and has upper, lower, and a digit
public class PasswordValidator implements Validator {
    private Validator nextValidator;


    // Links this validator to the next one
    @Override
    public void setNextValidator(Validator nextValidator) {
        this.nextValidator = nextValidator;
    }


    // Checks the password rules and continues to the next validator
    @Override
    public boolean validate(UserRegistration user, Map<String, String> errors) {
        String password = user.getPassword();
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        if (password != null) {
            for (char c : password.toCharArray()) {
                if (Character.isUpperCase(c)) hasUpper = true;
                if (Character.isLowerCase(c)) hasLower = true;
                if (Character.isDigit(c)) hasDigit = true;
            }
        }
        boolean valid = password != null && password.length() >= 8 && hasUpper && hasLower && hasDigit;
        if (!valid) {
            errors.put("password", "Password must be at least 8 characters with upper, lower, and a digit.");
        }
        boolean nextValid = (nextValidator == null) || nextValidator.validate(user, errors);
        return valid && nextValid;


    }


}
