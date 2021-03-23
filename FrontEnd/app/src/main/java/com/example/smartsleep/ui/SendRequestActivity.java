package com.example.smartsleep.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.smartsleep.R;
import com.example.smartsleep.User;
import com.example.smartsleep.app.VolleyController;
import com.example.smartsleep.ui.accountManage.LoginActivity;
import com.example.smartsleep.ui.me.MeFragment;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class SendRequestActivity extends AppCompatActivity {
    ArrayList<User> userArrayList = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_request);
        final EditText et = findViewById(R.id.editText_mail);
        Button search = findViewById(R.id.button_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", response);
                        userArrayList = VolleyController.gson.fromJson(response, new TypeToken<ArrayList<User>>(){}.getType());
                        if(searchUser(et.getText().toString())){
                            VolleyController.sendRelationshipRequest(SendRequestActivity.this,MeFragment.loginUser,getUser(et.getText().toString()));
                            Toast.makeText(SendRequestActivity.this, "Request Sent" ,Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else{
                            Toast.makeText(SendRequestActivity.this, "User not exist, Please try again" ,Toast.LENGTH_LONG).show();
                        }
                    }
                };
                VolleyController.getUserRequestToServer(SendRequestActivity.this,listener);
            }
        });
    }

    private boolean searchUser(String em){
        for(int i=0;i<userArrayList.size();i++){
            if(userArrayList.get(i).email.equalsIgnoreCase(em)){
                return true;
            }
        }
        return false;
    }

    private User getUser(String em){
        for(int i=0;i<userArrayList.size();i++){
            if(userArrayList.get(i).email.equalsIgnoreCase(em)){
                return userArrayList.get(i);
            }
        }
        return null;
    }
}
