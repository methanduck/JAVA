package com.example.dhdms.std_all;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class DateSelector extends AppCompatActivity implements View.OnClickListener{

    DatePicker date_Picker;
    Button btn_PickedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences pref = getSharedPreferences("Pref",MODE_PRIVATE);                   //공통 테마를 사용하기 위한 Preference
        if(pref.getString("tmselected","").equals("dark"))
            setTheme(R.style.Theme_AppCompat);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_selector);

        date_Picker = findViewById(R.id.date_Picker);
        btn_PickedDate = findViewById(R.id.btn_PickDate);
        btn_PickedDate.setOnClickListener(this);
        Intent settext = getIntent();
        if(settext.getIntExtra("settext",0)==1)                                   //날짜를 조회하기 위한것과 입력하기 위한것을 구분
            btn_PickedDate.setText("날짜 선택");
        else
            btn_PickedDate.setText("날짜 조회");
    }

    @Override
    public void onClick(View v) {
        Intent output ;
        if(btn_PickedDate.getText().toString().equals("날짜 선택")) {
            output = new Intent(getApplicationContext(), InputData.class);
            output.putExtra("date", date_Picker.getDayOfMonth());
            output.putExtra("month", date_Picker.getMonth()+1);
        }
        else {
            output = new Intent(getApplicationContext(), MainActivity.class);
            output.putExtra("date", date_Picker.getDayOfMonth());
            output.putExtra("month", date_Picker.getMonth()+1);
        }

        setResult(Activity.RESULT_OK,output);
        finish();
    }
}
