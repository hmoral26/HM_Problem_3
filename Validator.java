import java.util.Map;


// Interface that every validator in the chain must follow
public interface Validator {

    // Sets the next validator in the chain
    void setNextValidator(Validator nextValidator);


    // Checks this rule and then passes the request to the next validator
    boolean validate(UserRegistration user, Map<String, String> errors);
}



