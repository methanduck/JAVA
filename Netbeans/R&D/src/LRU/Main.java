/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LRU;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import jdk.nashorn.internal.codegen.CompilerConstants;
/**
 *
 * @author methanduck
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        //variable
        float Access_count =0;
        Scanner sc = new Scanner(System.in);
        File txt = null;
        BufferedReader br;
        Cache ch ;
        
        //construct 
        System.out.print("Cache size :");
        ch = new Cache(sc.nextInt());
        System.out.print("File name :");
        String file_data = sc.next();
        txt   = new File("./"+file_data+".txt");
        br = new BufferedReader(new FileReader(txt));
        file_data = null;
         long start = System.currentTimeMillis();
        while((file_data = br.readLine())!=null)
        {
            ch.Cache_Access(Integer.parseInt(file_data));
            Access_count ++;
        }
        long end = System.currentTimeMillis();
        
        System.out.println((end-start)/1000.0+"초");
        System.out.println("Acc count : "+Access_count);
        System.out.println("MISS count :"+(Access_count-ch.getHit_count()));
        System.out.println("HIT ratio : "+(ch.getHit_count() / Access_count)*100);
    }
}
