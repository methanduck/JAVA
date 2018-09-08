
package BookManager;

import java.util.Scanner;


public class Main 
{
    public static void main(String[] args) 
    {
        boolean bFlag = true;   //메인문 반복 함수를 유지 및 종료하기 위한 flag
        int iQuantity;
        String strTitle;
        String strAuthor;//값을 입력 받기 위한 세가지 변수
        Scanner dis = new Scanner(System.in , "EUC-KR");//스캐너 함수
        BookRecordSet brs = new BookRecordSet(10); //객체 생성 및 초기화
        BookRecord br; //하위 함수에서 값을 받아오기 위한 임시 bookrecord클래스 레퍼런스
        

      
          
          
        while(bFlag)
        {
            int iMenu;//메뉴 선택위한 변수
            
            System.out.println("\n===========================");
            System.out.println("= 1. 도서 목록 추가 =");
            System.out.println("= 2. 도서 목록 검색 =");
            System.out.println("= 3. 도서 목록 변경 =");
            System.out.println("= 4. 도서 목록 삭제 =");
            System.out.println("= 5. 종료           =");
            System.out.println("\n===========================");
            System.out.println("메뉴를 선택하세요(1-5) \t");
            
            try 
            {
                iMenu = Integer.parseInt(dis.next());//int형 이외에 입력시 
            }
            catch (Exception e) 
            {
                System.out.println("다시 입력해 주세요!");//예외 처리합니다.
                            System.out.println("---------------------------");
                            continue;//재입력 받기 위한 continue
            }
            
            switch(iMenu)//iMenu값에 따라 case문 지정
            {
                case 1: //추가
                {
                        if(brs.isAvailable(2))//해당 함수의 반환값에 작동
                        {
                            while(true)//무한 반복 해당 while 구문은 예외처리를 위해 모든 case에서 동일하게 적용됩니다.
                            {
                                try 
                                {
                                    System.out.println("도서명을 입력하세요 :");
                                    strTitle = dis.next();
                                    System.out.println("저자를 입력하세요 :");
                                    strAuthor = dis.next();
                                    System.out.println("수량을 입력하세요 :");
                                    iQuantity= Integer.parseInt(dis.next());
                                }
                                catch (Exception e) 
                                {
                                    System.out.println("다시 입력해 주세요!");
                                    System.out.println("---------------------------");
                                    continue;
                                }
                                break;//구문 탈출을 위한 break;
                            }
                            br = new BookRecord(strTitle,strAuthor,iQuantity);//입력받은 값을 토대로 생성자를 통해 객체 생성및 레퍼런스 지정
                            brs.appenBookRecord(br); //bookrecordset클래스에 append함수를 통하여 br값을 파라미터로 사용
                            break;//1번 case종료
                       }
                        else//brs.isAvailable에서 false반환 시 배열이 가득 차 있으므로 해당 문 출력
                        {
                            System.out.print("더이상 추가 할 수 없습니다!");
                        break;
                        }
                }
                    
                    case 2://검색
                    {
                        
                        while(true)//무한 반복 해당 while 구문은 예외처리를 위해 모든 case에서 동일하게 적용됩니다.
                        {
                            try 
                            {
                                System.out.println("도서명을 입력하세요 :");
                                strTitle=dis.next();
                                
                            } 
                            catch (Exception e) 
                            {
                                 System.out.println("다시 입력해 주세요!");
                                System.out.println("---------------------------");
                                continue;
                            }
                            break;
                        }
                         
                        brs.SetIndex(0);//처음부터 검색하기위해 bookrecordset클래스의 index변수를 초기화 합니다.
                        while (brs.hasNext()) //함수의 반환값에 따라 반복
                        {                            
                            br=(BookRecord) brs.next(); //해당 함수의 반환값으로 bookrecord객체를 br레퍼런스에 대입합니다.
                            if(br.getTitle().equals(strTitle)) //br레퍼런스를 통해 입력받은 값과 br객체 내부의 값이 같다면 출력합니다.
                            {
                                System.out.println("\n도서명 :"+br.getTitle());
                                System.out.println("저    자 :"+br.getAuthor());
                                System.out.println("수    량 :"+br.getQuantity());
                                brs.SetIndex(0);
                                break;
                            }
                              if(brs.isAvailable(1))//해당 함수의 결과로 배열 끝까지 없으면
                                    {
                                        System.out.println("찾으시는 책이 없습니다.");
                                        break;
                                    }
                        }
                        break;
                    }
                        
                        case 3://변경
                        {
                            while(true)//무한 반복 해당 while 구문은 예외처리를 위해 모든 case에서 동일하게 적용됩니다.
                            {
                                try 
                                {
                                    System.out.print("도서명을 입력하세요");
                                    strTitle = dis.next();
                                } catch (Exception e)
                                {  System.out.println("다시 입력해 주세요!");
                                System.out.println("---------------------------");
                                continue;
                                }
                                break;
                            }
                            brs.SetIndex(0); //처음부터 검색하기위해 bookrecordset클래스의 index변수를 초기화 합니다.
                            while (brs.hasNext()) 
                                {                
                                    br=(BookRecord) brs.next();
                                    if(br.getTitle().equals(strTitle))
                                    {
                                        System.out.print("수정할 도서명을 입력하세요 :");
                                        strTitle=dis.next();
                                         br.setstrTitle(strTitle); //br은 bookrecordset의 배열중 하나와 연결된객체를 공유하므로 br의 메서드로 수정합니다.
                                          System.out.print("수정할 저자명을 입력하세요 :");
                                          strAuthor=dis.next();
                                          br.setstrAuthor(strAuthor);//br은 bookrecordset의 배열중 하나와 연결된객체를 공유하므로 br의 메서드로 수정합니다.
                                           System.out.print("수정할 수량을 입력하세요 :");
                                          iQuantity=dis.nextInt();
                                          br.setiQuantity(iQuantity);//br은 bookrecordset의 배열중 하나와 연결된객체를 공유하므로 br의 메서드로 수정합니다.
                                          break;
                                    } 
                                 if(brs.isAvailable(1)) //해당 함수의 결과로 배열 끝까지 없으면
                                    {
                                        System.out.println("찾으시는 책이 없습니다.");
                                        break;
                                    }
                                }     
                          
                          
                            break;
                        }
                           
                        case 4://삭제
                        {
                             
                            while(true)//무한 반복 해당 while 구문은 예외처리를 위해 모든 case에서 동일하게 적용됩니다.
                            {
                                try 
                                {
                                    System.out.print("도서명을 입력하세요");
                                    strTitle = dis.next();
                                } catch (Exception e)
                                {
                                    System.out.println("다시 입력해 주세요!");
                                System.out.println("---------------------------");
                                continue;
                                }
                                break;
                            }
                            
                            brs.SetIndex(0);
                            while (brs.hasNext()) 
                                {                
                                    br=(BookRecord) brs.next();
                                    if(br.getTitle().equals(strTitle))
                                    { 
                                        brs.delete(); // 해당 함수는 bookrecordset클래스에서 설명하겠습니다.
                                        while (brs.isAvailable(3))//해당 함수 결과를 통해 참일 경우
                                        {   
                                            brs.arrange();//호출
                                        }
                                        System.out.print("삭제 완료");//반복문이 종료되면 삭제 완료 출력
                                        break;
                                    }
                                      if(brs.isAvailable(1))//해당 함수의 결과로 배열 끝까지 없으면
                                    {
                                        System.out.println("찾으시는 책이 없습니다.");
                                        break;
                                    }
                                  
                                }
                            break;
                        }
                            
                        case 5://종료
                            bFlag= false; // while문 전체 종료 이후 메인문 내에 더이상 코드가 없으므로 프로그램 종료
                            break;
                            
                        default://지정 값 이외에 입력시 다시 입력 받습니다.
                            System.out.println("다시 입력해 주세요");
                                break;
            }
            
        }
    }
}
