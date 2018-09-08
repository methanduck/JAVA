/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

/**
 *
 * @author dhdms
 */
public class Main {
     public static void main(String[] args) throws InterruptedException {
            boolean is_first = false;
            boolean is_firstnon = false;
            Scanner sc = new Scanner(System.in);
            StringBuffer sbf = new StringBuffer();
            String [] tmp ;
            String [] tmp2;
            
            String input = sc.next();
            tmp = input.split("&&");
            HashSet<String> finalequal[]= new HashSet[tmp.length*2];
            HashSet<String> finalequalnon[] = new HashSet[tmp.length*2];
            Queue<Integer> eqRemain = new LinkedList<Integer>();
            Queue<Integer> noneqRemain = new LinkedList<>();
            for (int i = 0; i < tmp.length*2; i++) {
             finalequal[i] = new HashSet<>();
             finalequalnon[i] = new HashSet<>();
             eqRemain.add(i);
             noneqRemain.add(i);
         }
            for (int i = 0; i < tmp.length; i++) {
                if(tmp[i].toString().contains("!="))
                    {
                        tmp2 = tmp[i].split("!=");
                        is_firstnon=data_input(tmp2, finalequalnon, is_first, noneqRemain);
                    }
                else
                {
                   tmp2 = tmp[i].split("==");
                    is_first=data_input(tmp2, finalequal, is_first, eqRemain);
                }
        }
            make_sent(sbf, finalequal, finalequalnon);
    } 
    public static void make_sent(StringBuffer sbf,HashSet<String>[] equal,HashSet<String>[] nonequal)
    {
        boolean chk=false;
       String [] equalmin = new String[equal.length];
       String [] noneqmin = new String[nonequal.length];
       
       if(!equal[0].isEmpty())
       {
            for (int i = 0; i < equal.length; i++) {
             Iterator<String> eqiterator = equal[i].iterator();

             if(eqiterator.hasNext())
             {
                  equalmin[i] = eqiterator.next();
             while (eqiterator.hasNext()) {
                 String next = eqiterator.next();
                 if(next.length() < equalmin[i].length())
                 {
                     equalmin[i]=next;
                 }
             }

             }

         }
         for (int i = 0; i < equal.length; i++) {
             Iterator<String> eqiterator = equal[i].iterator();
             for (Iterator iterator = eqiterator; iterator.hasNext();) {
                 Object next = iterator.next();
                 if(equalmin[i] != next)
                 {
                     sbf.append(equalmin[i]+"=="+next);
                     if(iterator.hasNext()) 
                     {
                         sbf.append("&&");
                     }
                 }
                 else
                 {
                     chk = true;
                 }
                 
                 
                if((!iterator.hasNext())&&equal[i+1]!=null && !chk)
                {
                    sbf.append("&&");
                }
             }
             
         }
        
       }
        
          for (int i = 0; i < nonequal.length; i++) {
            Iterator<String> noneqiterator = nonequal[i].iterator();
            if(noneqiterator.hasNext())
            {
                noneqmin[i] = noneqiterator.next();
            while (noneqiterator.hasNext()) {
                String next = noneqiterator.next();
                if(next.length() < noneqmin[i].length())
                {
                    noneqmin[i] = next;
                }
            }
            }
            
        }
        for (int i = 0; i < nonequal.length; i++) {
            Iterator<String> noneqiterator = nonequal[i].iterator();
            for (Iterator iterator = noneqiterator; iterator.hasNext();) {
                Object next = iterator.next();
                if(noneqmin[i] != next)
                {
                    sbf.append(noneqmin[i]+"!="+next);
                    if((!noneqiterator.hasNext()) && nonequal[i+1] !=null)
                    {
                        sbf.append("&&");
                    }
                }
                
            }
        }
        System.out.println(sbf);
    }
    public static String find_min(HashSet<String> input)
    {
        if(input.isEmpty())
        {
            return "empty";
        }
        else
        {
            Iterator<String> iterator = input.iterator();
        String res = iterator.next();
        for (Iterator<String> iterator1 = input.iterator(); iterator1.hasNext();) {
            String next = iterator1.next();
            if(next.length() < res.length())
                res = next;
        }
        return res;
        }
    }
    public static boolean data_input(String[] input, HashSet<String> [] setinput,boolean chk,Queue<Integer> remaining) throws InterruptedException
    {
        boolean is_putted = false;
        if(chk)
        {
            for (int j = 0; j <setinput.length; j++) {
                        if(setinput[j].contains(input[0]) || setinput[j].contains(input[1]))
                        {
                            setinput[j].add(input[0]);setinput[j].add(input[1]);
                            is_putted = true;
                            break;
                        }
                    }
            
            if(!is_putted)
            {
                int pos = remaining.remove();
                setinput[pos].add(input[0]);
                setinput[pos].add(input[1]);
                        
            }
            return true;
        }
        else{
            setinput[0].add(input[0]);
            setinput[0].add(input[1]);
            
            remaining.remove();
            return true;
        }
    }
}
