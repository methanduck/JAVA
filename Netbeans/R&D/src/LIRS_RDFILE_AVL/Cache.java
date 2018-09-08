/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LIRS_RDFILE_AVL;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author methanduck
 */
public class Cache {
    
    //variables
    protected float count =0;
    protected float Count_Access =0;
    protected int Size_HIR;
    protected int Size_LIR;
    protected boolean Undersize = true;
    protected ArrayList<LIRS_RDFILE_AVL.Block_Func> Block_recency = new ArrayList<>();
    protected AvlTree<LIRS_RDFILE_AVL.Block_Func> Block_recAssist = new AvlTree<>();
    protected Queue<LIRS_RDFILE_AVL.Block_Func> Block_residentHIR = new LinkedList<>();
    protected Queue<LIRS_RDFILE_AVL.Block_Func> Block_LIR = new LinkedList<>();
    
    
    //constructor
    Cache(int Input_CacheSize) 
    {
        if((int)(Input_CacheSize*0.01) < 1)
        {
            this.Size_HIR = 1;
            this.Size_LIR = Input_CacheSize - 1;
        }
        else
        {
        this.Size_HIR =  (int) (Input_CacheSize*0.01);
        this.Size_LIR = Input_CacheSize-this.Size_HIR;
        }
        System.out.println("HIR : "+Size_HIR+"LIR :"+Size_LIR);
    }

    //getter setter    
    public float getCount() {
        return count;
    }
    public Queue<Block_Func> getBlock_LIR() {
        return Block_LIR;
    }
    public int getLir_size() {
        return Size_LIR;
    }
    public int getHir_size() {
        return Size_HIR;
    }
    public ArrayList<Block_Func> getBlock_recency() {
        return Block_recency;
    }
    public Queue<Block_Func> getBlock_residentHIR() {
        return Block_residentHIR;
    }
}
