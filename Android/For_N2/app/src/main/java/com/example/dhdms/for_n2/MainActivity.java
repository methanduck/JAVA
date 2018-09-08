package com.example.dhdms.for_n2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText txt;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.Btn);
        txt = findViewById(R.id.Txt);
        btn.setOnClickListener(this);

    }
    @Override
    protected void onStart() {
        super.onStart();
        txt.setText(this.txt.getText().append("onStart"));
        android.util.Log.i("test","onstart"+count);
    }
    @Override
protected void onResume() {
    count++;
    super.onResume();
    txt.setText(this.txt.getText().append("onResume"));
    android.util.Log.i("test","onResume"+count);
}
    @Override
    protected void onPause() {
        count++;
        super.onPause();
        txt.setText(this.txt.getText().append("onPause"));
        android.util.Log.i("test","onPause"+count);
    }

    @Override
    protected void onStop() {
        count++;
        super.onStop();
        txt.setText(this.txt.getText().append("onStop"));
        android.util.Log.i("test","onSTop"+count);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        txt.setText(this.txt.getText().append("onDestroy"));
    }

    @Override
    public void onClick(View v) {
        this.onDestroy();
    }
}
