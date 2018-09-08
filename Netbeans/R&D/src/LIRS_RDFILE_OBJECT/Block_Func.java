/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LIRS_RDFILE_OBJECT;

import LIRS_RDFILE_OBJECT.*;

/**
 *
 * @author methanduck
 */
public class Block_Func extends LIRS_RDFILE_OBJECT.Block_Value implements Comparable<Block_Func>{

    //constructor   
    public Block_Func(int Block_num) 
    {
            this.Block_num = Block_num;
    }

    //getter setter
    public int getBlock_num() {
        return Block_num;
    }

    public int getBlock_irr() {
        return Block_irr;
    }

    public int getBlock_status() {
        return Block_status;
    }

    public void setBlock_num(int Block_num) {
        this.Block_num = Block_num;
    }

    public void setBlock_irr(int Block_irr) {
        this.Block_irr = Block_irr;
    }

    public void setBlock_status(int Block_status) {
        this.Block_status = Block_status;
    }

    public void Print_status()
    {
        //System.out.println("+++++++++++++++++++++++++++++++++");
        System.out.println("Block Num : "+Block_num);
        System.out.println("Block IRR :"+(Block_irr == -1 ? "INFINITE":Integer.toString(Block_irr)));
        System.out.println("Block Status :"+(Block_status == 0 ? "LIR" : (Block_status == 1 ? "Resident HIR" : "Non-Resident HIR")));
        System.out.println("+++++++++++++++++++++++++++++++++");
    }
    
    public String PrintS_status()
    {
        String ret = "+++++++++++++++++++++++++++++++++\n"+
                "Block Num : "+Block_num+'\n'+
                "Block IRR :"+(Block_irr == -1 ? "INFINITE" : Integer.toString(Block_irr))+'\n'+
                "Block Status :"+ (Block_status == 0? "LIR" : Block_status == 1 ? "Resident HIR" : "Non-Resident HIR")+'\n'+
                "+++++++++++++++++++++++++++++++++\n";
        return ret;
    };

    @Override
    public int compareTo(Block_Func o) 
    {
        if(getBlock_num() > o.getBlock_num())
        {
            return 1;
        }
        else if(getBlock_num() == o.getBlock_num())
        {
            return 0;
        }
        return -1;
    }

       
}
