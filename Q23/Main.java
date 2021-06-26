/*
Problem Description:

Consider the series 1+2+3+…+100. This can be considered as (1+3+5+…+99)+(2+4+6+…+100).
Create two threads to compute two series in parallel (do not use simplified equation). Finally print the final sum.

IMPORTANT:
THERE'S A PROBLEM AT LINE: 80
I HAVE A FEW DOUBTS RELATED TO THREAD
->HERE IN THIS PROGRAM I HAVEN'T USED SYNCHRONIZED() BECAUSE HERE THE TASKS ARE NOT DEPENDENT ON EACH OTHER,
  BUT IN THE PREVIOUS CASES ONE HAD TO WAIT FOR THE OTHER ONE AND THEY WERE USING THE SAME OBJECT RESOURCES

->I MADE TWO DIFFERENT CLASSES FOR THE TWO DIFFERENT SERIES SO THAT THE RUN() METHODS CAN RUN PARALLEL,
  OTHERWISE IF WE HAD ONLY ONE CLASS THEN IT WOULD GET SYNCHRONIZED

**HERE IN THIS PROGRAM TWO THREADS WERE RUNNING PARELLALLY, BUT I DON'T KNOW WHAT ARE THE ADVANTAGES OF USING THREAD SYCHRONIZATION,
  DOESN'T IT TAKE MORE TIME THAN USING SINGLE THREAD?? B'COZ HERE WE HAVE THE STUFFS RELATED TO WAIT(), NOTIFY() ETC...
  ->May be the programs where multithreading is mandatory and one thread should get access for any particular resource at a time,
   in that case we should go for thread synchronization

  Means, if you have two choices-
  1) Use single Thread
  2)Use MultiThreading, but here you may need to use synchronization() for some purpose

  You should go for the first one, but in case you have to use multiThreading then there's no choice....
 */

import java.util.Scanner;

public class Main
{
    static int  fSum=0, sum1=0, sum2=0, N;
    static boolean flag1=false, flag2=false;

    static
    {
        System.out.print("Input the value of N:");
        Scanner sc=new Scanner(System.in);
        N=Integer.parseInt(sc.nextLine());
    }

    public static void main(String[] args) throws InterruptedException
    {
        Main objM=new Main();

        //Creating T1 Thread using Anonymous Class
        new Thread(new Runnable() {
            public void run()
            {
                for (int i=1;i<Main.N;i+=2)
                {
                    sum1+=i;
                    System.out.println("SUM("+Thread.currentThread().getName()+"):"+sum1);
                }
                flag1=true;
            }
        },"T1").start();

        System.out.println("T1 started");


        //Creating T2 Thread using Anonymous Class
        new Thread(new Runnable() {
            public void run()
            {
                for (int i=2;i<=Main.N;i+=2)
                {
                    sum2+=i;
                    System.out.println("SUM("+Thread.currentThread().getName()+"):"+sum2);
                }
                flag2=true;
            }
        },"T2").start();

        System.out.println("T2 started");


        while(!flag1 || !flag2)
        {
            Thread.sleep(2000);
        }
        fSum=sum1+sum2;
        System.out.println("The Final Sum:"+fSum);
    }
}
