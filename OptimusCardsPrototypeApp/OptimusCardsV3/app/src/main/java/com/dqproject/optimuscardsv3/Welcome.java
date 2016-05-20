package com.dqproject.optimuscardsv3;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by David on 21/12/2015.
 */
public class Welcome extends AppCompatActivity
{
    private Button log_out;
    private Button lastTenTrans;
    private Button lastFiftyTrans;
    private TextView textView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        //get the toolbar
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        //set our Toolbar as an ActionBar
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.WHITE);
        toolbar.setLogo(R.drawable.ocglogo);
        //Drawable myIcon = ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_menu_white_18dp);
        //Set Toolbar Navigation icon
        //toolbar.setNavigationIcon(R.drawable.ic_menu_white_18dp);
        //toolbar.setOverflowIcon(myIcon);
        // We use this variable to access our instance of our User class so we can
        //get the userid for the particular user who is logged in
        final User globalVariable = (User) getApplicationContext();

        //textview for our welcome message
        textView = (TextView)findViewById(R.id.textView);
        //Append the userid of the logged in user to our welcome message
        textView.append(" "+globalVariable.getUserId());

        lastTenTrans = (Button) findViewById(R.id.lastTenTrans);
        lastTenTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //prieviously was startActivity(new Intent(getApplicationContext(), RecentTransactions.class));
                startActivity(new Intent(getApplicationContext(), CustomTListActivity.class));
            }
        });

        lastFiftyTrans = (Button) findViewById(R.id.lastFiftyTrans);
        lastFiftyTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //prieviously was startActivity(new Intent(getApplicationContext(), RecentTransactions.class));
                startActivity(new Intent(getApplicationContext(), CustomTListActivity2.class));
            }
        });

        log_out = (Button) findViewById(R.id.logout);
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            }
        });
    }


}
