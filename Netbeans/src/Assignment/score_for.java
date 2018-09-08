/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author dhdms
 */
public class score_for 
{
    public static void main(String[] args)
    {
    Scanner scanner = new Scanner(System.in,"EUC-KR");
    int num= 0,input=0;
    System.out.print("총 인원 수를 입력하세요>>");
        
        while (true) 
        {            
            try 
            {
                num=scanner.nextInt();
                break;
            }
            catch (Exception e) 
            {
             System.out.print("인원 수를 다시 입력하세요 : ");
              scanner=new Scanner(System.in);
            }
        }
            String[][] name = new String[num][2];
            int [][] hakbun = new int[num][2];
            int [][] kor = new int[num][2];
            int [][]  math = new int[num][2];
            int [][]  eng = new int[num][2];
            int [][]  total = new int[num][2];
            int [][]  avg = new int[num][2];
        
            for (int i = 0; i < num; i++) 
            {
                int count=0;
                            try 
                            {
                                System.out.print("이름을 입력하시오 :");
                                name[i][0]=scanner.next();
                        System.out.print("학번을 입력하시오 :");
                        hakbun[i][0]=scanner.nextInt();
                        System.out.print("국어 점수를 입력하시오 :");
                        kor[i][0]=scanner.nextInt();
                        System.out.print("영어 점수를 입력하시오 :");
                        eng[i][0]=scanner.nextInt();
                        System.out.print("수학 점수를 입력하시오 :");
                        math[i][0]=scanner.nextInt();
                            } 
                            catch (InputMismatchException e) 
                            {
                                System.out.println("올바른 값을 입력해주세요.");
                             scanner=new Scanner(System.in);
                             i--;
                            continue;
                            }
                         name[i][1]=Integer.toString(i);
                         hakbun[i][1]=i;
                        kor[i][1]=i;
                        eng[i][1]=i;
                        math[i][1]=i;
                        total[i][1]=i;
                        avg[i][1]=i;
                            total[i][0] = eng[i][0]+kor[i][0]+math[i][0];
                         avg[i][0]=total[i][0]/3;
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
                     sort_score(name,hakbun,kor,math,eng,total,avg,num);
                     //print(name,hakbun,kor,math,eng,total,avg,1,num);
                     System.out.println("=====================================================================");
                     continue;
                 case 2:
                      System.out.println("=====================================================================");
                     System.out.println("순번" + "\t" + "이름"+ "\t" + "학번"+ "\t" + "국어" + "\t" + "영어" + "\t" + "수학" + "\t" + "총점" + "\t" + "평균");
                     sort_hak(name,hakbun,kor,math,eng,total,avg,num);
                      
                     System.out.println("=====================================================================");
                     continue;
                 case 3:
                     break;
             }
             break;
         }
    }
     public static void sort_score(String[][] name, int [][] hakbun,int [][] kor, int[][] math, int [][] eng, int[][] total, int[][] avg, int arrnum)
    {
        int temp;
        for (int i = 0; i < arrnum-1; i++) 
        {
            for (int j = 1; j < arrnum; j++)
            {
                  if((total[i][0]>total[j][0])&&(total[i][1]<total[j][1]))
                  {
                     
                      temp=total[i][1];
                      total[i][1]=total[j][1];
                      total[j][1]=temp;
                  }
            }
        }
         for (int j = 0; j < arrnum; j++)
         {
             for (int i = 0; i < arrnum; i++) {
                 if(total[i][1]==j)
                     System.out.println((j+1)+"\t"+name[i][0]+"\t"+ hakbun[i][0] +"\t"+ kor[i][0] +"\t"+ eng[i][0] +"\t"+ math[i][0] +"\t"+ total[i][0] +"\t"+ avg[i][0]);
             }
         }
        
    }
    public static void sort_hak(String[][] name, int [][] hakbun,int [][] kor, int[][] math, int [][] eng, int[][] total, int[][] avg,int arrnum)
    {
        int temp;
        for (int i = 0; i < arrnum-1; i++) 
        {
            for (int j = 1; j < arrnum; j++)
            {
                  if((hakbun[i][0]>hakbun[j][0])&&(hakbun[i][1]<hakbun[j][1]))
                  {
                     
                      temp=hakbun[i][1];
                      hakbun[i][1]=hakbun[j][1];
                      hakbun[j][1]=temp;
                  }
            }
        }
     
         for (int j = 0; j < arrnum; j++)
         {
             for (int i = 0; i < arrnum; i++) {
                 if(hakbun[i][1]==j)
                     System.out.println((j+1)+"\t"+name[i][0]+"\t"+ hakbun[i][0] +"\t"+ kor[i][0] +"\t"+ eng[i][0] +"\t"+ math[i][0] +"\t"+ total[i][0] +"\t"+ avg[i][0]);
             }
         }
    }
}
