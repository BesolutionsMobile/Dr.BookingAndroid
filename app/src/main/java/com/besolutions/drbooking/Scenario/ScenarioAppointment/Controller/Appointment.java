package com.besolutions.drbooking.Scenario.ScenarioAppointment.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.drbooking.NetworkLayer.Apicalls;
import com.besolutions.drbooking.NetworkLayer.NetworkInterface;
import com.besolutions.drbooking.NetworkLayer.ResponseModel;
import com.besolutions.drbooking.R;
import com.besolutions.drbooking.Scenario.ScenarioAppointment.Model.ModelReservation;
import com.besolutions.drbooking.Scenario.ScenarioAppointment.Model.ModelReservation_Details;
import com.besolutions.drbooking.Scenario.ScenarioAppointment.Pattrens.ReservationAdapter;
import com.besolutions.drbooking.Scenario.ScenarioFragments.SearchFragment.Pattrens.SAF_Adapter;
import com.besolutions.drbooking.Scenario.ScenarioSignIn.Model.Model_Failed;
import com.besolutions.drbooking.local_data.saved_data;
import com.google.gson.Gson;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class Appointment extends AppCompatActivity implements NetworkInterface {
    ImageView back;
    TextView title;
    String user_id;
    ProgressBar pg;
    ModelReservation[] reservations;
    ArrayList<ModelReservation> reservationsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        title = (TextView) findViewById(R.id.products);
        pg = findViewById(R.id.progressbar);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText("حجوزاتى");
        user_id = saved_data.get_user_id(this);
        pg.setVisibility(View.VISIBLE);
        new Apicalls(this, Appointment.this).appointments(user_id);
    }


    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        pg.setVisibility(View.GONE);
        Gson gson = new Gson();
        ModelReservation_Details modelReservationDetails = gson.fromJson(model.getResponse(), ModelReservation_Details.class);
        reservations = modelReservationDetails.getReservations();
        if (modelReservationDetails.getStatus() == 1) {

            for (int i = 0; i < reservations.length; i++) {
                ModelReservation reservation = new ModelReservation();

                reservation.setDoctor(reservations[i].getDoctor());
                reservation.setJobTitle(reservations[i].getJobTitle());
                reservation.setPrice(reservations[i].getPrice());
                reservation.setPlace(reservations[i].getPlace());
                reservation.setTimeFrom(reservations[i].getTimeFrom());
                reservation.setTimeTo(reservations[i].getTimeTo());
                reservation.setDate(reservations[i].getDate());
                reservation.setDoctorId(reservations[i].getDoctorId());
                reservation.setRate(reservations[i].getRate());
                reservation.setLatitude(reservations[i].getLatitude());
                reservation.setLongitude(reservations[i].getLongitude());
                reservation.setImage(reservations[i].getImage());

                reservationsList.add(reservation);

            }


        } else if (modelReservationDetails.getStatus() == 2) {

            Gson gson1 = new Gson();
            Model_Failed failed = gson1.fromJson(model.getResponse(), Model_Failed.class);
            Toasty.error(this, "" + failed.getMessage(), Toast.LENGTH_LONG).show();

        } else {

            Gson gson3 = new Gson();
            Model_Failed failed1 = gson3.fromJson(model.getResponse(), Model_Failed.class);
            Toasty.error(this, "" + failed1.getMessage(), Toast.LENGTH_LONG).show();


        }


        RecyclerView recyclerView = findViewById(R.id.appoienmentslist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ReservationAdapter adabter = new ReservationAdapter(reservationsList, this);
        recyclerView.setAdapter(adabter);

    }

    @Override
    public void OnError(VolleyError error) {
        pg.setVisibility(View.GONE);

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

}
