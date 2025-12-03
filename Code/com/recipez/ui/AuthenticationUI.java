package com.recipez.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.recipez.core.Application;
import com.recipez.user.BodyGoal;
import com.recipez.user.User;
import com.recipez.util.DietType;
import com.recipez.util.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class AuthenticationUI extends JPanel{

    private CardLayout authenticationCardLayout;

    private JPanel authenticationPanel;

    private JPanel loginPanel;

    private JPanel registerPanel;

    private JLabel errorLabelForRegistration;

    private JTextField loginUsernameField;
    private JPasswordField loginPasswordField;

    private JTextField registerUsernameField;
    private JPasswordField registerPasswordField;


    private User loggedInUser;

    public AuthenticationUI() {
        authenticationCardLayout = new CardLayout();
        authenticationPanel = new JPanel(authenticationCardLayout);


        initLoginPanel();
        initRegisterPanel();



        JPanel loginWrapper = new JPanel(new GridBagLayout());
        loginWrapper.setBackground(Color.decode("#121417"));

        GridBagConstraints center = new GridBagConstraints();
        center.gridx = 0;
        center.gridy = 0;
        center.anchor = GridBagConstraints.CENTER;
        center.weightx = 1;
        center.weighty = 1;

        loginWrapper.add(loginPanel, center);

        authenticationPanel.add(loginWrapper, "login");

        JPanel registerWrapper = new JPanel(new GridBagLayout());
        registerWrapper.setBackground(Color.decode("#121417"));

        GridBagConstraints center2 = new GridBagConstraints();
        center2.gridx = 0;
        center2.gridy = 0;
        center2.anchor = GridBagConstraints.CENTER;
        center2.weightx = 1;
        center2.weighty = 1;

        registerWrapper.add(registerPanel, center2);

        authenticationPanel.add(registerWrapper, "register");

        authenticationCardLayout.show(authenticationPanel, "login");
    }

    private void initRegisterPanel() {
        registerPanel = new JPanel(new GridBagLayout());

        registerPanel.setMaximumSize(new Dimension(400, 600));
        registerPanel.setPreferredSize(new Dimension(400, 600));
        registerPanel.setMinimumSize(new Dimension(400, 600));
        registerPanel.setBackground(Color.decode("#252A2F"));

        registerPanel.putClientProperty(FlatClientProperties.STYLE,
                "arc: 40; background: #252A2F;");



        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.gridwidth = 1;

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/assets/recipez.png"));
        Image resizedImageIcon = imageIcon.getImage().getScaledInstance(294, 75, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(resizedImageIcon));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 3;
        registerPanel.add(iconLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 3;
        JLabel createUsernameLabel = new JLabel("Create Username");
        registerPanel.add(createUsernameLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        this.registerUsernameField = new JTextField();
        registerPanel.add(registerUsernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel createPasswordLabel = new JLabel("Create Password");
        registerPanel.add(createPasswordLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        this.registerPasswordField = new JPasswordField();
        registerPanel.add(registerPasswordField, gbc);

        // HANDLE USER INPUT DATA LIKE WEIGHT AND HEIGHT
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 3;
        JLabel weightLabel = new JLabel("Weight (lbs)");
        registerPanel.add(weightLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        JTextField weightTextField = new JTextField();
        registerPanel.add(weightTextField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 1;
        JLabel heightLabel = new JLabel("Height (ft + in)");
        registerPanel.add(heightLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        Integer[] heightFeetOptions = {1, 2, 3, 4, 5, 6, 7};
        JComboBox<Integer> heightFeetComboBox = new JComboBox<>(heightFeetOptions);
        registerPanel.add(heightFeetComboBox, gbc);

        gbc.gridx = 2; gbc.gridy = 4;
        Integer[] heightInchesOptions = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        JComboBox<Integer> heightInchesComboBox = new JComboBox<>(heightInchesOptions);
        registerPanel.add(heightInchesComboBox, gbc);

        // AGE
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 3;
        JLabel ageLabel = new JLabel("Age");
        registerPanel.add(ageLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 5;
        JTextField ageTextField = new JTextField();
        registerPanel.add(ageTextField, gbc);

        // GENDER
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 3;
        JLabel genderLabel = new JLabel("Gender");
        registerPanel.add(genderLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 6;
        String[] genderOptions = {"Male", "Female"};
        JComboBox<String> genderComboBox = new JComboBox<>(genderOptions);
        registerPanel.add(genderComboBox, gbc);

        // DIET TYPES
        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 3;
        JLabel dietTypeLabel = new JLabel("Diet Type");
        registerPanel.add(dietTypeLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 7;
        String[] dietTypeOptions = {"NONE", "VEGAN", "VEGETARIAN", "PESCATARIAN",  "KETO", "CARNIVOROUS"};
        JComboBox<String> dietTypeComboBox = new JComboBox<>(dietTypeOptions);
        registerPanel.add(dietTypeComboBox, gbc);

        // BODY GOAL TYPES
        gbc.gridx = 0; gbc.gridy = 8;
        gbc.gridwidth = 3;
        JLabel bodyGoalLabel = new JLabel("Body Goal");
        registerPanel.add(bodyGoalLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 8;
        String[] bodyGoalOptions = {"CUT", "BULK", "MAINTAIN"};
        JComboBox<String> bodyGoalComboBox = new JComboBox<>(bodyGoalOptions);
        registerPanel.add(bodyGoalComboBox, gbc);


        gbc.gridx = 0; gbc.gridy = 9;
        gbc.gridwidth = 3;
        JButton createAccountButton = new JButton("Sign Up");
        registerPanel.add(createAccountButton, gbc);

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // You can also check which button was pressed if you have multiple
                if (e.getSource() == createAccountButton) {
                    String password = new String(registerPasswordField.getPassword());
                    createAccount(registerUsernameField.getText(), password, weightTextField.getText(), (Integer)heightFeetComboBox.getSelectedItem(), (Integer)heightInchesComboBox.getSelectedItem(), (String)genderComboBox.getSelectedItem(), ageTextField.getText(), (String)dietTypeComboBox.getSelectedItem(), (String)bodyGoalComboBox.getSelectedItem());
                }
            }
        });

        gbc.gridx = 0; gbc.gridy = 10;
        gbc.gridwidth = 3;
        JButton backToLoginButton = new JButton("Back");
        registerPanel.add(backToLoginButton, gbc);


        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // You can also check which button was pressed if you have multiple
                if (e.getSource() == backToLoginButton) {
                    backToLoginButtonClicked();

                    // Reset all text boxes
                    registerUsernameField.setText("");
                    registerPasswordField.setText("");
                    heightFeetComboBox.setSelectedIndex(0);
                    heightInchesComboBox.setSelectedIndex(0);
                    genderComboBox.setSelectedIndex(0);
                    ageTextField.setText("");
                    dietTypeComboBox.setSelectedIndex(0);
                    bodyGoalComboBox.setSelectedIndex(0);
                    errorLabelForRegistration.setText("");

                }
            }
        });

        // STYLING FOR REGISTER PANEL

        String labelStyle = "font:bold; foreground:#C5D1BA;";
        createUsernameLabel.putClientProperty(FlatClientProperties.STYLE, labelStyle);
        createPasswordLabel.putClientProperty(FlatClientProperties.STYLE, labelStyle);
        weightLabel.putClientProperty(FlatClientProperties.STYLE, labelStyle);
        heightLabel.putClientProperty(FlatClientProperties.STYLE, labelStyle);
        ageLabel.putClientProperty(FlatClientProperties.STYLE, labelStyle);
        genderLabel.putClientProperty(FlatClientProperties.STYLE, labelStyle);
        dietTypeLabel.putClientProperty(FlatClientProperties.STYLE, labelStyle);
        bodyGoalLabel.putClientProperty(FlatClientProperties.STYLE, labelStyle);

        String inputStyle =
                "arc:20;"
                        + "background:#121417;"
                        + "foreground:#C5D1BA;"
                        + "caretColor:#C5D1BA;"
                        + "borderColor:#C5D1BA;"
                        + "borderWidth:2;"
                        + "margin:5,5,5,5;"
                        + "focusWidth:0;";

        String inputStyle2 =
                "arc:20;"
                        + "background:#121417;"
                        + "foreground:#C5D1BA;"
                        + "borderColor:#C5D1BA;"
                        + "borderWidth:2;"
                        + "focusWidth:0;"
                        + "buttonArrowColor:#C5D1BA;"
        + "buttonBackground:#252A2F;"
                        + "popupBackground:#121417;"
                        + "buttonEditableBackground:#121417;"
                + "padding: 5,5,5,5;";



        registerUsernameField.putClientProperty(FlatClientProperties.STYLE, inputStyle);
        registerPasswordField.putClientProperty(FlatClientProperties.STYLE, inputStyle);
        weightTextField.putClientProperty(FlatClientProperties.STYLE, inputStyle);
        ageTextField.putClientProperty(FlatClientProperties.STYLE, inputStyle);

        heightFeetComboBox.putClientProperty(FlatClientProperties.STYLE, inputStyle2);
        heightInchesComboBox.putClientProperty(FlatClientProperties.STYLE, inputStyle2);
        genderComboBox.putClientProperty(FlatClientProperties.STYLE, inputStyle2);
        dietTypeComboBox.putClientProperty(FlatClientProperties.STYLE, inputStyle2);
        bodyGoalComboBox.putClientProperty(FlatClientProperties.STYLE, inputStyle2);

        String buttonStyle =
                "arc:20;"
                        + "font:bold;"
                        + "foreground:#C5D1BA;"
                        + "background:#121417;"
                        + "borderColor:#C5D1BA;"
                        + "borderWidth:2;"
                        + "focusWidth:0;"
                        + "margin:5,5,5,5;";

        String buttonStyle2 =
                "arc:20;"
                        + "font:bold;"
                        + "foreground:#C5D1BA;"
                        + "background:#252A2F;"
                        + "borderColor:#C5D1BA;"
                        + "borderWidth:2;"
                        + "focusWidth:0;"
                        + "margin:5,5,5,5;";

        createAccountButton.putClientProperty(FlatClientProperties.STYLE, buttonStyle);
        backToLoginButton.putClientProperty(FlatClientProperties.STYLE, buttonStyle2);
        createAccountButton.setFocusPainted(false);
        backToLoginButton.setFocusPainted(false);



        gbc.gridx = 0; gbc.gridy = 11;
        gbc.gridwidth = 3;
        errorLabelForRegistration = new JLabel();
        errorLabelForRegistration.setHorizontalAlignment(SwingConstants.CENTER);
        //#FFE96B
        errorLabelForRegistration.putClientProperty(FlatClientProperties.STYLE,
                "font:bold; foreground:#FEFFDA");
        registerPanel.add(errorLabelForRegistration, gbc);

    }

    private void initLoginPanel() {
        loginPanel = new JPanel(new GridBagLayout());

        loginPanel.setMaximumSize(new Dimension(400, 600));
        loginPanel.setPreferredSize(new Dimension(400, 600));
        loginPanel.setMinimumSize(new Dimension(400, 600));
        loginPanel.setBackground(Color.decode("#252A2F"));

        loginPanel.putClientProperty(FlatClientProperties.STYLE,
                "arc: 40; background: #252A2F;");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/assets/recipez.png"));
        Image resizedImageIcon = imageIcon.getImage().getScaledInstance(294, 75, Image.SCALE_SMOOTH);
        gbc.insets = new Insets(10, 10, 50, 10);
        JLabel iconLabel = new JLabel(new ImageIcon(resizedImageIcon));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(iconLabel, gbc);

        gbc.insets = new Insets(15, 10, 15, 10);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.decode("#C5D1BA"));
        usernameLabel.putClientProperty(FlatClientProperties.STYLE, "font: bold");
        loginPanel.add(usernameLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        loginUsernameField = new JTextField();
        loginUsernameField.putClientProperty(FlatClientProperties.STYLE,
                "arc:20;" +
                        "background:#121417;" +
                        "foreground:#C5D1BA;" +
                        "caretColor:#C5D1BA;" +
                        "borderColor:#C5D1BA;"
                        + "borderWidth: 2;"
                        + "margin: 5,5,5,5;"
        );
        loginPanel.add(loginUsernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.decode("#C5D1BA"));
        passwordLabel.putClientProperty(FlatClientProperties.STYLE, "font: bold");
        loginPanel.add(passwordLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        loginPasswordField = new JPasswordField();
        loginPasswordField.putClientProperty(FlatClientProperties.STYLE,
                "arc:20;" +
                        "background:#121417;" +
                        "foreground:#C5D1BA;" +
                        "caretColor:#C5D1BA;" +
                        "borderColor:#C5D1BA;"
                        + "borderWidth: 2;"
                        + "margin: 5,5,5,5;"
        );
        loginPanel.add(loginPasswordField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton loginButton = new JButton("Login");
        loginButton.setFocusPainted(false);
        loginButton.putClientProperty(FlatClientProperties.STYLE,
                "arc:20;"
                        + "font:bold;"
                        + "foreground:#C5D1BA;"
                        + "background:#121417;"
                        + "borderColor:#C5D1BA;"
                + "borderWidth: 2;"
                + "margin: 5,5,5,5;"


        );

        loginPanel.add(loginButton, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton registerButton = new JButton("Sign Up");

        registerButton.putClientProperty(FlatClientProperties.STYLE,
                "arc:20;"
                        + "font:bold;"
                        + "foreground:#C5D1BA;"
                        + "background:#252A2F;"
                        + "borderColor:#C5D1BA;"
                        + "borderWidth: 2;"
                        + "margin: 5,5,5,5;"

        );


        loginPanel.add(registerButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // You can also check which button was pressed if you have multiple
                if (e.getSource() == loginButton) {
                    loginButtonClicked();
                }
            }
        });


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // You can also check which button was pressed if you have multiple
                if (e.getSource() == registerButton) {
                    registerButtonClicked();
                }
            }
        });


    }

    private void createAccount(String username, String password, String weight, int heightFeet, int heightInches, String gender, String age, String dietType, String bodyGoal) {
        int validationCode = validateCreateAccountData(username, password, weight, age);

        String errorMessage = "";

        if (validationCode != 0) {
            if (validationCode == 100) {
                errorMessage = "Empty username";
            } else if (validationCode == 200) {
                errorMessage = "Empty password";
            } else if  (validationCode == 300) {
                errorMessage = "Empty weight";
            } else if  (validationCode == 301) {
                errorMessage = "Negative weight";
            } else if (validationCode == 302) {
                errorMessage = "Weight must be a number";
            } else if (validationCode == 400) {
                errorMessage = "Empty age";
            } else if (validationCode == 401) {
                errorMessage = "Negative age";
            } else if (validationCode == 402) {
                errorMessage = "Age must be a number";
            }
            errorLabelForRegistration.setText(errorMessage);
            return;
        }

        double heightInFeet = heightFeet + (heightInches / 12.0);
        boolean isMan;
        isMan = gender.equals("Male");
        double convertedWeight = Double.parseDouble(weight);
        int convertedAge = Integer.parseInt(age);

        // passed safety checks, we can now create the account
        loggedInUser = new User(username, password, BodyGoal.valueOf(bodyGoal), DietType.valueOf(dietType), convertedWeight, heightInFeet, convertedAge, isMan);

        authenticationCardLayout.show(authenticationPanel, "login");
    }

    private int validateCreateAccountData(String username, String password, String weight, String age) {
        // Username checks: 100
        if (username.equals(""))
            return 100; // empty username
        // Password checks: 200
        if (password.equals(""))
            return 200; // empty password
        // Weight checks: 300
        try {
            if (weight.equals(""))
                return 300; // empty weight
            else if (Double.parseDouble(weight) < 0)
                return 301; // negative weight
        } catch (NumberFormatException e) {
            return 302; // not a number
        }

        // Age checks: 400
        try {
            if (age.equals(""))
                return 400; // empty weight
            else if (Integer.parseInt(age) < 0)
                return 401; // negative age

        } catch (NumberFormatException e) {
            return 402; // not a number
        }

        return 0; //success
    }

    private void loginButtonClicked() {
        String username = loginUsernameField.getText();
        String password = new String(loginPasswordField.getPassword());

        // Create a login attempt object WITHOUT touching UserManager
        User loginAttempt = new User(username, password);

        // Now create the real user so UserManager can load JSON

        if (loginAttempt.getUserManager().checkPassword(password)) {
            Application.activeUser = loginAttempt;
            Application.activeInstance.loginUser(loginAttempt);
        } else {
            Log.warning("Login failed for " + username);
        }
    }


    private void registerButtonClicked() {
        authenticationCardLayout.show(authenticationPanel, "register");
    }

    private void backToLoginButtonClicked() {
        authenticationCardLayout.show(authenticationPanel, "login");
    }

    public JPanel getAuthenticationPanel() {
        return authenticationPanel;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

}
