package com.example.dhdms.std_pref;

import android.content.SharedPreferences;
import android.os.TestLooperManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class second extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView name = findViewById(R.id.textView);
        TextView age = findViewById(R.id.textView2);

        SharedPreferences common = getSharedPreferences("TST",MODE_PRIVATE);
        name.setText(common.getString("name",""));
        age.setText(common.getString("age",""));

    }
}
