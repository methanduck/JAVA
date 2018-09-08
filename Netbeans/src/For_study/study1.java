package For_study;

import java.util.Scanner;

abstract class  calculator 
{
    protected int a,b;
    abstract protected int calc();
    protected void input()
    {
        Scanner input = new Scanner(System.in,"EUC-KR");
        System.out.print("정수 2개 :");
        a=input.nextInt();
        b=input.nextInt();
    }
    public void run()
    {
        input();
        int res = calc();
        System.out.print("계산된 값은"+res);
    }
}

class Adder extends calculator
{
    int res;
    protected int calc()
    {
        res=this.a+this.b;
        return res;
    }
}
class substracter extends calculator
{
    int res;
    protected int calc()
    {
        res = this.a-this.b;
        return res;
    }
}


public class study1 
{
    public static void main (String[] args)
    {
        Adder adder = new Adder();
        substracter sub = new substracter();
        
        adder.run();
        sub.run();
    }
    
}
