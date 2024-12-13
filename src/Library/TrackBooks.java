package Library;

import LinkedList.BorrowedLinkedList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TrackBooks extends JPanel {

    BorrowedLinkedList BorrowedList = new BorrowedLinkedList();

    private JPanel cardPanel;
    private JPanel borrowedP;
    private JPanel returnedP;
    private JPanel renewedP;
    private JTable borrowedTable;
    public DefaultTableModel borrowedModel;
    private JTable returnTable;
    private DefaultTableModel returnModel;

    public DefaultTableModel booksModel;
    private JTable booksTable;
    public int selectedRow;
    
    private SearchAndSort_Track searchAndSort = new SearchAndSort_Track();


    public TrackBooks() {
        setLayout(null);
        setBackground(new Color(250, 250, 240));

        // Add Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(20, 20, 750, 50);
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setLayout(null);
        add(buttonPanel);

        // Create buttons for navigation
        JButton borrowedBtn = new JButton("Borrowed");
        borrowedBtn.setBounds(0, 10, 120, 30);
        borrowedBtn.setBackground(new Color(46, 125, 50));
        borrowedBtn.setForeground(Color.WHITE);
        borrowedBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        borrowedBtn.setBorderPainted(false);
        borrowedBtn.setFocusPainted(false);
        borrowedBtn.setOpaque(true);
        buttonPanel.add(borrowedBtn);

        JButton returnedBookBtn = new JButton("Returned");
        returnedBookBtn.setBounds(130, 10, 120, 30);
        returnedBookBtn.setBackground(new Color(169, 169, 169));
        returnedBookBtn.setForeground(Color.WHITE);
        returnedBookBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        returnedBookBtn.setBorderPainted(false);
        returnedBookBtn.setFocusPainted(false);
        returnedBookBtn.setOpaque(true);
        buttonPanel.add(returnedBookBtn);

        JButton renewBookBtn = new JButton("Renewed");
        renewBookBtn.setBounds(260, 10, 120, 30);
        renewBookBtn.setBackground(new Color(169, 169, 169));
        renewBookBtn.setForeground(Color.WHITE);
        renewBookBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        renewBookBtn.setBorderPainted(false);
        renewBookBtn.setFocusPainted(false);
        renewBookBtn.setOpaque(true);
        buttonPanel.add(renewBookBtn);

        cardPanel = new JPanel();
        cardPanel.setLayout(new CardLayout());
        cardPanel.setBounds(20, 80, 750, 520);
        add(cardPanel);

        borrowedP = createBorrowedPanel();
        returnedP = createReturnedPanel();
        renewedP = createRenewedPanel();

        cardPanel.add(borrowedP, "Borrowed");
        cardPanel.add(returnedP, "Returned");
        cardPanel.add(renewedP, "Renewed");

        borrowedBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchCard("Borrowed", borrowedBtn, returnedBookBtn, renewBookBtn);
            }
        });

        returnedBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchCard("Returned", returnedBookBtn, borrowedBtn, renewBookBtn);
            }
        });

        renewBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchCard("Renewed", renewBookBtn, borrowedBtn, returnedBookBtn);
            }
        });
    }
    
    private JPanel createBorrowedPanel() {
        JPanel borrowedPanel = new JPanel();
        borrowedPanel.setLayout(null);
        borrowedPanel.setBackground(Color.WHITE);

        JTextField searchTrackField = new JTextField();
        searchTrackField.setBounds(60, 10, 400, 30);
        searchTrackField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchTrackField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        borrowedPanel.add(searchTrackField);

        JButton searchTrackBtn = new JButton("Search");
        searchTrackBtn.setBounds(480, 10, 100, 30);
        searchTrackBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        searchTrackBtn.setBackground(Color.WHITE);
        searchTrackBtn.setForeground(new Color(76, 175, 80));
        searchTrackBtn.setBorder(BorderFactory.createLineBorder(new Color(76, 175, 80), 1));
        searchTrackBtn.setFocusPainted(false);
        borrowedPanel.add(searchTrackBtn);

        JComboBox<String> sortTrackComboBox = new JComboBox<>(new String[]{"Sort by Borrower No", "Sort by Borrowed Date"});
        sortTrackComboBox.setBounds(590, 10, 150, 30);
        sortTrackComboBox.setFont(new Font("Segoe UI", Font.BOLD, 12));
        sortTrackComboBox.setBorder(BorderFactory.createLineBorder(new Color(76, 175, 80), 1));
        borrowedPanel.add(sortTrackComboBox);

        borrowedModel = new DefaultTableModel(
                new String[]{"Borrower No", "Quantity of Books", "Borrowed Date", "Expected Return Date", "Borrower"}, 0);
        borrowedTable = new JTable(borrowedModel);
        JScrollPane borrowedScrollPane = new JScrollPane(borrowedTable);
        borrowedScrollPane.setBounds(10, 50, 730, 460);
        borrowedPanel.add(borrowedScrollPane);
        
        borrowedModel.addRow(new Object[]{"ILY001", 2, "2024-11-23", "2024-12-15", "Student No.: 12345"});
        borrowedModel.addRow(new Object[]{"ILY006", 1, "2024-07-02", "2024-12-16", "Student No.: 67890"});
        borrowedModel.addRow(new Object[]{"ILY002", 3, "2024-10-13", "2024-12-17", "Student No.: 11223"});
        borrowedModel.addRow(new Object[]{"ILY009", 1, "2024-09-04", "2024-12-18", "Student No.: 33445"});
        borrowedModel.addRow(new Object[]{"ILY005", 2, "2024-12-05", "2024-12-19", "Student No.: 55667"});


        // Attach sorting and searching
        sortTrackComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) sortTrackComboBox.getSelectedItem();
                if ("Sort by Borrower No".equals(selectedOption)) {
                    searchAndSort.sortByBorrowerNo(borrowedModel);
                } else if ("Sort by Borrowed Date".equals(selectedOption)) {
                    searchAndSort.sortByDate(borrowedModel, 2); // Column index for Borrowed Date
                }
            }
        });

        searchTrackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchTrackField.getText();
                searchAndSort.linearSearch(borrowedModel, query);
            }
        });

        return borrowedPanel;
    }
    
    private JPanel createReturnedPanel() {
        JPanel returnedPanel = new JPanel();
        returnedPanel.setLayout(null);
        returnedPanel.setBackground(Color.WHITE);

        JTextField searchTrackField = new JTextField();
        searchTrackField.setBounds(10, 10, 400, 30);
        searchTrackField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchTrackField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        returnedPanel.add(searchTrackField);

        JButton searchTrackBtn = new JButton("Search");
        searchTrackBtn.setBounds(420, 10, 100, 30);
        searchTrackBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        searchTrackBtn.setBackground(Color.WHITE);
        searchTrackBtn.setForeground(new Color(76, 175, 80));
        searchTrackBtn.setBorder(BorderFactory.createLineBorder(new Color(76, 175, 80), 1));
        searchTrackBtn.setFocusPainted(false);
        returnedPanel.add(searchTrackBtn);

        JComboBox<String> sortTrackComboBox = new JComboBox<>(new String[]{"Sort by Borrower No", "Sort by Borrowed Date"});
        sortTrackComboBox.setBounds(540, 10, 150, 30);
        sortTrackComboBox.setFont(new Font("Segoe UI", Font.BOLD, 12));
        sortTrackComboBox.setBorder(BorderFactory.createLineBorder(new Color(76, 175, 80), 1));
        returnedPanel.add(sortTrackComboBox);

        returnModel = new DefaultTableModel(
        new String[]{"Borrower No", "Quantity of Books", "Borrowed Date", "Return Date", "Borrower"}, 0);
        returnTable = new JTable(returnModel);
        JScrollPane returnedScrollPane = new JScrollPane(returnTable);
        returnedScrollPane.setBounds(10, 50, 730, 460);
        returnedPanel.add(returnedScrollPane);

        return returnedPanel;
    }

    private JPanel createRenewedPanel() {
        JPanel renewedPanel = new JPanel();
        renewedPanel.setLayout(null);
        renewedPanel.setBackground(Color.WHITE);

        JTextField searchTrackField = new JTextField();
        searchTrackField.setBounds(10, 10, 400, 30);
        searchTrackField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchTrackField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        renewedPanel.add(searchTrackField);

        JButton searchTrackBtn = new JButton("Search");
        searchTrackBtn.setBounds(420, 10, 100, 30);
        searchTrackBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        searchTrackBtn.setBackground(Color.WHITE);
        searchTrackBtn.setForeground(new Color(76, 175, 80));
        searchTrackBtn.setBorder(BorderFactory.createLineBorder(new Color(76, 175, 80), 1));
        searchTrackBtn.setFocusPainted(false);
        renewedPanel.add(searchTrackBtn);

        JComboBox<String> sortTrackComboBox = new JComboBox<>(new String[]{"Sort by Borrower No", "Sort by Borrowed Date"});
        sortTrackComboBox.setBounds(540, 10, 150, 30);
        sortTrackComboBox.setFont(new Font("Segoe UI", Font.BOLD, 12));
        sortTrackComboBox.setBorder(BorderFactory.createLineBorder(new Color(76, 175, 80), 1));
        renewedPanel.add(sortTrackComboBox);

        booksModel = new DefaultTableModel(
        new String[]{"Borrower No", "Quantity of Books", "Borrowed Date", "Renewed Date"}, 0);
        booksTable = new JTable(booksModel);
        JScrollPane renewedScrollPane = new JScrollPane(booksTable);
        renewedScrollPane.setBounds(10, 50, 730, 460);
        renewedPanel.add(renewedScrollPane);

        return renewedPanel;
    }

    private void switchCard(String cardName, JButton activeButton, JButton... otherButtons) {
        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, cardName);

        activeButton.setBackground(new Color(46, 125, 50));
        for (JButton button : otherButtons) {
            button.setBackground(new Color(169, 169, 169));
        }
    }
}
