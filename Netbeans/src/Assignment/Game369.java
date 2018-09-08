
package Assignment;
import java.util.Scanner;
/**
 *
 * @author dhdms
 */
public class Game369 {
  public static  int count=0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
         Scanner scanner = new Scanner(System.in);

        System.out.printf("1~999사이의 정수를 입력하세요 >>");  

        int input;
        input = scanner.nextInt();
        
        if((1<=input)&&(input<=999))
        {
         countf(input,100);
        
            switch(count)
            {
                case 0:
                    System.out.println("박수 없음");
                    break;
                case 1:
                    System.out.println("박수 짝");
                    break;
                case 2:
                    System.out.println("박수 짝짝");
                    break;
                case 3:
                    System.out.println("박수 짝짝짞");
                    break;
            }
        }
        else
            System.out.print("1에서 999사이의 값을 입력해 주세요");

    }
    public static void countf(int a,int b)
    {

        if(b==1)
        {
            if((a==3)||(a==6)||(a==9))
            count++;

        }
        else
        {
        if(((a/b)==3)||((a/b)==6)||((a/b)==9))
            count++;    
        
         countf(a%b,b/10);
        }

    }
  }
    

