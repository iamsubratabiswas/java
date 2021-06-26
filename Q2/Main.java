/*
Problem Description:
Write a program to implement a class “student” with the following members.
Name of the student.
Marks of the student obtained in three subjects.
Function to assign initial values.
Function to compute total average.
Function to display the student’s name and the total marks.
Write an appropriate main() function to demonstrate the functioning of the above.
 */

class Student
{
    private String name;
    private int m1, m2, m3;
    Student()
    { }
    Student(String name, int m1,int m2,int m3)
    {
        this.name=name; this.m1=m1; this.m2=m2; this.m3=m3;
    }
    public void setValue(String name, int m1,int m2,int m3)
    {
        this.name=name; this.m1=m1; this.m2=m2; this.m3=m3;
    }
    public int total()
    {
        return (m1+m2+m3);
    }
    public void showDetails()
    {
        System.out.println("Name:"+name);
        System.out.println("Total marks:"+this.total());  //only total() can also be used
    }

}

public class Main {
    public static void main(String[] args) {
        Student s1=new Student();
        s1.setValue("SUBRATA BISWAS",70,80,85);
        Student s2=new Student("SUJOY BISWAS", 80,90,85);
        s1.showDetails();
        s2.showDetails();
    }
}
