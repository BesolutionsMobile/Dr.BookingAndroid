package com.besolutions.drbooking.Scenario.ScenarioMapDoctorLocation.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.besolutions.drbooking.R;
import com.google.android.gms.maps.GoogleMap;

public class map_doctor_location extends AppCompatActivity {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_doctor_location);

        goToMap();
        finish();

    }

    public void goToMap() {
        Intent intent = getIntent();
        String lat = intent.getStringExtra("lat");
        Double latt;
        String longt = intent.getStringExtra("longt");
        Double longg;
        String getName = intent.getStringExtra("placeName");

        if (lat.equals("") && longt.equals("")) {
            latt = 0.0;
            longg = 0.0;

        } else {
            latt = Double.valueOf(lat);
            longg = Double.valueOf(longt);
        }
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + latt + "," + longg + "?z=zoom");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }

}
