package com.example.alumno.bdpbankmobileapp;

import android.app.Application;

/**
 * Created by Alumno on 27/05/2015.
 */
public class LoginApplication extends Application {

    private static String username;
    private static String password;

    @Override
    public void onCreate() {
        super.onCreate();
        username = "";
        password = "";
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        LoginApplication.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        LoginApplication.password = password;
    }
}
