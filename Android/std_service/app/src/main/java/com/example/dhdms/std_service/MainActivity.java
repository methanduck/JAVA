package com.example.dhdms.std_service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Intent inte;
    Button start,end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.btn_service);
        end = findViewById(R.id.btn_shutdown);
        start.setOnClickListener(this);
        end.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_service:
                inte = new Intent(getApplicationContext(),std_Serv.class);
               startService(inte);
               android.util.Log.i("service","startService");
                break;

            case R.id.btn_shutdown:
                stopService(inte);
                android.util.Log.i("service","stop");
                break;
        }
    }
}
