/*
Problem Description:

Write a class “BankAccount” with the following instance variables:
AccountNumber (an integer), balance (a floating-point number), and “ownerName” (a String).
Write proper constructor for this class. Also write methods balance, add (to deposit an amount),
and subtract (to withdraw an amount) and implement them. Now create another class “AccountManager”
that contains an array of BankAccount. Write methods create (to create an account), delete(to terminate an account),
deposit (to deposit an amount to an account) and withdraw (to withdraw an amount from an account). Also write a class “Bank”,
add main() function that creates an AccountManager and add 5 accounts. Now print the details of all accounts in this Bank.
 */


class BankAccount
{
    private int AccountNumber;
    private float balance;
    private String ownerName;
    BankAccount()
    { }

    public void setDetails(int AccN,float bal, String name)
    {
        AccountNumber=AccN; balance=bal; ownerName=name;
    }

    public float getBalance()
    { return balance;}

    public void add(float dep)
    {
        if(dep<0)
            System.out.println("Dear "+ownerName+", Please enter a valid amount and try again\nThank You!");
        else
          balance=balance+dep;
    }

    public void subtract(float sub)
    {
            if(sub>balance)
            {
                System.out.println("Sorry "+ownerName+", Your withdrawal amount exceeds the available balance !" +
                        "\nTry again with a valid amount:)");
            }
            else {
                balance = balance - sub;
                System.out.println(sub+" withdrawned successfully:)");
            }
    }
    public void showAccountDetails()
    {
        System.out.println("Acc No:"+AccountNumber+"\nAvail. Bal:"+getBalance()+"\nAcc owner:"+ownerName);
    }
}

class AccountManager
{
    BankAccount B[];
    AccountManager()
    {
        B=new BankAccount[5];
        for(int i=0;i<5;i++)
            B[i]=new BankAccount();
    }
    public int accountIndex=-1;  //This will help us to track the existing accounts

    public void create(int AccN,float bal, String name) //Our program doesn't check duplicate account entries->We can do so but for now just let it as it is
    {
        if(accountIndex<5)
        {
            ++accountIndex;
            B[accountIndex].setDetails(AccN, bal, name);
        }
        else
            System.out.println("Sorry! Crossed the maximum number of accounts:(");
    }
    public void delete() //In our program only that account will be deleted which is added at the very last->We can do so but for now let it as it is
    {
        B[accountIndex]=null;
        --accountIndex;
    }

    public void DisplayAccountDetails()
    {
        for(int i=0;i<=accountIndex;i++)
        {
            System.out.println("\n\nAccount"+(i+1)+":");
            B[i].showAccountDetails();
        }
    }
}


public class Bank{
    public static void main(String[] args)
    {
      AccountManager A=new AccountManager();

      A.create(1055,1000f,"SUBRATA");
      A.create(1054,1050f,"SUJOY");
      A.create(1056,1110f,"ANUPAM");
      A.create(1052,1050f,"SOHAM");
      A.create(1053,2000f,"DHERAJ");
      A.DisplayAccountDetails();

      //Adding invalid balance to Rakib's account
      A.B[1].add(-10000f);
      A.DisplayAccountDetails();

      //Deleting the last account
      A.delete();

      //subtracting 100000 from My Account which is > available balance
      A.B[0].subtract(10000f);
      A.DisplayAccountDetails();
    }
}