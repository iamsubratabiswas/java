/*
Problem Description:

Write a class for “Account” containing data members ‘accountNumber’, ‘holderName’, ‘balance’ and add constructors and necessary
accessor/modifier functions for these data members. Now create two class “SavingsAccount” and “CurrentAccount” extending from this class.
SavingsAccount will have a member variable ‘interestRate’ and member function ‘calculateYearlyInterest’. Write another class “Manager”
that contains a list Account. Also write a main() function to create an instance of Manager class. Add two SavingsAccount and
three CurrentAccount to Manager. Calculate interest of each SavingsAccount. Print the details of all accounts.
 */
import java.util.Scanner;

abstract class Account
{
    protected long accountNumber;
    protected String holderName;
    protected float balance=0;
    abstract protected float calculateYearlyInterest();
    protected void showAccDetails()
    { }
}

class SavingsAccount extends Account
{
  private static float interestRate;
  public static int noOfSavingsAcc;
  public static int SavingsIndex=-1;

  static
  {
      System.out.print("Interest Rate of the Savings Accounts:");
      Scanner sc=new Scanner(System.in);
      interestRate=Float.parseFloat(sc.nextLine());
  }

  SavingsAccount()
  {
      System.out.println("SAVINGS_ACCOUNT#:"+(++SavingsAccount.SavingsIndex+1));
      System.out.print("Account Number:");
      Scanner sc=new Scanner(System.in);
      accountNumber=Long.parseLong(sc.nextLine());
      System.out.print("Holder Name:");
      holderName=sc.nextLine();
      System.out.print("Balance:");
      balance=Float.parseFloat(sc.nextLine());
  }
  public float calculateYearlyInterest()
  {
    return((balance*1*interestRate)/(float)100);
  }
  protected void showAccDetails()
  {
      System.out.println("Account No:"+accountNumber);
      System.out.println("Holder Name:"+holderName);
      System.out.println("Balance:"+balance);
      System.out.println("Interest Rate:"+interestRate+"%");
      System.out.println("Yearly Interest:"+calculateYearlyInterest());
  }
}

class CurrentAccount extends Account
{
    public static int noOfCurrentAcc;
    public static int CurrentIndex=-1;

    CurrentAccount()
    {
        System.out.println("CURRENT_ACCOUNT#:"+(++CurrentAccount.CurrentIndex+1));
        System.out.print("Account Number:");
        Scanner sc=new Scanner(System.in);
        accountNumber=Long.parseLong(sc.nextLine());
        System.out.print("Holder Name:");
        holderName=sc.nextLine();
        System.out.print("Balance:");
        balance=Float.parseFloat(sc.nextLine());
    }

    @Override
    protected float calculateYearlyInterest() {  //We implemented this method here only b'coz it is declared as abstract in the parent class->Otherwise we are not required this method
        return 0;
    }

    protected void showAccDetails()
    {
        System.out.println("Account No:"+accountNumber);
        System.out.println("Holder Name:"+holderName);
        System.out.println("Balance:"+balance);
    }
}

class Manager
{
    Account A[];
    Manager()
    {
        System.out.print("No of Savings Account:");
        Scanner sc=new Scanner(System.in);
        SavingsAccount.noOfSavingsAcc=Integer.parseInt(sc.nextLine());
        System.out.print("No of Current Account:");
        CurrentAccount.noOfCurrentAcc=Integer.parseInt(sc.nextLine());
        A=new Account[SavingsAccount.noOfSavingsAcc+CurrentAccount.noOfCurrentAcc];
        for (int i=0;i<(SavingsAccount.noOfSavingsAcc+CurrentAccount.noOfCurrentAcc);i++)
            if(i>=SavingsAccount.noOfSavingsAcc)
                A[i]=new CurrentAccount();
            else
                A[i]=new SavingsAccount();
    }
    public void showAccDetails()
    {
        System.out.println("\nDISPLAYING EXISTING ACCOUNT DETAILS:");
        for (int i=0;i<(SavingsAccount.noOfSavingsAcc+CurrentAccount.noOfCurrentAcc);i++)
            if(i>=SavingsAccount.noOfSavingsAcc)
            {
                System.out.println("\nCURRENT_ACCOUNT#"+(i-SavingsAccount.noOfSavingsAcc+1));
                A[i].showAccDetails();
            }
            else
            {
                System.out.println("SAVINGS_ACCOUNT#"+(i+1));
                A[i].showAccDetails();
            }
    }
}

public class Main {
    public static void main(String[] args) {
        Manager M1=new Manager();
        M1.showAccDetails();
    }
}
