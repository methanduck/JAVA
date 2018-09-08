/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookManager;

/**
 *
 * @author 1217
 */
public class BookRecord //책 한권의 값을 정의하는 클래스 입니다.
{

    private String strTitle;//책 제목
private String strAuthor;//책 저자
private int iQuantity;//책 수량
       
public BookRecord(String title,String author,int quantity)//생성자, 세개의 값을 입력받아 초기화합니다.
{
    this.strTitle=title;
    this.strAuthor=author;
    this.iQuantity=quantity;
}

public String getTitle() //책 제목을 리턴합니다.
{
    return this.strAuthor;
}
public String getAuthor()//책 저자를 리턴합니다.
{
    return this.strAuthor;
}
public int getQuantity()//책 수량을 리턴합니다.
{
    return this.iQuantity;
}
public void setstrTitle(String title)//책 제목이 될 title을 입력받아 this.title에 대입합니다.
{
    this.strTitle=title;
}
public void setstrAuthor(String author)//책 저자가 될 author를 입력받아 this.author에 대입합니다.
{
    this.strAuthor=author;
}
public void setiQuantity(int quatity)//책 수량이 될 quantity를 입력받아 this.quantity에 대입합니다.
{
    this.iQuantity=quatity;
}
}
