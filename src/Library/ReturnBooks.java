
package Library;
import LinkedList.BorrowedBookNode;
import Interface.LibraryInterface;
import LinkedList.BorrowedLinkedList;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.*;
import java.time.temporal.ChronoUnit;
import javax.swing.*;
import static javax.swing.JOptionPane.*;
import javax.swing.table.DefaultTableModel;


public class ReturnBooks implements ItemListener, LibraryInterface {
    private TrackBooks trackBooks;
    private Dashboard_Inventory inventory;
    
    DefaultTableModel books;
    DefaultTableModel borrowedModel;
    DefaultTableModel booksModel;
    DefaultTableModel InventoryModel;
    JPanel borrowerDitsP;
    JLabel dateborrowed;
    JTextArea borrowerDits;
    JTextField dateField;
    JLabel fee;
    ButtonGroup damagesGroup = new ButtonGroup();
    JButton saveBtn;
    public JPanel returnbooksP;
    JPanel returnPanel2;
    JLabel borrowerLabel;
    JPanel datePanel;
    JTextField code;
    JButton search;
    JPanel damagesPnl;
    JLabel damagelbl;
    JCheckBox tornPages;
    JCheckBox waterDamage;
    JCheckBox missingpage;
    JCheckBox writing;
    JCheckBox binding;
    JCheckBox cover;
    JCheckBox foodStains;
    JLabel scalelbl;
    JRadioButton minor;
    JRadioButton moderate;
    JRadioButton severe;
    JRadioButton extensive;
    JLabel explainmo;
    JTextField explanationField;
    JLabel damagefee;
    JLabel total;

    double inventoryPrice,damageFee = 0, totalFees = 0;
    String damagesItem, inventoryisbn, bookisbn,modelBorrowerNum,borrowerNum,borrowedDate,borrowerInfo, expectedReturnDate, returndate;
    int lateFee = 0;
    
    public ReturnBooks(TrackBooks trackBooks, Dashboard_Inventory inventory){
        this.trackBooks = trackBooks;
        this.inventory = inventory;
        
        returnbooksP = new JPanel();
        
        returnbooksP.setLayout(null);
        returnbooksP.setBackground(new Color(250, 250, 240));
        
        //borrower and dits bg
        returnPanel2 = new JPanel();
        returnPanel2.setLayout(null);
        returnPanel2.setBounds(680, 0, 400, 690);
        returnPanel2.setBackground(Color.white);
        returnbooksP.add(returnPanel2);
        
        borrowerDitsP = new JPanel();
        borrowerDitsP.setLayout(null);
        borrowerDitsP.setBackground(Color.WHITE);
        borrowerDitsP.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        borrowerDitsP.setBounds(25, 30, 350, 110);
        returnPanel2.add(borrowerDitsP);
        
        borrowerLabel = new JLabel("Borrower Details");
        borrowerLabel.setBounds(10, 5, 100, 20);
        borrowerDitsP.add(borrowerLabel);
        
        borrowerDits = new JTextArea();
        borrowerDits.setBounds(10, 40, 300, 50);
        borrowerDits.setEditable(false);
        borrowerDitsP.add(borrowerDits);
        
        datePanel = new JPanel();
        datePanel.setLayout(null);
        datePanel.setBackground(Color.WHITE);
        datePanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        datePanel.setBounds(25, 150, 350, 30);
        returnPanel2.add(datePanel);
        
        dateborrowed = new JLabel("Date borrowed: ");
        dateborrowed.setBounds(10, 5, 300, 20);
        datePanel.add(dateborrowed);
        
        String [] header = {"Call Number","ISBN","Title","Author"};
        books = new DefaultTableModel(header,0);
        JTable booksToReturn = new JTable(books);
        JScrollPane scrollable = new JScrollPane(booksToReturn);
        scrollable.setBounds(25, 200, 350, 200);
        returnPanel2.add(scrollable);

        code = new JTextField();
        code.setBounds(70, 30, 370, 30);
        code.setBackground(Color.WHITE);
        code.setForeground(new Color(76, 175, 80));
        code.setFont(new Font("Segoi UI", Font.BOLD, 14));
        code.setBorder(BorderFactory.createLineBorder(new Color(76, 175, 80), 1));
 
        code.setOpaque(true);
        returnbooksP.add(code);

        search = new JButton("Search");
        search.setBounds(460, 30, 120, 30);
        search.setBackground(Color.WHITE);
        search.setForeground(new Color(76, 175, 80));
        search.setFont(new Font("Segoi UI", Font.BOLD, 14));
        search.setBorder(BorderFactory.createLineBorder(new Color(76, 175, 80), 1));
        search.setBorderPainted(true);
        search.setFocusPainted(false);
        search.setOpaque(true);
        returnbooksP.add(search);
        
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrowerNum = code.getText().trim();
                if (!borrowerNum.isEmpty()) {
                    filterBooks(borrowerNum);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "No borrower number found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // DAMAGES NA CHECKBOX 
        damagesPnl = new JPanel();
        damagesPnl.setBounds(70, 80, 540, 550);
        damagesPnl.setBackground(new Color(250, 250, 240));
        damagesPnl.setLayout(null);
        damagesPnl.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        
        JLabel date = new JLabel(" Date Returned:");
        date.setBounds(10, 10, 150, 20);
        date.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        date.setForeground(new Color(54, 69, 79));
        damagesPnl.add(date);

        dateField = new JTextField();
        dateField.setBounds(150, 10, 170, 25);
        dateField.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        dateField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        damagesPnl.add(dateField);

        damagelbl = new JLabel(" Damages:");
        damagelbl.setBounds(10, 40, 150, 22);
        damagelbl.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        damagelbl.setForeground(new Color(54, 69, 79));
        damagesPnl.add(damagelbl);

        tornPages = new JCheckBox("Torn Pages");
        tornPages.setBounds(10, 70, 200, 25);
        tornPages.setBackground(new Color(250, 250, 240));
        tornPages.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        damagesPnl.add(tornPages);

        waterDamage = new JCheckBox("Water Damage");
        waterDamage.setBounds(10, 100, 200, 25);
        waterDamage.setBackground(new Color(250, 250, 240));
        waterDamage.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        damagesPnl.add(waterDamage);

        missingpage = new JCheckBox("Missing Pages");
        missingpage.setBounds(10, 130, 200, 25);
        missingpage.setBackground(new Color(250, 250, 240));
        missingpage.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        damagesPnl.add(missingpage);

        writing = new JCheckBox("Writing or Markings on Pages");
        writing.setBounds(10, 160, 250, 25);
        writing.setBackground(new Color(250, 250, 240));
        writing.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        damagesPnl.add(writing);

        binding = new JCheckBox("Loose Binding");
        binding.setBounds(10, 190, 200, 25);
        binding.setBackground(new Color(250, 250, 240));
        binding.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        damagesPnl.add(binding);

        cover = new JCheckBox("Missing Cover");
        cover.setBounds(10, 220, 200, 25);
        cover.setBackground(new Color(250, 250, 240));
        cover.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        damagesPnl.add(cover);

        foodStains = new JCheckBox("Food or Drink Stains");
        foodStains.setBounds(10, 250, 200, 25);
        foodStains.setBackground(new Color(250, 250, 240));
        foodStains.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        damagesPnl.add(foodStains);
        
        scalelbl = new JLabel(" Scale:");
        scalelbl.setBounds(10, 280, 150, 22);
        scalelbl.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        scalelbl.setForeground(new Color(54, 69, 79));
        damagesPnl.add(scalelbl);
        
        minor = new JRadioButton("Minor damage");
        minor.setBounds(10, 310, 200, 25);
        minor.setBackground(new Color(250, 250, 240));
        minor.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        damagesPnl.add(minor);
        damagesGroup.add(minor);
        minor.addItemListener(this);
        
        moderate = new JRadioButton("Moderate damage");
        moderate.setBounds(10, 335, 200, 25);
        moderate.setBackground(new Color(250, 250, 240));
        moderate.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        damagesPnl.add(moderate);
        damagesGroup.add(moderate);
        moderate.addItemListener(this);
        
        severe = new JRadioButton("Severe damage");
        severe.setBounds(10, 360, 200, 25);
        severe.setBackground(new Color(250, 250, 240));
        severe.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        damagesPnl.add(severe);
        damagesGroup.add(severe);
        severe.addItemListener(this);
        
        extensive = new JRadioButton("Extensive damage");
        extensive.setBounds(10, 385, 200, 25);
        extensive.setBackground(new Color(250, 250, 240));
        extensive.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        damagesPnl.add(extensive);
        damagesGroup.add(extensive);
        extensive.addItemListener(this);
        
        explainmo = new JLabel("Explain Further (if any):");
        explainmo.setBounds(15, 455, 200, 20);
        explainmo.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        explainmo.setForeground(new Color(54, 69, 79));
        damagesPnl.add(explainmo);

        explanationField = new JTextField();
        explanationField.setBounds(180, 455, 220, 23);
        explanationField.setFont(new Font("Sans Serif", Font.PLAIN, 12));
        explanationField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        damagesPnl.add(explanationField);

        saveBtn = new JButton("Enter");
        saveBtn.setBounds(410, 455, 80, 23);
        saveBtn.setBackground(Color.WHITE);
        saveBtn.setForeground(new Color(76, 175, 80));
        saveBtn.setFont(new Font("Segoi UI", Font.BOLD, 12));
        saveBtn.setBorder(BorderFactory.createLineBorder(new Color(76, 175, 80), 1));
        saveBtn.setBorderPainted(true);
        saveBtn.setFocusPainted(false);
        saveBtn.setOpaque(true);
        damagesPnl.add(saveBtn);
        returnbooksP.add(damagesPnl);
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReturnFees();
            }
        });
        
        JPanel FeePanel = new JPanel();
        FeePanel.setLayout(null);
        FeePanel.setBackground(Color.WHITE);
        FeePanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        FeePanel.setBounds(25, 420, 350, 150);
        returnPanel2.add(FeePanel);
        
        fee = new JLabel("Late Fee: " + lateFee);
        fee.setBounds(10, 20, 100, 20);
        fee.setFont(new Font("Segoi UI", Font.PLAIN, 15));
        fee.setForeground(new Color(54, 69, 79));
        FeePanel.add(fee);

        damagefee = new JLabel("Damage Fee: " + damageFee);
        damagefee.setBounds(10, 60, 200, 20);
        damagefee.setFont(new Font("Segoi UI", Font.PLAIN, 15));
        damagefee.setForeground(new Color(54, 69, 79));
        FeePanel.add(damagefee);

        total = new JLabel("Total Fee: "+ totalFees);
        total.setBounds(200, 100, 200, 20);
        total.setFont(new Font("Segoi UI", Font.PLAIN, 15));
        total.setForeground(new Color(54, 69, 79));
        FeePanel.add(total);
        
        JButton returnbtn = new JButton("Return books");
        returnbtn.setBounds(80, 600, 250, 30);
        returnbtn.setBackground(new Color(76, 175, 80));
        returnbtn.setForeground(Color.WHITE);
        returnbtn.setFont(new Font("Arial", Font.BOLD, 14));
        returnbtn.setBorderPainted(false);
        returnbtn.setFocusPainted(false);
        returnbtn.setOpaque(true);
        returnPanel2.add(returnbtn);
        
        returnbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dateField.equals(null)){
                    JOptionPane.showMessageDialog(dateborrowed, "Please input the return date", "Error", ERROR_MESSAGE);
                }else{
                    // check if the book has already been returned
                    if (isBookAlreadyReturned(modelBorrowerNum)) {
                        JOptionPane.showMessageDialog(null, "This book has already been returned.");
                        clearFields();
                    } else{
                        ReturnBooks();
                    }
                }
            }
        });

    }
    
    private void filterBooks(String borrowerNum) {
        // Clear the current books table
        books.setRowCount(0);
        // Filter books from TrackBooks.booksModel
        booksModel = trackBooks.booksModel;
        borrowedModel = trackBooks.borrowedModel;
        for (int i = 0; i < booksModel.getRowCount(); i++) {
            modelBorrowerNum = booksModel.getValueAt(i, 0).toString();
            bookisbn = booksModel.getValueAt(i, 2).toString();
            
            if (modelBorrowerNum.equals(borrowerNum)) {
                Object[] row = new Object[booksModel.getColumnCount() - 1];
                for (int j = 1; j < booksModel.getColumnCount(); j++) {
                    row[j - 1] = booksModel.getValueAt(i, j);
                }
                books.addRow(row);
            }
        }
        
        for(int x = 0; x < borrowedModel.getRowCount(); x++){
             String borrowerNo = borrowedModel.getValueAt(x, 0).toString();
             borrowerInfo = borrowedModel.getValueAt(x, 4).toString();
             borrowedDate = borrowedModel.getValueAt(x, 2).toString();
             
             if(borrowerNo.equals(borrowerNum)){
                  borrowerDits.setText(borrowerInfo);
                  dateborrowed.setText("Date borrowed: " + borrowedDate);
             }
             
             returnbooksP.revalidate();
             returnbooksP.repaint();
        }
    }
    
    private void ReturnFees(){
        returndate = dateField.getText().trim();
        String dateFormat = "E, MMM dd yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        
        try {
            borrowedModel = trackBooks.borrowedModel;
            for (int i = 0; i < borrowedModel.getRowCount(); i++) {
                modelBorrowerNum = borrowedModel.getValueAt(i, 0).toString();
                if (modelBorrowerNum.equals(borrowerNum)) {
                    expectedReturnDate = trackBooks.borrowedModel.getValueAt(i, 3).toString();
                    LocalDate parseexpectedDate = LocalDate.parse(expectedReturnDate, formatter);
                    // Parse the input date
                    LocalDate parsedReturnDate = LocalDate.parse(returndate, formatter);
                    
                    // Calculate the number of late days
                    int daysLate = (int) ChronoUnit.DAYS.between(parseexpectedDate, parsedReturnDate);
                    
                    lateFee = 0;
                    if (daysLate > 0) {
                        lateFee = (int) daysLate * 10;  // 10 pesos per day
                        
                        fee.setText("Late Fee: " + lateFee);
                    }
                }
            }
            
        } catch (DateTimeParseException e) {
            // Handle invalid date format
            JOptionPane.showMessageDialog(null, "Invalid Date", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        InventoryModel = inventory.Inventorytable;
        for (int n = 0; n < InventoryModel.getRowCount(); n++) {
            inventoryPrice = (double) InventoryModel.getValueAt(n, 8);
            inventoryisbn = InventoryModel.getValueAt(n, 1).toString();

            if (inventoryisbn.equals(bookisbn)) {

                damageFee = 0;
                // Check damages (damageFee = bookprice * percentage)
                if (minor.isSelected()) {
                    damageFee = inventoryPrice * 0.1;  //10%  
                }
                if (moderate.isSelected()) {
                    damageFee = inventoryPrice * 0.3; //30%
                }
                if (severe.isSelected()) {
                    damageFee = inventoryPrice * 0.5; //50%
                }
                if (extensive.isSelected()) {
                    damageFee = inventoryPrice * 1; //full price 100%
                }
                damagefee.setText("Damage Fee: " + damageFee);
            }
        }
        totalFees = damageFee + lateFee;
        total.setText("Total Fee: " + totalFees);
    }
    
    private void ReturnBooks(){
        borrowedModel = trackBooks.borrowedModel;
        for (int i = 0; i < borrowedModel.getRowCount(); i++) {
            modelBorrowerNum = borrowedModel.getValueAt(i, 0).toString();
             // Get the quantity being returned
            int quantityReturned = Integer.parseInt(borrowedModel.getValueAt(i, 1).toString());

            if (modelBorrowerNum.equals(borrowerNum)) {
                Object[] rowData = new Object[trackBooks.borrowedModel.getColumnCount()];
                for (int x = 0; x < trackBooks.borrowedModel.getColumnCount(); x++) {
                    rowData[x] = trackBooks.borrowedModel.getValueAt(i, x);
                }
                
                // Add the data to the returnModel in TrackBooks
                trackBooks.returnModel.addRow(rowData);
                trackBooks.borrowedModel.removeRow(i);
                inventory.BorrowedList.deleteItemAt(i);
                
                
                for(int r = 0; r< trackBooks.returnModel.getRowCount(); r++){
                    String borrowernum = trackBooks.returnModel.getValueAt(r, 0).toString();
                    
                    if(modelBorrowerNum.equals(borrowernum)){
                        trackBooks.returnModel.setValueAt(returndate, r, 3);
                        trackBooks.returnModel.setValueAt(totalFees, r, 5);
                    }
                }
                
                InventoryModel = inventory.Inventorytable;
                for(int row = 0; row < InventoryModel.getRowCount(); row++){
                    inventoryisbn = InventoryModel.getValueAt(row, 1).toString();
                    
                   
                    for (int b = 0; b < booksModel.getRowCount(); b++) {
                        bookisbn = booksModel.getValueAt(b, 2).toString();
                    
                     if(inventoryisbn.equals(bookisbn)){

                        // Increment the available quantity in inventory
                        int currentQuantity = Integer.parseInt(InventoryModel.getValueAt(row, 6).toString());
                        InventoryModel.setValueAt(currentQuantity + quantityReturned, row, 6);

                        // Decrement the borrowed quantity of the book
                        int currentBorrowed = Integer.parseInt(InventoryModel.getValueAt(row, 7).toString());
                        InventoryModel.setValueAt(currentBorrowed - quantityReturned, row, 7);
                        
                        int totalBorrowedBooks = TotalBorrowedBooks();
                        inventory.borrow.setText(" Total Borrowed Books: " + totalBorrowedBooks);

                        int totalCurrentBooks = CurrentNumBooks();
                        inventory.current.setText(" Current No. of Books: " + totalCurrentBooks);

                        break;
                    }
                  }
                }
                
                JOptionPane.showMessageDialog(null, "Return Successfully");
                clearFields();
            }
        }
    }
    
    private boolean isBookAlreadyReturned(String borrowerNum) {
        // Loop through the returnModel to check if the book has already been returned
        for (int r = 0; r < trackBooks.returnModel.getRowCount(); r++) {
            String returnBorrowerNum = trackBooks.returnModel.getValueAt(r, 0).toString();
            if (borrowerNum.equals(returnBorrowerNum)) {
                return true;  // Book already returned
            }
        }
        return false;  // Book not returned yet
    }
    
    private void clearFields(){
        code.setText("");
        dateField.setText("");
        explanationField.setText("");
        tornPages.setSelected(false);
        waterDamage.setSelected(false);
        missingpage.setSelected(false);
        writing.setSelected(false);
        binding.setSelected(false);
        cover.setSelected(false);
        foodStains.setSelected(false);
        borrowerInfo = "";
        borrowedDate = "";
        borrowerDits.setText("");
        dateborrowed.setText("Date borrowed: ");
        books.setRowCount(0);
        damagesGroup.clearSelection();
        lateFee = 0;
        damageFee = 0;
        totalFees = 0;
        fee.setText("Late Fee: " + lateFee);
        damagefee.setText("Damage Fee: " + damageFee);
        total.setText("Total Fee: " + totalFees);
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) { // to identify the chosen button
        
        if(minor.isSelected()){
            damagesItem = minor.getText();
        }else if(moderate.isSelected()){
            damagesItem = moderate.getText();
        }else if(severe.isSelected()){
            damagesItem = severe.getText();
        }else if(extensive.isSelected()){
            damagesItem = extensive.getText();
        }else{
            damagesItem = "null";
        }
    }



    @Override
    public int TotalBorrowedBooks() {
        int totalBorrowedBooks = 0; // Initialize the total borrowed books count
        for (int i = 0; i < inventory.Inventorytable.getRowCount(); i++) {
            int borrowedQuantity = (int) inventory.Inventorytable.getValueAt(i, 7); // Assuming column 7 is the borrowed quantity
            totalBorrowedBooks += borrowedQuantity; // Sum the borrowed quantities
        }
        return totalBorrowedBooks; // Return the total borrowed books count
    }

    @Override
    public int CurrentNumBooks() {
        int CurrentNumBooks = 0;
        for (int i = 0; i < inventory.Inventorytable.getRowCount(); i++) {
            int CurrentTotal = (int) inventory.Inventorytable.getValueAt(i, 6);
            CurrentNumBooks += CurrentTotal;
        }
        return CurrentNumBooks;
    }
    

    @Override
    public int TotalBooks() {
        int totalBooks = 0; // Reset totalBooks to zero
        for (int i = 0; i < inventory.Inventorytable.getRowCount(); i++) {
            int quantity = (int) inventory.Inventorytable.getValueAt(i, 6); // Assuming column 6 is the quantity
            totalBooks += quantity;
        }
        return totalBooks;
    }
}
