package com.dqproject.optimuscardsv3;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by David on 30/12/2015.
 */

public class CustomTransactionsAdapter extends ArrayAdapter<Transaction> {
    List list = new ArrayList();
        public CustomTransactionsAdapter(Context context, ArrayList<Transaction> transactions) {
            super(context, 0, transactions);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Transaction transaction = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.transaction_template, parent, false);
            }
            // Lookup view for data population
            TextView tDateAndTime = (TextView) convertView.findViewById(R.id.tDateAndTime);
            TextView tDescription = (TextView) convertView.findViewById(R.id.tDescription);
            TextView tPrice = (TextView) convertView.findViewById(R.id.tPrice);
            // Populate the data into the template view using the data object
            tDateAndTime.setText(transaction.getDateAndTime());
            tDescription.setText(transaction.getDescription());
           // Locale locale = Resources.getSystem().getConfiguration().locale;
            //Use Irish locale for currency
            Locale locale = new Locale("en", "IE");
            NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
            String moneyString = formatter.format(transaction.getPrice());
            tPrice.setText(moneyString);

            // Return the completed view to render on screen
            return convertView;
        }

        public void add(Transaction object) {
            super.add(object);
            list.add(object);
        }
    }


