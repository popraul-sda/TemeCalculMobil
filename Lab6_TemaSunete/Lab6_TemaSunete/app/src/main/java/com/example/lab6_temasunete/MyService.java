package com.example.lab6_temasunete;

import static java.lang.Thread.*;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.IBinder;
import android.util.Log;

import android.widget.Toast;

public class MyService extends Service {

    private static final String TAG = "MyService";


    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.


        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: Service STARTED");
        Toast.makeText(getApplicationContext(), "Service STARTED by User", Toast.LENGTH_SHORT).show();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



        int volum = Integer.parseInt(intent.getStringExtra("volum"));
        int perioada = Integer.parseInt(intent.getStringExtra("perioada"));
        int durata = Integer.parseInt(intent.getStringExtra("durata"));
        int secunde = perioada * 1000;

      while(true){
            if( perioada != 0 ){
                    Toast.makeText(getApplicationContext(), "Sunetul se va genera dupa o perioada de " + perioada + " secunde", Toast.LENGTH_SHORT).show();
                    perioada -- ;

                try {
                    sleep(secunde);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }else{
                ToneGenerator beep = new ToneGenerator(AudioManager.STREAM_MUSIC, volum);
                beep.startTone(ToneGenerator.TONE_CDMA_ANSWER, durata);
                break;
            }
        }
      return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: Services DESTROVED");
        Toast.makeText(getApplicationContext(), "Service STOPPED by User", Toast.LENGTH_SHORT).show();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.i(TAG, "onTaskRemoved: Stopping Service by Swipe");
        //stopSelf();
        super.onTaskRemoved(rootIntent);

    }

}