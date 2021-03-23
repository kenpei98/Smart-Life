package com.example.smartsleep.ui.alarm;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Response;
import com.example.smartsleep.Alarm;
import com.example.smartsleep.AlarmActivity;
import com.example.smartsleep.AlertReceiver;
import com.example.smartsleep.OnlineAlarm;
import com.example.smartsleep.R;
import com.example.smartsleep.User;
import com.example.smartsleep.app.VolleyController;
import com.example.smartsleep.ui.CreateNewAlarmActivity;
import com.example.smartsleep.ui.CreateOnlineAlarmActivity;
import com.example.smartsleep.ui.me.MeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class AlarmFragment extends Fragment {

    private AlarmViewModel alarmViewModel;
    public static ArrayList<Alarm> alarmList = new ArrayList<Alarm>();
    ArrayList<OnlineAlarm> onlineAlarms = new ArrayList<OnlineAlarm>();
    LinearLayout fly;

    @SuppressLint("FragmentLiveDataObserve")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        alarmViewModel =
                ViewModelProviders.of(this).get(AlarmViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_alarm, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        alarmViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        FloatingActionButton createAlarm = root.findViewById(R.id.createNewAlarm);
        createAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MeFragment.loginD==true&&MeFragment.loginUser.type==1) {
                    Intent i = new Intent(root.getContext(), CreateOnlineAlarmActivity.class);
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(root.getContext(), CreateNewAlarmActivity.class);
                    startActivity(i);
                }
            }
        });

        alarmList.clear();
        readData();
        final LinearLayout ly = root.findViewById(R.id.alarmLayout);
        fly = ly;
        fly.removeAllViews();
        formList(fly);
        if(MeFragment.loginD==true&&MeFragment.loginUser.type==0){
            Response.Listener<String> listener = new Response.Listener<String>(){
                @Override
                public void onResponse(String response) {
                    Log.d("TAG", response);
                    final ArrayList<Alarm> tem = new ArrayList<Alarm>();
                    /*
                    ！！！
                    ！！！
                    ！！！
                    ！！！
                    这里也用到了Online Alarm，也需要改一下
                    ！！！
                    ！！！
                    ！！！
                    ！！！
                     */
                    onlineAlarms = VolleyController.gson.fromJson(response, new TypeToken<ArrayList<OnlineAlarm>>(){}.getType());
                    for(int i=0;i<onlineAlarms.size();i++){
                        int h = getH(onlineAlarms.get(i).time);
                        int m = getM(onlineAlarms.get(i).time);
                        Alarm alarm = new Alarm(onlineAlarms.get(i).time,0,h,m,onlineAlarms.get(i).type);
                        tem.add(alarm);
                    }
                    for(int i=0;i<tem.size();i++){
                        TextView time =  new TextView(getContext());
                        time.setText("  "+tem.get(i).time);
                        time.setTextSize(50);

                        final Switch sw = new Switch(getContext());
                        sw.setChecked(true);

                        final int num = i;
                        sw.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(sw.isChecked()){
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTimeInMillis(System.currentTimeMillis());

                                    calendar.set(Calendar.HOUR_OF_DAY, tem.get(num).hour);
                                    calendar.set(Calendar.MINUTE, tem.get(num).minute);

                                    calendar.set(Calendar.SECOND, 0);
                                    calendar.set(Calendar.MILLISECOND, 0);

                                    Intent intent = new Intent(getContext(), AlertReceiver.class);
                                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
                                    AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(getContext().ALARM_SERVICE);


                                    intent.putExtra("requestCode",num+100);
                                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                                }
                                else{
                                    Intent intent = new Intent(getContext(),AlertReceiver.class);
                                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(),num+100, intent, 0);
                                    AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(getContext().ALARM_SERVICE);
                                    alarmManager.cancel(pendingIntent);
                                }
                            }
                        });
                        sw.setChecked(false);
                        sw.performClick();

                        ly.addView(time);
                        ly.addView(sw);
                    }

                }
            };
            VolleyController.getUserAlarm(getContext(),listener);
        }
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        formList(fly);
    }

    private void formList(LinearLayout ly){
        //LinearLayout ly = findViewById(R.id.alarmLayout);
        ly.removeAllViews();
        final ArrayList<Alarm> aList = alarmList;
        for(int i=0;i<aList.size();i++){
            TextView time =  new TextView(getContext());
            time.setText("  "+aList.get(i).time);
            time.setTextSize(50);

            final Switch sw = new Switch(getContext());
            sw.setChecked(true);

            final int num = i;
            sw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(sw.isChecked()){
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());

                        calendar.set(Calendar.HOUR_OF_DAY, aList.get(num).hour);
                        calendar.set(Calendar.MINUTE, aList.get(num).minute);

                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);

                        Intent intent = new Intent(getContext(), AlertReceiver.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
                        AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(getContext().ALARM_SERVICE);


                        intent.putExtra("requestCode",num);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                    }
                    else{
                        Intent intent = new Intent(getContext(),AlertReceiver.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(),num, intent, 0);
                        AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(getContext().ALARM_SERVICE);
                        alarmManager.cancel(pendingIntent);
                    }
                }
            });
            sw.setChecked(false);

            ly.addView(time);
            ly.addView(sw);
        }
    }

    public void readData(){
        FileInputStream fis = null;

        try {
            fis = getActivity().openFileInput("alarmSave.txt");
            Scanner sc = new Scanner(fis);

            String time;
            int code;
            String type;
            int hour;
            int minute;
            while(sc.hasNext()){
                time = sc.next();
                System.out.println(time);
                code = sc.nextInt();
                System.out.println(code);
                type = sc.next();
                System.out.println(type);
                hour = sc.nextInt();
                System.out.println(hour);
                minute = sc.nextInt();
                System.out.println(minute);

                //int icode = Integer.parseInt(code);
                //int ihour = Integer.parseInt(hour);
                //int iminute = Integer.parseInt(minute);

                Alarm a = new Alarm(time,code,hour,minute,type);
                AlarmFragment.alarmList.add(a);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if(fis != null)
            {
                fis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void formOnlineList(LinearLayout ly){
        //LinearLayout ly = findViewById(R.id.alarmLayout);
        ly.removeAllViews();
        final ArrayList<Alarm> aList = alarmList;
        for(int i=0;i<aList.size();i++){
            TextView time =  new TextView(getContext());
            time.setText("  "+aList.get(i).time);
            time.setTextSize(50);

            final Switch sw = new Switch(getContext());
            sw.setChecked(true);

            final int num = i;
            sw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(sw.isChecked()){
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());

                        calendar.set(Calendar.HOUR_OF_DAY, aList.get(num).hour);
                        calendar.set(Calendar.MINUTE, aList.get(num).minute);

                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);

                        Intent intent = new Intent(getContext(), AlertReceiver.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
                        AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(getContext().ALARM_SERVICE);


                        intent.putExtra("requestCode",num);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                    }
                    else{
                        Intent intent = new Intent(getContext(),AlertReceiver.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(),num, intent, 0);
                        AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(getContext().ALARM_SERVICE);
                        alarmManager.cancel(pendingIntent);
                    }
                }
            });
            sw.setChecked(false);

            ly.addView(time);
            ly.addView(sw);
        }
    }

    private int getH(String s){
        char[] c= s.toCharArray();
        String tem = Character.toString(c[0])+Character.toString(c[1]);
        return Integer.parseInt(tem);
    }
    private int getM(String s){
        char[] c= s.toCharArray();
        String tem = Character.toString(c[3])+Character.toString(c[4]);
        return Integer.parseInt(tem);
    }



}