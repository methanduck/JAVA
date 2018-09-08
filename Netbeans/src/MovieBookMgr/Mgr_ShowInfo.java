/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieBookMgr;

import java.time.LocalTime;

/**
 *
 * @author dhdms
 */
public class Mgr_ShowInfo 
{
    protected boolean result;
    protected int Sales, Seat;
    protected Theater TheaterRef = null;
    protected LocalTime SMorning = LocalTime.of(9, 00);
    protected LocalTime EMorning = LocalTime.of(10, 00);
    protected LocalTime SNight = LocalTime.of(23, 00);
    protected LocalTime ENight = LocalTime.of(1, 00);
    public Mgr_ShowInfo(Theater TheaterRef) //parameter (정의되어있는 영화관리 객체) : 
    {
        this.TheaterRef = TheaterRef;
    }
    public  String getMovieInfo(int movieNum)//parameter (영화구분키) : string(영화제목, 영화개봉일, 상영시간)
    {
        StringBuffer buff = new StringBuffer();
        String result;
        for(MovieInfo MTemp : this.TheaterRef.MovieData)
        {
            if(MTemp.MovieNum == movieNum)
            {
                buff.append(MTemp.MovieName).append("\t").append(MTemp.ReleaseDay).append("\t").append(Integer.toString(MTemp.RunTime));
                break;
            }
        }
        result = new String(buff);
        return result;
    }
    public String getTheaterInfo(int MovieNum, int Menu) //parameter (영화기본키, 키값영화기준 0:상영관 할당정보  1:가장 가까운 상영시간  2: 가장 늦은 상영시간) : String (상영횟수, 상영관 빈 좌석, 상영관 사용 좌석)
    {
         int EmpSeat=0,UseSeat=0;
        StringBuffer SStemp = new StringBuffer();
        String result = null;
        
        switch(Menu)
        {
            case 0:
            for(MovieInfo MTemp : TheaterRef.MovieData)
                {
                    if(MTemp.MovieNum == MovieNum)
                    {
                        SStemp.append(Integer.toString(MTemp.Data.size())).append("\t");
                        for(ShowingRoom TTemp : MTemp.Data )
                        {
                            UseSeat+=TTemp.getUseseat();
                            EmpSeat+=TTemp.getEmpseat();
                            //Stemp.append(TTemp.getTime(0, 1)).append("\t");
                        }
                    }
                }
            
            SStemp.append(Integer.toString(EmpSeat)).append("\t").append(Integer.toString(UseSeat)).append("예약률:").append(Integer.toString(UseSeat / (EmpSeat+UseSeat)));
            result = new String(SStemp);
            break;
            
            case 1: //첫번째 상영시간
                TheaterRef.MovieData.stream().filter((MovieInfo MTemp) -> {
                 return MTemp.MovieNum == MovieNum;
                }).forEachOrdered((MTemp) -> {
                    if(((LocalTime)MTemp.Data.get(0).getTime(1, 0)).getMinute() == 0)
                        SStemp.append(MTemp.Data.get(0).getTime(0, 0)).append("0").append("\t");
                    else
                    SStemp.append(MTemp.Data.get(0).getTime(0, 0));
         });
                result = new String(SStemp);
                        break;
                        
            case 2://마지막 상영시간
                TheaterRef.MovieData.stream().filter((MovieInfo MTemp) -> {
                    return MTemp.MovieNum == MovieNum;
                }).forEachOrdered((MTemp) -> {
                    if(((LocalTime)MTemp.Data.get(MTemp.Data.size()-1).getTime(1, 0)).getMinute()==0)
                        SStemp.append(MTemp.Data.get(MTemp.Data.size()-1).getTime(0, 0)).append("0").append("\t");
                    SStemp.append(MTemp.Data.get(MTemp.Data.size()-1).getTime(0, 0)).append("\t");
         });
                result = new String(SStemp);
                break;
                  
            case 3://전체 시간
                this.TheaterRef.MovieData.stream().filter((MovieInfo MTemp) -> {
                    return MTemp.MovieNum == MovieNum;
                }).forEachOrdered((MovieInfo MTemp) -> {
                    MTemp.Data.stream().forEach((ShowingRoom STemp) -> {
                        if(((LocalTime)STemp.getTime(1, 0)).getMinute() == 0)
                    SStemp.append(STemp.getTime(0, 0)).append("0").append("\t");
                        else
                            SStemp.append(STemp.getTime(0, 0)).append("\t");
                    });
                });
                result = new String(SStemp);
                break;

        }
   
    return result;
    }
    public String getSeat(int movieNum, String STime)
    {
        StringBuffer sbuff = new StringBuffer();
        for(MovieInfo Mtemp : this.TheaterRef.MovieData)
        {
            if(Mtemp.MovieNum == movieNum)
            {
                for(ShowingRoom STemp : Mtemp.Data)
                {
                    if(STemp.getTime(0, 0).equals(STime))
                        sbuff.append(Integer.toString(STemp.getUseseat())).append("\t").append(Integer.toString(STemp.getEmpseat()));
                }
                break;
            }
        } 
        String temp = new String(sbuff);
        return temp;
    }
    public String getSales(int MovieNum, int Menu)//parameter (영화키, 메뉴(-1 : 조조, 0 : 평상시 , 1 : 심야))
    {
        this.Seat = 0;
        switch (Menu)
        {
            case -1:
              this.TheaterRef.MovieData.stream().filter((MovieInfo MTemp) -> {
                  return MTemp.MovieNum == MovieNum;
              }).forEachOrdered((MovieInfo MTemp) -> {
              MTemp.Data.stream().filter((ShowingRoom STemp) -> {
               return STemp.getDiscount() == -1;
              }).forEachOrdered((ShowingRoom STemp) -> {
               this.Seat = STemp.getUseseat();
              });
              });
              this.Sales = 0;
            this.Sales = this.Seat * 6000;
            break;
                    
            case 0:
              this.TheaterRef.MovieData.stream().filter((MovieInfo MTemp) -> {
              return MTemp.MovieNum == MovieNum;
              }).forEachOrdered((MovieInfo MTemp) -> {
              MTemp.Data.stream().filter((ShowingRoom STemp) -> {
               return STemp.getDiscount() == 0;
              }).forEachOrdered((ShowingRoom STemp) -> {
               this.Seat = STemp.getUseseat();
              });
              });
              this.Sales = 0;
              this.Sales = this.Seat * 10000;
              break;
              
            case 1:
                 this.TheaterRef.MovieData.stream().filter((MovieInfo MTemp) -> {
              return MTemp.MovieNum == MovieNum;
              }).forEachOrdered((MovieInfo MTemp) -> {
              MTemp.Data.stream().filter((ShowingRoom STemp) -> {
               return STemp.getDiscount() == 1;
              }).forEachOrdered((ShowingRoom STemp) -> {
               this.Seat = STemp.getUseseat();
              });
              });
              this.Sales = 0;
              this.Sales = this.Seat * 70000;
              break;
    }
        String res = Integer.toString(this.Sales)+"원";
   return res;
    }
    public String getSTime(int MovieNum, String inputSTime)
    {
        StringBuffer Sbuff = new StringBuffer();
        String Sresult;
        this.TheaterRef.MovieData.stream().filter((MovieInfo MTemp) -> {
            return MTemp.MovieNum == MovieNum;
        }).forEachOrdered((MovieInfo MTemp) -> {
           MTemp.Data.stream().forEach((ShowingRoom STemp) -> {
               Sbuff.append(STemp.getTime(0, 0));
           });
        });
        Sresult = new String(Sbuff);
        return Sresult;
    }
    public boolean isMEmpty()
    {
      return this.TheaterRef.MovieData.isEmpty();
    }
   
    public LocalTime getEMorning()
    {
        return this.EMorning;
    }
    public LocalTime getSMorning()
    {
        return this.SMorning;
    }
    public LocalTime getSNight()
    {
        return this.SNight;
    }
    public LocalTime getENight()
    {
        return this.ENight;
    }
}
