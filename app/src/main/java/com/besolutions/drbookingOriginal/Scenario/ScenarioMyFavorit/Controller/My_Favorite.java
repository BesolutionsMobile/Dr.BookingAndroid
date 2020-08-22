package com.besolutions.drbookingOriginal.Scenario.ScenarioMyFavorit.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.drbookingOriginal.NetworkLayer.Apicalls;
import com.besolutions.drbookingOriginal.NetworkLayer.NetworkInterface;
import com.besolutions.drbookingOriginal.NetworkLayer.ResponseModel;
import com.besolutions.drbookingOriginal.R;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.SearchFragment.Model.Doctor;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.SearchFragment.Model.Model_SAF;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.SearchFragment.Pattrens.SAF_Adapter;
import com.besolutions.drbookingOriginal.Scenario.ScenarioSignIn.Model.Model_Failed;
import com.besolutions.drbookingOriginal.local_data.saved_data;
import com.google.gson.Gson;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class My_Favorite extends AppCompatActivity implements NetworkInterface {
    TextView title;
    ImageView back;
    String user_id;
    ProgressBar pg;
    Doctor[] doctors;
    ArrayList<Doctor> arrayList = new ArrayList<Doctor>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite);
        back = (ImageView) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.products);
        pg = findViewById(R.id.progressbar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText("مفضلتى");
        user_id = saved_data.get_user_id(this);
        pg.setVisibility(View.VISIBLE);
        new Apicalls(this,My_Favorite.this).my_favorite(user_id);
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        pg.setVisibility(View.GONE);

        Gson gson2 = new Gson();
        Model_SAF modelSaf = gson2.fromJson(model.getResponse(), Model_SAF.class);

        doctors = modelSaf.getDoctors();
        Log.e("doctorsliststsss",""+doctors.length);

        if (modelSaf.getStatus() == 1) {

            for (Doctor value : doctors) {

                Doctor doctor = new Doctor();

                doctor.setFavorite(value.getFavorite());
                if (doctor.getFavorite() == 1)
                {
                    doctor.setIsFav(true);
                }
                doctor.setId(value.getId());
                doctor.setName(value.getName());
                doctor.setAddress(value.getAddress());
                doctor.setImage(value.getImage());
                doctor.setPrice(value.getPrice());
                doctor.setJobTitle(value.getJobTitle());
                doctor.setDescription(value.getDescription());
                doctor.setLongitude(value.getLongitude());
                doctor.setLatitude(value.getLatitude());
                doctor.setRating(value.getRating());

                arrayList.add(doctor);

            }


        } else if (modelSaf.getStatus() == 2) {

            Gson gson1 = new Gson();
            Model_Failed failed = gson1.fromJson(model.getResponse(), Model_Failed.class);
            Toasty.error(this, "" + failed.getMessage(), Toast.LENGTH_LONG).show();

        } else {

            Gson gson3 = new Gson();
            Model_Failed failed1 = gson3.fromJson(model.getResponse(), Model_Failed.class);
            Toasty.error(this, "" + failed1.getMessage(), Toast.LENGTH_LONG).show();
        }

        RecyclerView recyclerView = findViewById(R.id.favList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SAF_Adapter adabter = new SAF_Adapter(this, arrayList, R.layout.favorite_item);
        recyclerView.setAdapter(adabter);
    }





    @Override
    public void OnError(VolleyError error) {
        pg.setVisibility(View.GONE);

    }
}
