package com.example.dhdms.std_broadcastrc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView batt;
    TextView level,status;
    BroadcastReceiver br=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(action.equals(Intent.ACTION_BATTERY_CHANGED))
            {
                int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                level.setText("현재 충전량 :"+ remain);

                if(remain >= 90)
                    batt.setImageResource(R.drawable.three);
                else if(remain >= 70)
                    batt.setImageResource(R.drawable.two);
                else if(remain >=50)
                    batt.setImageResource(R.drawable.one);
                else if(remain >= 30)
                    batt.setImageResource(R.drawable.warn);
                else if (remain >=10)
                    batt.setImageResource(R.drawable.cri);

                int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0);
                switch (plug)
                {
                    case 0:
                        status.setText("전원 연결 안됨");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        status.setText("어댑터 연결됨");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        status.setText("usb연결됨");
                        break;

                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.batt = findViewById(R.id.batticon);
        this.level = findViewById(R.id.txt_level);
        this.status = findViewById(R.id.txt_status);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        IntentFilter ifilter = new IntentFilter();
        ifilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(br,ifilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br);
    }

}
