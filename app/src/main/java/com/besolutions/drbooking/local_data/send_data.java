package com.besolutions.drbooking.local_data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class send_data {

    public static void userId_check(Context context , boolean value)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("loged",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("islogin",value);
        editor.commit();
    }

    public static void SET_USER_ID(Context context, String user_id)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("user_id",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("user_id", user_id);
        editor.commit();
    }

    public static void SET_DOCTOR_ID(Context context, String doctor_id)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("doctor_id",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("doctor_id", doctor_id);
        editor.commit();
    }

    public static void check_fav(Context context , boolean value)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("fav",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("isFav",value);
        editor.commit();
    }
    public static void SET_TEXT_SEARCH(Context context, String text_search)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("text_search",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("text_search", text_search);
        editor.commit();
    }

    public static void Check_doctor_Appointment(Context context , boolean value)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("appointment",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("appointment",value);
        editor.commit();
    }


}
