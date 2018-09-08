package com.example.dhdms.test;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Second extends AppCompatActivity implements View.OnClickListener{
    TextView txt;
    String name,age;
    Button confirm;
    int tmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txt = findViewById(R.id.txt_show);
        confirm = findViewById(R.id.btn_confirm);
        confirm.setOnClickListener(this);
        Intent getfirst = getIntent();
        name = getfirst.getExtras().getString("name").toString();
        age = getfirst.getExtras().getString("age").toString();
        tmp = 6;

        txt.setText(name+"\t"+age);
    }



    @Override
    public void onClick(View v) {
        process();
        finish();
    }

    private void process()
    {
        Intent send_main = new Intent(this,MainActivity.class);
        send_main.putExtra("tmp",this.tmp);
        setResult(Activity.RESULT_OK,send_main);
    }
}
