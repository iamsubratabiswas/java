/*
Problem Description:

Write a Java program using threading technology and print the thread index and location where the element has been found.
*/

import java.util.Scanner;
class ThreadIndex extends Thread
{
	int arr[],e;
	boolean status=true;
	ThreadIndex(int num[],int e)
	{
		arr=new int[num.length];
		for(int i=0;i<num.length;i++)
			arr[i]=num[i];
		this.e=e;
	}
	void BinarySearch(int arr[],int e)
	{
		String s=Thread.currentThread().getName();
		int x=Integer.parseInt(s.substring(7));
		int low=x*10,high=x*10+9;
		while(low<=high)
		{
			int mid=(low+high)/2;
			if(arr[mid]==e)
			{
				System.out.println("Element "+e+" found at index "+mid);
				System.out.println("Thread Index is "+x);
				System.exit(0);
			}
			else if(arr[mid]>e)
				high=mid-1;
			else
				low=mid+1;
		}
		status=false;
	}
	public void run()
	{
		
		this.BinarySearch(arr,e);
	}
}
public class Threading {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		int num[]= {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,37,118,119,445};
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter element to be searched:");
		int e=sc.nextInt();
		ThreadIndex threads[]=new ThreadIndex[num.length/10];
		for(int i=0;i<num.length/10;i++)
		{
			threads[i]=new ThreadIndex(num,e);
			threads[i].start();		
			
		}
		threads[num.length/10-1].join();
		int count=0;
		for(int i=0;i<num.length/10;i++)
		{
			if(threads[i].status)
			{
				break;
			}
			else
				count++;
		}
		if(count==num.length/10)
			System.out.println("The element "+e+" is not present in the array");
	}

}
