/*
Problem Description:
Write a class to represent complex numbers with necessary constructors. Write member functions to add, multiply two complex numbers.
There should be three constructors: (i) to initialize real and imaginary parts to 0; (ii) to initialize imaginary part to 0 but to
initialize the real part to user defined value; (iii) to initialize the real part and the imaginary part to user defined values.
Also, write a main() function to (i) create two complex numbers 3+2i and 4-2i; (ii) to print the sum and product of those numbers.
 */

public class Complex {
    private int real, img;
    Complex()
    { real=img=0; }

    Complex(int r)
    {real=r; img=0;}

    Complex(int r, int i)
    {real=r; img=i;}

    public Complex add(Complex c)
    {
       Complex temp=new Complex();
       temp.real=real+c.real;
       temp.img=img+c.img;
       return temp;
    }

    public Complex mult(Complex c)
    {
        Complex temp=new Complex();
        temp.real=real*c.real - img*c.img;
        temp.img=real*c.img + img*c.real;
        return temp;
    }

    public void showData()
    {
        System.out.println(real+"+"+img+"i");
    }

    public static void main(String[] args) {
        Complex c1=new Complex(3,2);
        Complex c2=new Complex(4,-2);

        Complex c3=c1.add(c2);
        System.out.print("Addition of c1 and c2:");
        c3.showData();

        c3=c1.mult(c2);
        System.out.print("Multiplication of c1 and c2:");
        c3.showData();
    }
}
