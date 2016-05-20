package com.dqproject.optimuscardsv3;

import android.app.Application;

/**
 * Created by David on 23/12/2015.
 */
public class User extends Application{
    private String userid;

    public String getUserId() {
        return userid;
    }

    public void setUserid(String useridvar) {
        this.userid = useridvar;
    }
}
