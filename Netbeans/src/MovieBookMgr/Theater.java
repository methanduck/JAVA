/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieBookMgr;
import java.util.*;
/**
 *
 * @author dhdms
 */
public class Theater
{
    ArrayList<MovieInfo> MovieData = new ArrayList<>(); //영화종류 3가지
    public Theater(){}
    public Theater(int Num) //parameter Num(생성할 수)
    {
        MovieInfo Temp = new MovieInfo();
        for (int i = 0; i < Num; i++) 
        {
        MovieData.add(Temp);    
        }
    }
}