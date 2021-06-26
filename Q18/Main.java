/*
Problem Description:
Write two interfaces “Fruit” and “Vegetable” containing methods ‘hasAPeel’ and ‘hasARoot’. Now write a class “Tomato” implementing Fruit and Vegetable.
Instantiate an object of Tomato. Print the details of this object.
 */

interface Fruits
{
    //>All the methods are public and abstract. And all the fields are public, static, and final.
    public Boolean hasAPeel();
    public Boolean hasARoot();
}

interface Vegetable
{
    //>All the methods are public and abstract. And all the fields are public, static, and final.
    public Boolean hasAPeel();
    public Boolean hasARoot();
}

class Tomato implements Fruits,Vegetable
{
    /*
    PS: If a type implements two interfaces, and each interface define a method that has identical signature, then in effect there
    is only one method, and they are not distinguishable.
    */

    public Boolean hasAPeel()
    {
        return false;
    }

    public Boolean hasARoot()
    {
        return true;
    }

    public void Display()
    {
        if(hasAPeel())
           System.out.println("It has Peel");
        else
            System.out.println("It doesn't has a Peel");

        if(hasARoot())
            System.out.println("It has Root");
        else
            System.out.println("It doesn't has a Root");
    }
}

public class Main {
    public static void main(String[] args)
    {
        Tomato T=new Tomato();
        T.Display();
    }
}
