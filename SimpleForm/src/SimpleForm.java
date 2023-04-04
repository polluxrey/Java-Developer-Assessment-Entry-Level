
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleForm {

    private JFrame mainFrame = new JFrame();
    private JTextField firstNameField, lastNameField, emailAddressField;
    private JSpinner birthDateSpinner;
    private JComboBox<String> genderComboBox;

    public SimpleForm() {
        initComponents();
        mainFrame.setTitle("Java Developer Assessment");
        mainFrame.setSize(600, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private void initComponents() {
        String[] genders = {"", "Male", "Female", "Prefer not to say"};

        firstNameField = new JTextField("", 129);
        lastNameField = new JTextField("", 129);
        emailAddressField = new JTextField("", 129);

        birthDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(
                birthDateSpinner, "MM/dd/yyyy");
        birthDateSpinner.setEditor(dateEditor);
        birthDateSpinner.setValue(new Date());

        genderComboBox = new JComboBox<>(genders);
        genderComboBox.setSelectedItem("");

        JLabel firstNameLabel = new JLabel("First name");
        JLabel lastNameLabel = new JLabel("Last name");
        JLabel birthDateLabel = new JLabel("Birth date (mm/dd/yyyy)");
        JLabel genderLabel = new JLabel("Gender");
        JLabel emailAddressLabel = new JLabel("E-mail address");

        JButton printInfoButton = new JButton("Print information");
        JButton clearInfoButton = new JButton("Clear");

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        printInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                printInfo();
            }
        });

        clearInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearInfo();
            }
        });

        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(birthDateLabel);
        panel.add(birthDateSpinner);
        panel.add(genderLabel);
        panel.add(genderComboBox);
        panel.add(emailAddressLabel);
        panel.add(emailAddressField);
        panel.add(printInfoButton);
        panel.add(clearInfoButton);

        mainFrame.add(panel);
    }

    private void printInfo() {
        String info, firstName, lastName, emailAddress, gender;
        Date birthDate;

        firstName = firstNameField.getText().trim();
        lastName = lastNameField.getText().trim();
        emailAddress = emailAddressField.getText().trim();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
        birthDate = (Date) birthDateSpinner.getValue();

        gender = genderComboBox.getSelectedItem().toString();

        if (firstName.isBlank() || lastName.isBlank() || emailAddress.isBlank()
                || gender.isBlank()) {
            printError("One of the fields is empty!");
            return;
        }

        JFrame infoFrame = new JFrame("Information");
        infoFrame.setSize(300, 150);
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        infoFrame.setLocationRelativeTo(null);

        info = "<html>" + firstName + " " + lastName + " (" + gender + ") <br>"
                + "Born on " + dateFormat.format(birthDate) + "<br>"
                + emailAddress + "</html>";

        JLabel infoLabel = new JLabel(info);

        infoFrame.add(infoLabel);
        infoFrame.setVisible(true);
    }

    private void clearInfo() {
        firstNameField.setText("");
        lastNameField.setText("");
        emailAddressField.setText("");
        birthDateSpinner.setValue(new Date());
        genderComboBox.setSelectedItem("");
    }

    private void printError(String err) {
        JFrame infoFrame = new JFrame("Error");
        infoFrame.setSize(300, 150);
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        infoFrame.setLocationRelativeTo(null);
        infoFrame.add(new JLabel(err));
        infoFrame.setVisible(true);
    }
}
