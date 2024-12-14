package Library;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TrackBooks extends JPanel {

    private JPanel cardPanel;
    private JPanel borrowedP;
    private JPanel returnedP;
    private JPanel LostP;
    private JTable borrowedTable;
    JButton searchTrackBtn;
    JTextField searchTrackField;
    JComboBox<String> sortTrackComboBox;
    public DefaultTableModel borrowedModel;
    public JTable returnTable;
    public DefaultTableModel returnModel;
    public DefaultTableModel filteredBooksModel;
    public DefaultTableModel booksModel;
    private JTable booksTable;
    public int selectedRow;
    public String borrower;
    public String formattedText; 
    
    SearchAndSort s = new SearchAndSort();

    public TrackBooks() {
        setLayout(null); //for manual positioning
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
      //  renewedP = createRenewedPanel();

        cardPanel.add(borrowedP, "Borrowed");
        cardPanel.add(returnedP, "Returned");
       // cardPanel.add(renewedP, "Renewed");

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
        booksModel = new DefaultTableModel(new String[]{"Borrower number","Call Number", "ISBN", "Title", "Author"}, 0);
        booksTable = new JTable(booksModel);
        
    }

    private JPanel createBorrowedPanel() {
        JPanel borrowedPanel = new JPanel();
        borrowedPanel.setLayout(null); 
        borrowedPanel.setBackground(Color.white);
        
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

        String[] headerTrack = {"Borrower no.","Quantity of book", "Borrowed Date", "Expected Return Date","Borrower"};
        borrowedModel = new DefaultTableModel(headerTrack, 0);
        borrowedTable = new JTable(borrowedModel);
        JScrollPane borrowedScroll = new JScrollPane(borrowedTable);
        
        borrowedScroll.setBounds(10, 50, 730, 460);
        borrowedPanel.add(borrowedScroll);
        borrowedTable.getColumnModel().removeColumn(borrowedTable.getColumnModel().getColumn(4));
        
        // Attach sorting and searching
        sortTrackComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) sortTrackComboBox.getSelectedItem();
                if ("Sort by Borrower No".equals(selectedOption)) {
                    boolean ascending = false; 
                    s.sortByBorrowerNo(borrowedModel,ascending);
                } else if ("Sort by Borrowed Date".equals(selectedOption)) {
                    boolean ascending = true; 
                    s.sortByDate(borrowedModel, 2,ascending); // Column index for Borrowed Date
                }
            }
        });

        searchTrackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchTrackField.getText();
                s.linearSearch(borrowedModel, query);
            }
        });
    

        borrowedTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedRow = borrowedTable.getSelectedRow();
                if (selectedRow != -1) {
                    borrowerDetails();
                }
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

        String[] returnTrack = {"Borrower no.", "Quantity of book", "Borrowed Date", "Return Date", "Borrower","Paid fine"};
        returnModel = new DefaultTableModel(returnTrack, 0);
        returnTable = new JTable(returnModel);
        JScrollPane returnScroll = new JScrollPane(returnTable);
        returnScroll.setBounds(10, 50, 730, 460);
        returnedPanel.add(returnScroll);

        // Hide the Borrower column in the returned table for a cleaner display
        returnTable.getColumnModel().removeColumn(returnTable.getColumnModel().getColumn(4));
        
        // Attach sorting and searching
        sortTrackComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) sortTrackComboBox.getSelectedItem();
                if ("Sort by Borrower No".equals(selectedOption)) {
                    boolean ascending = false;
                    s.sortByBorrowerNo(returnModel, ascending);
                } else if ("Sort by Borrowed Date".equals(selectedOption)) {
                    boolean ascending = true;
                    s.sortByDate(returnModel, 2, ascending); // Column index for Borrowed Date
                }
            }
        });

        searchTrackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchTrackField.getText();
                s.linearSearch(returnModel, query);
            }
        });

        returnTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedRow = returnTable.getSelectedRow();
                if (selectedRow != -1) {
                    borrowerDetails();
                }
            }
        });

         return returnedPanel;
    }

    private JPanel createLostBookPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null); 
        panel.setBackground(Color.blue);

        return panel;
    }

//    private void switchCard(String cardName) {
//        CardLayout cl = (CardLayout) cardPanel.getLayout();
//        cl.show(cardPanel, cardName);
//    }
    
    public void borrowerDetails(){
        JFrame detailsfrm = new JFrame();
        detailsfrm.setLayout(null);
        detailsfrm.setResizable(false);
        detailsfrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        detailsfrm.setSize(500, 500);
        detailsfrm.setVisible(true);
        detailsfrm.setLocationRelativeTo(null);
        
        JPanel bgpanel = new JPanel();
        bgpanel.setLayout(null);
        bgpanel.setBounds(0, 0, 500, 500);
        bgpanel.setBackground(Color.white);
        detailsfrm.add(bgpanel);
        
        JPanel borrowerDitsP = new JPanel();
        borrowerDitsP.setLayout(null);
        borrowerDitsP.setBackground(Color.WHITE);
        borrowerDitsP.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        borrowerDitsP.setBounds(20, 20, 450, 110);
        bgpanel.add(borrowerDitsP);
        
        JLabel borrowerLabel = new JLabel("Borrower Details");
        borrowerLabel.setBounds(10, 5, 200, 20);
        borrowerDitsP.add(borrowerLabel);
        
        // Determine the active table and model using if-else
        JTable activeTable;
        DefaultTableModel activeModel;

        if (borrowedP.isVisible()) {
            activeTable = borrowedTable;
            activeModel = borrowedModel;
        } else {
            activeTable = returnTable;
            activeModel = returnModel;
        }

        int selectedRow = activeTable.getSelectedRow();
        borrower = activeModel.getValueAt(selectedRow, 4).toString();
        formattedText = borrower.replace("Student No.:", "Student No.:")
                .replace("Full Name:", "Full Name:")
                .replace("Program:", "Program:");

        // JTextArea for multi-line text
        JTextArea borrowerDits = new JTextArea(formattedText);
        borrowerDits.setBounds(10, 40, 400, 50);
        borrowerDits.setEditable(false);
        borrowerDitsP.add(borrowerDits);

        // Create a filtered model for books specific to the borrower
        filteredBooksModel = new DefaultTableModel(
                new Object[]{"Call Number", "ISBN", "Title", "Author"},
                0
        );

        String borrowerNum = activeModel.getValueAt(selectedRow, 0).toString();

        // Filter the booksModel for the selected borrower
        for (int i = 0; i < booksModel.getRowCount(); i++) {
            String modelBorrowerNum = booksModel.getValueAt(i, 0).toString();
            if (modelBorrowerNum.equals(borrowerNum)) {
                Object[] row = new Object[booksModel.getColumnCount() - 1]; // Exclude BorrowerNum column
                for (int j = 1; j < booksModel.getColumnCount(); j++) {
                    row[j - 1] = booksModel.getValueAt(i, j);
                }
                filteredBooksModel.addRow(row);
            }
        }

        JTable filteredBooksTable = new JTable(filteredBooksModel);
        JScrollPane booksScroll = new JScrollPane(filteredBooksTable);
        booksScroll.setBounds(20, 200, 450, 200);
        bgpanel.add(booksScroll);
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
