/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieBookMgr;

import java.io.IOException;
import java.time.LocalTime;


/**
 *
 * @author dhdms
 */
public class Main 
{
  
    public static void main(String[] args) {
         Theater MovieBookSet = new Theater();
     Manager Mgr = new Manager(MovieBookSet);
     Mgr.AddMovieInfo("dd", "12", "120", 0);
     Mgr.AddMovieInfo("23", "24", "60", 1);
     Mgr.AddMovieInfo("bf", "12", "60", 2);
     Mgr.AddShowingRoom(0, "10");
     System.out.print(Mgr.AddShowingRoom(0, "11"));
     System.out.print(Mgr.AddShowingRoom(0, "13"));
     Mgr.AddShowingRoom(1, "15");
     Mgr.AddShowingRoom(2, "17");
     
     System.out.print(Mgr.getTheaterInfo(0, 3));
    }
    /*
       class TimeChecker extends Thread
       {
          @Override
          public void run()
          {
              while (true) 
              {                  
                  MovieBookSet.MovieData.forEach((MTemp) -> {
                      MTemp.Data.forEach((STemp) -> {
                          LocalTime Temp = (LocalTime)STemp.getTime(1, 0);
                          if(Temp.isAfter(Mgr.getSMorning())&&Temp.isBefore(Mgr.getEMorning()))
                              STemp.setDiscount(-1);
                          else if(Temp.isAfter(Mgr.getSNight())&&Temp.isBefore(Mgr.getENight()))
                              STemp.setDiscount(1);
                          else
                              STemp.setDiscount(0);
                      });
                  });
              }
          }
       }*/
    }

    
