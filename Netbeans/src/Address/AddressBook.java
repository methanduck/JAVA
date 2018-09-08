/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Address;

import java.io.IOException;

/**
 *
 * @author dhdms
 */
public interface AddressBook
{
    public boolean init(String strFile) throws IOException; //초기 작업을 위한 함수
    
    public void add(String strName , String strAddress, String strPhone); //arraylist에 값을 추가하기 위한 함수
    
    public void save() throws IOException; // 외부 파일에 저장하기 위한 함수
    
    public void save (String strFile) throws IOException; // 다른 이름으로 저장하기 위한 함수
    
    public void delete(String strName, boolean checksave) throws IOException; // arraylist 또는 외부에 저장된 주소록을 삭제하기 위한 함수
    
    public void edit(String strName,boolean checksave) throws IOException; // 특정 주소록 값을 수정하기 위한 
    
    public int find(String strName) throws IOException;
}
