/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW;

import java.util.Scanner;
/**
 *
 * @author dhdms
 */
public class 가위바위보_switch {
   
    
    public static void main(String[] args)
    {
         Scanner scanner = new Scanner(System.in,"EUC-KR");
    
         String input1,input2;
         System.out.printf("철수 : ");
         input1= scanner.next();
         System.out.printf("영희 :");
         input2 = scanner.next();
         

         switch(input1)
         {
             case "가위":
             {
                 if("가위".equals(input2))
                 {
                     System.out.println("비겼습니다.");
                 }
                 else if("바위".equals(input2))
                 {
                     System.out.println("졌습니다.");
                 }
                 else
                     System.out.println("이겼습니다.");
                 
                 break;
             }
             
             case "바위":
             {
                 if("가위".equals(input2))
                 {
                     System.out.println("이겼습니다");
                 }
                 else if("바위".equals(input2))
                 {
                     System.out.println("비겼습니다.");
                 }
                 else
                     System.out.println("졌습니다.");
                             
                 break;
             }
             
             case "보":
             {
                 if("가위".equals(input2))
                 {
                     System.out.println("졌습니다.");
                 }
                 else if("바위".equals(input2))
                 {
                     System.out.println("이겼습니다.");
                 }
                 else
                     System.out.println("비겼습니다.");
                 
                 break;
                 
             }
             default:
                 System.out.println("잘못 입력했습니다.");
         }
         scanner.close();
    }
  
}
