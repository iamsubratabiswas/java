/*
Problem Description:

Consider the following parallel binary search algorithm for series a1, a2…an sorted in increasing order such that n mod 10 = 0. Element to be searched is e.

a) Create n/10 threads t1, t2,..,tn/10.
b) Distribute the numbers among threads such that ti will have numbers ai, ai+1, ….a2i-1. 
c) Distribute the element e to all threads.
d) Each thread searches the element e in its sub-array using binary search algorithm.


*/


import java.lang.*;

public class Q24 {
    public static void main(String[] args) {
        int arr[] = {5,9,12,16,20,23,28,35,39,40,43,47,53,56,62,65,66,72,75,76,94,98,100};
        int tc = 10;
        int sd = (int)Math.ceil(arr.length/(float)tc);

        for (int i = 0; i < sd; i++) {
            int f = tc * i;
            int l = (tc * (i+1)) - 1;
            l = (l > arr.length-1)?arr.length-1:l;
            Thread t = new Thread(new BinarySearch(arr, 75,f,l));
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

class BinarySearch implements Runnable {
    private int arr[], k, index, f, l;

    public BinarySearch(int arr[], int key, int first, int last) {
        this.arr = arr;
        this.k = key;
        this.index = -1;
        this.f = first;
        this.l = last;
    }

    public void run() {
        int m = (f + l)/2;
        while (f <= l) {
            if (arr[m] < k) {
                f = m + 1;
            } else if (arr[m] == k) {
                System.out.println(Thread.currentThread().getName() + " Found At "+m);
                break;
            } else {
                l = m - 1;
            }
            m = (f + l)/2;
        }
        if (f > l) {
            System.out.println(Thread.currentThread().getName() + " Not Found!");
        }
    }
}