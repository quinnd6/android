package com.dqproject.optimuscardsv3;

import java.util.ArrayList;

/**
 * Created by David on 29/12/2015.
 */

//Transaction Model class
public class Transaction {

    private String id;
    private String dateAndTime;
    private String description;
    private double price;

    public Transaction(String dateAndTime, String description, double price) {


        this.dateAndTime = dateAndTime;
        this.description = description;
        this.price = price;
    }

    public static ArrayList<Transaction> getTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(new Transaction("2015-12-23 15:48:30", "Margerine and butter and crap and loads of other stuff and a partridge in a pair tree", 0.80));
        transactions.add(new Transaction("2015-12-23 15:49:30", "tea", 1.10));
        transactions.add(new Transaction("2015-12-23 15:50:30", "Butter", 1.00));
        transactions.add(new Transaction("2015-12-23 15:51:30", "Bread", 1.29));
        transactions.add(new Transaction("2015-12-23 15:52:30", "Milk", 1.00));
        return transactions;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
