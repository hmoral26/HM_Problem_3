import java.util.Map;


// Checks the phone number only if the user entered one
public class PhoneNumberValidator implements Validator {
    private Validator nextValidator;


    // Links this validator to the next one
    @Override
    public void setNextValidator(Validator nextValidator) {
        this.nextValidator = nextValidator;
    }



    // Allows a blank phone; if something is entered it must be exactly 10 digits
    @Override
    public boolean validate(UserRegistration user, Map<String, String> errors) {
        String phone = user.getPhoneNumber();
        boolean blank = phone == null || phone.trim().isEmpty();
        boolean valid = blank || phone.matches("[0-9]{10}");
        if (!valid) {
            errors.put("phoneNumber", "Phone must be exactly 10 digits.");
        }
        boolean nextValid = (nextValidator == null) || nextValidator.validate(user, errors);
        return valid && nextValid;


    }


}


