/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Address;
import java.util.*;
import java.io.*;
import java.io.IOException;

/**
 *
 * @author dhdms
 */
public class AddressBookImpl implements AddressBook //addressbook 인터페이스를 구현한 클래스
{
    
    ArrayList<Data> vecList = null; //arraylist 형의 vecList 레퍼런스 선언
    private  File file = null;//FIle 형태의 file 레퍼런스 선언
    private Data address = null;//Data 클래스형의 address레퍼런스 선언

    @Override
    public boolean init(String strFile) throws IOException //초기화를 위한 함수 boolean값을 출력하여 main문에서 boolean값을 변경 할 수 있게 합니다.
    {
        FileReader fr = null; 
        BufferedReader br = null;
        vecList = new ArrayList<>(50); //50개 arraylist 객체를 생성
        file = new File(strFile.concat(".txt")); // 파일 이름을 입력받고 .txt를 붙여 파일객체 생성
        address = new Data();//Data객체 생성
        if(file.canRead())//입력받은 파일이 경로에 있어서 읽어올 수 있을경우
        {
            
            fr = new FileReader(file); // filereader에 파일을 연결하고 bufferedreader에 연결
            br = new BufferedReader(fr);
            
            String strTemp; // 버퍼에 들어온 값을 저장할 임시 스트링 변수
            while ((strTemp=br.readLine()) != null) // 더이상 읽어들일 수 없을때까지 한줄씩 읽어 strTemp변수에 저장
            {                
                String strArray[] = strTemp.split("\t"); // 배열에 tap을 기준으로 나누어 저장
                address.strName = strArray[0];
                address.strAddress = strArray[1];
                address.strPhone = strArray[2];
                
                vecList.add(address); // address에 저장된 값을 arraylist에 저장
            }
            System.out.print("파일을 찾았습니다 : ");//파일을 발견했을 시 출력문자
             System.out.println(file.getAbsolutePath());//해당 파일의 경로 출력 (디버깅 용도로 추가하였습니다.)
            br.close();
            fr.close();
                   return true;//파일이 저장되어있고 불러왔으므로 true값을 리턴
        }
        else //경로에 입력받은 이름의 파일이 없을경우
        {
            file.createNewFile();//파일을 새로 생성합니다.
            System.out.print("파일이 생성되었습니다 : ");
            System.out.println(file.getAbsolutePath());//해당 파일의 경로 출력 (디버깅 용도로 추가하였습니다.)
            return false;//파일을 새로 생성했고 파일에 저장된 값이 없으므로 false리턴
        }    
    }

    @Override
    public void add(String strName, String strAddress, String strPhone) //arraylist에 입력된 세 개 인자로 저장
    {
       address = new Data(); // 세 개 인자를 저장하기 위한 address
        address.strAddress =strAddress;
        address.strName = strName;
        address.strPhone = strPhone;
        
        vecList.add(address);//값이 저장된 address를 arraylist 에 저장
    }

    @Override
    public void save() throws IOException //현재 경로에 있는 파일에 arraylist 값을 저장
    {
        FileWriter fw = new FileWriter(file); //파일 클래스를 인자로 하여 filewriter와 연결
        BufferedWriter bw = new BufferedWriter(fw);//버퍼를 활용하여 출력을 효율화 
        address = new Data();
                
        try
        {
            for (int index = 0; index < vecList.size(); index++) //vecList.size()만큼 반복하며 arraylist 내 저장된 값을 
            {
                address = vecList.get(index);
                bw.write(address.strName + "\t"+ address.strAddress + "\t"+ address.strPhone+"\n");//버퍼에 각 요소와 \t을 합하여 저장
                bw.flush();//버퍼에 저장된 값을 출력
            }
                System.out.println("파일 저장이 완료되었습니다.");
                System.out.println("==================================");
        }
        catch(IOException e) //출력중 오류 발생시
        {
            System.out.println("파일 입출력 실패");
            System.out.println("=================================="); 
        }
        bw.close();
        fw.close();
    }

    @Override
    public void save(String strFile) throws IOException // 다른 이름으로 저장
    {
        address = new Data();
        FileWriter fw = new FileWriter(new File(strFile.concat(".txt"))); //인자로 들어온 입력값에 .txt를 붙여서 저장     
        BufferedWriter bw = new BufferedWriter(fw);
        try
        {
            for (int index = 0; index < vecList.size(); index++) 
            {
                address = vecList.get(index);

                bw.write(address.strName + "\t"+address.strAddress+ "\t"+address.strPhone + "\n");
                bw.flush();
            }
            System.out.println("다른 이름으로 저장되었습니다.");
                System.out.println("==================================");
        }
        catch(IOException e)
        {
            System.out.println("파일 입출력 실패");
            System.out.println("=================================="); 
        }
        bw.close();
        fw.close();
    }

    @Override
    public void delete(String strName,boolean checksave) throws IOException // arraylist 또는 저장된 파일 내용을 삭제
    {

            int index = this.find(strName); // find함수를 통해 일치하는 주소록의 index값을 받습니다.(-1은 찾지 못했을 경우)
                    if(index == -1)//주소록에서 찾지 못했을 경우
                    System.out.println("찾는 주소록이 없습니다.");
                    else
                    {
                            vecList.remove(index);//arraylist 에서 삭제
                            System.out.println("삭제가 완료되었습니다.");    
                            if(checksave == true)//프로그램 실행 후 외부에 저장한적이 있다면
                            {
                                try
                                {
                                file.delete();//파일 삭제 
                                file.createNewFile();//새 파일 생성
                                this.save();//arraylist 에 저장된 값 저장
                                System.out.println("파일 수정이 완료되었습니다.");
                                }
                                catch(IOException e)
                                {
                                   System.out.println("파일 입출력 오류가 발생했습니다.");
                                }
                                System.out.println("=================================="); 
                            }
                    }
    }

    @Override
    public void edit(String strName,boolean checksave) throws IOException //arraylist 또는 저장된 파일 내용에 대한 수정
    {
        address = new Data(); // 수정할 값을 저장할 data객체 생성
        int index = this.find(strName); // 찾는 이름의 index 값을 받습니다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in,"EUC-KR"));//값을 입력받기 위한 버퍼 리더
        if(index == -1)//찾는 이름이 주소록에 없을경우
        {
            System.out.println("찾는 이름이 없습니다.");
        System.out.println("=================================="); 
        }
        else
        {
            try
            {
                System.out.print("이   름 :");
                address.strName = br.readLine();
                System.out.print("주   소 :");
                address.strAddress = br.readLine();
                System.out.print("전화번호:");
                address.strPhone = br.readLine(); //address에 수정될 값을 입력 받습니다.

                if(checksave == false) // 외부에 저장 된 적이 없을 경우
                {
               vecList.set(index, address); //arraylist내 index에 해당하는 Data를 address에 저장된 값으로 교체합니다.
               System.out.println("편집이 완료되었습니다.");
               System.out.println("==================================");
                }
                else // 외부에 저장된 적이 있을 경우
                {
                    vecList.set(index, address);//arraylist값을 수정 한 뒤
                    file.delete();//파일 삭제
                    file.createNewFile();//파일 생성
                    this.save();//arraylist에 저장된 값을 파일에 저장합니다.
               System.out.println("편집 및 파일 저장이 완료되었습니다.");
               System.out.println("==================================");
                }
            }
            catch(IOException e)
            {
                System.out.print("편집에 실패했습니다.");
                System.out.println("=================================="); 
            }
                    
        }
    }

    @Override
    public int find(String strName) throws IOException //이름을 인자로 하여 arraylist 내 일치하는 위치의 index를 출력
    {
        address = new Data();//arraylist에서 가져온 값을 받을 address
        int result=-1; // 일치하는 이름이 없을경우 -1리턴
        try
        {
            for (int index = 0; index < vecList.size(); index++) //arraylist에 입력된 자료의 크기만큼 처음부터 검색
            {
                address = vecList.get(index);//리스트에서 index에 해당하는 데이터를 가져와 address에 대입
                if(address.strName.equals(strName))//address내 이름과 인자로 가져온 strName과 일치 할 경우 
                   result = index;//현재 index를 결과로 대입
            }
        }
        catch(Exception e)
        {
            System.out.println("파일 검색에 실패했습니다.");
            System.out.println("=================================="); 
        }
        finally
        {
            return result;//결과 출력
        }
        
    }

    
    
}
