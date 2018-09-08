package com.example.dhdms.for_n3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,View.OnClickListener{

    EditText txt_show,txt_input;
    RadioButton btn_black,btn_blue,btn_red;
    RadioGroup gp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_input = findViewById(R.id.Txt_input);
        txt_show = findViewById(R.id.Txt_show);
        gp = findViewById(R.id.group);

        txt_show.setText("안녕하세요, 반갑습니다.");
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
    switch (gp.getCheckedRadioButtonId())
    {
        case R.id.Btn_black:
            txt_show.setTextColor(Color.BLACK);
            break;

        case R.id.Btn_blue:
            txt_show.setTextColor(Color.BLUE);
            break;

        case R.id.Btn_red:
            txt_show.setTextColor(Color.RED);
            break;
    }


    }

    @Override
    public void onClick(View v) {
        txt_show.setTextSize(Float.parseFloat(this.txt_input.getText().toString()));
    }
}
