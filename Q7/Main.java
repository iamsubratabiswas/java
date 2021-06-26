/*
Problem Description:
Implement a class for “Date”. Write member functions for (i) getting the previous day, (iv) getting the next day, (iii) printing a day
There should be four constructors: (i) day, month and year are initialized to 01, 01, 1970; (ii) day is initialized to user
specified value but month and year are initialized to 01, 1970; (iii) day, month are initialized to user specified value but year
is initialized to 1970; (iv) day, month and year are initialized to user defined values.
Also, write a main() function to (i) create a date object; (ii) print the next and the previous day.

 */

class Date
{
    private int day,month,year;
    Date()
    {day=1; month=1; year=1970;}

    Date(int d)
    {day=d; month=01; year=1970;}

    Date(int d,int m)
    {day=d; month=m;year=1970;}

    Date(int d, int m, int y)
    {day=d;month=m;year=y;}

    private boolean isLeapYear(int y)
    {
        return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
    }

    public Date prevDay()
    {
        Date d=new Date();
        d.day=day; d.month=month; d.year=year;

        if ((d.day - 1) > 0)
            d.day--;
        else if ((d.day - 1) == 0)
        {
            if ((d.month - 1) == 0)
            {
                d.month = 12;
                d.year--;
            } else
                d.month--;

            if (d.month == 1 || d.month == 3 || d.month== 5 || d.month== 7 || d.month == 9 || d.month== 11)
                d.day = 31;
            else if (d.month == 4 || d.month == 6 || d.month== 8 ||d.month == 10 || d.month== 12)
                d.day = 30;
            else
                {
                if (isLeapYear(d.year))
                    d.day = 29;
                else
                    d.day = 28;
            }
        }
        return d;
    }
    public Date nextDay()
    {
        Date d=new Date();
        d.day=day; d.month=month; d.year=year;
        if(d.month == 1 || d.month == 3 || d.month== 5 || d.month== 7 || d.month == 9 || d.month== 11)
        {
            if((d.day+1)>31)
            {
                d.month++;
                d.day=1;
            }
            else
                d.day++;
        }
        else if(d.month == 4 || d.month == 6 || d.month== 8 ||d.month == 10 || d.month== 12)
        {
            if((d.day+1)>30)
            {
                if((d.month+1)>12)
                {
                    d.month = 1;
                    d.year++;
                }
                else
                   d. month++;
                d.day=1;
            }
            else
                d.day++;
        }
        else
        {
            if(isLeapYear(d.year))
            {
                if((d.day+1)>29)
                {
                    d.month++;
                    d.day=1;
                }
                else
                    d.day++;
            }
            else
            {
                if((d.day+1)>28)
                {
                    d.month++;
                    d.day=1;
                }
                else
                    d.day++;
            }
        }
        return d;
    }

    public void showDate()
    {
        System.out.println("DD/MM/YYYY:"+day+"/"+month+"/"+year);
    }
}

public class Main
{
    public static void main(String[] args) {
        Date d1=new Date();
        d1.showDate();  //Should print the default date-> 01/01/1970

        Date d=d1.nextDay();
        d.showDate();    // 2/1/1970

        d=d1.prevDay();
        d.showDate();   //30/12/1969

        Date d2=new Date(30,12,2000);
        d=d2.nextDay();  //should be 1/1/2001
        d.showDate();

        Date d3=new Date(1,3,2000);
        d=d3.prevDay();
        d.showDate();  // should be 29/02/2000->Leap year

        Date d4=new Date(1,3,2001);
        d=d4.prevDay();
        d.showDate();  // should be 28/02/2001 ->not a leap year

    }
}
