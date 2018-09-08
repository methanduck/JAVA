package com.example.dhdms.std_service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class std_Serv extends Service {
    MediaPlayer mp;

    public std_Serv() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.stop();
        android.util.Log.i("service","onDestroy()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        android.util.Log.i("service","onStartComm()");
        mp = MediaPlayer.create(this, R.raw.t);
        mp.setLooping(true);
        mp.start();
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        android.util.Log.i("service","onCreate()");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
