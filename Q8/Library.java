/*
Problem Description:

Implement a class for a “Book”. Book contains a title (a String), a list of authors (array of authors), number of pages (an integer),
price (floating point number), publisher (a String) etc. Write suitable constructor and accessor/modifier methods.
Implement a class for “Library”. A library contains a list of books (array of Book). Write add (to add a book) and remove (to delete a book)
methods for library.
Write a main() function to create a “Library” and add five “Book” to library. Print the total price of all books.

 */
import java.util.Scanner;
class Book
{
    private String title;
    private int noOfAuth;
    private String authors[];
    private int pages;
    private float price;
    private String publisher;

    public void addBook()
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Input the number of Authors of this Book:");
        noOfAuth=Integer.parseInt(sc.nextLine());
        authors=new String[noOfAuth];
        System.out.println("Input the Author Names:");
        sc.reset();
        for(int i=0;i<noOfAuth;i++)
        {
            System.out.print("Author"+(i+1)+":");
            sc.ioException();
            authors[i]=sc.nextLine();
        }

        System.out.print("Enter the title name:");
        title=sc.nextLine();
        System.out.print("No of pages in the book:");
        pages=Integer.parseInt(sc.nextLine());
        System.out.print("Enter the Price:");
        price=Float.parseFloat(sc.nextLine());
        System.out.print("Publisher:");
        publisher=sc.nextLine();
    }
    public float getBookPrice()
    {
        return price;
    }

}

public class Library {
    Book B[];
    public int bookIndex=-1;
    Library()
    {
        B=new Book[5];
        for(int i=0;i<5;i++)
            B[i]=new Book();
    }

    public void add()
    {
        bookIndex++;
        B[bookIndex].addBook();
    }

    public void remove()
    {
        B[bookIndex]=null;
        bookIndex--;
    }
    public float countTotalPrice()
    {
        float tPrice=0.0f;
        for(int i=0;i<=bookIndex;i++)
           tPrice+=B[i].getBookPrice();
        return tPrice;
    }

    public static void main(String[] args) {
        Library L1=new Library();
        System.out.println("Book1:");
        L1.add();

        System.out.println("Book2:");
        L1.add();

        System.out.println("Total Price of the existing Books in Library L1: INR"+L1.countTotalPrice());
    }
}
