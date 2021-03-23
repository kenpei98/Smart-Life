package com.example.smartsleep.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.smartsleep.OnlineAlarm;
import com.example.smartsleep.R;
import com.example.smartsleep.User;
import com.example.smartsleep.app.VolleyController;
import com.example.smartsleep.ui.me.MeFragment;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class CreateOnlineAlarmActivity extends AppCompatActivity {
    ArrayList<User> childrenList = new ArrayList<User>();
    public static User selectedUser = null;
    public static boolean online = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_online_alarm);

        final LinearLayout ly = findViewById(R.id.childrenList);

        LinearLayout tem = new LinearLayout(getApplicationContext());
        TextView tx = new TextView(getApplicationContext());
        tx.setText("  Yourself");
        tx.setTextSize(20);
        tx.setTextColor(Color.BLACK);
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateOnlineAlarmActivity.this, CreateNewAlarmActivity.class));
            }
        });
        ImageView im = new ImageView(getApplicationContext());
        Drawable myDrawable = getResources().getDrawable(R.drawable.ic_children);
        im.setImageDrawable(myDrawable);

        tem.addView(im);
        tem.addView(tx);

        ly.addView(tem);

        TextView empty = new TextView(getApplicationContext());
        ly.addView(empty);



        Response.Listener<String> listener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
                childrenList = VolleyController.gson.fromJson(response, new TypeToken<ArrayList<User>>(){}.getType());
                for(int i=0;i<childrenList.size();i++){
                    LinearLayout tem = new LinearLayout(getApplicationContext());
                    TextView tx = new TextView(getApplicationContext());
                    tx.setText("  "+childrenList.get(i).name);
                    tx.setTextSize(20);
                    tx.setTextColor(Color.BLACK);
                    final User temUser = childrenList.get(i);
                    tx.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            selectedUser = temUser;
                            online = true;
                            startActivity(new Intent(CreateOnlineAlarmActivity.this, CreateNewAlarmActivity.class));
                        }
                    });
                    ImageView im = new ImageView(getApplicationContext());
                    Drawable myDrawable = getResources().getDrawable(R.drawable.ic_children);
                    im.setImageDrawable(myDrawable);

                    tem.addView(im);
                    tem.addView(tx);

                    ly.addView(tem);

                    TextView empty = new TextView(getApplicationContext());
                    ly.addView(empty);
                }
            }
        };
        VolleyController.formChildrenList(CreateOnlineAlarmActivity.this,listener);
    }
}
