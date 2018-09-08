package com.example.dhdms.std_all;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.ErrnoException;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener ,CompoundButton.OnCheckedChangeListener{
    //INTENT 구별을 위한 상수값
    final int ERROR = -1;
    final int INCOME = 0;
    final int WITHDRAW = 1;
    final int DATESELECTOR_INCOME = 2;
    final int DATESELECTOR_WITHDRAW =3;

    //참조 변수
    ListView list_View;                                                                             //리스트 뷰
    ListAdapter adap;                                                                               //리스트 뷰 어댑터
    ArrayList<String> list_Data = new ArrayList<>();                                                //STRING 형태의 리스트 데이터
    Button btn_Income,btn_Withdraw,btn_showIncome,btn_showWithdraw;                                 //수입, 지출 버튼
    TextView txt_Incnt, txt_Witcnt,txt_TodayTot,txt_Status,txt_TodayTotLabel;                       //등록된 수입금액, 등록된 지출금액, 조회된 내역 중 오늘의 소비 합, 조회의 성공실패 여부
    Switch Theme;                                                                                   //테마 변경을 위한 스위치
    SQLiteDatabase db;                                                                              //database 사용을 위한 참조변수
    MoneyData dbm;                                                                                  //sqlopenhelper연결을 위한 참조변수

    //일반 변수
    boolean chk=false;                                                                              //스위치 상태변경으로 인한 인텐트 루프 방지 불린값
    int money_Income, money_Withdraw,date_Month,date_Date;                                          //인텐트 엑스트라를 받아내기 위한 변수
    int money_Total= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //설정을 불러와 테마 적용
       SharedPreferences theme = getSharedPreferences("Pref",MODE_PRIVATE);
         String dark = theme.getString("tmselected","");
        if(dark.equals("dark"))
            setTheme(R.style.Theme_AppCompat);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("자금관리");

        //XML연결과 리스너 등록
        btn_showIncome = findViewById(R.id.btn_showIncome);
        btn_showIncome.setOnClickListener(this);
        btn_showWithdraw = findViewById(R.id.btn_showWithdraw);
        btn_showWithdraw.setOnClickListener(this);
        btn_Income = findViewById(R.id.btn_inputIn);
        btn_Income.setOnClickListener(this);
        btn_Withdraw = findViewById(R.id.btn_inputMin);
        btn_Withdraw.setOnClickListener(this);
        txt_Incnt = findViewById(R.id.txt_inCnt);
        txt_Witcnt = findViewById(R.id.txt_outCnt);
        txt_TodayTot = findViewById(R.id.txt_todayTot);
        txt_TodayTotLabel = findViewById(R.id.txt_todayTotLabel);
        txt_Status = findViewById(R.id.txt_showStatus);
        list_View = findViewById(R.id.list_Data);
        Theme = findViewById(R.id.sw_Theme);
        Theme.setOnCheckedChangeListener(this);

        //테마에 맞는 스위치 상태 변경
        if(dark.equals("dark")) {
            Theme.setChecked(true);
            Theme.setText("어두운 테마");
        }
        else
            Theme.setText("밝은 테마");
        chk=true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            //우측 수입버튼 하단 입력된 값 확인
            case INCOME:
                if(resultCode == RESULT_OK) {
                    this.money_Income = Integer.parseInt(data.getStringExtra("Money_Income"));
                    this.txt_Incnt.setText("등록된 금액 :"+this.money_Income);
                    this.txt_Status.setText("새로운 값이 등록되었습니다 재조회 해주세요");
                    this.txt_Status.setTextSize(20);
                }
                break;

            //우측 지출버튼 하단 입력된 값 확인
            case WITHDRAW:
                if(resultCode == RESULT_OK)
                {
                    this.money_Withdraw = Integer.parseInt(data.getStringExtra("Money_Withdraw"));
                    this.txt_Witcnt.setText("등록된 금액:"+this.money_Withdraw);
                    this.txt_Status.setText("새로운 값이 등록되었습니다 재조회 해주세요");
                    this.txt_Status.setTextSize(20);
                }
                break;

            //좌측 수입조회버튼 클릭 시 리스트뷰 시현
            case DATESELECTOR_INCOME:
                if(resultCode == RESULT_OK)
                {
                    Query(INCOME,data);
                }
                break;

            //좌측 수입조회버튼 클릭 시 리스트뷰 시현
            case DATESELECTOR_WITHDRAW:
                if(resultCode == RESULT_OK)
                {
                  Query(WITHDRAW,data);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent Call_intent;
        switch (v.getId())
        {
            //수입 자료 입력 버튼
            case R.id.btn_inputIn:
             Call_intent = new Intent(getApplicationContext(),InputData.class);
            Call_intent.putExtra("dtg",INCOME);                                               //인텐트 하단 버튼 텍스트 구별 위한 정수
            startActivityForResult(Call_intent,INCOME);
            break;

            //지출 자료 입력 버튼
            case R.id.btn_inputMin:
                Call_intent = new Intent(getApplicationContext(),InputData.class);
                Call_intent.putExtra("dtg",WITHDRAW);                                         //인텐트 하단 버튼 텍스트 구별 위한 정수
                startActivityForResult(Call_intent,WITHDRAW);
                break;

            //수입 자료 조회 버튼
            case R.id.btn_showIncome:
                Call_intent = new Intent(getApplicationContext(),DateSelector.class);
                Call_intent.putExtra("settext",0);                                      //인텐트 하단 버튼 텍스트 구별 위한 정수
                startActivityForResult(Call_intent,DATESELECTOR_INCOME);
                break;

            //지출 자료 조회 버튼
            case R.id.btn_showWithdraw:
                Call_intent = new Intent(getApplicationContext(),DateSelector.class);
                Call_intent.putExtra("settext",0);                                      //인텐트 하단 버튼 텍스트 구별 위한 정수
                startActivityForResult(Call_intent,DATESELECTOR_WITHDRAW);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {                    //인텐트 구동 시 테마 적용을 위해 바꾸는 즉시 값을 Preference에 저장
        if(chk)
        {
            if(isChecked)                                                                           //어두움(TRUE)
            {
                Intent inte = getIntent();
                SharedPreferences pref = getSharedPreferences("Pref",MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("tmselected","dark");                                                //"dark"로 저장
                edit.commit();
                finish();                                                                           //현재 인텐트 종료
                startActivity(inte);                                                                //현재 인텐트 시작
            }
            else                                                                                    //밝음(FALSE)
            {
                Intent inte = getIntent();
                SharedPreferences pref = getSharedPreferences("Pref",MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("tmselected","bright");
                edit.commit();
                finish();
                startActivity(inte);
            }
        }

    }

    private void CtrlTxt(int dtg)                                                                   //내용 조회 시 오늘의 지출 / 수입 과 최 상단 조회상황 텍스트 변경 (Integer 수입, 지출)
    {

        switch (dtg)
        {
            case INCOME:
                txt_Status.setTextSize(30);
                txt_Status.setText("결과가 조회되었습니다.");
                txt_TodayTotLabel.setText(date_Month+"월"+date_Date+"일 수입내역");
                txt_TodayTot.setText(Integer.toString(money_Total)+"원");
                break;

            case WITHDRAW:
                txt_Status.setTextSize(30);
                txt_Status.setText("결과가 조회되었습니다.");
                txt_TodayTotLabel.setText(date_Month+"월"+date_Date+"일 지출내역");
                txt_TodayTot.setText("-"+Integer.toString(money_Total)+"원");
                break;

                default:
                    txt_Status.setTextSize(30);
                    txt_Status.setText("조회된 결과가 없습니다.");
                    txt_TodayTotLabel.setText(null);
                    txt_TodayTot.setText(null);
                    break;
        }
    }

    private void Query(int dtg,Intent data)                                                         //데이터베이스에서 자료를 가져와 리스트뷰에 뿌려주는 메서드 (int 수입/지출 , Intent ActivityResult)
    {
        date_Month = data.getIntExtra("month",0);                                 //DateSelector로부터 가져온 달
        date_Date = data.getIntExtra("date",0);                                   //DateSelector로부터 가져온 일


        String str_Dtg;                                                                             //SQL 및 테이블칼럼 조회시 사용될 스트링값
        if(dtg == INCOME)
            str_Dtg = "Income";
        else
            str_Dtg = "Withdraw";

        dbm = new MoneyData(getApplicationContext(),str_Dtg,null,1);
        db = dbm.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT Date,"+str_Dtg+",Memo FROM "+str_Dtg+" WHERE Month ="+this.date_Month+" AND "+"Date ="+this.date_Date+";",null);

        if(cur != null&&cur.moveToFirst())
        {
            money_Total =0;
            list_Data.clear();
            do
            {
                String Date = cur.getString(cur.getColumnIndex("Date"));
                String Money = cur.getString(cur.getColumnIndex(str_Dtg));

                if(cur.getString(cur.getColumnIndex("Memo")).equals(""))
                    list_Data.add(Date+"일\t\t\t\t\t"+Money+"원");
                else {
                    String Memo = cur.getString(cur.getColumnIndex("Memo"));
                    list_Data.add(Date + "일\t\t\t\t\t" + Money + "원 \t\t\t\t\t 메모 :" + Memo);
                }
                money_Total+= Integer.parseInt(Money);

            }while(cur.moveToNext());
            adap = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,list_Data);
            list_View.setAdapter(adap);
            CtrlTxt(str_Dtg.equals("Income")?INCOME:WITHDRAW);
        }
        else {
            list_Data.clear();
            adap = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,list_Data);
            list_View.setAdapter(adap);
            CtrlTxt(ERROR);
        }
    }
}
