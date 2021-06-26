/*
Problem Description:

Implement a class for a “Person”. Person has data members ‘age’, ’weight’, ‘height’, ‘dateOfBirth’, ‘address’ with proper reader/write methods etc.
Now create two subclasses “Employee” and “Student”. Employee will have additional data member ‘salary’, ‘dateOfJoining’, ‘experience’ etc.
Student has data members ‘roll’, ‘listOfSubjects’, their marks and methods ‘calculateGrade’. Again create two sub-classes “Technician” and “Professor”
from Employee. Professor has data members ‘courses’, ‘listOfAdvises’ and their add/remove methods. Write a main() function to demonstrate the creation
of objects of different classes and their method calls.
*/

import java.util.Date;
import java.util.Scanner;

abstract class Person
{
    String name;
    float age, weight, height;
    Date dateOfBirth;
    class Address  //Nested Class
    {
        String city, PIN, country;
    }
}


class Student extends Person
{
    static long sampleRollNo=1911001050;
    static int noOfSubjects=3;
    long rollNo;
    static String []subjects;
    int []marks;
    static int studentIndex=-1;
    char grade;

    static
    {
        //System.out.print("No. of subjects the students have:");
        //Scanner sc=new Scanner(System.in);
        //noOfSubjects=Integer.parseInt(sc.nextLine());

        //We will uncomment the above code when we will take input from the user
        subjects=new String[noOfSubjects];
        subjects[0]="PHYS";
        subjects[1]="CHEM";
        subjects[2]="MATH";
    }
    Student(int s)
    {
        //Empty Code
    }

    Student()
    {
        //We can take all of these as input also
        System.out.print("Name of the STUDENT#"+(++studentIndex+1)+":");
        Scanner sc=new Scanner(System.in);
        name=sc.nextLine();
        rollNo=sampleRollNo++;

        System.out.print("Height(in cm):");
        height=Float.parseFloat(sc.nextLine());
        System.out.print("Weight(in Kgs):");
        weight=Float.parseFloat(sc.nextLine());

        dateOfBirth=new Date(2002,01,29);  //same DOB for all
        age=19;

        Student obj=new Student(0);  //Because of this line there will be an infinite Student object creation if we write 'Student()' instead of 'Student(int )'
        Person.Address add=obj.new Address();
        System.out.println("ADDRESS:");
        System.out.print("City:");
        add.city=sc.nextLine();
        System.out.print("PIN:");
        add.PIN=sc.nextLine();
        System.out.print("Country:");
        add.country=sc.nextLine();

        marks=new int[noOfSubjects];  //I put same Marks of all the students
        marks[0]=94;
        marks[1]=81;
        marks[2]=100;

        grade=calculateGrade();
    }

    char calculateGrade()
    {
        float sum=0f,avg;
        for(int i=0;i<noOfSubjects;i++)
            sum+=marks[i];
        avg=sum/noOfSubjects;

        if(avg>=90)
            return 'O';
        else if(avg>=80&&avg<=89)
            return 'A';
        else if(avg>=70&&avg<=79)
            return 'B';
        else if(avg>=60&&avg<=69)
            return 'C';
        else
            return 'X';

    }
}


class Employee extends Person
{
  float salary=40000;
  Date dateOfJoining;
  String experience;  //e.g. - 4 years as a managing director
  public static int employeeIndex=-1;

  Employee(int s)
  {

  }

  Employee()
  {
      System.out.print("Name of the EMPLOYEE#"+(++employeeIndex+1)+":");  //We will not call super() as we want to Print this line before taking any input
      Scanner sc=new Scanner(System.in);
      dateOfJoining=new Date(2020,01,01);
      System.out.print("\nExperience(in words):");
      experience=sc.nextLine();

      System.out.print("Height(in cm):");
      height=Float.parseFloat(sc.nextLine());
      System.out.print("Weight(in Kgs):");
      weight=Float.parseFloat(sc.nextLine());

      dateOfBirth=new Date(1997,03,20);
      age=24;

      Employee obj=new Employee(0);
      Person.Address add=obj.new Address();
      System.out.println("ADDRESS:");
      System.out.print("City:");
      add.city=sc.nextLine();
      System.out.print("PIN:");
      add.PIN=sc.nextLine();
      System.out.print("Country:");
      add.country=sc.nextLine();
  }
}


class Professor extends Employee
{
    int noOfCourses=2;
    String courses[];
    int noOfAdvices=2;
    String listOfAdvices[];
    Professor()
    {
        super();
        courses=new String[noOfCourses];
        courses[0]="Public Speaking";
        courses[1]="ph.D in CSE";

        listOfAdvices=new String[noOfAdvices];
        listOfAdvices[0]="Control Your Own Destiny, or Someone Else Will";
        listOfAdvices[1]="There is No Shortcut of Success Without Working Hard";
    }
}


class Technician extends Employee
{
    String technicianType;
    Technician()
    {
        super();
        System.out.print("Technician Type:");
        Scanner sc=new Scanner(System.in);
        technicianType=sc.nextLine();
    }
}


public class Main {
    public static void main(String[] args) {

        //PS: The above code can be changed as per the requirements
        Student S1=new Student();
        Employee E1=new Employee();
        Professor P1=new Professor();  //Will be counted as Employee
        Technician T1= new Technician();  //This one also

        //Here we can display the details of all the persons after creating the corresponding Display functions
    }
}
