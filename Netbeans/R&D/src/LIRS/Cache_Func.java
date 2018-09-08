/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LIRS;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author dhdms
 */
public class Cache_Func extends Cache
{

    public Cache_Func(int Input_CacheSize) {
        super(Input_CacheSize);
    }
    
    public void LIR_underSize(Block_Func Input_block)
    {
        Block_residentHIR.remove(Input_block);
        Block_LIR.add(Input_block);
        Input_block.setBlock_status(Block_Value.LIR);
        Stk_moveToTop(Input_block);
        Stk_Pruning();
    }
    
    public void Access_block(LIRS.Block_Func Input_block)
    {
        if(Undersize)
        {
            if(Block_LIR.size() == Size_LIR)
            {
                Undersize = false;
                Cache_reArrange();
                Access_block(Input_block);
            }
            else
            {
            System.out.println("LIR 부족으로 LIR전환상태");
            Input_block.setBlock_status(Block_Value.LIR);
            Block_LIR.add(Input_block);
            Block_recency.add(Input_block);   
            }
        }
        else
        {
                
            if(Block_recency.contains(Input_block)) //스택에 존재할 경우
            {
                System.out.println(Input_block.getBlock_num()+"번 블록 재참조");
                Stk_reAccess(Input_block);
                Input_block.Print_status();
                System.out.println('\n');
            }
            else //should be HIR block
            {
                //push HIR block
                System.out.println(Input_block.getBlock_num()+"번 블록 HIR로 분류");
                Block_recency.add(Input_block);
                Input_block.setBlock_status(Block_Value.RESIDENT_HIR);
                //enqueue HIR block
                if(Block_residentHIR.contains(Input_block))
                {
                    Block_residentHIR.remove(Input_block);
                    Block_residentHIR.add(Input_block);
                }
                else
                {
                    if(Block_residentHIR.size()>0)
                    {
                        Block_Func Block_tmp = Block_residentHIR.remove();
                        Block_tmp.setBlock_status(Block_Value.NON_RESIDENT_HIR);
                    }
                    Block_residentHIR.add(Input_block);
                }
            }
        }
    }
    
    public void Cache_reArrange()
    {
        int count =0 ;
        System.out.println("Cache 재정렬 시작");
        for (Iterator<Block_Func> Search_infinite = Block_recency.iterator(); Search_infinite.hasNext();) 
        {
            Block_Func Block_next = Search_infinite.next();
            if((Block_next.getBlock_irr() == Block_Value.INFINITE) && (Block_residentHIR.size()<Size_HIR))
            {
                Block_next.setBlock_status(Block_Value.RESIDENT_HIR);
                Block_LIR.remove(Block_next);
                Block_residentHIR.add(Block_next);
                System.out.println(Block_next.getBlock_num() + "번 블록 Resident HIR로 재분류");
            }
        }
        while(Block_residentHIR.size()< Size_HIR)
        {
            Block_recency.get(count).setBlock_status(Block_Value.RESIDENT_HIR);
            Block_LIR.remove(Block_recency.get(count));
            Block_residentHIR.add(Block_recency.get(count));
            System.out.println(Block_recency.get(count).getBlock_num()+"번 블록 Resident HIR로 재분류");
        }
    }
    
    public void Status_swap(LIRS.Block_Func Block_HIR) 
    {  
                if(Block_recency.get(0).equals(Block_HIR))
                 {Stk_moveToTop(Block_HIR);}
            //Get LIR block in bottom of the stack 
            LIRS.Block_Func block_lir = Block_recency.get(0);

            //HIR -> LIR, Remove block at queue
            Block_HIR.setBlock_status(Block_Value.LIR); //change its status to LIR
            Block_residentHIR.remove(Block_HIR); //remove from HIR q

            //replace LIR cache eachother
            this.Block_LIR.remove(block_lir);
            this.Block_LIR.add(Block_HIR);
            Stk_moveToTop(Block_HIR);

            //LIR -> HIR, Add block came from stk
            block_lir.setBlock_status(Block_Value.RESIDENT_HIR);
            Block_residentHIR.add(block_lir); //enqueue block which was LIR block
            Stk_moveToTop(block_lir);

            Stk_Pruning();//conduct pruning
          
                    Stk_Pruning();
    }

    public void Stk_Pruning()
    {
      Iterator<LIRS.Block_Func> Iterator_recency = Block_recency.iterator(); //start at first
      LIRS.Block_Func Tmp_block;
      
        while (Iterator_recency.hasNext()) {
            Tmp_block = Iterator_recency.next();
            if(Tmp_block.getBlock_status()>0) // if HIR , 0 = LIR
            {
                System.out.println("Remove this block By stk_pruning \n"+Tmp_block.PrintS_status());
                 Iterator_recency.remove();
            }
            else
            {
                break;
            }
        }
    }
        
        //stack must include target Block
    public void Stk_moveToTop(LIRS.Block_Func Input_block) 
    {
        int Prev_index = Block_recency.indexOf(Input_block);
        //Calc_IRR(Input_block);
        Block_recency.add(Input_block);
        Block_recency.remove(Prev_index);
    }
        
        //based on the stack
    public void Stk_reAccess(LIRS.Block_Func Input_block) //스택에 존재할 경우 
    {
            switch(Input_block.getBlock_status())
        {
            case LIRS.Block_Value.LIR:
                Calc_IRR(Input_block);
                Stk_moveToTop(Input_block);
                Stk_Pruning();
                break;
                
            case LIRS.Block_Value.RESIDENT_HIR:
                Calc_IRR(Input_block);
                if(Block_LIR.size() < Size_LIR)
                {
                    LIR_underSize(Input_block);
                }
                else
                {
                    Status_swap(Input_block);
                    Stk_Pruning();
                }
                break;
                
            case LIRS.Block_Value.NON_RESIDENT_HIR:
                
                if(Block_residentHIR.size()>0)
                {
                Block_Func Block_tmp = Block_residentHIR.remove();
                Block_tmp.setBlock_status(Block_Value.NON_RESIDENT_HIR);
                }
                Block_residentHIR.add(Input_block);
                Input_block.setBlock_status(Block_Value.RESIDENT_HIR);
                Calc_IRR(Input_block);
                
                if(Block_LIR.size() < Size_LIR)
                {
                    LIR_underSize(Input_block);
                }
                else
                {
                Status_swap(Input_block);    
                Stk_Pruning();
                }
                break;
        }
    }
        
    public void Calc_IRR(LIRS.Block_Func Input_block)
    {
            boolean Is_inf = true;
            ArrayList<Integer> History = new ArrayList<>();
            LIRS.Block_Func Tmp_block;
            
            //iterate from last index of the stack
            for (int i = Block_recency.size()-1; i >=0 ; i--) 
            {
                Tmp_block = Block_recency.get(i);
                if(Tmp_block.getBlock_num() != Input_block.getBlock_num())
                {
                    int SearchRslt_hitory = History.indexOf(Tmp_block.getBlock_num());
                    if(SearchRslt_hitory ==-1) 
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
    
    public void Print_recency()
    {
        System.out.printf("Recency : ");
        for (Iterator<Block_Func> iterator = Block_recency.iterator(); iterator.hasNext();) {
            Block_Func next = iterator.next();
            System.out.print(next.getBlock_num()+"  ");
        }
        System.out.println('\n');
    }
}
