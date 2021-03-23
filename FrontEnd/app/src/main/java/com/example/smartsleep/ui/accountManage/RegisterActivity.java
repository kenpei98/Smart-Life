package com.example.smartsleep.ui.accountManage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.smartsleep.R;
import com.example.smartsleep.User;
import com.example.smartsleep.app.VolleyController;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static User newUser = new User(0,"","","","",0);
    public static ArrayList<User> userList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        VolleyController.sendCheckingRequestToServer(RegisterActivity.this);

        Spinner gender = findViewById(R.id.spinner_gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.gender,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);
        gender.setOnItemSelectedListener(this);

        Spinner accountType = findViewById(R.id.spinner_accountType);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.accountType,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountType.setAdapter(adapter2);
        accountType.setOnItemSelectedListener(this);


        final EditText email = findViewById(R.id.editText_Email);
        final EditText password = findViewById(R.id.editText_password);
        //EditText passwordConfirm = findViewById(R.id.editText_passwordConfirm);
        final EditText userName = findViewById(R.id.editText_userName);
        final Spinner gd = findViewById(R.id.spinner_gender);
        //Spinner ty = findViewById(R.id.spinner_accountType);

        Button register = findViewById(R.id.button_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputCorrect()){
                    Toast.makeText(RegisterActivity.this, "Sign up successfully." ,Toast.LENGTH_LONG).show();
                    VolleyController.sendRegisterRequest(RegisterActivity.this,userName,password,email,gd,newUser.type);
                    finish();
                }
            }
        });
    }

    private boolean inputCorrect(){
        EditText email = findViewById(R.id.editText_Email);
        EditText password = findViewById(R.id.editText_password);
        EditText passwordConfirm = findViewById(R.id.editText_passwordConfirm);
        EditText userName = findViewById(R.id.editText_userName);
        Spinner gd = findViewById(R.id.spinner_gender);
        Spinner ty = findViewById(R.id.spinner_accountType);

        if(emailAlreadyExist(email.getText().toString())){
            Toast.makeText(RegisterActivity.this, "email already exists." ,Toast.LENGTH_LONG).show();
            return false;
        }
        else if(!password.getText().toString().equals(passwordConfirm.getText().toString())){
            Toast.makeText(RegisterActivity.this, "password not correct." ,Toast.LENGTH_LONG).show();
            return false;
        }
        else if(gd.getSelectedItem().toString().equals("Choose your gender")){
            Toast.makeText(RegisterActivity.this, "Please input your gender." ,Toast.LENGTH_LONG).show();
            return false;
        }
        else if(ty.getSelectedItem().toString().equals("Choose your account type")){
            Toast.makeText(RegisterActivity.this, "Please input your account type." ,Toast.LENGTH_LONG).show();
            return false;
        }

        int type = 0;
        if(ty.getSelectedItem().toString().equals("parent")){
            type = 1;
        }
        newUser = new User(0,userName.getText().toString(),password.getText().toString(),gd.getSelectedItem().toString(),email.getText().toString(),type);
        return true;
    }

    private boolean emailAlreadyExist(String em){
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
