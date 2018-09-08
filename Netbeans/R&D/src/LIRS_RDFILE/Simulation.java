/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LIRS_RDFILE;


/**
 *
 * @author dhdms
 */
public class Simulation 
{
    //var
     private AvlTree<Block_Func> blk_set = null;
     private Cache_FuncTmp cache = null;
     
    //construct
    public Simulation(int Cache_size) 
    {
       blk_set  = new AvlTree<>();
    cache = new Cache_FuncTmp(Cache_size);
    }
    
    //getter setter
    public Cache_FuncTmp getCache() {
        return cache;
    }

    public AvlTree<Block_Func> getBlk_set() {
        return blk_set;
    }
    
    public void Data_Input(int Block_num)
    {
        Block_Func blk_tmp;
         if(blk_set.contains_blk(Block_num))
        {
          blk_tmp = blk_set.getData(Block_num);
          cache.Cache_Access(blk_tmp);
        }
         else
         {
            blk_tmp = new Block_Func(Block_num);
             blk_set.insert(blk_tmp);
             cache.Cache_Access(blk_tmp);
         }
    }
    
    
    
    
}
