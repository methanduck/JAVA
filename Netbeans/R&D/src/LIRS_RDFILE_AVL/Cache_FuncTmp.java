/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LIRS_RDFILE_AVL;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author dhdms
 */

public class Cache_FuncTmp extends Cache{

    private boolean FLAG_undersize = true;
    
    public Cache_FuncTmp(int Input_CacheSize) {
        super(Input_CacheSize);
    }
    
    public void Cache_Access(Block_Func Input_block)
    {
        Count_Access ++ ;
        if(FLAG_undersize) //cache size is not enough
        {
           if(/*Block_recency.contains(Input_block)*/ Block_recAssist.contains(Input_block)) //Always HIT
           {
               count++;
               Stk_MvTop(Input_block);
           }
           else //first acc
           {
               
               if(Block_LIR.size()<Size_LIR)
               {
                   Input_block.setBlock_status(Block_Value.LIR);
                   Block_LIR.add(Input_block);
                   Block_recency.add(Input_block);
                   Block_recAssist.insert(Input_block);//assist
               }
               else if(/* Block_LIR.size() == Size_LIR &&*/ Block_residentHIR.size() == Size_HIR-1)
               {
                   Input_block.setBlock_status(Block_Value.RESIDENT_HIR);
                   Block_residentHIR.add(Input_block);
                   Block_recency.add(Input_block);
                   Block_recAssist.insert(Input_block);//assist
                   FLAG_undersize = false;
               }
           }
        }
        else //after enough
        {
                switch(Input_block.getBlock_status()) //stk_reaccess
           {
               case Block_Value.LIR: //Always HIT
                   if(this.Block_recency.get(0).equals(Input_block))
                   {
                    this.count++;
                    IRR_calculator(Input_block);
                    Stk_MvTop(Input_block);
                    stk_pruning();
                   }
                   else
                   {
                       this.count++;
                    IRR_calculator(Input_block);
                    Stk_MvTop(Input_block);
                    Block_LIR.remove(Input_block);
                    Block_LIR.add(Input_block);
                   }
                   break;

               case Block_Value.RESIDENT_HIR: 
                   if(Stk_isReaccess(Input_block)) 
                   {
                       count++;
                       IRR_calculator(Input_block);
                       Stk_MvTop(Input_block);
                       Block_swap(Input_block);
                       stk_pruning();
                   }
                   else
                   {
                       count++;
                       IRR_calculator(Input_block);
                       Block_recency.add(Input_block);
                       Block_recAssist.insert(Input_block);//assist
                   }
                   break;

               case Block_Value.NON_RESIDENT_HIR:
                   if(Stk_isReaccess(Input_block))// Exist in stk
                   {
                       IRR_calculator(Input_block);
                       Stk_MvTop(Input_block);
                       Block_StatusCTRL(Input_block, 0); //NON to resi
                       Block_swap(Input_block);
                       stk_pruning();
                   }
                   else //not Exist in stk
                   {
                       Block_recency.add(Input_block);
                       Block_recAssist.insert(Input_block);//assist
                       Block_StatusCTRL(Input_block,0); //NON to Resi
                   }
                   
               break;
           }
        }
       
    }
    
    public void stk_pruning()
    {
      Iterator<LIRS_RDFILE_AVL.Block_Func> Iterator_recency = Block_recency.iterator(); //start at first
      LIRS_RDFILE_AVL.Block_Func Tmp_block;
      
        while (Iterator_recency.hasNext()) 
        {
            Tmp_block = Iterator_recency.next();
            if(Tmp_block.getBlock_status()>0) // if HIR , 0 = LIR
            {
                Block_recAssist.remove(Tmp_block);
                Iterator_recency.remove();
                
            }
            else
            {
                break;
            }
        }
    }
    
    /*
public void IRR_calculator(LIRS_RDFILE.Block_Func Input_block)
    {
            boolean Is_inf = true;
            AvlTree<Integer> History = new AvlTree<>();
            LIRS_RDFILE.Block_Func Tmp_block;
            
            //iterate from last index of the stack
            for (int i = Block_recency.size()-1; i >=0 ; i--) 
            {
                Tmp_block = Block_recency.get(i);
                if(Tmp_block.getBlock_num() != Input_block.getBlock_num())
                {
                    if(!History.contains(Tmp_block.getBlock_num()))
                    History.insert(Tmp_block.getBlock_num());
                }
                else if(Tmp_block.getBlock_num() == Input_block.getBlock_num())
                {
                    Is_inf = false;
                    break;
                }
            }
            
            //set block IRR
            if(Is_inf || !Block_recency.contains(Input_block))
            {
                Input_block.setBlock_irr(-1); //-1 : infinite
            }
            else
            {
                Input_block.setBlock_irr(History.countInsertions);
            }
    }
*/ //IRR 카운팅 시 avl트리보다 리스트형태가 훨씬 더 효율적임
  
    public void IRR_calculator(LIRS_RDFILE_AVL.Block_Func Input_block)
    {
            boolean Is_inf = true;
            ArrayList<Integer> History = new ArrayList<>();
            LIRS_RDFILE_AVL.Block_Func Tmp_block;
            
            //iterate from last index of the stack
            for (int i = Block_recency.size()-1; i >=0 ; i--) 
            {
                Tmp_block = Block_recency.get(i);
                if(Tmp_block.getBlock_num() != Input_block.getBlock_num())
                {
                    if(!History.contains(Tmp_block.getBlock_num()))
                    History.add(Tmp_block.getBlock_num());
                }
                else if(Tmp_block.getBlock_num() == Input_block.getBlock_num())
                {
                    Is_inf = false;
                    break;
                }
            }
            
            //set block IRR
            if(Is_inf || !Block_recency.contains(Input_block))
            {
                Input_block.setBlock_irr(-1); //-1 : infinite
            }
            else
            {
                Input_block.setBlock_irr(History.size());
            }
    }
    
    public void Stk_MvTop(LIRS_RDFILE_AVL.Block_Func Input_block) 
    {
        int Prev_index = Block_recency.indexOf(Input_block);
        if(Prev_index == -1)
        {
            System.out.println(Prev_index);
        }
        //Calc_IRR(Input_block);
        Block_recency.add(Input_block);
        Block_recency.remove(Prev_index);
    }
    
    public boolean Stk_isReaccess(Block_Func Input_block)
    {
        if(/*Block_recency.contains(Input_block)*/ Block_recAssist.contains(Input_block))
        {
            return true;
        }
        else
        return false;
    }
    
    public void Block_swap(Block_Func Input_block)
    {
        Block_Func Bottom_LIR = Block_recency.get(0);
        
        Bottom_LIR.setBlock_status(Block_Value.RESIDENT_HIR);
        Block_LIR.remove(Bottom_LIR);
        
       
            Block_residentHIR.remove(Input_block);
        Input_block.setBlock_status(Block_Value.LIR);

        Block_LIR.add(Input_block);
        Block_residentHIR.add(Bottom_LIR);
    }
    
    public void Block_StatusCTRL(Block_Func Input_block, int dst)
    {
        switch(dst)
        {
            //NON_Resi to Resi
            case 0:
                if(Block_residentHIR.size() > 0)
                {
                    Block_residentHIR.remove().setBlock_status(Block_Value.NON_RESIDENT_HIR);
                }
                Block_residentHIR.add(Input_block);
                Input_block.setBlock_status(Block_Value.RESIDENT_HIR);
                break;
                
                //Resi to LIR
            case 1:
                Input_block.setBlock_status(Block_Value.LIR);
//                Block_residentHIR.remove(Input_block);
                Block_swap(Input_block);
                break;
                
            default:
                break;
        }
    }
}


