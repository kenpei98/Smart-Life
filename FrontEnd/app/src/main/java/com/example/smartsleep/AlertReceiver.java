package com.example.smartsleep;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

import java.io.Serializable;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

import com.example.smartsleep.ui.NormalRingActivity;
import com.example.smartsleep.ui.ShakeRingActivity;


public class AlertReceiver extends BroadcastReceiver {
    public static boolean play = true;

    public static int num;

    @Override
    public void onReceive(Context arg0, Intent intent) {

        Toast.makeText(arg0,"Alarm",Toast.LENGTH_SHORT).show();


        int code = intent.getIntExtra("requestCode",0);

        num = code;


        int type = intent.getIntExtra("type",0);

        if(type == 1){
            Intent playIntent =new Intent(arg0, ShakeRingActivity.class);
            playIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            arg0.startActivity(playIntent);
        }
        else{
            Intent playIntent =new Intent(arg0, NormalRingActivity.class);
            playIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            arg0.startActivity(playIntent);
        }






    }

//    private void ring(Context arg0){
//        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        Ringtone r = RingtoneManager.getRingtone(arg0.getApplicationContext(), notification);
//
//        play =true;
//        int count =0;
//
//        while(play) {
//            r.play();
//            count++;
//            if(count== 20){
//                play =false;
//            }
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }

}
