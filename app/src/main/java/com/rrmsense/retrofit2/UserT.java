package com.rrmsense.retrofit2;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Talha on 5/8/2017.
 */

class UserT {
    @SerializedName("username")
    String userName;
    @SerializedName("password")
    String password;

    public UserT() {
    }

    public UserT(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
