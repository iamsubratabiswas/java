/*
Problem Description:

Create an abstract class “Publication” with data members ‘noOfPages’, ‘price’, ‘publisherName’ etc. with their accessor/modifier functions.
Now create two sub-classes “Book” and “Journal”. Create a class Library that contains a list of Publications.
Write a main() function and create three Books and two Journals, add them to library and print the details of all publications.
 */


//PS: THIS QUESTION IS PROPERLY CLEAR TO ME
import java.util.Scanner;
abstract class Publication
{
    protected int noOfPages;
    protected float price;
    protected String publisherName;
    abstract protected void showChildDetails();
}

class Book extends Publication
{
  private String bookName;
  public static int bookIndex=-1;
  public static int noOfBooks;

  Book()
  {
      System.out.println("Enter BOOK#"+(++bookIndex+1)+" Details:");
      System.out.print("Name:");
      Scanner sc=new Scanner(System.in);
      bookName=sc.nextLine();
      System.out.print("No of Pages:");
      noOfPages=Integer.parseInt(sc.nextLine());
      System.out.print("Price:");
      price=Float.parseFloat(sc.nextLine());
      System.out.print("Publisher_Name:");
      publisherName=sc.nextLine();
  }

    @Override
    protected void showChildDetails()
    {
        System.out.println("Name:"+bookName);
        System.out.println("Pages:"+noOfPages);
        System.out.println("Price:"+price);
        System.out.println("Publisher:"+publisherName);
    }
}

class Journal extends Publication
{
    private String journalName;
    public static int noOfJournals;
    public static int journalIndex=-1;

    Journal()
    {
        System.out.println("Enter JOURNAL#"+(++journalIndex+1)+"Details:");
        System.out.print("Name:");
        Scanner sc=new Scanner(System.in);
        journalName=sc.nextLine();
        System.out.print("No of Pages:");
        noOfPages=Integer.parseInt(sc.nextLine());
        System.out.print("Price:");
        price=Float.parseFloat(sc.nextLine());
        System.out.print("Publisher_Name:");
        publisherName=sc.nextLine();
    }

    @Override
    protected void showChildDetails()
    {
        System.out.println("Name:"+journalName);
        System.out.println("Pages:"+noOfPages);
        System.out.println("Price:"+price);
        System.out.println("Publisher:"+publisherName);
    }
}

class Library
{
   Publication P[];
   Library()
   {
       System.out.print("Number of Books:");
       Scanner sc=new Scanner(System.in);
       Book.noOfBooks=Integer.parseInt(sc.nextLine());
       System.out.print("Number of Journals:");
       Journal.noOfJournals=Integer.parseInt(sc.nextLine());
       P=new Publication[Book.noOfBooks+Journal.noOfJournals];
       for (int i=0;i<(Book.noOfBooks+Journal.noOfJournals);i++)
           if(i>=Book.noOfBooks)
               P[i]=new Journal();  //Last blocks of P[] are filled with Journal type references
           else
               P[i]=new Book();  //First blocks of P[] are filled with Book type references

   }
   public void showPublicationDetails()
   {
       System.out.println("\nDISPLAYING EXISTING PUBLICATION DETAILS:");
       for (int i=0;i<(Book.noOfBooks+Journal.noOfJournals);i++)
           if(i>=Book.noOfBooks)
           {
               System.out.println("JOURNAL#"+(i-Book.noOfBooks+1));
               P[i].showChildDetails();
           }
           else
               {
               System.out.println("BOOK#" + (i + 1));
               P[i].showChildDetails();
           }
   }
}

public class Main
{
    public static void main(String[] args)
    {
        Library L1=new Library();
        L1.showPublicationDetails();
    }
}