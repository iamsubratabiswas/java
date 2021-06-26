/*
Problem Description:
Write a Java class “Employee” containing information name, id, address, salary etc. Write necessary constructor and read/write methods.
Create a class “Dept” that has a name, location etc. The “Dept” contains a number of “Employee”. Write methods “add” and “remove” to add and remove an employee to/from this department.
Write a main() function and create “Information Technology” department. Add five employees and print yearly expenditure for this department.

 */

import java.util.Scanner;

class Employee
{
    private String name;
    private int id;
    private float salary;

    public void addEmp()
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Name of the Employee:");
        name=sc.nextLine();
        System.out.print("Emp_ID:");
        id=Integer.parseInt(sc.nextLine());
        System.out.print("Salary:");
        salary=Float.parseFloat(sc.nextLine());
    }

    public float getSalary()
    {
        return salary;
    }
}

public class Department {
    Employee E[];
    private String deptName;
    private String location;
    private int noOfEmp;
    private int empIndex=-1;

    Department()
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Name of the Department:");
        deptName=sc.nextLine();
        System.out.print("Location:");
        location=sc.nextLine();
        System.out.print("Enter the number of Employees present in the Department "+deptName+":");
        noOfEmp=Integer.parseInt(sc.nextLine());
        E=new Employee[noOfEmp];
        for(int i=0;i<noOfEmp;i++)
            E[i]=new Employee();
    }

    public void addEmp()
    {
        empIndex++;
        E[empIndex].addEmp();
    }

    public void remEmp()  //Our program removes only lastly added Employee->we'll optimize the code later
    {
        E[empIndex]=null;
        empIndex--;
    }

    public float calYearlyExp()
    {
        float exp=0f;
        for(int i=0;i<=empIndex;i++)
            exp+=E[i].getSalary();
        return exp;
    }

    public static void main(String[] args) {
        Department D1=new Department();

        for(int i=1;i<= D1.noOfEmp;i++)
        {
            System.out.println("Employee@"+i+":");
            D1.addEmp();
        }

        System.out.println("Yearly Expenditure of the Department '"+D1.deptName+"': INR"+D1.calYearlyExp());
                              //hence we are in the same class->we can access the private members of Department class
    }
}
