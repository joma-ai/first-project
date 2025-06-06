import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register {
    JPanel borderPanel;
    JComboBox occupationField;
    JTextField forenameField = new JTextField();
    JTextField surnameField = new JTextField();
    JTextField dateOfBirthField = new JTextField();
    JPasswordField password = new JPasswordField();
    JPasswordField passwordConfirm = new JPasswordField();
    JFrame f;
    JLabel dateOfBirthLabel;
    DateOfBirth dateOfBirth;
    JLabel forenameLabel;
    JLabel surnameLabel;
    JLabel passwordLabel;
    JLabel passwordConfirmLabel;

    Register() {
        borderPanel = new JPanel();

        dateOfBirthLabel = new JLabel("Date of birth (DD.MM.YY)");
        forenameLabel = new JLabel("Forename");
        surnameLabel = new JLabel("Surname");
        passwordLabel = new JLabel("Password");
        passwordConfirmLabel = new JLabel("Confirm password");

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new GridLayout(1,2));

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(1,2));

        JPanel left = new JPanel();
        left.setLayout(new BorderLayout());

        JPanel right = new JPanel();
        right.setLayout(new GridLayout(20,0));

        ImageIcon icon = new ImageIcon(getClass().getResource("image.png"));
        Image iconImage = icon.getImage();

        JLabel image = new JLabel();
        image.setIcon(icon);

        String[] popupMenuItems = {"Teacher", "Student"};
        occupationField = new JComboBox(popupMenuItems);

        right.add(occupationField);
        right.add(new JLabel());
        right.add(forenameLabel);
        right.add(forenameField);
        right.add(new JLabel());
        right.add(surnameLabel);
        right.add(surnameField);
        right.add(new JLabel());
        right.add(dateOfBirthLabel);
        right.add(dateOfBirthField);
        right.add(new JLabel());
        right.add(passwordLabel);
        right.add(password);
        right.add(new JLabel());
        right.add(passwordConfirmLabel);
        right.add(passwordConfirm);
        right.add(new JLabel());
        right.add(new JLabel());

        left.add(image,BorderLayout.EAST);

        JButton register = new JButton("Register");
        JButton cancel = new JButton(("Cancel"));

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Search();
            }
        });

        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fieldCheck()) {
                    registerUser();
                    int answer = JOptionPane.showConfirmDialog(null, "Would you like to register another person?",null, JOptionPane.YES_NO_OPTION);
                    if (answer == 0) {
                        forenameField.setText("");
                        surnameField.setText("");
                        dateOfBirthField.setText("");
                        password.setText("");
                        passwordConfirm.setText("");
                    } else if (answer == 1) {
                        f.dispose();
                        new Search();
                    }
                }
            }
        });

        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBounds(0,0,800,500);
        f.setIconImage(iconImage);
        //f.setLayout(new GridLayout(1,2));
        f.setLocationRelativeTo(null);
        //f.setResizable(false);
        f.setVisible(true);

        borderPanel.setLayout(new BorderLayout());
        borderPanel.add(backgroundPanel);
        Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        borderPanel.setBorder(padding);
        f.setContentPane(borderPanel);

        bottom.add(register);
        bottom.add(cancel);

        backgroundPanel.add(left);
        backgroundPanel.add(right);

        borderPanel.add(backgroundPanel,BorderLayout.CENTER);
        borderPanel.add(bottom,BorderLayout.SOUTH);

    }

    public void registerUser() {
        Person person = new Person(occupationField.getSelectedIndex(), forenameField.getText(), surnameField.getText(), dateOfBirth, String.valueOf(password.getPassword()));
        DBConnection conn = new DBConnection();
        System.out.println(person);
        conn.addPerson(person);
    }

    public boolean fieldCheck() {
        boolean check;
        String[] parts;
        try {
            String line = dateOfBirthField.getText();
            parts = line.split("\\.");
            if ((Integer.valueOf(parts[0]) > 31 || Integer.valueOf(parts[0]) < 0) || (Integer.valueOf(parts[1]) > 12 || Integer.valueOf(parts[1]) < 0) || (Integer.valueOf(parts[2]) > 2020 || Integer.valueOf(parts[2]) < 1900)) {
                dateOfBirthLabel.setForeground(Color.RED);
                dateOfBirthLabel.setText("*Date of birth (DD.MM.YY) Incorrect date!");
                check = false;
            } else {
                dateOfBirthLabel.setText("Date of birth (DD.MM.YY)");
                dateOfBirthLabel.setForeground(forenameField.getSelectedTextColor());
                dateOfBirth = new DateOfBirth(Integer.valueOf(parts[0]),Integer.valueOf(parts[1]),Integer.valueOf(parts[2]));
                check = true;
            }
        } catch (Exception e) {
            dateOfBirthLabel.setForeground(Color.RED);
            dateOfBirthLabel.setText("*Date of birth (DD.MM.YY) Incorrect format!");
            check = false;
        }

        if (forenameField.getText().isEmpty()) {
            forenameLabel.setText("*Forename (Please fill field)");
            forenameLabel.setForeground(Color.RED);
            check = false;
        } else {
            forenameLabel.setText("Forename");
            forenameLabel.setForeground(forenameField.getSelectedTextColor());
            check = true;
        }

        if (surnameField.getText().isEmpty()) {
            surnameLabel.setText("*Surame (Please fill field)");
            surnameLabel.setForeground(Color.RED);
            check = false;
        } else {
            surnameLabel.setText("Surname");
            surnameLabel.setForeground(forenameField.getSelectedTextColor());
            check = true;
        }

        if (String.valueOf(password.getPassword()).isEmpty()||String.valueOf(passwordConfirm.getPassword()).isEmpty()) {
            passwordLabel.setText("*Passwords do not match");
            passwordConfirmLabel.setText("*Passwords do not match");
            passwordLabel.setForeground(Color.RED);
            passwordConfirmLabel.setForeground(Color.RED);
            check = false;
        } else if (!String.valueOf(password.getPassword()).equals(String.valueOf(passwordConfirm.getPassword()))){
            passwordLabel.setText("*Passwords do not match");
            passwordConfirmLabel.setText("*Passwords do not match");
            passwordLabel.setForeground(Color.RED);
            passwordConfirmLabel.setForeground(Color.RED);
            check = false;
        } else {
            passwordLabel.setText("Password");
            passwordConfirmLabel.setText("Confirm password");
            passwordLabel.setForeground(forenameField.getSelectedTextColor());
            passwordConfirmLabel.setForeground(forenameField.getSelectedTextColor());
            check = true;
        }
        return check;
    }



}
