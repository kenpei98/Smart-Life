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
import com.example.smartsleep.R;
import com.example.smartsleep.Relationship;
import com.example.smartsleep.User;
import com.example.smartsleep.app.VolleyController;
import com.example.smartsleep.ui.accountManage.LoginActivity;
import com.example.smartsleep.ui.me.MeFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class RelationshipActivity extends AppCompatActivity {
    public static ArrayList<User> relationships = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relationship);

        //System.out.println(relationships.get(0).name);

        final LinearLayout ly = findViewById(R.id.relationLayout);
        Response.Listener<String> listener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
                RelationshipActivity.relationships = VolleyController.gson.fromJson(response, new TypeToken<ArrayList<User>>(){}.getType());
                //System.out.println(LoginActivity.user_list.get(0).email);
                ly.removeAllViews();
                for(int i=0;i<relationships.size();i++) {
                    LinearLayout tem = new LinearLayout(getApplicationContext());
                    TextView tx = new TextView(getApplicationContext());
                    tx.setText("  "+relationships.get(i).name);
                    tx.setTextSize(20);
                    tx.setTextColor(Color.BLACK);
                    ImageView im = new ImageView(getApplicationContext());
                    Drawable myDrawable = getResources().getDrawable(R.drawable.ic_children);
                    im.setImageDrawable(myDrawable);

                    tem.addView(im);
                    tem.addView(tx);

                    ly.addView(tem);

                    TextView empty = new TextView(getApplicationContext());
                    ly.addView(empty);

                }

                Button bt = new Button(RelationshipActivity.this);
                ly.addView(bt);
                bt.setText("Request");
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(MeFragment.loginUser.type==0){
                            Intent i = new Intent(RelationshipActivity.this, RequestConfirmActivity.class);
                            startActivity(i);
                        }
                        else if(MeFragment.loginUser.type==1){
                            Intent i = new Intent(RelationshipActivity.this, SendRequestActivity.class);
                            startActivity(i);
                        }
                    }
                });
            }
        };

        VolleyController.sendGetRelationshipRequestToServer(RelationshipActivity.this,listener);

    }
}
