package com.example.firstuserinterface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends Activity {
    public final static String MESSAGE_KEY = "com.example.firstuserinterface.message_key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MESSAGE_KEY);
        TextView textView = new TextView(this);
        textView.setTextSize(45);
        textView.setText(message);
        setContentView(textView);


    }

}
