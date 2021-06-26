/*
Problem Description:

Imagine a toll booth and a bridge. Cars passing by the booth are expected to pay an amount of Rs. 50/- as toll tax.
Mostly they do but sometimes a car goes by without paying. The toll booth keeps track of the number of the cars that have passed without paying,
total number of cars passed by, and the total amount of money collected. Execute  this with a class called “Tollbooth” and print out the result as follows:
The total number of cars passed by without paying.
Total number of cars passed by.
Total cash collected.
*/

import java.util.Random;
import java.util.Scanner;

class TollBooth
{
    public static int totalCarPassed=0;
    public static int PassedWithoutPaying=0;
    public static char []code=new char[]{'A','S','K','M','J','O','W','B','V'};
    public static String []carType=new String[]{"TAXI","BIKE","OLA","UBER","BMW","TRUCK"};
    public static float cashCollected=0f;
    public static String []notPaid=new String[10];
    private String HSRP;
    private String temp;

    TollBooth()
    {
        ++totalCarPassed;
        Random random=new Random();
        HSRP="WB"+String.valueOf(random.nextInt(100))+String.valueOf(code[random.nextInt(code.length)])+String.valueOf(random.nextInt(100));
        System.out.print("Hey..! A "+carType[random.nextInt(carType.length)]+", having HSRP number: "+HSRP+" is just passed the Booth" +
                "\nHas it paid the tax?(Y/N):");
        Scanner sc=new Scanner(System.in);
        temp=sc.nextLine();
        if(temp.equals("N")||temp.equals("n"))
        {
            ++PassedWithoutPaying;
            notPaid[PassedWithoutPaying-1]=HSRP;
        }
        else
            cashCollected+=50.0f;
    }

    static void NotPaid()
    {
        System.out.println("\nHSRP of those cars who has not paid the tax:");
        for(int i=0;i<PassedWithoutPaying;i++)
            System.out.println(notPaid[i]);
    }
}


public class Main {
    public static void main(String[] args)
    {
        System.out.println("\n\nIt is "+java.time.LocalDate.now()+", Let's check how many cars pass the bridge today!\n");
        Random random=new Random();
        TollBooth []TB=new TollBooth[random.nextInt(10)];

        if(TB.length==0)
        {
            System.out.println("Oops..! No Car passed the Bridge Today!");  //A very rare case when random() generates 0
            return;
        }

        for(int i=0;i<TB.length;i++)
            TB[i]=new TollBooth();

        System.out.println("\nFinal Report After the end of the day:\n");
        System.out.println("Total Car Passed Today: "+TB.length);
        System.out.println("Car Passed without paying: "+TollBooth.PassedWithoutPaying);
        System.out.println("Total Money Collected Today: "+TollBooth.cashCollected+"INR");

        TollBooth.NotPaid();
    }
}
