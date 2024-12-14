
package Library;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LogInFrame extends JFrame implements ActionListener{
   
    private JLabel titlelbl = new JLabel("Library Management System");
    private JLabel idlbl = new JLabel("Librarian ID:");
    private JLabel passlbl = new JLabel("Password:");
    private JTextField idField = new JTextField();
    private JPasswordField passField = new JPasswordField();
    private JButton loginButton = new JButton("LOG IN");

    public LogInFrame() {
        // Set frame properties
        setTitle("Library Management System");
        setSize(1400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Left panel (form)
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBounds(0, 0, 700, 700);
        add(formPanel);

        // Add form elements
        titlelbl.setFont(new Font("Arial", Font.BOLD, 24));
        titlelbl.setBounds(200, 50, 400, 40);
        formPanel.add(titlelbl);

        idlbl.setFont(new Font("Arial", Font.PLAIN, 18));
        idlbl.setBounds(200, 150, 120, 30);
        formPanel.add(idlbl);

        idField.setBounds(200, 180, 300, 40);
        formPanel.add(idField);

        passlbl.setFont(new Font("Arial", Font.PLAIN, 18));
        passlbl.setBounds(200, 250, 120, 30);
        formPanel.add(passlbl);

        passField.setBounds(200, 280, 300, 40);
        formPanel.add(passField);

        loginButton.setFont(new Font("Arial", Font.PLAIN, 18));
        loginButton.setBounds(300, 350, 150, 40);
        formPanel.add(loginButton);

        // Add action listener to the login button
        loginButton.addActionListener(this);

        // Right panel (picture)
        JPanel picturePanel = new JPanel();
        picturePanel.setBounds(700, 0, 700, 700);
        picturePanel.setBackground(new Color(0, 102, 51));
        add(picturePanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String librarianID = idField.getText();
        String password = new String(passField.getPassword());

        // Check login credentials
        if (librarianID.equals("admin") && password.equals("123456")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");

            // Open the Dashboard
            Dashboard_Inventory dashboard = new Dashboard_Inventory();
            dashboard.setVisible(true);

            // Close the login form
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid ID or Password!");
        }
    }


    public static void main(String[] args) {
       LogInFrame loginForm = new LogInFrame();
        loginForm.setVisible(true);
    }
}