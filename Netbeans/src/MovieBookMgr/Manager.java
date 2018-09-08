/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieBookMgr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;


/**
 *
 * @author dhdms
 */
public class Manager extends Mgr_ShowInfo
{

    public Manager(Theater TheaterRef) 
    {
        super(TheaterRef);
    }
    public boolean AddMovieInfo(String MovieName, String ReleseDay, String RunTime, int MovieNum)//parameter (영화제목, 개봉일, 상영길이, 영화기본키)  //영화추가메서드
    {
     this.result = false;
     if(MovieName == null || ReleseDay == null || RunTime == null )
     {}
     else
     {
        if(this.TheaterRef.MovieData.size() == 4)
          this.result = false;//추가 실패
        else
        {
            try {
                MovieInfo Temp = new MovieInfo(MovieName, ReleseDay, Integer.parseInt(RunTime), MovieNum);
                 this.TheaterRef.MovieData.add(Temp);
                   this.result = true; //추가성공
            } catch (ClassCastException e) {
                this.result = false;
            }
        
        }
     }
        return this.result;
    }
    public void SetMovieInfo(String FindName,String MovieName, String ReleseDay, int RunTime, int MovieNum)//parameter (바꿀영화제목, 설정할 개봉일, 설정할 상영길이, 설정할 영화기본키)  //영화변경메서드
    {
        this.TheaterRef.MovieData.stream().filter((MTemp) -> (MTemp.MovieName.equals(FindName))).forEachOrdered((MTemp) ->
        {
            if(MovieNum == -1)
            {
                MTemp.MovieName = MovieName;
                MTemp.ReleaseDay = ReleseDay;
                MTemp.RunTime = RunTime;
            }
            else
            {
                MTemp.MovieName = MovieName;
                MTemp.ReleaseDay = ReleseDay;
                MTemp.RunTime = RunTime;
                MTemp.MovieNum=MovieNum;
            }
        });
    }
    public boolean AddShowingRoom(int MovieNum,String STime) //parameter (영화기본키, 상영시작시간)
    {
        boolean CheckResult = false;
        
        if(STime.length() <= 2)
        {
            LocalTime inputSTime = LocalTime.of(Integer.parseInt(STime), 0);
            LocalTime inputETime = LocalTime.of(Integer.parseInt(STime), 0);
            
            for(MovieInfo MTemp : this.TheaterRef.MovieData)
            {
                if(MTemp.MovieNum == MovieNum)
                {
                    inputETime = inputETime.plusMinutes(MTemp.RunTime);
                     if(this.CkShowingRoom(MTemp, inputSTime, inputETime))
                    {
                        ShowingRoom NewFace = new ShowingRoom(inputSTime, inputETime);
                        MTemp.Data.add(NewFace);
                        CheckResult = true; // 연산 성공 시
                        Compare CompObject = new Compare();
                        Collections.sort(MTemp.Data,CompObject);
                    }
                }
                
              
            }
        }
        else
        {
        String Stemp[] = STime.split(":");
        LocalTime inputSTime = LocalTime.of(Integer.parseInt(Stemp[0]), Integer.parseInt(Stemp[1])); 
        LocalTime inputETime = LocalTime.of(Integer.parseInt(Stemp[0]), Integer.parseInt(Stemp[1]));

             for(MovieInfo MTemp : this.TheaterRef.MovieData)
            {
                if(MTemp.MovieNum == MovieNum)
                {
                   inputETime = inputETime.plusMinutes(MTemp.RunTime);
                    if(this.CkShowingRoom(MTemp, inputSTime, inputETime))
                    {
                        ShowingRoom NewFace = new ShowingRoom(inputSTime, inputETime);
                        MTemp.Data.add(NewFace);
                        CheckResult = true; // 연산 성공 시
                        Compare CompObject = new Compare();
                        Collections.sort(MTemp.Data,CompObject);
                    }
                }
            }
        }
             
      return CheckResult;
    }
     public void SetShowingRoom(int MovieNum, String FindTime,String STime) //parameter (영화기본키, 찾을 상영시작시간, 설정할 상영시작시간)
    {
        int index=0;
        String Stemp[] = STime.split(":");
        LocalTime inputSTime = LocalTime.of(Integer.parseInt(Stemp[0]), Integer.parseInt(Stemp[1])); 
        LocalTime inputETime = LocalTime.of(Integer.parseInt(Stemp[0]), Integer.parseInt(Stemp[1]));
        ShowingRoom Input;
        for(MovieInfo MTemp : this.TheaterRef.MovieData)
        {
            if(MTemp.MovieNum == MovieNum)
            {
                for(ShowingRoom STemp : MTemp.Data)
                {
                    if(STemp.getTime(0, 0).equals(FindTime))
                    {
                        break;
                    }
                    index++;
                }
                
               inputETime = inputETime.plusMinutes(MTemp.RunTime);
               if(this.CkShowingRoom(MTemp, inputSTime, inputETime))
               {
                   Input = new ShowingRoom(inputSTime, inputETime);
                   MTemp.Data.set(index,Input);
               }
            }
        }
    }
     
     public boolean CkShowingRoom(MovieInfo Path,LocalTime inputSTime, LocalTime inputETime) //parameter (연산할 영화컬렉션, 비교할 영화시작시간, 비교할 영화종료시간)//함수 내 연산메서드
    {
               result = false;
        int isChecked =0;
        if(Path.Data.isEmpty()) //상영시간 첫 삽입시 
           {
               result = true;
           }
           else if(Path.Data.size() == 1) //1개의 상영시간 존재 할 시
           {
               if(!(inputSTime.plusMinutes(30).isAfter((LocalTime)Path.Data.get(Path.Data.size()-1).getTime(1, 0))&&inputSTime.plusMinutes(30).isBefore((LocalTime)Path.Data.get(Path.Data.size()-1).getTime(1, 1))))
               {
                   result = true;
               }
           }
           else //2개 이상 상영시간 존재 할 시
           {
                for (int i = 1; i < Path.Data.size(); i++) 
                {
                    if(inputSTime.plusMinutes(30).isAfter((LocalTime)Path.Data.get(Path.Data.size()-i).getTime(1, 0))&&inputSTime.plusMinutes(30).isBefore((LocalTime)Path.Data.get(Path.Data.size()-i).getTime(1, 1)))
                    {
                        isChecked++;
                    }
                    result = isChecked == 0;
                }
           }
        return result;

    }
     public boolean CKShowingRoom(int MovieNum, String inputSTime)
     {
         String Temp[] = inputSTime.split("\t");
         LocalTime LTemp = LocalTime.of(Integer.parseInt(Temp[0]),Integer.parseInt(Temp[1]));
         MovieInfo Path = null;
         for(MovieInfo MTemp : this.TheaterRef.MovieData)
         {
             if(MTemp.MovieNum == MovieNum)
             { Path = MTemp;
             break;
             }
         }
           result = false;
        int isChecked =0;
        if(Path.Data.isEmpty()) //상영시간 첫 삽입시 
           {
               result = true;
           }
           else if(Path.Data.size() == 1) //1개의 상영시간 존재 할 시
           {
               if(!(LTemp.plusMinutes(30).isAfter((LocalTime)Path.Data.get(Path.Data.size()-1).getTime(1, 0))&&LTemp.plusMinutes(30).isBefore((LocalTime)Path.Data.get(Path.Data.size()-1).getTime(1, 1))))
               {
                   result = true;
               }
           }
           else //2개 이상 상영시간 존재 할 시
           {
                for (int i = 1; i < Path.Data.size(); i++) 
                {
                    if(LTemp.plusMinutes(30).isAfter((LocalTime)Path.Data.get(Path.Data.size()-i).getTime(1, 0))&&LTemp.plusMinutes(30).isBefore((LocalTime)Path.Data.get(Path.Data.size()-i).getTime(1, 1)))
                    {
                        isChecked++;
                    }
                    result = isChecked == 0;
                }
           }
        return result;
         
         /*
         this.result = false;
         LocalTime temp ;
          String[] SStemp = null;
         if(inputSTime.length()<=2)
          temp = LocalTime.of(Integer.parseInt(inputSTime), 00);
         else
         {
             SStemp = inputSTime.split(":");
             temp = LocalTime.of(Integer.parseInt(SStemp[0]),Integer.parseInt(SStemp[1]));
         }
         this.TheaterRef.MovieData.stream().filter((MovieInfo MTemp) -> {
           return MTemp.MovieNum == MovieNum;
         }).forEachOrdered((MovieInfo Mtemp) -> {
             Mtemp.Data.stream().forEach((ShowingRoom STemp) -> {
                if(temp.isAfter((LocalTime)STemp.getTime(1, 0))&&temp.isBefore((LocalTime)STemp.getTime(1, 1)))
                    result = true;
             });
         });
         return result;*/
     }
     public boolean AddSeat(int Menu, int MovieNum, String STime)//parameter (Menu( 0: EmpSeat 1: UseSeat), 영화키값, 상영시작시간) :boolean 연산성공여부 리턴(True: 성공 False : 실패)
     {
         this.result = false;
         switch(Menu)
        {
                case 0:
                     this.TheaterRef.MovieData.stream().filter((Mtemp) -> {
                return Mtemp.MovieNum == MovieNum;
            })
                    .forEachOrdered((MovieInfo temp) -> {
                       temp.Data.stream().filter((ShowingRoom STemp) -> {
                           return STime.equals(STemp.getTime(0, 0));
                       }).forEachOrdered((ShowingRoom STemp) -> {
                       this.result=STemp.addEmpseat();

                       });
                    });
                     break;

                case 1:
                    this.TheaterRef.MovieData.stream().filter((Mtemp) -> {
                return Mtemp.MovieNum == MovieNum;
            })
                    .forEachOrdered((MovieInfo temp) -> {
                       temp.Data.stream().filter((ShowingRoom STemp) -> {
                           return STime.equals(STemp.getTime(0, 0));
                       }).forEachOrdered((ShowingRoom STemp) -> {
                       this.result=STemp.addUseseat();
                       });
                    });
         }
         return this.result;
     }
     public boolean DelSeat(int Menu, int MovieNum, String STime)//parameter (Menu( 0: EmpSeat 1: UseSeat), 영화키값, 상영시작시간) :boolean 연산성공여부 리턴(True: 성공 False : 실패)
     {
         this.result = false;
         switch(Menu)
         {
             case 0:
                 this.TheaterRef.MovieData.stream().filter((MovieInfo MTemp) -> {
                 return MTemp.MovieNum == MovieNum;
                 }).forEachOrdered((MovieInfo MTemp) -> {
                 MTemp.Data.stream().filter((ShowingRoom STemp) -> {
                   return STime.equals(STemp.getTime(0, 0));
                 }).forEachOrdered((ShowingRoom STemp) -> {
                 this.result = STemp.delEmpseat();
                 });
                 });
                 break;
                 
             case 1:
                  this.TheaterRef.MovieData.stream().filter((MovieInfo MTemp) -> {
                 return MTemp.MovieNum == MovieNum;
                 }).forEachOrdered((MovieInfo MTemp) -> {
                 MTemp.Data.stream().filter((ShowingRoom STemp) -> {
                   return STime.equals(STemp.getTime(0, 0));
                 }).forEachOrdered((ShowingRoom STemp) -> {
                 this.result = STemp.delUseseat();
                 });
                 });
                 break;
         }
         return this.result;
     }
     
    public void saveData() throws IOException
    {
         try 
         {  
             File MovieData = new File("TheaterData.txt");
             File SRoomData = new File("ShowingRoom.txt");
             BufferedWriter Mbw = new BufferedWriter(new FileWriter(MovieData));
             BufferedWriter Sbw = new BufferedWriter(new FileWriter(SRoomData));
          
             if(!(MovieData.canRead()&&SRoomData.canRead()))
             {
                 MovieData.createNewFile();
                 SRoomData.createNewFile();
             }
                  this.TheaterRef.MovieData.stream().forEach((MovieInfo Mtemp) -> {
                      try 
                      {
                          Mbw.append(Mtemp.getName() +"\t" +Mtemp.ReleaseDay + "\t" +  Integer.toString(Mtemp.RunTime));
                          Mbw.newLine();
                          Mbw.flush();
                          Mtemp.Data.stream().forEach((ShowingRoom STemp) -> {
                              try {
                                  Sbw.append(Integer.toString(Mtemp.MovieNum)+ "\t"+(String)STemp.getTime(0,0)+ "\t" +(String)STemp.getTime(0, 1) + "\t" +Integer.toString(STemp.getUseseat())+"\t"+ Integer.toString(STemp.getEmpseat()));
                                  Sbw.flush();
                              } catch (IOException ex) {                           
                              }
                          });
                      }
                      catch (IOException ex) 
                      {
                      }
                     });
                  Mbw.close();
                  Sbw.close();
         }
         catch (Exception e) 
         {
         }
         
     }
    class Compare implements Comparator<MovieBookMgr.ShowingRoom>
    {
        @Override
        public int compare(ShowingRoom t, ShowingRoom t1) 
        {
            if(((LocalTime)t.getTime(1, 0)).isBefore((LocalTime)t1.getTime(1, 0)))
            return -1;
            else if(((LocalTime)t1.getTime(1, 0)).isBefore((LocalTime)t.getTime(1, 0)))
            return 1;
            else
             return 0;
        }
    }
}