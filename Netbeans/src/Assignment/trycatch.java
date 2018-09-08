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
public class trycatch {
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
            try
            {
                result = op1 / op2;
            }
            catch(ArithmeticException e)
            {
                System.out.println("나누는 수로 0을 입력 할 수 없습니다.");
            }
            break;
            
        default:
            System.out.print("연산자 기호가 잘 못 입력되었습니다.");
    }
    System.out.print("연산 결과 :"+result);
    }
}
