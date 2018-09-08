/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LIRS_RDFILE_OBJECT;

import LIRS_RDFILE_OBJECT.*;



/**
 *    
 * @author dhdms
 */
public class Block_Value {
    
public static final int INFINITE = -1;
public static final int LIR = 0;
public static final int RESIDENT_HIR = 1;
public static final int NON_RESIDENT_HIR =2;

//variables    
   protected int Block_num;
   protected int Block_irr =INFINITE; //-1 : infinite, other : IRR size
   protected int Block_status = NON_RESIDENT_HIR; // false = HIR, true = LIR;
}
