package com.example.smartsleep.ui.me;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.smartsleep.AlarmActivity;
import com.example.smartsleep.R;
import com.example.smartsleep.User;
import com.example.smartsleep.ui.MessageActivity;
import com.example.smartsleep.ui.RelationshipActivity;
import com.example.smartsleep.ui.accountManage.LoginActivity;
import com.example.smartsleep.ui.alarm.AlarmFragment;

public class MeFragment extends Fragment {

    private MeViewModel meViewModel;
    public static boolean loginD = false;
    public static User loginUser = new User(0," "," "," "," ",0);

    @SuppressLint("FragmentLiveDataObserve")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        meViewModel =
                ViewModelProviders.of(this).get(MeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_me, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        meViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        if(loginD == true){
            Button signOut = root.findViewById(R.id.loginButton);
            signOut.setText("SIGN OUT");
            signOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginD = false;
                    loginUser = new User(0,"","","","",0);
                }
            });
            TextView hint = root.findViewById(R.id.text_loginHint);
            hint.setText("");

            TextView userName = root.findViewById(R.id.userName);
            userName.setText(loginUser.name);
            userName.setTextColor(Color.BLUE);

            ImageView userPicture = root.findViewById(R.id.userPicture);
            Drawable myDrawable = getResources().getDrawable(R.drawable.defaultimage);
            userPicture.setImageDrawable(myDrawable);

            TextView family = root.findViewById(R.id.textView_Family);
            TextView message = root.findViewById(R.id.textView_message);

            if(loginUser.type==0) {
                family.setText("Your parent   >");
            }
            else{
                family.setText("Your family   >");
            }

            message.setText("Recent message  >");

            family.setTextSize(25);
            message.setTextSize(25);

            family.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), RelationshipActivity.class));
                }
            });

            message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), MessageActivity.class));
                }
            });
        }

        if(loginD == false){
            TextView usname = root.findViewById(R.id.userName);
            usname.setText("");
            Button login = root.findViewById(R.id.loginButton);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }
            });

        }
        return root;
    }


}