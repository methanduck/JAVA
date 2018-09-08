/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import java.util.Scanner;
import java.util.InputMismatchException;
/**
 *
 * @author dhdms
 */
public class 가위바위보_rand 
{
    
    public static void main(String[] args)
    {
         Scanner scanner = new Scanner(System.in,"EUC-KR");
    
         String input1;
         
         
         
while(true)
{
                try
               {
                   System.out.printf("가위 바위 보!>>: ");
                   input1=scanner.next();
                   if(!("가위".equals(input1)||("바위".equals(input1))||("보".equals(input1))))
                       throw new InputMismatchException();
               }
                catch (InputMismatchException e) 
                {
                    System.out.println("다시 입력해 주세요");
                    continue;
                }
              
         
         int input1_res=mapping(input1);
         int computer=(int)(Math.random()*3)+1;
         
         
         switch(input1_res)
         {
             case 1:
             {
                 if(computer==1)
                     System.out.println("비겼습니다.");
                 else if(computer==2)
                     System.out.println("졌습니다.");
                 else
                     System.out.println("이겼습니다.");
                 continue;
             }   
             case 2:
             {
                  if(computer==1)
                     System.out.println("이겼습니다.");
                 else if(computer==2)
                     System.out.println("비겼습니다.");
                 else
                     System.out.println("졌습니다.");
                  continue;
             }    
             case 3:
             {
                  if(computer==1)
                     System.out.println("졌습니다.");
                 else if(computer==2)
                     System.out.println("이겼습니다.");
                 else
                     System.out.println("비겼습니다.");
                  
                  continue;
             }
             default :
                 break;
         }
         break;
        }
    }
    public static int mapping(String input)
    {
        int n=0;
       switch(input)
       {
           case "가위":
            n= 1;
           break;
            
           case "바위":
            n= 2;
            break;
            
           case "보":
            n= 3;
            break;
           case "그만":
            n=4;
            break;
       }
       return n;
    }
}