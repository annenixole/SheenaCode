
package Library;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LibrarianReport extends JPanel {
    
    public LibrarianReport(){
        setLayout(null);
        setBackground(Color.white);
        
        // Center panel for the report content
        JPanel reportPanel = new JPanel();
        reportPanel.setLayout(null);
        reportPanel.setBackground(new Color(245, 245, 245)); // Light gray background
        reportPanel.setBounds(50, 50, 980, 560);
        add(reportPanel);
        
        JLabel titleLabel = new JLabel("CURRENT LIBRARY STATISTICS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(350, 30, 300, 20);
        reportPanel.add(titleLabel);
        
        JLabel dateLabel = new JLabel(getCurrentDate(0));
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        dateLabel.setBounds(410, 60, 100, 20);
        reportPanel.add(dateLabel);
        
        // Report fields (design only, no calculations)
        addReportField(reportPanel, "Total Fees Collected:  ", "hfhfhf", 400);
        addReportField(reportPanel, "Total no. of books borrowed:  ", "", 440);
        addReportField(reportPanel, "Total no. of books returned:  ", "", 480);
        
        
        DefaultTableModel mostBorrowed = new DefaultTableModel(new String[]{"ISBN","Title","Author","Book","Category"},0);
        JTable BookReport = new JTable(mostBorrowed);
        JScrollPane bookTable = new JScrollPane(BookReport);
        bookTable.setBounds(80,170, 820, 200);
        reportPanel.add(bookTable);

    } 
    
    public static String getCurrentDate(int daysToAdd) {
        LocalDate currentDate = LocalDate.now().plusDays(daysToAdd);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
        return currentDate.format(formatter);
    } // to display the current date
    
    // Helper method to add report fields
    private void addReportField(JPanel panel, String label, String value, int yPosition) {
        JPanel fieldPanel = new JPanel(new BorderLayout());
        fieldPanel.setBackground(new Color(245, 245, 245)); // Same light gray background
        fieldPanel.setBounds(80, yPosition, 820, 30); // Set the bounds for each report field (x, y, width, height)

        JLabel fieldLabel = new JLabel(label);
        fieldLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField fieldInput = new JTextField(value);
        fieldInput.setPreferredSize(new Dimension(400, 30));
        fieldInput.setEditable(false); // Set as non-editable

        fieldPanel.add(fieldLabel, BorderLayout.WEST);
        fieldPanel.add(fieldInput, BorderLayout.CENTER);

        panel.add(fieldPanel);
    }
}
