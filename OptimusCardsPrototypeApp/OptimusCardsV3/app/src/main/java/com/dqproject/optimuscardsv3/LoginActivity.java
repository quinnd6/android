package com.dqproject.optimuscardsv3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText userid, password;
    private Button login;
    private RequestQueue requestQueue;
    //private static final String URL ="http://192.168.1.3/optimus/user_control.php";
    private static final String URL ="http://dqphpapps.16mb.com/optimus/user_control.php";
    private StringRequest request;
    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Retrieve login fields and button from the activity_login.xml login form
        userid = (EditText) findViewById(R.id.userid);
        password = (EditText) findViewById(R.id.password);
        login= (Button) findViewById(R.id.login);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        //set our Toolbar as an ActionBar
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.WHITE);
        toolbar.setLogo(R.drawable.ocglogo);
        // We will use this variable to store the userid for the user
        final User globalVariable = (User) getApplicationContext();






        requestQueue = Volley.newRequestQueue(this);

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            //if login is successful ie. our php script returns a json result of success
                            //then we send the user to the welcome screen and set the global variable for the user
                            if(jsonObject.names().get(0).equals("success"))
                            {
                                //Here we set the global variable to the userid so it can be used in all activities
                                //in our application
                                globalVariable.setUserid(userid.getText().toString());

                                Toast.makeText(getApplicationContext(), "LOGIN SUCCESSFUL " + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Welcome.class));
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Error " + jsonObject.getString("error"),Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //other catches
                        //CHECK FOR NO INTERNET CONNECTION
                        if(error instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(), "No Network Available", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        hashMap.put("userid",userid.getText().toString());
                        hashMap.put("password",password.getText().toString());
                        return hashMap;
                    }
                };

                requestQueue.add(request);
            }
        });
    }
}
