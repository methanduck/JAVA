/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Address;
import java.io.*;
/**
 *
 * @author dhdms
 */
public class Main 
{
   
    
       public static void main(String[] args) throws UnsupportedEncodingException //인코딩 오류 발생시 위한 throws 
       {
           
            boolean checksave = false; // 파일로의 출력이 한번이라도 이뤄졌는지 확인하기 위한 불린
           BufferedReader dis = new BufferedReader(new InputStreamReader(System.in,"EUC-KR")); //버퍼리더를 활용하여 바이트입력을 받습니다. 
           AddressBook ab = null;  
            int iMenu =0;
          
            
            try
            {
                System.out.println("주소록을 초기화 중입니다.");
                ab = new AddressBookImpl(); //addressbookimpl클래스 객체생성
                System.out.print("주소록 이름을 입력하세요 :");
                checksave=ab.init(dis.readLine());//버퍼리더를 활용하여 주소록 이름을 인자로 init에 넘겨준다
                
                while (iMenu != 6) //6 종료 메뉴를 제외하고 메뉴입력을 반복합니다.
                {                    
                  System.out.println("=================================="); 
                  System.out.println("  0. 주소록 파일 불러오기          "); 
                  System.out.println("  1. 주소 추가하기                 "); 
                  System.out.println("  2. 주소 삭제하기                 ");
                  System.out.println("  3. 주소 편집하기                 "); 
                  System.out.println("  4. 주소록 파일 저장하기          "); 
                  System.out.println("  5. 주소록 다른 파일에 저장하기    "); 
                  System.out.println("  6. 종료하기                      "); 
                  System.out.println("=================================="); 
                  
                  System.out.print("입력하세요 >>");
                  iMenu = Integer.parseInt(dis.readLine()); // 스트링 값을 int값으로 변환합니다.
                  
                  
                  switch(iMenu)
                  {
                      case 0: // 주소록 파일 불러오기
                          System.out.print("파일 이름을 입력하세요 :");
                          checksave=ab.init(dis.readLine()); // init함수에서 설명
                          break;
                          
                      case 1:
                          String strName;
                          String strAddress;
                          String strPhone;
                          
                          System.out.print("이   름 :");
                          strName =dis.readLine();
                          System.out.print("주   소 :");
                          strAddress = dis.readLine();
                          System.out.print("전화번호:");
                          strPhone = dis.readLine();
                          
                          ab.add(strName, strAddress, strPhone);//입력받은 세개의 값을 add인자로 넘겨주어 arraylist 에 추가합니다.
                          break;
                          
                      case 2:
                          System.out.print("삭제할 이름을 입력하세요 :");
                          strName = dis.readLine();
                          ab.delete(strName, checksave);//삭제할 이름과 외부파일로 저장 여부를 체크하는 불린을 인자로 넘겨줍니다.
                          break;
                      case 3:
                          System.out.print("편집할 이름을 입력하세요 :");
                          strName = dis.readLine();
                          ab.edit(strName,checksave);//편집할 주소록을 찾기위해 이름을 인자로 넘겨줍니다.
                          break;
                          
                      case 4:
                          ab.save();//초기에 입력한 이름으로 파일에 arraylist값을 저장합니다.
                          checksave = true;//저장한 이력을 알기 위해 불린값을 true로 전환합니다.
                          break;
                          
                      case 5:   
                          System.out.print("저장할 파일의 이름을 입력하세요 : ");
                          ab.save(dis.readLine());//파일의 이름을 인자로 넘겨 새로운 파일로 저장합니다.
                          checksave = true;
                          break;
                          
                      case 6:
                          break;
                           
                          default: 
                              System.out.print("잘못 입력하셨습니다.");
                              break;
                                  
                  }
                  
                }
            }
            catch (Exception e) 
            {
                System.out.println("오류가 발생하였습니다.");
                
            }
             
        }
       
      
 
}
