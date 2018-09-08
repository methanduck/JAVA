package com.example.dhdms.for_n7;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.arch.lifecycle.LiveData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends ListActivity implements AdapterView.OnItemSelectedListener{
    String item [] = {"ㄱ","ㄴ","ㄷ","ㄹ"};
    String girsG [] = {"윤","권","박"};
    String input;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        }

}
