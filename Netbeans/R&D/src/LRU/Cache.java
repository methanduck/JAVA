/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LRU;

import java.util.Stack;

/**
 *
 * @author methanduck
 */

public class Cache {
    //variable
    private Stack<Integer> Cache = new Stack<>();
    private int Cache_size = 0;
    private float Hit_count = 0;
    public Cache(int Cache_size) {
        this.Cache_size = Cache_size;
    }
    
    //getter

    public Stack<Integer> getCache() {
        return Cache;
    }

    public float getHit_count() {
        return Hit_count;
    }

   
    
    //method
    public void Cache_Access(int INput_block)
    {
        if(Cache.contains(INput_block))
        {
            Hit_count++;
            Cache_MvTop(INput_block);
        }
        else
        {
            if(Cache.size() < Cache_size)
            {
                Cache.push(INput_block);
            }
            else
            {
                Cache.remove(0);
            Cache.push(INput_block);
            }
            
        }
    }
    
    public void Cache_MvTop(int INput_block)
    {
        Cache.removeElement(INput_block);
        Cache.push(INput_block);
    }
}
