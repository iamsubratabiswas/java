/*
Problem Description:

Create two threads and call them EvenThread and OddThread. EvenThread will print number as 2 4 6 8 10... and
OddThread will print number as 1 3 5…. Now synchronize these two thread to get the output as 1 2 3 4 5 6 7 8.
*/

public class Main
{
    int count = 1;
    int MAX = 20;

    public void printOdd()
    {
        synchronized (this)
        {
            while (count < MAX)
            {
                if(count%2==0)  //If count is Even then printOdd will go on wait until it is notified by printEven
                {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        //TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                System.out.println("Odd Thread :" + count);
                count++;
                notify();
            }
        }
    }

    public void printEven()
    {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        synchronized (this)
        {
            while (count < MAX)
            {
                if(count%2==1)
                {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Even thread :" + count);
                count++;
                notify();
            }
        }
    }

    public static void main(String[] args)
    {
        Main mT = new Main();
        Thread t1 = new Thread(new Runnable() {  //Here we used the concept of Anonymous class where methods can be overridden instantly
            // during object creation, as we overridden the run() function

            @Override
            public void run() {
                mT.printEven();

            }
        });
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                mT.printOdd();

            }
        });

        t1.start();
        t2.start();

    }
}

/*
The join () method of thread class waits for a thread to die. It is used when you want one thread to wait for completion of another.
This process is like a relay race where the second runner waits until the first runner comes and hand over the flag to him.
*/