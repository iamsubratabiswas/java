/*
           Name:SUBRATA BISWAS   Roll:001911001019
Problem Description:

Implement a class for a “Student”. Information about a student includes name, roll no and an array of five subject names. The class should have suitable constructor and get/set methods.
Implement a class “TabulationSheet”. A tabulation sheet contains roll numbers and marks of each student for a particular subject. This class should have a method for adding the marks and roll no of a student.
Implement a class “MarkSheet”. A mark sheet contains marks of all subjects for a particular student. This class should have a method to add name of a student and marks in each subject.
Write a main() function to create three “Student” objects, Five “Tabulationsheet” objects for Five subjects and three “Marksheet” object for three students. Print the mark sheets.
 */

import java.util.Scanner;

class Student
{
    private String name;
    private int rollNo;
    final public static String Subjects[];
    final public static int noOfSubjects=5;
    private int Marks[];
    public static int studIndex;

    static
    {
        Scanner sc=new Scanner(System.in);
        Subjects=new String[noOfSubjects];
        System.out.println("Enter the Subject Names(Total 5):");
        for(int i=0;i<noOfSubjects;i++)
        {
            System.out.print("Subject "+(i+1)+":");
            Subjects[i]=sc.nextLine();
        }
        studIndex=-1;
    }

    {
        Marks=new int[noOfSubjects];
    }

    public void addStudent()
    {
        System.out.println("STUDENT#"+(studIndex+1)+":");
        System.out.print("Name:");
        Scanner sc=new Scanner(System.in);
        name=sc.nextLine();
        System.out.print("Roll No:");
        rollNo=Integer.parseInt(sc.nextLine());
        System.out.println("Enter the marks achieved by "+name+":");
        for(int i=0;i<noOfSubjects;i++)
        {
            System.out.print(Subjects[i]+":");
            Marks[i]=Integer.parseInt(sc.nextLine());
        }
    }

    public static String getSubject(int subIndex)
    {
        return Subjects[subIndex];
    }
    public int getRollNo()
    {
        return this.rollNo;
    }
    public int getMarks(int m)
    {
        return this.Marks[m];
    }
    public void showMarkSheet()
    {

            System.out.println(rollNo+" \t "+name+" \t\t "+Marks[0]+" \t "+Marks[1]+" \t "+Marks[2]+" \t "+Marks[3]+" \t "+Marks[4]);
    }
}

class TabulationSheet
{
    private String subName;
    private int rollNo[];
    private int marks[];  //In the Tabulation Sheet marks will also be the noOfStudents
    TabulationSheet()
    {

    }
    TabulationSheet(int subIndex, Student st[])
    {
        rollNo=new int[MarkSheet.noOfStudents];
        marks=new int[MarkSheet.noOfStudents];
        this.subName=Student.getSubject(subIndex);
        for(int i = 0; i< MarkSheet.noOfStudents; i++)
        {
            this.rollNo[i]=st[i].getRollNo();
            this.marks[i]=st[i].getMarks(subIndex);  //Will store the marks of that subject whose index is 'subIndex'
        }

    }
    public void showTabSheet()
    {
        System.out.println("Tabulation Sheet of SUBJECT: "+subName);
        System.out.println("RollNo\tMarks");
        for (int i = 0; i< MarkSheet.noOfStudents; i++)
        {
            System.out.println(this.rollNo[i]+"\t"+this.marks[i]);
        }
    }
}

class MarkSheet
{
    Student stud[];
    TabulationSheet TS[];
    public static int noOfStudents;
    MarkSheet()
    {
        System.out.print("Enter the number of Students present in the class \n(Make sure whether it matches with the no of Add methods present in main  method or not):");
        Scanner sc=new Scanner(System.in);
        noOfStudents=Integer.parseInt(sc.nextLine());
        stud=new Student[noOfStudents];  //Student() constructor will execute here
        for (int i=0;i<noOfStudents;i++)
            stud[i]=new Student();

    }

    //Creating TabulationSheets
    public void createTabSheets()
    {
        TS=new TabulationSheet[Student.noOfSubjects];
        for (int i=0;i<Student.noOfSubjects;i++)
            TS[i]=new TabulationSheet(i,stud);
    }
    public void addStudent()
    {
        stud[++Student.studIndex].addStudent();
    }
    public void showTabulationSheet()
    {
        int tabIndex;
        System.out.println("Input the subject Index of the corresponding Tabular Sheet:");
        Scanner sc=new Scanner(System.in);
        tabIndex=Integer.parseInt(sc.nextLine());
        if(tabIndex>(Student.noOfSubjects-1))
            System.out.println("Subject Index should be less than "+(Student.noOfSubjects-1)+"Please Try again later:(");
        else
          TS[tabIndex].showTabSheet();
    }

    public void showMarkSheet()
    {
        System.out.println("FINAL MARKSHEET:");
        System.out.println("ROLL_NO\tNAME\t\tPHYS\tCHEM\tMATH\tBIOS\tENG");
        for (int i = 0; i< MarkSheet.noOfStudents; i++)
            stud[i].showMarkSheet();
    }
}

public class Main
{
    public static void main(String[] args) {
        MarkSheet MS=new MarkSheet();

        //Adding 3 students
        MS.addStudent();
        MS.addStudent();
        MS.addStudent();

        //After adding all the students we should create the TabSheet, otherwise some values will be skipped in the TabSheet
        //If we add any other student then we need to create TabulerSheet again
        MS.createTabSheets();

        //Displaying two TabulerSheet
        MS.showTabulationSheet();
        MS.showTabulationSheet();

        //Displaying the Final MarkSheet
        MS.showMarkSheet();
    }
}
