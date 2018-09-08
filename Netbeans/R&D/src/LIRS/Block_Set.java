/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LIRS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author dhdms
 */
public class Block_Set {
    
    //construct
    ArrayList<Block_Func> Blocks = new ArrayList<>();
    
    //getter setter
    public ArrayList<Block_Func> getBlocks() {
        return Blocks;
    }

    public void setBlocks(ArrayList<Block_Func> Blocks) {
        this.Blocks = Blocks;
    }
    
    public void Print_Blocks()
    {
        Collections.sort(Blocks);
        for (Iterator<Block_Func> iterator = Blocks.iterator(); iterator.hasNext();) {
            Block_Func Block_next = iterator.next();
            Block_next.Print_status();
        }
    }
}
