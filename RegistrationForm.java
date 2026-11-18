import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


// Swing window that collects registration info and runs the validation chain
public class RegistrationForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField phoneField;
    private JLabel usernameError;
    private JLabel passwordError;
    private JLabel emailError;
    private JLabel phoneError;
    private Validator firstValidator;


    // Builds the form and sets up the chain
    public RegistrationForm() {
        setTitle("User Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        usernameField = new JTextField(20);
        usernameError = createErrorLabel();
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(usernameError);

        passwordField = new JPasswordField(20);
        passwordError = createErrorLabel();
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(passwordError);

        emailField = new JTextField(20);
        emailError = createErrorLabel();
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(emailError);

        phoneField = new JTextField(20);
        phoneError = createErrorLabel();
        panel.add(new JLabel("Phone Number (optional):"));
        panel.add(phoneField);
        panel.add(phoneError);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> handleRegister());
        panel.add(Box.createVerticalStrut(10));
        panel.add(registerButton);


        add(panel, BorderLayout.NORTH);
        pack();
        setSize(600, getHeight());
        setLocationRelativeTo(null);

        buildChain();
    }



    // Creates a red empty error label
    private JLabel createErrorLabel() {
        JLabel label = new JLabel(" ");
        label.setForeground(Color.RED);
        return label;
    }


    // Links the four validators in order
    private void buildChain() {
        UsernameValidator usernameValidator = new UsernameValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        EmailValidator emailValidator = new EmailValidator();
        PhoneNumberValidator phoneValidator = new PhoneNumberValidator();

        usernameValidator.setNextValidator(passwordValidator);
        passwordValidator.setNextValidator(emailValidator);
        emailValidator.setNextValidator(phoneValidator);

        firstValidator = usernameValidator;
    }


    // Reads the form, runs the chain, and shows errors or success
    private void handleRegister() {
        usernameError.setText(" ");
        passwordError.setText(" ");
        emailError.setText(" ");
        phoneError.setText(" ");

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String email = emailField.getText();
        String phone = phoneField.getText();

        UserRegistration user = new UserRegistration(username, password, email, phone);
        Map<String, String> errors = new HashMap<>();

        boolean valid = firstValidator.validate(user, errors);

        usernameError.setText(errors.getOrDefault("username", " "));
        passwordError.setText(errors.getOrDefault("password", " "));
        emailError.setText(errors.getOrDefault("email", " "));
        phoneError.setText(errors.getOrDefault("phoneNumber", " "));

        if (valid) {
            JOptionPane.showMessageDialog(this, "Registration successful!");
        }

    }


    // Starts the Swing window
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationForm().setVisible(true));
    }


}



