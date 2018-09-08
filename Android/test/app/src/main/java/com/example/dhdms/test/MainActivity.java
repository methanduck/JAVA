package com.example.dhdms.test;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{
    Button transfer,term;
    EditText name,age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transfer = findViewById(R.id.btn_trans);
        term = findViewById(R.id.btn_termi);
        transfer.setOnClickListener(this);
        term.setOnClickListener(this);

        name = findViewById(R.id.txtin_name);
        age = findViewById(R.id.txtin_age);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_trans)
        {
            Intent second = new Intent(this, Second.class);
            second.putExtra("name",this.name.getText().toString());
            second.putExtra("age",this.age.getText().toString());
            startActivityForResult(second,1);
        }
        else
            finish();
    }
    @Override
    protected void onActivityResult(int rqcode, int rstcode,Intent data)
    {
        super.onActivityResult(rqcode,rstcode,data);

       if(rqcode == 1)
       {
           if(rstcode == RESULT_OK)
           {
               String tmp = Integer.toString(data.getExtras().getInt("tmp"));

               Toast.makeText(this,tmp,Toast.LENGTH_SHORT).show();
           }
       }
    }
}
