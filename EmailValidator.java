import java.util.Map;


// Checks that the email has a valid format
public class EmailValidator implements Validator {
    private Validator nextValidator;


    // Links this validator to the next one
    @Override
    public void setNextValidator(Validator nextValidator) {
        this.nextValidator = nextValidator;
    }


    // Checks the email and continues to the next validator
    @Override
    public boolean validate(UserRegistration user, Map<String, String> errors) {
        String email = user.getEmail();


        boolean valid = email != null
                && email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")
                && !email.contains("..")
                && !email.startsWith(".")
                && !email.contains("@-");


        if (!valid) {
            errors.put("email", "Enter a valid email like name@example.com.");
        }


        boolean nextValid = (nextValidator == null) || nextValidator.validate(user, errors);
        return valid && nextValid;


    }


}
