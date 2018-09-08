/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ctrl_txt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author methanduck
 */
public class cutter_str {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);
      // String file_name = sc.next();
        File fl = new File("./"+"res.txt");
         String tmp;
         int count =0;
        BufferedWriter bw = new BufferedWriter(new FileWriter("result2.txt"));
        
        sc = new Scanner(fl);
       String file_name = null;
        try {
            while (sc.hasNext()) {            
            sc.next();
            sc.next();
            sc.next();
            file_name = sc.next();
            bw.write(file_name);
            bw.newLine();
            count++;
        }
        } catch (Exception e) {
        }
        System.out.println(count);
    }
}
