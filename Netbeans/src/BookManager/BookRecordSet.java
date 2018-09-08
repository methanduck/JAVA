
package BookManager;


public class BookRecordSet implements Iterator //iterator 인터페이스를 구현한 클래스입니다.
{
    private BookRecord [] brSet;//bookrecord클래스의 배열레퍼런스를 선언합니다.
    private int iLast,index;//배열 내 입력된 최대값위의 빈 배열과 특정 배열을 가리키기 위한 index변수


    public BookRecordSet()//생성자 , 50개 배열 생성
    {
        brSet = new BookRecord[50];
    }
    public BookRecordSet(int max)//생성자, max개 배열 생성
    {
        brSet = new BookRecord[max];
    }
    public BookRecord getBookRecord(int index)//index값에 해당하는 bookrecord 배열레퍼런스를 리턴
    {
        return brSet[index];
    }
    public void appenBookRecord(BookRecord book) //book레퍼런스를 파라미터로 하여 빈 배열레퍼런스에 입력된 객체를 공유합니다.
    {
       brSet[iLast]=book; 
       iLast++;
    }
    public  void SetIndex(int param)//this클래스의 index값을 파라미터로 설정합니다.
    {
        index = param;
    }
    public void delete()//추가한 메서드 , 
    {
        --index;//this.next()로 인해 증가한 index를 감소시켜 대상 index를 가리키게 합니다.
        brSet[this.index]=null;//해당 객체를 가비지화 합니다.
        iLast--;//한개 더 입력받을수 있게 합니다.
    }
    public void arrange()//추가한 메서드 
    {
        brSet[this.index]=brSet[this.index+1];//다음 레퍼런스가 가리키는 객체를 공유합니다.
        brSet[this.index+1]=null;//다음 레퍼런스는 현재 레퍼런스와 공유를 하지 않습니다.
        index++;//인덱스 값 증가
    }
    public boolean isAvailable(int button)//추가한 메서드
    {
        boolean ret=false;//기본적으로 리턴값은 false값을 가집니다. 
        switch(button)//용도에 맞는 호출을 위해 button값을 입력받습니다.
        {
            case 1: //찾는 책이 없을 경우
                ret=((index>brSet.length)||(index>iLast));//index가 배열길이보다 크거나 입력되있는 수보다 클 경우 true
                break;
            case 2://isFull 역할 배열이 가득 찼을 경우
                ret= !(iLast>=brSet.length);//ilast가 전체 길이와 같거나 클경우
                break;
            case 3://정렬시 인덱스 위치 조정(arrange메서드에서 배열크기를 넘지 않기 위해)
                ret = index<brSet.length-1;//배열과 같으면 arrange함수에서 액세스 위반이 발생합니다.
                
        }
     return ret;//변수의 boolean 값을 반환합니다.
    }
    @Override
    public boolean hasNext() //다음 배열레퍼런스가 가리키는 값이 있는지 확인하는 메서드
    {
        if(index <iLast)//index값이 최대 입력된 배열수 보다 작을경우
            return true;//참
        else
            return false;//거짓
    }

    @Override
    public Object next()//index를 증가시키며 현재 인덱스에 해당하는 bookrecord레퍼런스를 반환합니다.
    {
        BookRecord br = getBookRecord(index);
        index++;
        return br;
    }
   
    
}
