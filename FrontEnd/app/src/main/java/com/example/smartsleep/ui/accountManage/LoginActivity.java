package com.example.smartsleep.ui.accountManage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.example.smartsleep.R;
import com.example.smartsleep.User;
import com.example.smartsleep.app.VolleyController;
import com.example.smartsleep.ui.me.MeFragment;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    public static ArrayList<User> user_list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        VolleyController.sendLoginRequestToServer(LoginActivity.this);

        Button register = findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        Button login = findViewById(R.id.userLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailET = findViewById(R.id.userEmail);
                EditText passwordET = findViewById(R.id.userPassword);

                String email = emailET.getText().toString();
                System.out.println("the editText is: "+ email);
                String password = passwordET.getText().toString();

                if(findUser(email)){
                    if(checkPassword(email,password)){
                        Toast.makeText(LoginActivity.this, "Login Successfully." ,Toast.LENGTH_LONG).show();
                        MeFragment.loginD = true;
                        MeFragment.loginUser = getUser(email);
                        finish();
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this, "Email not exist. Please try again." ,Toast.LENGTH_LONG).show();
                    emailET.setText("");
                    passwordET.setText("");
                }
            }
        });

    }

    private boolean findUser(String em){
        //System.out.println("the email is:");
        //System.out.println(user_list.get(0).email);
        for(int i=0;i<user_list.size();i++){
            if(user_list.get(i).email.equalsIgnoreCase(em)){
                return true;
            }
        }
        return false;
    }

    private boolean checkPassword(String em,String pw){
        for(int i=0;i<user_list.size();i++){
            if(user_list.get(i).email.equalsIgnoreCase(em)&&user_list.get(i).password.equalsIgnoreCase(pw)){
                return true;
            }
        }

        return false;
    }

    private User getUser(String em){
        for(int i=0;i<user_list.size();i++){
            if(user_list.get(i).email.equalsIgnoreCase(em)){
                return user_list.get(i);
            }
        }
        return null;
    }
}
