/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LIRS_RDFILE;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author methanduck
 */
public class Main {
   
    public static Scanner Read_data;
    
    public static void main(String[] args) {
        //var
        int Block_Num =0;
        float count_access =0 ;
        long start =0 ;
        long end = 0;
        Simulation simulator = null;
        
        //construct
        System.out.printf("캐쉬 크기를 입력하세요(2이상) : ");
        Read_data = new Scanner(System.in);
        while(Block_Num<2)
        {
            Block_Num = Read_data.nextInt();
        }
        simulator = new Simulation(Block_Num);
        
        //reader
        try {
            Read_data = new Scanner(System.in);
            System.out.print("File name :");
            String file  = Read_data.next();
            BufferedReader br =  new BufferedReader(new FileReader("./"+file+".txt"));

            file = null;
            start = System.currentTimeMillis();
            while ((file = br.readLine())!= null) {  
                count_access++;
                simulator.Data_Input(Integer.parseInt(file));
            }
            end = System.currentTimeMillis();
           
        }
        catch (FileNotFoundException e) 
        {
            System.out.println(e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("LIR"+simulator.getCache().getBlock_LIR().size());
        System.out.println("HIR"+simulator.getCache().getBlock_residentHIR().size());
        
        System.out.println("프로그램 소요시간 : "+(end-start)/1000.0+"초");
        System.out.println("전체 ACC횟수 : "+count_access);
        System.out.println("Miss count : "+ (count_access -simulator.getCache().getCount()));
        System.out.println("Hit Ratio : "+(simulator.getCache().getCount() / count_access)*100+"%");
    }
}
