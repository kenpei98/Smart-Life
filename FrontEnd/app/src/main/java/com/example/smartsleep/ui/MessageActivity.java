package com.example.smartsleep.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.smartsleep.OnlineAlarm;
import com.example.smartsleep.R;
import com.example.smartsleep.User;
import com.example.smartsleep.app.VolleyController;
import com.example.smartsleep.ui.me.MeFragment;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {
    ArrayList<OnlineAlarm> onlineAlarms = new ArrayList<OnlineAlarm>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        final LinearLayout ly = findViewById(R.id.messageLayout);

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
                onlineAlarms = VolleyController.gson.fromJson(response, new TypeToken<ArrayList<OnlineAlarm>>(){}.getType());
                for(int i=0;i<onlineAlarms.size();i++){
                    TextView tx = new TextView(MessageActivity.this);
                    tx.setText(onlineAlarms.get(i).message);
                    ly.addView(tx);

                    TextView etx = new TextView(MessageActivity.this);
                    ly.addView(etx);
                }
            }
        };

        if(MeFragment.loginUser.type==0){
            VolleyController.getUserAlarm(MessageActivity.this,listener);
        }
        else{
            VolleyController.getOuserAlarm(MessageActivity.this,listener);
        }


    }
}
