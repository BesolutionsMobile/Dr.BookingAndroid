package com.besolutions.drbookingOriginal.Scenario.ScenarioHome.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import com.besolutions.drbookingOriginal.R;
import com.besolutions.drbookingOriginal.Scenario.ScenarioSignIn.Controller.SignIn;
import com.besolutions.drbookingOriginal.Utils.TinyDB;
import com.besolutions.drbookingOriginal.local_data.saved_data;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_splash_screen);

        tinyDB = new TinyDB(this);


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                saved_data saved_data = new saved_data();
                if (saved_data.get_user_check(SplashScreen.this) == false) {

                    startActivity(new Intent(getApplicationContext(), SignIn.class));
                    finish();

                }
                if (saved_data.get_user_check(SplashScreen.this) == true) {

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        };
        new Timer().schedule(timerTask, 3000);
    }

    @Override
    protected void onResume() {

        super.onResume();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                //startActivity(new Intent(getApplicationContext(), SignIn.class));
                saved_data saved_data = new saved_data();


                if (saved_data.get_user_check(SplashScreen.this) == false) {

                    startActivity(new Intent(getApplicationContext(), SignIn.class));
                    finish();

                }
                if (saved_data.get_user_check(SplashScreen.this) == true) {

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }

            }
        };
        new Timer().schedule(timerTask, 3000);
    }
}
