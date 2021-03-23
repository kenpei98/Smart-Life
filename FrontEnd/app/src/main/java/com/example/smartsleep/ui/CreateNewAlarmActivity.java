package com.example.smartsleep.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.smartsleep.Alarm;
import com.example.smartsleep.AlarmActivity;
import com.example.smartsleep.AlertReceiver;
import com.example.smartsleep.OnlineAlarm;
import com.example.smartsleep.R;
import com.example.smartsleep.app.VolleyController;
import com.example.smartsleep.ui.accountManage.LoginActivity;
import com.example.smartsleep.ui.alarm.AlarmFragment;
import com.example.smartsleep.ui.me.MeFragment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Calendar;

public class CreateNewAlarmActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_alarm);
        final Calendar calendar = Calendar.getInstance();
        Button next = findViewById(R.id.setTime);



        next.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {


                //Toast.makeText(CreateNewAlarmActivity.this,rb.getText(),Toast.LENGTH_SHORT).show();


                calendar.setTimeInMillis(System.currentTimeMillis());
                new TimePickerDialog(CreateNewAlarmActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker arg0, int hourOfDay, int minute) {
                        Alarm a;
                        if(minute>=10) {
                            /*
                            这里开始出问题的
                            没错就在这里
                            是的是的就是这里
                            怕你看不见
                            弄的明显一点
                            就在这个下一行
                             */
                            a = new Alarm(formatTime(hourOfDay, minute), AlarmFragment.alarmList.size(), hourOfDay, minute, "normal");
                        }
                        else{
                            String time = hourOfDay+":"+"0"+minute;
                            a = new Alarm(time, AlarmFragment.alarmList.size(), hourOfDay, minute, "normal");

                        }
                        if(MeFragment.loginD==true&&MeFragment.loginUser.type==1&&CreateOnlineAlarmActivity.online==true&&CreateOnlineAlarmActivity.selectedUser!=null){
                            String message = MeFragment.loginUser.name+" has set up an alarm for "+CreateOnlineAlarmActivity.selectedUser.name;
//                            int setType;
//                            if(a.type.equals("normal"))
//                                setType = 0;
//                            else
//                                setType = 1;
                            /*
                            !!!
                            !!!
                            !!!
                            !!!
                            !!!
                            错误在这里！！要把online alarm里的type改成int，把normal 转成0，shake转成1，貌似其他AlarmFragment里有用到这里，需要改的地方挺多的  -------
                            !!!                                                                                      这个a.type里存的是normal,没法转成int   |
                            !!!                                                                                                                          |
                            !!!                                                                                                                          |
                            !!!                                                                                                                          |
                            !!!                                                                                                                          |
                            !!!                                                                                                                         \|/
                            !!!                                                                                                                          |
                             */
                            OnlineAlarm oa = new OnlineAlarm(0,CreateOnlineAlarmActivity.selectedUser.id,a.time,MeFragment.loginUser.id,message,a.type);
                            VolleyController.SendOnlineAlarm(CreateNewAlarmActivity.this,oa);
                            Toast.makeText(CreateNewAlarmActivity.this, "Remote Alarm set." ,Toast.LENGTH_LONG).show();

                            finish();

                        }
                        else {
                            calendar.setTimeInMillis(System.currentTimeMillis());
                            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            calendar.set(Calendar.MINUTE, minute);

                            calendar.set(Calendar.SECOND, 0);
                            calendar.set(Calendar.MILLISECOND, 0);

                            RadioGroup rg = findViewById(R.id.type_rb_group);
                            int selectedId = rg.getCheckedRadioButtonId();
                            RadioButton rb;
                            rb = (RadioButton) findViewById(selectedId);

                            Intent i = new Intent(CreateNewAlarmActivity.this, AlertReceiver.class);
                            i.putExtra("requestCode", AlarmFragment.alarmList.size());

                            if (rb.getText().equals("Shake")) {
                                i.putExtra("type", 1);
                            } else {
                                i.putExtra("type", 0);
                            }

                            PendingIntent pendingIntent = PendingIntent.getBroadcast(CreateNewAlarmActivity.this, AlarmFragment.alarmList.size(), i, 0);

                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                            //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),10*1000,pendingIntent);

                            AlarmFragment.alarmList.add(a);
                            writeData();
                            finish();
                        }
                    }
                },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();

            }

            private String formatTime(int h,int m){
                String s = h+":"+m;
                return s;
            }

            private void writeData() {
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput("alarmSave.txt",MODE_PRIVATE);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                for(int i=0;i<AlarmFragment.alarmList.size();i++){
                    try {
                        //System.out.println(AlarmFragment.alarmList.get(i).time.getBytes());
                        //fos.write(AlarmFragment.alarmList.get(i).time.getBytes());
                        //fos.write(AlarmFragment.alarmList.get(i).code);
                        //fos.write(AlarmFragment.alarmList.get(i).type.getBytes());
                        //fos.write(AlarmFragment.alarmList.get(i).hour);
                        //fos.write(AlarmFragment.alarmList.get(i).minute);
                        bw.write(AlarmFragment.alarmList.get(i).time);
                        bw.newLine();
                        bw.write(String.valueOf(AlarmFragment.alarmList.get(i).code));
                        bw.newLine();
                        bw.write(AlarmFragment.alarmList.get(i). type);
                        bw.newLine();
                        bw.write(String.valueOf(AlarmFragment.alarmList.get(i).hour));
                        bw.newLine();
                        bw.write(String.valueOf(AlarmFragment.alarmList.get(i).minute));
                        bw.newLine();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }



}
