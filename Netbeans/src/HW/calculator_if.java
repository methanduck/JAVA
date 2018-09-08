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
public class calculator_if {
    public static void main(String[] args)
    {
         Scanner scanner = new Scanner(System.in,"EUC-KR");
        
    System.out.print("식을 입력하세요>>");
    double op1 = scanner.nextDouble();
    String operator = scanner.next();
    double op2 = scanner.nextDouble();
    
    if("+".equals(operator))
    {
        System.out.print("연산결과 :"+(op1+op2));
    }
    else if("*".equals(operator))
    {
        System.out.print("연산결과 :"+(op1*op2));
    }
     else if("-".equals(operator))
    {
        System.out.print("연산결과 :"+(op1-op2));
    }
    else
    {
        if(op2==0)
            System.out.print("나누는 수가 0일 수 없습니다.");
        else
            System.out.println("연산결과 :"+(op1/op2));
    }
    }
}
