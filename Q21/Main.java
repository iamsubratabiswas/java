/*
Problem Description:

Consider the following series
x = 1+1/1! +1/2! + 1/3! + ………1/10!
Create two threads t1 & t2.  t1 will generate the denominators and t2 will form the term and add them up. Finally print the result.
*/

/*
temp -> for checking whether this is an even or odd step, means which thread should work now that will bw tracked
den -> for calculating the denominator each time and to store it
series -> same for calculating the series
tIndex -> non-static ->For checking which Thread has the current access on the synchronized block
noOfTerms -> Will help us to know for how long the while loop should execute
factIndex -> It will give us the value whose factorial is to be calculated, it is incremented each time

->the 'obj.notifyAll()' is important which is written at the end of each block, otherwise at a certain time both the Threads will be under wait,
and the program will be in a paused stage

DOUBT: Here as I can see the Threads are working simultaneously (not at a same time), means one Thread is waiting for the other Thread till it finishes its work
       -> So what are the advantages we are getting here after using Threads???
*/

import java.util.Scanner;

class A implements Runnable
{
    static Object obj=new Object();  //The Common Resource/Reference that will be used by both the Threads
    static int temp=1, den=0, noOfTerms, factIndex=0;
    static float series=0;
    int tIndex;

    A(int index)
    {
        tIndex=index;
    }

    static
    {
        System.out.print("Enter the number of terms present in the series: ");
        Scanner sc = new Scanner(System.in);
        noOfTerms=Integer.parseInt(sc.nextLine());
    }

    int fact(int n)
    {
        int fact=1;
        for (int i = 1; i <= n; ++i) {
            fact *= i;
        }

        return fact;
    }

    void calDen(int f)
    {
        den=fact(f);
        noOfTerms--;  //Should be incremented only when a factorial is calculated, it indicates no of steps is decreasing
    }

    @Override
    public void run()
    {
        while(noOfTerms>0)
        {
            synchronized (obj)
            {
                System.out.println(Thread.currentThread().getName()+" is Running");  //For checking purpose
                if(temp%2==1 && tIndex==1) //Condition#1: If true, Thread1 should calculate 'den' and notify the Thread2 for calculating the 'series'
                {
                    System.out.println("First Block");
                    calDen(factIndex++);
                    temp++;
                    obj.notifyAll();
                }

                else if(temp%2==0 && tIndex==1)  //Condition#2: If true, Thread1 will go on wait and should be under wait until Thread2 calculates the current series and notifies it again
                {
                    System.out.println("Second Block");
                    try {
                        System.out.println("T1 is under wait");
                        obj.wait();
                        System.out.println("T1 is resumed");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //The below code executes only After the Thread1 got notified
                    calDen(factIndex++);
                    temp++;
                    obj.notifyAll();
                }

                else if(temp%2==0 && tIndex==2)  //Condition#3: If true, Thread2 should calculate the 'series' and notify the Thread1 to calculate the next 'den'
                {
                    System.out.println("Third Block");
                    series=series+1/(float)den;
                    temp++;
                    obj.notifyAll();
                }

                else if(temp%2==1 && tIndex==2)  //Condition#4: If true, Thread2 will go on wait and should be under wait until Thread1 calculates the next 'den' and notifies Thread2 again
                {
                    System.out.println("Fourth Block");
                    try {
                        System.out.println("T2 is under wait");
                        //obj.notifyAll();
                        obj.wait();
                        System.out.println("T2 is resumed");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    series=series+1/(float)den;
                    temp++;
                    obj.notifyAll();
                }
            }
        }

        //Both the Threads will come at this stage, but we are neglecting the result obtained by T1
        //b'coz, T1 calculates 'den' only and T2 calculates the final series, that's why we should wait for T2 to finish it's work successfully
        if(A.noOfTerms==0&&tIndex==2)
        {
            System.out.println("\n\nTHE FINAL RESULTED SERIES: "+A.series+"(This line is Printed by "+Thread.currentThread().getName()+")");
            System.exit(0);
        }
    }
}

public class Main
{
    public static void main(String[] args)
    {
        A obj1=new A(1);
        A obj2=new A(2);

        Thread T1=new Thread(obj1, "T1");
        Thread T2=new Thread(obj2, "T2");

        T1.start();
        T2.start();

    }
}
