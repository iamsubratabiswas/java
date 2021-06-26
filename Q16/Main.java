/*
Problem Description:

A bookshop maintains the inventory of books that are being sold at the shop. The list includes details such as author, title, publisher,
cost and stock position. Whenever a customer wants a book, the sales person inputs the title and author and the system searches the list and displays
whether it is available or not. If it is not, an appropriate message is displayed. If it is, then the system displays the book details and details
and requests for the number of copies required. If the required copies are available, the total cost of the requested copies is displayed,
otherwise the message “requires copies not in stock” is displayed. Design a system using a class called “Book” with suitable member methods and constructors.
*/

import java.util.Scanner;

class StockPos
{
    static int row=1, col=0;
}
class Book {
    String author, title, publisher;
    private float costPerCopy;
    private int noOfCopies = 0;

    Book() {
        System.out.println("INPUT DETAILS:");  //We can add another member(static int ) in Book class for counting the book number
        Scanner sc = new Scanner(System.in);

        System.out.print("Title: ");
        title = sc.nextLine();
        System.out.print("Author: ");
        author = sc.nextLine();
        System.out.print("Publisher: ");
        publisher = sc.nextLine();
        System.out.print("Cost Per Copy: ");
        costPerCopy = Float.parseFloat(sc.nextLine());
        System.out.print("No of Copies:");
        noOfCopies = Integer.parseInt(sc.nextLine());
        ++StockPos.col;
        if (StockPos.col > 5)  //We want to put 5 books in a row
        {
            ++StockPos.row;
            StockPos.col = 1;
        }
    }

    public void showDetails() {
        System.out.println("Here are the details related to this particular Book:");
        System.out.println("Title: " + title + "\nAuthor: " + author + "\nPublisher: " + publisher);
        System.out.println("Cost per Copy:" + costPerCopy + "\nStock Pos:(row,col)->(" + StockPos.row + "," + StockPos.col + ")");
    }

    public void saleBook() {
        int reqCopy;
        showDetails();

        if (noOfCopies == 0) {
            System.out.println("Sorry! This Book is currently out of stock\nKindly visit again later:(");
            return;
        }

        System.out.print("How many copies you want?(" + noOfCopies + " copies of this book is available for now):");
        Scanner sc = new Scanner(System.in);
        reqCopy = Integer.parseInt(sc.nextLine());
        if (reqCopy <= noOfCopies) {
            System.out.println("Thank You for shopping with us!\nHere's your total bill: " + costPerCopy * reqCopy+"INR");
            noOfCopies -= reqCopy;
            return;
        } else if (reqCopy > noOfCopies && noOfCopies > 0) {
            System.out.println("Sorry! only " + noOfCopies + " copies are available right now:(");
            return;
        }
    }


    public static class Main {
        Main() {
        }

        public int checkExistence(Book B[], String title, String auth) {
            for (int i = 0; i < B.length; i++) {
                if (B[i].title.equals(title))
                    if (B[i].author.equals(auth))
                        return i;
            }
            System.out.println("Sorry! The Book doesn't exist");
            return 0;
        }

        public static void main(String[] args) {
            int n;
            Book B[];
            String title, author;

            System.out.println("The Library is empty now:(\nPlease add some books");
            System.out.print("Enter How many books will be added to the library:");
            Scanner sc = new Scanner(System.in);
            n = Integer.parseInt(sc.nextLine());

            B = new Book[n];
            for (int i = 0; i < B.length; i++)
                B[i] = new Book();

            //The Books are added, Now let's see what customer wants
            System.out.println("Enter the Book title and Book Author which you want to buy:");
            title = sc.nextLine();
            author = sc.nextLine();

            Main M = new Main();
            if (M.checkExistence(B, title, author) != 0)
            {
                System.out.println("Yes, the Book Exists!");
                B[M.checkExistence(B, title, author)].saleBook();
            }
            else
                System.out.println("The Book doesn't exist in our Library:(");
        }
    }
}

//PS: THIS PROGRAM IS NOT WORKING FOR THE FIRSTLY ADDED BOOK,IT NEEDS TO BE VERIFIED ONCE AND SHOULD BE UPDATED
