package com.example.dhdms.std_sqlite;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
    EditText name,count,price;
    Button add,result;
    ListView lv;

    String sangpum[];
    SQLiteDatabase sdb;
    DBmanager dbm;
    ContentValues rvalue = new ContentValues();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.lv = findViewById(R.id.listview);
        this.name = findViewById(R.id.txt_name);
        this.count = findViewById(R.id.txt_count);
        this.price = findViewById(R.id.txt_price);
        this.add = findViewById(R.id.btn_add);
        this.result = findViewById(R.id.btn_res);

        add.setOnClickListener(this);
        result.setOnClickListener(this);
        name.requestFocus();

    }

    @Override
    public void onClick(View v) {
    switch (v.getId())
    {
        case R.id.btn_add:
            if(name.getText().toString().equals("")||price.getText().toString().equals("")||count.getText().toString().equals(""))
                Toast.makeText(getApplicationContext(),"뭔가 입력이 덜됨",Toast.LENGTH_LONG).show();
            else {
                rvalue.put("name", this.name.getText().toString());
                rvalue.put("price", this.price.getText().toString());
                rvalue.put("count", this.count.getText().toString());
            }
            try {
                dbm = new DBmanager(this,"myDB.db",null,1);
                sdb = dbm.getWritableDatabase();
                sdb.insert("sanpum",null,rvalue);
                dbm.close();
                Toast.makeText(getApplicationContext(),"상품:"+rvalue.get("name")+"가격"+rvalue.get("price")+"수량:"+rvalue.get("count"),Toast.LENGTH_LONG).show();
            }catch (SQLiteException e)
            {
                Toast.makeText(getApplicationContext(),"오류코드 :"+e,Toast.LENGTH_LONG).show();
            }
            break;

        case R.id.btn_res:
            this.name.setText("");
            this.count.setText("");
            this.price.setText("");
            try {
                dbm = new DBmanager(getApplicationContext(),"myDB.db",null,1);
                sdb = dbm.getReadableDatabase();
                Cursor cr = sdb.rawQuery("select * from sangpum",null);
                int count=0;
                sangpum = new String[cr.getCount()];
                while (cr.moveToNext())
                {
                    sangpum[count] = cr.getInt(0) + " " + cr.getString(1)+ " " +cr.getString(2)+ " " +
                    cr.getString(3)+ " " +cr.getString(4);
                    count++;
                }
                cr.close();
                dbm.close();
                ListAdapter ad = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,sangpum);
                lv.setAdapter(ad);
            }catch (SQLException e)
            {
                Toast.makeText(getApplicationContext(),"오류 : "+e,Toast.LENGTH_LONG).show();
            }

    }
    }
}
