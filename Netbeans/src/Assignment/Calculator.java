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
public class Calculator {
    public static void main(String[] args)
    {
    Scanner scanner = new Scanner(System.in,"EUC-KR");
        
    System.out.print("식을 입력하세요>>");
    double op1 = scanner.nextDouble();
    String operator = scanner.next();
    double op2 = scanner.nextDouble();
    
    double result = 0;
    switch(operator)
    {
        case "+":
            result = op1 + op2;
            break;
        case "-":
            result = op1 - op2;
            break;
        case "*":
            result = op1 * op2;
            break;
        case "/":
            if(op2==0)
            {
                System.out.printf("0으로 나눌 수 없습니다.");
            break;
            }
            else
            {
            result = op1 / op2;
            break;
            }
        default:
            System.out.print("연산자 기호가 잘 못 입력되었습니다.");
    }
    System.out.print("연산 결과 :"+result);
    }
}
