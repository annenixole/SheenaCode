
package Library;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class EditProfile extends JPanel implements ActionListener {
    
    public EditProfile(){
        
        setLayout(null);
        setBackground(new Color(245, 245, 245));
        setLayout(new CardLayout());
        
        JPanel editProfileP = new JPanel();
        editProfileP.setLayout(null);
        editProfileP.setBackground(new Color(245, 245, 245));
        editProfileP.setBounds(20, 20, 1040, 630);
        add(editProfileP, "EditProfile");
        
        JPanel contentPanelP = new JPanel();
        contentPanelP.setBounds(20, 20, 1040, 630);
        contentPanelP.setBackground(new Color(245, 250, 245)); // Light green tint
        contentPanelP.setLayout(null);
        contentPanelP.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
        editProfileP.add(contentPanelP);

        JLabel titleLabel = new JLabel("Profile");
        titleLabel.setBounds(40, 15, 200, 40);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        contentPanelP.add(titleLabel);

        JLabel imageBorder = new JLabel();
        imageBorder.setBounds(200, 130, 180, 180);
        imageBorder.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        contentPanelP.add(imageBorder);

        Font labelFontP = new Font("Segoi UI", Font.BOLD, 16);
        Font valueFont = new Font("Segoi UI", Font.PLAIN, 15);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(450, 120, 100, 30);
        nameLabel.setFont(labelFontP);
        contentPanelP.add(nameLabel);

        JLabel nameValue = new JLabel("Sheena S.P Ramos");
        nameValue.setBounds(450, 150, 300, 30);
        nameValue.setFont(valueFont);
        contentPanelP.add(nameValue);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(450, 180, 100, 30);
        emailLabel.setFont(labelFontP);
        contentPanelP.add(emailLabel);

        JLabel emailValue = new JLabel("ramossheena028@gmail.com");
        emailValue.setBounds(450, 210, 300, 30);
        emailValue.setFont(valueFont);
        contentPanelP.add(emailValue);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(450, 240, 100, 30);
        phoneLabel.setFont(labelFontP);
        contentPanelP.add(phoneLabel);

        JLabel phoneValue = new JLabel("+63945408756");
        phoneValue.setBounds(450, 270, 300, 30);
        phoneValue.setFont(valueFont);
        contentPanelP.add(phoneValue);

        JLabel employeeIdLabel = new JLabel("Employee ID:");
        employeeIdLabel.setBounds(450, 300, 120, 30);
        employeeIdLabel.setFont(labelFontP);
        contentPanelP.add(employeeIdLabel);

        JLabel employeeIdValue = new JLabel("LIB123456");
        employeeIdValue.setBounds(450, 330, 300, 30);
        employeeIdValue.setFont(valueFont);
        contentPanelP.add(employeeIdValue);

        JButton uploadImageBtn = new JButton("Upload Picture");
        uploadImageBtn.setBounds(215, 325, 150, 35);
        uploadImageBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        uploadImageBtn.setBackground(new Color(46, 125, 50));
        uploadImageBtn.setForeground(Color.WHITE);
        uploadImageBtn.setBorderPainted(false);
        uploadImageBtn.setFocusPainted(false);
        uploadImageBtn.setOpaque(true);
        contentPanelP.add(uploadImageBtn);

        JButton editProfileBtn = new JButton("Edit Profile");
        editProfileBtn.setBounds(450, 380, 150, 40);
        editProfileBtn.setBackground(Color.WHITE);
        editProfileBtn.setFont(new Font("Segoi UI", Font.BOLD, 14));
        editProfileBtn.setBorder(BorderFactory.createLineBorder(new Color(76, 175, 80), 1));
        editProfileBtn.setBorderPainted(true);
        editProfileBtn.setFocusPainted(false);
        editProfileBtn.setOpaque(true);
        contentPanelP.add(editProfileBtn);

        // EDIT PROFILE PANEL
        JPanel editProfile2 = new JPanel();
        editProfile2.setLayout(null);
        editProfile2.setBackground(new Color(245, 245, 245));
        add(editProfile2, "EditProfile2");

        JPanel contentPanel2 = new JPanel();
        contentPanel2.setBounds(20, 20, 1040, 630);
        contentPanel2.setBackground(new Color(245, 250, 245)); // Light green tint
        contentPanel2.setLayout(null);
        contentPanel2.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
        editProfile2.add(contentPanel2);

        JLabel editTitle = new JLabel("Edit Profile");
        editTitle.setBounds(30, 15, 300, 40);
        editTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        contentPanel2.add(editTitle);

        JLabel imgBorder = new JLabel();
        imgBorder.setBounds(30, 80, 150, 150);
        imgBorder.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        contentPanel2.add(imgBorder);

        JButton imgBtn = new JButton("Upload");
        imgBtn.setBounds(30, 240, 150, 30);
        imgBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        imgBtn.setBackground(new Color(46, 125, 50));
        imgBtn.setForeground(Color.WHITE);
        imgBtn.setBorderPainted(false);
        imgBtn.setFocusPainted(false);
        imgBtn.setOpaque(true);
        contentPanel2.add(imgBtn);

        JSeparator line = new JSeparator();
        line.setBounds(30, 290, 960, 2);
        line.setBackground(Color.BLACK);
        contentPanel2.add(line);

        Font lblFont = new Font("Segoe UI", Font.BOLD, 14);

        JLabel nameLbl = new JLabel("Name:");
        nameLbl.setBounds(220, 80, 100, 30);
        nameLbl.setFont(lblFont);
        contentPanel2.add(nameLbl);

        JTextField nameField = new JTextField();
        nameField.setBounds(320, 80, 250, 30);
        contentPanel2.add(nameField);

        JLabel emailLbl = new JLabel("Email:");
        emailLbl.setBounds(220, 130, 100, 30);
        emailLbl.setFont(lblFont);
        contentPanel2.add(emailLbl);

        JTextField emailField = new JTextField();
        emailField.setBounds(320, 130, 250, 30);
        contentPanel2.add(emailField);

        JLabel phoneLbl = new JLabel("Phone:");
        phoneLbl.setBounds(220, 180, 100, 30);
        phoneLbl.setFont(lblFont);
        contentPanel2.add(phoneLbl);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(320, 180, 250, 30);
        contentPanel2.add(phoneField);

        JLabel empIdLbl = new JLabel("Employee ID:");
        empIdLbl.setBounds(220, 230, 120, 30);
        empIdLbl.setFont(lblFont);
        contentPanel2.add(empIdLbl);

        JTextField empIdField = new JTextField();
        empIdField.setBounds(320, 230, 250, 30);
        empIdField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        empIdField.setEditable(false);
        contentPanel2.add(empIdField);

        JLabel acctTitle = new JLabel("Account Settings");
        acctTitle.setBounds(30, 310, 200, 40);
        acctTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        contentPanel2.add(acctTitle);

        JLabel currPassLbl = new JLabel("Current Password:");
        currPassLbl.setBounds(30, 370, 150, 30);
        currPassLbl.setFont(lblFont);
        contentPanel2.add(currPassLbl);

        JPasswordField currPassField = new JPasswordField();
        currPassField.setBounds(180, 370, 250, 30);
        contentPanel2.add(currPassField);

        JLabel newPassLbl = new JLabel("New Password:");
        newPassLbl.setBounds(30, 420, 150, 30);
        newPassLbl.setFont(lblFont);
        contentPanel2.add(newPassLbl);

        JPasswordField newPassField = new JPasswordField();
        newPassField.setBounds(180, 420, 250, 30);
        contentPanel2.add(newPassField);

        JButton save2 = new JButton("Save");
        save2.setBounds(770, 30, 100, 30);
        save2.setFont(new Font("SansSerif", Font.BOLD, 12));
        save2.setBackground(new Color(46, 125, 50));
        save2.setForeground(Color.WHITE);
        save2.setBorderPainted(false);
        save2.setFocusPainted(false);
        save2.setOpaque(true);
        contentPanel2.add(save2);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(880, 30, 100, 30);
        cancelBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        cancelBtn.setBackground(new Color(169, 169, 169));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setBorderPainted(false);
        cancelBtn.setFocusPainted(false);
        cancelBtn.setOpaque(true);
        contentPanel2.add(cancelBtn);

        JButton save = new JButton("Save Changes");
        save.setBounds(450, 520, 140, 35);
        save.setFont(new Font("Segoi UI", Font.BOLD, 14));
        save.setBackground(Color.WHITE);
        save.setForeground(new Color(76, 175, 80));
        save.setBorder(BorderFactory.createLineBorder(new Color(76, 175, 80), 1));
        save.setBorderPainted(true);
        save.setFocusPainted(false);
        save.setOpaque(true);
        contentPanel2.add(save);

        editProfileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setText(nameValue.getText());
                emailField.setText(emailValue.getText());
                phoneField.setText(phoneValue.getText());
                empIdField.setText(employeeIdValue.getText());

                CardLayout cardLayout = (CardLayout) EditProfile.this.getLayout();
                cardLayout.show(EditProfile.this, "EditProfile2");
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout)EditProfile.this.getLayout();
                cardLayout.show(EditProfile.this, "EditProfile");
            }
        });

        save2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameValue.setText(nameField.getText());
                emailValue.setText(emailField.getText());
                phoneValue.setText(phoneField.getText());
                JOptionPane.showMessageDialog(null, "Profile Updated Successfully!");
                CardLayout cardLayout = (CardLayout)EditProfile.this.getLayout();
                cardLayout.show(EditProfile.this, "EditProfile");
            }
        });

        uploadImageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath())
                            .getImage().getScaledInstance(imageBorder.getWidth(), imageBorder.getHeight(), Image.SCALE_SMOOTH));
                    imageBorder.setIcon(imageIcon);
                }
            }
        });

        imgBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath())
                            .getImage().getScaledInstance(imgBorder.getWidth(), imgBorder.getHeight(), Image.SCALE_SMOOTH));
                    imgBorder.setIcon(imageIcon);
                }
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredCurrentPassword = new String(currPassField.getPassword());
                String newPassword = new String(newPassField.getPassword());

//                if (!enteredCurrentPassword.equals(currentPassword)) {
//                    JOptionPane.showMessageDialog(contentPanel2,
//                            "The current password you entered is incorrect.",
//                            "Invalid Password",
//                            JOptionPane.ERROR_MESSAGE);
//                    return;
//                }

                if (newPassword.isEmpty() || newPassword.length() < 6) {
                    JOptionPane.showMessageDialog(contentPanel2, "The new password must be at least 6 characters long.", "Invalid Password",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(contentPanel2, "Your password change request has been submitted. It requires admin approval.", "Admin Approval Needed",
                        JOptionPane.INFORMATION_MESSAGE);

                currPassField.setText("");
                newPassField.setText("");
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
