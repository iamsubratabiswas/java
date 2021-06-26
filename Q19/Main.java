/*
Problem Description:

Write a program to create two threads. Print “In main thread” in main thread and “In child thread” in child thread.
*/

class mainThread extends Thread
{
    @Override
    public void run() {
        for (int i=0;i<6;i++)
            System.out.println("In Main Thread: "+i);
    }
}

class childThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 6; i++)
            System.out.println("In Child Thread: " + i);
    }
}

public class Main {
    public static void main(String[] args) {
        mainThread mT=new mainThread();
        childThread cT=new childThread();

        mT.start();
        cT.start();
    }
}
