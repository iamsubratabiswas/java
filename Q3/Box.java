/*
Problem Description:
Write a class “Box” that with three member-variables “height”, “width” and “breadth”.
 rite suitable constructors to initialize them. Add functions like “getVolume” and “getArea” that will return volume and
 surface area respectively. Instantiate two arbitrary boxes and then print their volume and surface area.
 */

public class Box
{
    private float height, width, breadth;
    Box(){ }
    Box(float h,float w,float b)
    {
        height=h; width=w; breadth=b;
    }
    public void setValue(float h,float w,float b)
    {
        height=h; width=w; breadth=b;
    }
    public float volume()
    {
        return(height*width*breadth);
    }
    public float area()
    {
        return(2*(height*breadth+breadth*width+width*height));
    }

    public static void main(String[] args) {
        Box b1=new Box(10.2f, 20f,30.5f);
        Box b2=new Box();
        b2.setValue(4.9f,5.2f,6f);
        System.out.println("Volume and Surface area of b1: "+b1.volume()+" ft^3 and "+b1.area()+" ft^2 respectively");
        System.out.println("Volume and Surface area of b2: "+b2.volume()+" ft^3 and "+b2.area()+" ft^2 respectively");
    }
}

