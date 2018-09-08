package com.example.dhdms.std_pref;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText name,age;
    Button next;
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences common = getSharedPreferences("TST",MODE_PRIVATE);
        name.setText(common.getString("name",""));
        age.setText(common.getString("age",""));
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences common = getSharedPreferences("TST",MODE_PRIVATE);
        SharedPreferences.Editor editor = common.edit();
        editor.putString("name",name.getText().toString());
        editor.putString("age",this.age.getText().toString());
        editor.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.name = findViewById(R.id.txtname);
        this.age = findViewById(R.id.txtage);
        this.next = findViewById(R.id.intent);
        next.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent next = new Intent(this,second.class);
        startActivity(next);
    }
}
