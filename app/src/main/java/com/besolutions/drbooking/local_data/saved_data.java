package com.besolutions.drbooking.local_data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class saved_data {

    public boolean get_user_check(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("loged",MODE_PRIVATE);
        boolean value=sharedPreferences.getBoolean("islogin",false);
        return value;
    }

    public static String get_user_id(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("user_id",MODE_PRIVATE);
        String user_id=sharedPreferences.getString("user_id","0");
        return user_id;
    }

    public static String get_doctor_id(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("doctor_id",MODE_PRIVATE);
        String doctor_id=sharedPreferences.getString("doctor_id","0");
        return doctor_id;
    }

    public boolean get_check_fav(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("fav",MODE_PRIVATE);
        boolean value=sharedPreferences.getBoolean("isFav",false);
        return value;
    }

    public static String get_text_search(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("text_search",MODE_PRIVATE);
        String text_search=sharedPreferences.getString("text_search","0");
        return text_search;
    }

    public static boolean get_check_appointment(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("appointment",MODE_PRIVATE);
        boolean value=sharedPreferences.getBoolean("appointment",false);
        return value;
    }
}
