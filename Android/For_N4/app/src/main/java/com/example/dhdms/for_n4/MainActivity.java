package com.example.dhdms.for_n4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,View.OnClickListener{
    Button calc,erase,term;
    RadioGroup rg;
    EditText num1,num2,res;
    int ck,result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calc = findViewById(R.id.Btn_calc);
        erase = findViewById(R.id.Btn_erase);
        term = findViewById(R.id.Btn_term);
        calc.setOnClickListener(this);
        erase.setOnClickListener(this);
        term.setOnClickListener(this);

        rg = findViewById(R.id.group);
        rg.setOnCheckedChangeListener(this);
        num1 = findViewById(R.id.Txt_num1);
        num2 = findViewById(R.id.Txt_num2);
        res = findViewById(R.id.Txt_res);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Btn_calc:
            {
                switch (ck)
                {
                    case 0:
                        this.result = Integer.parseInt(num1.getText().toString())+Integer.parseInt(num2.getText().toString());
                        res.setText(Integer.toString(result));
                        break;

                    case 1:
                        this.result = Integer.parseInt(num1.getText().toString())-Integer.parseInt(num2.getText().toString());
                        res.setText(result);
                        break;

                    case 2:
                        this.result = Integer.parseInt(num1.getText().toString())*Integer.parseInt(num2.getText().toString());
                        res.setText(result);
                        break;

                    case 3:
                        this.result = Integer.parseInt(num1.getText().toString())/Integer.parseInt(num2.getText().toString());
                        res.setText(result);
                        break;
                }
                break;
            }

            case R.id.Btn_erase:
            this.num1.setText("");
            this.num2.setText("");
            break;

            case R.id.Btn_term:
                this.onStop();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(group == rg)
        {
            switch (checkedId)
            {
                case R.id.Rb_plus :
                    this.ck = 0;
                    break;

                case R.id.Rb_mi:
                    this.ck = 1 ;
                    break;

                case R.id.Rb_mul:
                    this.ck = 2;
                    break;

                case R.id.Rb_div:
                    this.ck = 2;
                    break;
            }
        }
    }
}
