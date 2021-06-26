/*
Problem Description:
Create a class “Room” which will hold the “height”, “width” and “breadth” of the room in three fields.
This class also has a method “volume()” to calculate the volume of this room. Create another class “RoomDemo” which will
use the earlier class, create instances of rooms, and display the volume of room.
 */

class Room
{
    private int height, width, breadth;
    public void setValue(int h,int w,int b)
    {
        height=h; width=w; breadth=b;
    }
    public int volume()
    {
        return(height*width*breadth);
    }
}

public class RoomDemo {
    public static void main(String[] args) {
        Room r1=new Room();
        r1.setValue(10,20,30);
        System.out.println("Volume of the room:"+r1.volume()+" m^3");
    }
}
