package com.example.smartsleep.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.smartsleep.R;
import com.example.smartsleep.Relationship;
import com.example.smartsleep.User;
import com.example.smartsleep.app.VolleyController;
import com.example.smartsleep.ui.me.MeFragment;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class RequestConfirmActivity extends AppCompatActivity {
    ArrayList<Relationship> relationshipArrayList = new ArrayList<Relationship>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_confirm);

        final LinearLayout ly = findViewById(R.id.confirmList);

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
                relationshipArrayList = VolleyController.gson.fromJson(response, new TypeToken<ArrayList<Relationship>>(){}.getType());
                for(int i=0;i<relationshipArrayList.size();i++){
                    if(relationshipArrayList.get(i).approved==0){
                        final Relationship tem = relationshipArrayList.get(i);
                        TextView tx = new TextView(RequestConfirmActivity.this);
                        tx.setText("observer user "+relationshipArrayList.get(i).ouserid+" send you a request");
                        Button accept = new Button(RequestConfirmActivity.this);
                        accept.setText("accept");
                        accept.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(RequestConfirmActivity.this, "Request Accept" ,Toast.LENGTH_LONG).show();
                                VolleyController.ConfirmRelationshipRequest(RequestConfirmActivity.this,tem,1);
                                finish();
                            }
                        });
                        Button reject = new Button(RequestConfirmActivity.this);
                        reject.setText("reject");
                        reject.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(RequestConfirmActivity.this, "Request Reject" ,Toast.LENGTH_LONG).show();
                                VolleyController.ConfirmRelationshipRequest(RequestConfirmActivity.this,tem,3);
                                finish();
                            }
                        });
                        ly.addView(tx);
                        ly.addView(accept);
                        ly.addView(reject);
                    }
                }
            }
        };
        VolleyController.getRequestList(RequestConfirmActivity.this,listener,MeFragment.loginUser);
    }
}
