/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LIRS;

import java.util.Scanner;
import java.util.Iterator;
/**
 *
 * @author methanduck
 */
public class Main {
    public static Scanner Read_data = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        //초기 캐쉬크기를 입력받고 이후 블록 번호를 입력받기 위한 변수 
        int Block_Num =0; 
        
        //중복의 블록 객체를 생성하지 않기 위한 변수
        boolean Is_exist = false;
        boolean Is_EXIT = false;
        
        //construct
        Block_Set Block_set = new Block_Set();
        System.out.printf("캐쉬 크기를 입력하세요(2이상) : ");
        while(Block_Num<2)
        {
            Block_Num = Read_data.nextInt();
        }
        Cache_Func cache = new Cache_Func(Block_Num);
        
        
        //Input
        while (true) {            
            
            if(Is_EXIT)
                break;
            
            System.out.printf("블록번호를 입력하세요 :");
            Block_Num = Read_data.nextInt();
            
            switch(Block_Num)
            {
                case -1:
                    Is_EXIT = true;
                    break;
                case -2:
                    Block_set.Print_Blocks();
                    cache.Print_recency();
                    break;
                    
                default:
                    for (Iterator<Block_Func> Search = Block_set.Blocks.iterator(); Search.hasNext();) 
                    {
                        Block_Func Block_next = Search.next();
                        if(Block_next.getBlock_num() == Block_Num)
                        {
                                cache.Access_block(Block_next);
                                Is_exist = true;
                                break;
                        }
                        else
                        {
                            Is_exist = false;
                        }
                    }
                    if(!Is_exist)
                    {
                        Block_Func Block_New = new Block_Func(Block_Num);
                        Block_set.Blocks.add(Block_New);
                        cache.Access_block(Block_New);
                    }
                    break;
            }
        }
        
    }
}
