package com.example.smartsleep.ui.calendar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.smartsleep.Alarm;
import com.example.smartsleep.Event;
import com.example.smartsleep.R;
import com.example.smartsleep.User;
import com.example.smartsleep.app.MySingleton;
import com.example.smartsleep.app.VolleyController;
import com.example.smartsleep.ui.me.MeFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

import static android.content.Context.MODE_PRIVATE;

public class CalendarFragment extends Fragment {

    private Gson gson;
    private CalendarViewModel calendarViewModel;
    private View root;
    private ArrayList<Event> event_list = new ArrayList<>();
    private ArrayList<Event> event_online = new ArrayList<>();
    private int count = 1;
    private String dateToday = "";
    private ArrayList<User> user_list = new ArrayList<>();
    private boolean for_others = false;
    private int user_id;

    @SuppressLint("FragmentLiveDataObserve")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel =
                ViewModelProviders.of(this).get(CalendarViewModel.class);
        root = inflater.inflate(R.layout.fragment_calendar, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        CalendarView cv = (CalendarView) root.findViewById(R.id.Calendar);
        calendarViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Calendar time = Calendar.getInstance();
        int year = time.get(Calendar.YEAR);
        int month = time.get(Calendar.MONTH) + 1;//注意要+1，0表示1月份
        int day = time.get(Calendar.DAY_OF_MONTH);
        dateToday = year + "-" + month + "-" + day;

        Button event = (Button) root.findViewById(R.id.EventAdd);
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getContext(),NewEventActivity.class);
//                startActivity(intent);
                if (MeFragment.loginUser.type == 1)
                    choice_option(root);
                else
                    event_edit(root);
            }

        });

        final CalendarView.OnDateChangeListener mySelectDate = new CalendarView.OnDateChangeListener() {
            LinearLayout ln = (LinearLayout) root.findViewById(R.id.event_list);

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                dateToday = year+"-"+(month+1)+"-"+dayOfMonth;
                //得把用别的日期查出来的日程删除并将其隐藏
                ln.removeAllViews();
                count = 1;
                if(MeFragment.loginD)
                    show_event_by_date(dateToday);
                else
                    readData(dateToday);
            }
        };
        cv.setOnDateChangeListener(mySelectDate);
        return root;
    }

    private void choice_option(View view) {
        new AlertDialog.Builder(getContext()).setTitle("New Event")
                .setIcon(android.R.drawable.sym_def_app_icon)
                .setMessage("Choose if you want to set event for yourself or other user")
                .setPositiveButton("Myself", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for_others = false;
                        event_edit(root);
                    }
                })
                .setNeutralButton("Other user", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for_others = true;
                        user_choose(root);
                    }
                }).setNegativeButton("Cancel", null).show();
    }

    private void user_choose(View view) {
        GsonBuilder gb = new GsonBuilder();
        gson = gb.create();
        String url = "http://coms-402-sd-3.cs.iastate.edu:8080/users/relationship/ouserid/" + MeFragment.loginUser.id;
        StringRequest stringUserRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonObject json = null;
                        Log.d("TAG", response);
                        user_list = gson.fromJson(response, new TypeToken<ArrayList<User>>() {}.getType());
//                        String st1,st2,st3,st4,st5,st6,st7,st8,st9;
//                        st1 = user_list.get(0).name;
//                        st2 = user_list.get(1).name;
                        if(user_list.size() == 0){
                            Toast.makeText(getContext(),"You do not have any family member!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        String[] users = new String[user_list.size()];
                        for(int i = 0; i < user_list.size(); i++)
                            users[i] = user_list.get(i).name;
                        new AlertDialog.Builder(getContext()).setTitle("Choose User")
                                .setIcon(android.R.drawable.sym_def_app_icon)
                                .setSingleChoiceItems(users, 0, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        user_id = user_list.get(which).id;
                                    }
                                })
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        event_edit(root);
                                    }
                                })
                                .setNegativeButton("Cancel", null).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage(), error);
                    }
                });
        VolleyController.sendUserRequestToServer(getContext(), stringUserRequest);

    }

    private void event_edit(View view) {
        final EditText et = new EditText(getContext());
        final LinearLayout ln = (LinearLayout) root.findViewById(R.id.event_list);

        new AlertDialog.Builder(getContext()).setTitle("New Event")
                .setIcon(android.R.drawable.sym_def_app_icon)
                .setView(et)
                .setPositiveButton("Finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //按下确定键后的事件
                        if(!for_others)
                        {
                            TextView tv = new TextView(getContext());
                            String s = "Event " + count + ": " + et.getText().toString();
                            tv.setText(s);
                            tv.setTextSize(35);
                            ln.addView(tv);
                            count++;
                        }
                        if(MeFragment.loginD){
                            if(for_others){
                                add_event(dateToday,et.getText().toString(),user_id);
                                for_others = false;
                            }
                            else
                                add_event(dateToday,et.getText().toString(),MeFragment.loginUser.id);
                        }
                        else{
                            Event event = new Event(0,0,dateToday,et.getText().toString());
                            event_list.add(event);
                            for(int a = 0; a < event_list.size(); a++)
                                System.out.println(event_list.get(a).time + " " + event_list.get(a).contains);
                            writeData();
                        }
                    }
                }).setNegativeButton("Cancel", null).show();
    }

    private void show_event_by_date(String date) {
        final LinearLayout ln = (LinearLayout) root.findViewById(R.id.event_list);
        GsonBuilder gb = new GsonBuilder();
        gson = gb.create();
        String url = "http://coms-402-sd-3.cs.iastate.edu:8080/event/" + MeFragment.loginUser.id;
        StringRequest stringEventRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonObject json = null;
                        Log.d("TAG", response);
                        event_online = gson.fromJson(response, new TypeToken<ArrayList<Event>>() {}.getType());
                        for (int i = 0; i < event_online.size(); i++) {
                            if (event_online.get(i).time.equals(dateToday)) {
                                TextView tv = new TextView(getContext());
                                String s = "Event " + count + ": " + event_online.get(i).contains;
                                tv.setText(s);
                                tv.setTextSize(35);
                                ln.addView(tv);
                                count++;
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage(), error);
                    }
                });
        VolleyController.sendEventRequestToServer(getContext(), stringEventRequest);
    }

    private void add_event(String date, String event, int userId) {
        GsonBuilder gb = new GsonBuilder();
        gson = gb.create();
        final HashMap<String, Object> headers = new HashMap<>();
//        headers.put("id", 1);
        headers.put("userid",userId);
        headers.put("time",date);
        headers.put("event",event);
        JSONArray ja = new JSONArray();
        ja.put(headers);
        JSONObject jsonObject = new JSONObject(headers);
        String uRL = "http://coms-402-sd-3.cs.iastate.edu:8080/event/add";
        JsonObjectRequest js = new JsonObjectRequest(Request.Method.POST, uRL, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG",response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG",error.getMessage(),error);
                        System.out.println("send wrong");
                    }
                });
        MySingleton.getInstance(getContext()).addToRequestQueue(js);
    }


    private void writeData() {
        FileOutputStream fos = null;
        try {
            fos = getActivity().openFileOutput("eventSave.txt", MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        for(int i = 0; i < event_list.size(); i++)
        {
            try {
                bw.write(event_list.get(i).time + "\n");
                bw.write(event_list.get(i).contains + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bw.flush();
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void readData(String date)
    {
        final LinearLayout ln = (LinearLayout) root.findViewById(R.id.event_list);
        FileInputStream fis = null;
        try {
            fis = getActivity().openFileInput("eventSave.txt");
            Scanner sc = new Scanner(fis);
            String dates;
            String event;
            event_list = new ArrayList<>();
            while(sc.hasNextLine()){
                dates = sc.nextLine();
                event = sc.nextLine();
                Event nevent = new Event(0,0,dates,event);
                event_list.add(nevent);
                if(dates.equals(dateToday)){
                    TextView tv = new TextView(getContext());
                    String s = "Event " + count + ": " + event;
                    tv.setText(s);
                    tv.setTextSize(35);
                    ln.addView(tv);
                    count++;
                }
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

}