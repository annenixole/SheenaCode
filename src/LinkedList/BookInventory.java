
package LinkedList;


public class BookInventory { // node

    public String imagebook, titlebook, bookauthor,booktype,categtype;
    public int quantitybook, borrowedquantity;
    public long isbnbook;
    public double pricebook;
    public BookInventory next;
  
    
    public BookInventory(String image,long isbn,String booktype,String categ, String title, String author,int quantity,int borrowedquantity, double price){
        this.quantitybook = quantity;
        this.imagebook = image;
        this.isbnbook = isbn;
        this.booktype = booktype;
        this.categtype = categ;
        this.titlebook = title;
        this.bookauthor = author;
        this.pricebook = price;
        this.borrowedquantity = 0;
        this.next = null;
        
    }
}