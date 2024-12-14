
package LinkedList;


public class BorrowedBookNode {
    
    public String callNumber, title, author, borrowerNum, borrowedDate,borrowerDetails, expectReturn;
    public int quantityBorrowed;
    public long isbn;
    public BorrowedBookNode next;
    
    public BorrowedBookNode(String callnumber,long isbn,int quantityBorrowed, String borrowerNum, String borrowedDate,String expectReturn,String borrowerDetails, String title,String author){
        this.callNumber = callnumber;
        this.title = title;
        this.author = author;
        this.borrowerNum = borrowerNum;
        this.borrowedDate = borrowedDate;
        this.borrowerDetails = borrowerDetails;
        this.quantityBorrowed = quantityBorrowed;
        this.isbn = isbn;
        this.expectReturn = expectReturn;
        this.next = null;
    }
}
