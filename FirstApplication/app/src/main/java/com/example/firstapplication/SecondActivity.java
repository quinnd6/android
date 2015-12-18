package com.example.firstapplication;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by David on 03/12/2015.
 */
public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
    }
}
