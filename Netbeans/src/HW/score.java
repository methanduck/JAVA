/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW;

import java.util.Scanner;
import java.util.InputMismatchException;
/**
 *
 * @author dhdms
 */
class studentInfo
{
    private String name;
    private int hakbun;
    private float score_kor,score_math,score_eng;
             
    studentInfo()
    {
        this.name="NULL";
        this.hakbun=0;
        this.score_eng=0;
        this.score_kor=0;
        this.score_math=0;
    }
    public float getTotal()
    {
        return this.score_eng+this.score_kor+this.score_math;
    }
   public float getAvg()
    {
        return this.getTotal()/3;
    }
   public void setData(String name,int hakbun,float score_kor,float score_math,float score_eng)
   {
       this.name=name;
       this.hakbun=hakbun;
       this.score_eng=score_eng;
       this.score_kor=score_kor;
       this.score_math=score_math;
   }
   public void printData()
   {
       System.out.print(this.name+"\t"+this.hakbun+"\t"+this.score_kor+"\t"+this.score_eng+"\t"+this.score_math+"\t");
       System.out.printf("%.2f \t %.2f\n",this.getTotal(),this.getAvg());
   }
   public int getHakbun()
   {
       return this.hakbun;
   }
}

public class score 
{
    
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in,"EUC-KR");
        int num=0,input=0;
        System.out.print("총 인원 수를 입력하시오 :");

        while(true)
        {
            try 
            {
            num=scanner.nextInt();
            break;
            } 
            catch (InputMismatchException e) 
            {
                System.out.print("인원 수를 다시 입력하세요 :");
                   scanner=new Scanner(System.in);
            }
        }
        
          studentInfo [] stud_arr= new studentInfo[num]; 
          for (int i = 0; i < num; i++) 
          {
            stud_arr[i]=new studentInfo();
          }
          
         for (int i = 0; i < num; i++) 
         {
             String name;
             int hakbun;
             float kor,eng,math;
                try 
                {
                 System.out.print("이름을 입력하시오 :");
               name=scanner.next();
               System.out.print("학번을 입력하시오 :");
               hakbun=scanner.nextInt();
               System.out.print("국어 점수를 입력하시오 :");
               kor=scanner.nextFloat();
               System.out.print("영어 점수를 입력하시오 :");
               eng=scanner.nextFloat();
               System.out.print("수학 점수를 입력하시오 :");
               math=scanner.nextFloat();
                }
                catch (InputMismatchException e) 
                {
                    System.out.println("올바른 값을 입력해주세요.");
                    scanner=new Scanner(System.in);
                    i--;
                   continue;
                }
             stud_arr[i].setData(name, hakbun, kor, math, eng);
         }
         
         while(true)
         {
                System.out.println("1. 성적순 출력");
                System.out.println("2. 학번순 출력");
                System.out.println("3. 종료");
                System.out.print("메뉴의 번호를 선택하세요<1~3> :");
                while(true)
                {
                    try 
                    {
                       input=scanner.nextInt();
                      
                       break;
                    } 
                    catch (InputMismatchException e) 
                    {
                        System.out.print("숫자를 입력하세요>>");
                       scanner=new Scanner(System.in);
                    }
                     
                }
                
             switch(input)
             {
                 case 1:
                     System.out.println("=====================================================================");
                     System.out.println("순번" + "\t" + "이름"+ "\t" + "학번"+ "\t" + "국어" + "\t" + "영어" + "\t" + "수학" + "\t" + "총점" + "\t" + "평균");
                     sort_score(stud_arr,num);
                     System.out.println("=====================================================================");
                     continue;
                 case 2:
                      System.out.println("=====================================================================");
                     System.out.println("순번" + "\t" + "이름"+ "\t" + "학번"+ "\t" + "국어" + "\t" + "영어" + "\t" + "수학" + "\t" + "총점" + "\t" + "평균");
                     sort_hak(stud_arr,num);
                     System.out.println("=====================================================================");
                     continue;
                 case 3:
                     break;
             }
             break;
         }
           
    }
    public static void sort_score(studentInfo [] arr,int num)
    {
        studentInfo temp= new studentInfo();
        
        for (int i = 0; i < num-1; i++) 
        {
            for (int j = i+1; j < num; j++)
            {
                  if(arr[i].getTotal()<arr[j].getTotal())
                  {
                      temp=arr[i];
                      arr[i]=arr[j];
                      arr[j]=temp;
                  }
            }
          
        }
        for (int i = 0; i < num; i++) 
        {
            System.out.print((i+1)+"\t");
                    arr[i].printData();
        }
    }
     public static void sort_hak(studentInfo [] arr,int num)
    {
        studentInfo temp;
        temp =new studentInfo();
        
        for (int i = 0; i < num-1; i++) 
        {
            for (int j = 1; j < num; j++)
            {
                  if(arr[i].getHakbun()>arr[j].getHakbun())
                  {
                      temp=arr[i];
                      arr[i]=arr[j];
                      arr[j]=temp;
                  }
            }
          
        }
        for (int i = 0; i < num; i++) 
        {
            System.out.print((i+1)+"\t");
                    arr[i].printData();
        }
    }
    
}
