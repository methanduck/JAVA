/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package For_study;

class circle
{
    private int radius;
    public circle(int radius)
    {
        this.radius=radius;
    }
    public int getradius()
    {
        return this.radius;
    }
}

class namedcircle extends circle
{
    String name;
    public namedcircle(int radius,String input) {
       super(radius); 
       this.name=input;
    }
    public void show()
    {
        System.out.print(this.name+"반지름 ="+super.getradius());
    }
    
}
public class study2 
{
    public static void main(String[] args) {
        namedcircle w = new namedcircle(5,"waffle");
        w.show();
    }
}
