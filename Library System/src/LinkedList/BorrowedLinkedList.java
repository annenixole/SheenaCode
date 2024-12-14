
package LinkedList;

public class BorrowedLinkedList {
    public BorrowedBookNode head;
    
    public BorrowedLinkedList(){
        head = null;
    }
    
    public void borrowBook(String callnumber,long isbn,int quantityBorrowed, String borrowerNum, String borrowedDate,String expectReturn,String borrowerDetails, String title,String author){
        BorrowedBookNode newBook = new BorrowedBookNode(callnumber, isbn, quantityBorrowed, borrowerNum, borrowedDate,expectReturn, borrowerDetails, title, author);
        
        //Adding borrowedbook to the last row
        if(head == null){
            head = newBook;
        }  else{
            BorrowedBookNode current = head;
            while(current.next != null){
                current = current.next;
            }
            current.next = newBook;
        }
    }
    
    public void deleteItemAt(int index) {
        // If the list is empty
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        // If the node to delete is the head
        if (index == 0) {
            head = head.next;
            return;
        }
        BorrowedBookNode current = head;
        int currentIndex = 0;
        // Traverse the list to find the node just before the one to delete
        while (current != null && currentIndex < index - 1) {
            current = current.next;
            currentIndex++;
        }
        // If the index is out of bounds (current is null or no next node)
        if (current == null || current.next == null) {
            System.out.println("Index out of bounds or next node is null!");
            return;
        }
        // Skip the node to delete (i.e., remove it from the list)
        current.next = current.next.next;
    }
    
    
    public void printBook(){
      BorrowedBookNode current = head;
      while(current != null){
          System.out.println(current.callNumber + " " + current.isbn + " " + current.quantityBorrowed + " " + current.borrowerNum
                  + " " + current.borrowedDate + " " + current.expectReturn + " " + current.borrowerDetails + " " + current.title + " " + current.author);
          current = current.next;
      }
    }
    
    public BorrowedBookNode getItemAt(int index){
        BorrowedBookNode current = head;
        int currentIndex = 0;
        
        while(current!= null){
            if(currentIndex == index){
                return current;
            }
            current = current.next;
            currentIndex++;
        }
        return null;
    }
}
