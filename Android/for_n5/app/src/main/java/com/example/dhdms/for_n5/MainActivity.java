package com.example.dhdms.for_n5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Random num_Gen = new Random(System.currentTimeMillis());
    Button btn_rst;
    TextView txt_result;
    boolean used;
    int selected= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selected = num_Gen.nextInt(4)+1;
        Button btn_card1 = findViewById(R.id.Btn_1);
        Button btn_card2 = findViewById(R.id.Btn_2);
        Button btn_card3 = findViewById(R.id.Btn_3);
        Button btn_card4 = findViewById(R.id.Btn_4);
        btn_rst       =  findViewById(R.id.Btn_rst);
        txt_result = findViewById(R.id.txt_Result);

        btn_card1.setOnClickListener(this);
        btn_card2.setOnClickListener(this);
        btn_card3.setOnClickListener(this);
        btn_card4.setOnClickListener(this);
        btn_rst.setOnClickListener(this);

        btn_rst.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.Btn_1:
            this.CkSet_result(1);
            break;

            case R.id.Btn_2:
                this.CkSet_result(2);
                break;

            case R.id.Btn_3 :
                this.CkSet_result(3);
                break;

            case R.id.Btn_4:
                this.CkSet_result(4);
                break;

            case R.id.Btn_rst:
                this.Init();
        }
    }

    public void CkSet_result(int input)
    {
        if(selected == input)
        {
            this.txt_result.setText("축하합니다 홍길동님, 당첨되셨습니다.");
        }
        else
        {
            this.txt_result.setText("다시 시도하세요");

        }
        this.btn_rst.setVisibility(View.VISIBLE);
       used = true;
    }

    public void Init()
    {
        this.btn_rst.setVisibility(View.INVISIBLE);
        selected = num_Gen.nextInt(4)+1;
       used = false;
    }
}
