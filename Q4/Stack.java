/*
Problem Description:

Implement a class for stack of integers using an array. Please note that the operations defined for a stack data structure are as follows: “push”, “pop”, “print”. 
There should be a constructor to create an array of integers; the size of the array is provided by the user.
Write a main() function to (i) create a stack to hold maximum of 30 integers; (ii) push the numbers 10, 20, 30, 15, 9 to the stack; (iii) print the stack; (iii) pop thrice and (iv) print the stack again.
*/



public class Stack
{
    private int arr[],size,top=0;

    public  Stack( int x) {
        size = x;
        arr = new int[size];
    }

    public void push(int x)
    {
        arr[top++] = x;
    }

    public int pop()
    {
        int x = arr[top--];
        return x;
    }

    public void print() {


        System.out.println("Stack :");

        for (int i = top-1; i >= 0; i--) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {
        Stack st = new Stack(30);

        st.push(10);
        st.push(20);
        st.push(30);
        st.push(15);
        st.push(9);

        st.print();

        st.pop();
        st.pop();
        st.pop();

        st.print();

    }
}