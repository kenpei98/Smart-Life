package com.example.smartsleep.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartsleep.AlarmActivity;
import com.example.smartsleep.AlertReceiver;
import com.example.smartsleep.R;

import java.util.Calendar;
import java.util.Locale;


public class NormalRingActivity extends AppCompatActivity {
    public static boolean play = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nomral_ring);
        final boolean[] play = {true};

        TextView timeView = findViewById(R.id.timeTextViewNormalRing);
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        if(minute<10){
            timeView.setText(hour+": "+"0"+minute);
        }
        else{
            timeView.setText(hour+": "+minute);
        }


        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                while(play[0]) {
                    try {
                        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Ringtone r = RingtoneManager.getRingtone(NormalRingActivity.this, notification);
                        r.play();
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };


        final Thread myThread = new Thread(myRunnable);
        myThread.start();


        Button stop = findViewById(R.id.stopAlarm);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NormalRingActivity.this,AlertReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(NormalRingActivity.this,AlertReceiver.num, intent, 0);
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);

                play[0] = false;

                finish();
            }
        });



    }

}
