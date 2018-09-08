/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import java.util.Scanner;

/**
 *
 * @author dhdms
 */
public class 가위바위보 {
    
    public static void main(String[] args)
{
    Scanner scanner = new Scanner(System.in,"EUC-KR");
    
     String input1,input2;
    int input1_map=0,input2_map=0;
  
    System.out.print("철수 :");
    input1=scanner.next();
    
    System.out.print("영희 :");
    input2=scanner.next();
    
    input1_map=mapping(input1);
    input2_map=mapping(input2);
    
    if((input1_map==0)||(input2_map==0))
        System.out.print("잘못 입력했습니다.");
    
    if(input1_map==input2_map)
    {
        System.out.print("비겼습니다.");
    }
    else if((input1_map==1)&&(input2_map==2))
    {
        System.out.print("졌습니다.");
    }
     else if((input1_map==1)&&(input2_map==3))
    {
        System.out.print("이겼습니다.");
    }
     else if((input1_map==2)&&(input2_map==1))
    {
        System.out.print("이겼습니다.");
    }
     else if((input1_map==2)&&(input2_map==3))
    {
        System.out.print("졌습니다.");
    }
     else if((input1_map==3)&&(input2_map==1))
    {
        System.out.print("졌습니다.");
    }
     else if((input1_map==3)&&(input2_map==2))
    {
        System.out.print("이겼습니다.");
    }
    scanner.close();

}
    public static int mapping(String input)
    {
        int temp=0;
        if("가위".equals(input))
        {
            temp=1;
        }
        else if("바위".equals(input))
        {
              temp=2;
        }
        else if("보".equals(input))
        {
             temp=3;
        }
       return temp;
        
    }
}
