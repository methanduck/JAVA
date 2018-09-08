package com.example.dhdms.std_all;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Month;
import java.util.Calendar;

public class InputData extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener{
    //INTENT 구별을 위한 상수값
    final int INTENT_INCOME =0;
    final int DATESELECTOR =1;

    //일반 변수
    int date,month;
    boolean chk_Intent = false;                                                                     // false == INTENT_INCOME, true == INTENT_WITHDRAW

    //참조 변수
    Button btn_SelectDate,btn_InputComplete;
    EditText txt_Money,txt_Memo;
    TextView txt_Date,txt_ShowMoneyWay;
    Spinner spin_IncomeWay;
    SQLiteDatabase db;
    MoneyData dbm;
    ContentValues cv = new ContentValues();
    Intent intent_dtg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //MainActivity와 공통 테마 적용
        SharedPreferences pref = getSharedPreferences("Pref",MODE_PRIVATE);
        String Theme = pref.getString("tmselected","");
        if(Theme.equals("dark"))
            setTheme(R.style.Theme_AppCompat);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputdata);

        //XML연결과 리스너 등록
        btn_SelectDate = findViewById(R.id.btn_SelectDate);
        btn_SelectDate.setOnClickListener(this);
        btn_InputComplete = findViewById(R.id.btn_InputComplete);
        btn_InputComplete.setOnClickListener(this);
        txt_Memo = findViewById(R.id.txt_Memo);
        txt_Money = findViewById(R.id.txt_InputMoney);
        txt_Date = findViewById(R.id.txt_date);
        txt_ShowMoneyWay = findViewById(R.id.txt_MoneyWay);
        spin_IncomeWay = findViewById(R.id.spin_IncomeWay);
        ArrayAdapter spin_Adapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Income_Rsc,R.layout.support_simple_spinner_dropdown_item);
        spin_IncomeWay.setAdapter(spin_Adapter);
        spin_IncomeWay.setSelection(0);

        //호출에 따라 수입과 지출이 나뉨
        intent_dtg = getIntent();
        if(intent_dtg.getIntExtra("dtg",0) == INTENT_INCOME)                      //수입 입력인 인텐트 일 경우
        {
            setTitle("수입 입력");
        }
        else
        {
            setTitle("지출 입력");
            txt_ShowMoneyWay.setText("지출 경로");                                                    //기본 수입 경로이므로 지출일 경우 지출경로로 변경
            this.chk_Intent = true;
        }

        Calendar cal = Calendar.getInstance();                                                      //날짜는 기본 선택되기 위해 캘린더 클래스 사용
        this.date = cal.get(Calendar.DATE);
        this.month = cal.get(Calendar.MONTH)+1;
        txt_Date.setText(this.month+"월"+this.date+"일");                                            //오늘 날짜를 텍스트뷰에 기본 설정
        txt_Date.setGravity(Gravity.CENTER);
        txt_Date.setTextSize(30);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {                 //DatePicker로부터 선택된 날짜를 가져옴
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == DATESELECTOR)
        {
            if(resultCode == Activity.RESULT_OK)
            {
               date = data.getIntExtra("date",0);
               month = data.getIntExtra("month",0);
             this.txt_Date.setText(this.month+"월"+this.date+"일");
             this.txt_Date.setTextSize(30);
             this.txt_Date.setGravity(Gravity.CENTER);
            }
        }
    }

    @Override
    public void onClick(View v) {
    switch (v.getId())
    {
        //날짜 선택 버튼
        case R.id.btn_SelectDate:
            Intent call_Input = new Intent(getApplicationContext(),DateSelector.class);
            call_Input.putExtra("settext",1);
            startActivityForResult(call_Input, DATESELECTOR);
            break;

        //입력 완료 버튼
        case R.id.btn_InputComplete:
            if(txt_Money.getText().toString().isEmpty())                                            //금액 입력은 필수 항목이므로 비어잇을경우 토스트 출력
            {
                Toast.makeText(getApplicationContext(),"금액을 입력해 주세요",Toast.LENGTH_LONG).show();
                break;
            }


            Intent return_output = new Intent(getApplicationContext(),MainActivity.class);

                    cv.put("Month",this.month);
                    cv.put("Date",this.date);
                    cv.put("Memo",this.txt_Memo.getText().toString());                              //값 저장

                    if (intent_dtg.getIntExtra("dtg",0) == INTENT_INCOME)         //수입을 입력받는 인텐트일 경우
                {
                    cv.put("Income",this.txt_Money.getText().toString());
                    cv.put("IncomeWay",this.spin_IncomeWay.getSelectedItem().toString());           //수입, 수입경로 저장

                    dbm = new MoneyData(getApplicationContext(),"Income",null,1); //Income 테이블 연결
                    db = dbm.getWritableDatabase();
                    db.insert("Income",null,cv);                                //저장된 값 insert
                    dbm.close();
                    return_output.putExtra("Money_Income",this.txt_Money.getText().toString());//입력된 값을 메인화면에 뿌려주기위해 값을 돌려줌
                }
                else                                                                                //지출을 입력받는 인텐트일 경우
                {
                    cv.put("Withdraw",this.txt_Money.getText().toString());
                    cv.put("WithdrawWay",this.spin_IncomeWay.getSelectedItem().toString());
                    dbm = new MoneyData(getApplicationContext(),"Withdraw",null,1);
                    db = dbm.getWritableDatabase();
                    db.insert("Withdraw",null,cv);
                    dbm.close();
                    return_output.putExtra("Money_Withdraw",this.txt_Money.getText().toString());
                }
            setResult(Activity.RESULT_OK,return_output);
            finish();                                                                               //값을 돌려주고 현재 엑티비티 종료
            break;
    }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
