package com.example.smartsleep.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.smartsleep.OnlineAlarm;
import com.example.smartsleep.Relationship;
import com.example.smartsleep.User;
import com.example.smartsleep.ui.RelationshipActivity;
import com.example.smartsleep.ui.accountManage.LoginActivity;
import com.example.smartsleep.ui.accountManage.RegisterActivity;
import com.example.smartsleep.ui.me.MeFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.HashMap;

public class VolleyController {
    public static Gson gson;


    public static boolean checkNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        NetworkInfo netWorkInfo = info[i];
                        if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                            return true;
                        } else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void sendLoginRequestToServer(final Context context)
    {
        if(!checkNetworkAvailable(context)){
            Toast.makeText(context, "Connection Failed, please check your internet." ,Toast.LENGTH_LONG).show();
            return;
        }
        GsonBuilder gb = new GsonBuilder();
        gson = gb.create();

        StringRequest stringRequest = new StringRequest("http://coms-402-sd-3.cs.iastate.edu:8080/users",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        //JsonObject json =  null;
                        Log.d("TAG", response);
                        LoginActivity.user_list = gson.fromJson(response, new TypeToken<ArrayList<User>>(){}.getType());
                        System.out.println(LoginActivity.user_list.get(0).email);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("TAG", error.getMessage(), error);
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public static void sendCheckingRequestToServer(final Context context)
    {
        if(!checkNetworkAvailable(context)){
            Toast.makeText(context, "Connection Failed, please check your internet." ,Toast.LENGTH_LONG).show();
            return;
        }
        GsonBuilder gb = new GsonBuilder();
        gson = gb.create();

        StringRequest stringRequest = new StringRequest("http://coms-402-sd-3.cs.iastate.edu:8080/users",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        //JsonObject json =  null;
                        Log.d("TAG", response);
                        RegisterActivity.userList = gson.fromJson(response, new TypeToken<ArrayList<User>>(){}.getType());
                        System.out.println(RegisterActivity.userList.get(0).email);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("TAG", error.getMessage(), error);
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public static void sendRegisterRequest(Context context, EditText usName, EditText pw, EditText email, Spinner gender, int type){
        if(!checkNetworkAvailable(context)){
            Toast.makeText(context, "Connection Failed, please check your internet." ,Toast.LENGTH_LONG).show();
            return;
        }
        GsonBuilder gb = new GsonBuilder();
        gson = gb.create();

        HashMap<String,String> headers = new HashMap<String,String>();
        headers.put("id","99999");
        headers.put("name",usName.getText().toString().replaceAll("\"",""));
        headers.put("password",pw.getText().toString().replaceAll("\"",""));
        headers.put("email",email.getText().toString().replaceAll("\"",""));
        headers.put("gender",gender.getSelectedItem().toString().replaceAll("\"",""));
        headers.put("type",Integer.toString(type).replaceAll("\"",""));


        JSONObject jsonObject = new JSONObject(headers);

        String url = "http://coms-402-sd-3.cs.iastate.edu:8080/users";

        JsonObjectRequest js = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
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
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(js);
    }
    public static void sendGetRelationshipRequestToServer(final Context context,Response.Listener listener)
    {
        if(!checkNetworkAvailable(context)){
            Toast.makeText(context, "Connection Failed, please check your internet." ,Toast.LENGTH_LONG).show();
            return;
        }
        GsonBuilder gb = new GsonBuilder();
        gson = gb.create();

        String link = "userid/";
        int id = MeFragment.loginUser.id;

        if(MeFragment.loginUser.type==1){
            link = "ouserid/";
        }

        StringRequest stringRequest = new StringRequest("http://coms-402-sd-3.cs.iastate.edu:8080/users/relationship/"+link+id,
                listener,
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("TAG", error.getMessage(), error);
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public static void sendUserRequestToServer(Context context,StringRequest request)
    {
        if(!checkNetworkAvailable(context)){
            Toast.makeText(context, "Connection Failed, please check your internet." ,Toast.LENGTH_LONG).show();
            return;
        }
        GsonBuilder gb = new GsonBuilder();
        gson = gb.create();
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public static void getUserRequestToServer(final Context context,Response.Listener listener) {
        StringRequest stringRequest = new StringRequest("http://coms-402-sd-3.cs.iastate.edu:8080/users",
                listener,
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("TAG", error.getMessage(), error);
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }


    public static void sendEventRequestToServer(Context context,StringRequest request){
        if(!checkNetworkAvailable(context)){
            Toast.makeText(context, "Connection Failed, please check your internet." ,Toast.LENGTH_LONG).show();
            return;
        }
        GsonBuilder gb = new GsonBuilder();
        gson = gb.create();
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public static void sendRelationshipRequest(Context context,User ouser,User nuser){
        if(!checkNetworkAvailable(context)){
            Toast.makeText(context, "Connection Failed, please check your internet." ,Toast.LENGTH_LONG).show();
            return;
        }
        GsonBuilder gb = new GsonBuilder();
        gson = gb.create();

        HashMap<String,String> headers = new HashMap<String,String>();
        headers.put("id","99999");
        headers.put("ouserid",String.valueOf(ouser.id).replaceAll("\"",""));
        headers.put("userid",String.valueOf(nuser.id).replaceAll("\"",""));
        headers.put("approved","0".replaceAll("\"",""));


        JSONObject jsonObject = new JSONObject(headers);

        String url = "http://coms-402-sd-3.cs.iastate.edu:8080/relationship/add";

        JsonObjectRequest js = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
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
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(js);
    }

    public static void getRequestList(final Context context,Response.Listener listener,User user)
    {
        StringRequest stringRequest = new StringRequest("http://coms-402-sd-3.cs.iastate.edu:8080/relationship/userid/"+user.id,
                listener,
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("TAG", error.getMessage(), error);
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public static void ConfirmRelationshipRequest(Context context,Relationship relationship,int approved){
        if(!checkNetworkAvailable(context)){
            Toast.makeText(context, "Connection Failed, please check your internet." ,Toast.LENGTH_LONG).show();
            return;
        }
        GsonBuilder gb = new GsonBuilder();
        gson = gb.create();

        HashMap<String,String> headers = new HashMap<String,String>();
        headers.put("id",String.valueOf(relationship.id).replaceAll("\"",""));
        headers.put("ouserid",String.valueOf(relationship.ouserid).replaceAll("\"",""));
        headers.put("userid",String.valueOf(relationship.userid).replaceAll("\"",""));
        headers.put("approved",String.valueOf(approved).replaceAll("\"",""));


        JSONObject jsonObject = new JSONObject(headers);

        String url = "http://coms-402-sd-3.cs.iastate.edu:8080/relationship/add";

        JsonObjectRequest js = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
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
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(js);
    }

    public static void getOuserAlarm(final Context context,Response.Listener listener)
    {
        if(!checkNetworkAvailable(context)){
            Toast.makeText(context, "Connection Failed, please check your internet." ,Toast.LENGTH_LONG).show();
            return;
        }
        GsonBuilder gb = new GsonBuilder();
        gson = gb.create();

        StringRequest stringRequest = new StringRequest("http://coms-402-sd-3.cs.iastate.edu:8080/users/all/ouserid/"+MeFragment.loginUser.id,
                listener,
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("TAG", error.getMessage(), error);
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public static void getUserAlarm(final Context context,Response.Listener listener)
    {
        if(!checkNetworkAvailable(context)){
            Toast.makeText(context, "Connection Failed, please check your internet." ,Toast.LENGTH_LONG).show();
            return;
        }
        GsonBuilder gb = new GsonBuilder();
        gson = gb.create();

        StringRequest stringRequest = new StringRequest("http://coms-402-sd-3.cs.iastate.edu:8080/alarms/all/"+MeFragment.loginUser.id,
                listener,
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("TAG", error.getMessage(), error);
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public static void formChildrenList(final Context context,Response.Listener listener)
    {
        if(!checkNetworkAvailable(context)){
            Toast.makeText(context, "Connection Failed, please check your internet." ,Toast.LENGTH_LONG).show();
            return;
        }
        GsonBuilder gb = new GsonBuilder();
        gson = gb.create();

        StringRequest stringRequest = new StringRequest("http://coms-402-sd-3.cs.iastate.edu:8080/users/relationship/ouserid/"+MeFragment.loginUser.id,
                listener,
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("TAG", error.getMessage(), error);
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public static void SendOnlineAlarm (Context context, OnlineAlarm alarm){
        if (!checkNetworkAvailable(context)) {
            Toast.makeText(context, "Connection Failed, please check your internet.", Toast.LENGTH_LONG).show();
            return;
        }
        GsonBuilder gb = new GsonBuilder();
        gson = gb.create();

        HashMap<String, Object> headers = new HashMap<>();
        //headers.put("id","99999");
//        headers.put("userid", String.valueOf(alarm.userid).replaceAll("\"", ""));
        headers.put("userid", alarm.userid);
        headers.put("time", String.valueOf(alarm.time).replaceAll("\"", ""));
//        headers.put("ouserid", String.valueOf(alarm.ouserid).replaceAll("\"", ""));
        headers.put("ouserid", alarm.ouserid);
        headers.put("message", String.valueOf(alarm.message).replaceAll("\"", ""));
//        headers.put("type", String.valueOf(alarm.type).replaceAll("\"", ""));
        headers.put("type", 0);

        JSONObject jsonObject = new JSONObject(headers);

        String url = "http://coms-402-sd-3.cs.iastate.edu:8080/alarms/add2";

        JsonObjectRequest js = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage(), error);
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(js);
    }




}
