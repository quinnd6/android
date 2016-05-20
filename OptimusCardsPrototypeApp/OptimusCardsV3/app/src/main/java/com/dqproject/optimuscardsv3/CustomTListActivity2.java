package com.dqproject.optimuscardsv3;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by David on 30/12/2015.
 */
public class CustomTListActivity2 extends AppCompatActivity {

    private static final String url ="http://dqphpapps.16mb.com/optimus/transactions2.php";
    private RequestQueue requestQueue;
    private StringRequest request;
    private CustomTransactionsAdapter transactionAdapter;
    private Button log_out;
    private Toolbar toolbar;
    private double totalSpent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        //set our Toolbar as an ActionBar
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.WHITE);
        toolbar.setLogo(R.drawable.ocglogo);
        //populateUsersList();
        displayTransactions();
    }

    private void populateUsersList() {
        // Construct the data source
        ArrayList<Transaction> arrayOfTransactions = Transaction.getTransactions();
        // Create the adapter to convert the array to views
        CustomTransactionsAdapter adapter = new CustomTransactionsAdapter(this, arrayOfTransactions);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lvTransactions);

        listView.setAdapter(adapter);
    }





    //Method to try and display the json transaction data
    private void displayTransactions()
    {
        final ArrayList<Transaction> arrayOfTransactions = new ArrayList<>();
        final User globalVariable = (User) getApplicationContext();
        final CustomTransactionsAdapter adapter = new CustomTransactionsAdapter(this, arrayOfTransactions);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray transactions = jsonObject.getJSONArray("transactions");
                    for (int i = 0; i < transactions.length(); i++) {
                        JSONObject transaction = transactions.getJSONObject(i);

                        String dateandtime = transaction.getString("time");
                        String description = transaction.getString("description");
                        double price = transaction.getDouble("price");
                        totalSpent = totalSpent + price;
                        Transaction transaction1 = new Transaction(dateandtime,description,price);
                        arrayOfTransactions.add(transaction1);
                        System.out.println(transaction1);

                    }

                    System.out.println(arrayOfTransactions.get(0).getDescription());
                    // Attach the adapter to a ListView
                    ListView listView = (ListView) findViewById(R.id.lvTransactions);
                    // Inflate header view
                    ViewGroup headerView = (ViewGroup)getLayoutInflater().inflate(R.layout.header, listView,false);
                    ViewGroup footerView = (ViewGroup)getLayoutInflater().inflate(R.layout.footer, listView,false);
                    // Add header view to the ListView
                    listView.addHeaderView(headerView);
                    listView.addFooterView(footerView);
                    listView.setAdapter(adapter);
                    TextView textView = (TextView)findViewById(R.id.tv1);
                    textView.append(" "+globalVariable.getUserId());
                    TextView totalPriceView = (TextView)findViewById(R.id.totalPrice);
                    //Convert totalSpent variable for total money spent on transactions to a currency value
                    // and put it into totalMoney so for Ireland that will be the total in euros
                    Locale locale = new Locale("en", "IE");
                    NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
                    String totalMoney = formatter.format(totalSpent);
                    System.out.println(totalMoney);
                    totalPriceView.setText(totalMoney);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d(TAG, "Error: " + error.getMessage());
                // pDialog.hide();
                System.out.println("ERROR "+error);
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("userid",  globalVariable.getUserId());
                params.put("numOfTransactions",  "50");
                return params;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                if (response.headers == null)
                {
                    // cant just set a new empty map because the member is final.
                    response = new NetworkResponse(
                            response.statusCode,
                            response.data,
                            Collections.<String, String>emptyMap(), // this is the important line, set an empty but non-null map.
                            response.notModified,
                            response.networkTimeMs);


                }

                return super.parseNetworkResponse(response);
            }

        };


        requestQueue.add(jsonObjectRequest);
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
