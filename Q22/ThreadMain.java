/*
Problem Description:

Consider a file that contains a number of integers. Create two threads. Call them ‘producer’ and ‘consumer’ thread.
Producer thread will be reading the integers from the file continuously while consumer thread will add them up.
Use proper synchronization mechanism if needed.
*/

/*
THE CONCEPT I APPLIED HERE IS VERY SIMILAR WITH THE PREVIOUS PROGRAM(Q21)

PS: Please Remove The Comments(println() statements only) if You Face Any Difficulty for Debugging The Program

A few scenarios are not handled here->
                    1. While writing the file don't put space at the end  (This can be fixed, I'll do it later)
                    2. You may enter character also but it should not be at the end (This can be fixed, I'll do it later)
                    3. Whatever you write it must be terminated with an Integer
                    4. Integers having only single digit are considered here (Later I'll try to update the code)
*/


import java.io.*;
import java.util.Scanner;

class myThread implements Runnable
{
    static Object obj = new Object();
    static int dataCollected, sum = 0, temp = 1;
    static boolean flag=true;
    static BufferedReader br;
    static FileReader fr;
    int tIndex;

    myThread(int index)
    {
        tIndex = index;
    }

    @Override
    public void run()
    {
        while (flag)
        {
            synchronized (obj)
            {
                //System.out.println(Thread.currentThread().getName() + " is Running");  //For checking purpose
                if (temp % 2 == 1 && tIndex == 1) //Condition#1: If true, Thread1 should collect a character from the file and notify the Thread2 for calculating the 'sum'
                {
                    //System.out.println("First Block");
                    if(temp==1) //We will open the file only once->The very first time
                    {
                        try{
                            fr=new FileReader("../OOS.txt");
                            System.out.println("FILE OPENED SUCCESSFULLY..!");
                        }catch (IOException e) {
                            e.getMessage();
                        }

                        BufferedReader br=new BufferedReader(fr);
                    }

                    try{
                        dataCollected= fr.read();
                        System.out.println("Character fetched: "+(char)dataCollected+"(By "+Thread.currentThread().getName()+" Thread)");
                    }catch (IOException e) {
                        e.getMessage();
                    }

                    if(dataCollected!=-1)
                    {
                        while((dataCollected<48 || dataCollected>57))
                        {
                            try{
                                dataCollected= fr.read();
                                System.out.println("Character fetched: "+(char)dataCollected+"(By "+Thread.currentThread().getName()+" Thread)");
                            }catch (IOException e) {
                                e.getMessage();
                            }
                        }
                    }

                    else
                    {
                        System.out.println("\nEOF is reached!");
                        flag=false;  //Will terminate the while loop
                        dataCollected=48;
                    }

                    temp++;
                    obj.notifyAll();
                }

                else if (temp % 2 == 0 && tIndex == 1)  //Condition#2: If true, Thread1 will go on wait and should be under wait until Thread2 calculates the current sum and notifies it again
                {
                    //System.out.println("Second Block");
                    try {
                        //System.out.println("T1(producer) is under wait");
                        obj.wait();
                        //System.out.println("T1(producer) is resumed");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //The below code executes only After the Thread1 got notified

                    //T1 will fetch the next character
                    try{
                        dataCollected= fr.read();
                        System.out.println("Character fetched: "+(char)dataCollected+"(By "+Thread.currentThread().getName()+" Thread)");
                    }catch (IOException e) {
                        e.getMessage();
                    }

                    if(dataCollected!=-1)
                    {
                        while((dataCollected<48 || dataCollected>57))
                        {
                            try{
                                dataCollected= fr.read();
                                System.out.println("Character fetched: "+(char)dataCollected+"(By "+Thread.currentThread().getName()+" Thread)");
                            }catch (IOException e) {
                                e.getMessage();
                            }
                        }
                    }

                    else
                    {
                        System.out.println("\nEOF is reached!");
                        flag=false;  //Will terminate the while loop
                        dataCollected=48;
                    }

                    temp++;
                    obj.notifyAll();
                }

                else if (temp % 2 == 0 && tIndex == 2)  //Condition#3: If true, Thread2 should calculate the 'sum' and notify the Thread1 to fetch the next number from the file
                {
                    //System.out.println("Third Block");

                    //Calculate sum
                    dataCollected-=48;
                    sum+=(char)dataCollected;
                    System.out.println("Current Sum:"+sum+"(By "+Thread.currentThread().getName()+" Thread)");

                    temp++;
                    obj.notifyAll();
                }

                else if (temp % 2 == 1 && tIndex == 2)  //Condition#4: If true, Thread2 will go on wait and should be under wait until Thread1 fetches the next number and notifies Thread2 again
                {
                    //System.out.println("Fourth Block");
                    try {
                        //System.out.println("T2(consumer) is under wait");
                        obj.wait();
                        //System.out.println("T2(consumer) is resumed");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //Calculate sum
                    dataCollected-=48;
                    sum+=(char)dataCollected;
                    System.out.println("Current sum:"+sum+"(By "+Thread.currentThread().getName()+" Thread)");

                    temp++;
                    obj.notifyAll();
                }
            }

            if(flag==false && tIndex==2)
            {
                System.out.println("\nTHE FINAL RESULTED SUM="+sum+" (This MSG is printed by "+Thread.currentThread().getName()+")");
                return;
            }
        }
    }
}


public class ThreadMain
{
    public static void main(String[] args) throws IOException
    {
        myThread obj1=new myThread(1);
        myThread obj2=new myThread(2);

        Thread T1=new Thread(obj1, "producer");
        Thread T2=new Thread(obj2, "consumer");


        //First we'll perform some writing to a file where we will store the numbers
        FileWriter fw=new FileWriter("../OOS.txt", false);
        BufferedWriter bw=new BufferedWriter(fw);

        Scanner sc=new Scanner(System.in);
        System.out.println("Please Enter the Integers(HAVING SINGLE DIGIT ONLY) separated by spaces: ");
        String str=sc.nextLine();

        fw.write(str);
        fw.close();  //The close statement is mandatory
        System.out.println("THE DATA IS SUCCESSFULLY STORED TO THE FILE 'OOS.TXT'!");

        T1.start();
        T2.start();
    }
}
