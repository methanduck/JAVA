package com.example.dhdms.std_cp;

import android.Manifest;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        txt = findViewById(R.id.txt);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CALL_LOG},MODE_PRIVATE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText(getcallhis());
            }
        });
    }
    public String getcallhis()
    {

        String[] callset = new String[]{CallLog.Calls.DATE,CallLog.Calls.TYPE,CallLog.Calls.NUMBER,CallLog.Calls.DURATION};

        Cursor c = getContentResolver().query(CallLog.Calls.CONTENT_URI,callset,null,null,null);
        if(c == null)
        {
            return "통화기록 없음";
        }

        StringBuffer buf = new StringBuffer();
        buf.append("날짜 : 구분 : 전화번호 : 통화시간 \n");
        c.moveToFirst();
        do{
            long calldate = c.getLong(0);
            SimpleDateFormat datepattern = new SimpleDateFormat("yyyy-MM-dd");
            String date_str = datepattern.format(new Date(calldate));
            buf.append(date_str + ":");
            if(c.getInt(1) == CallLog.Calls.INCOMING_TYPE)
                buf.append("착신"+":");
            else
                buf.append("발신 :");
            buf.append(c.getString(2)+":");
            buf.append(c.getString(3)+"초\n");
        }while (c.moveToNext());

        c.close();
        return buf.toString();
    }

}
