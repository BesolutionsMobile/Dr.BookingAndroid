package com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.Doctor_Details_Fragment.Controller;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.besolutions.drbookingOriginal.NetworkLayer.Apicalls;
import com.besolutions.drbookingOriginal.NetworkLayer.NetworkInterface;
import com.besolutions.drbookingOriginal.NetworkLayer.ResponseModel;
import com.besolutions.drbookingOriginal.R;
import com.besolutions.drbookingOriginal.Scenario.Dialog_Anim;
import com.besolutions.drbookingOriginal.Scenario.ScenarioAppointment.Controller.Appointment;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.Doctor_Details_Fragment.Model.ModelDate;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.Doctor_Details_Fragment.Model.ModelDoctor_Details;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.Doctor_Details_Fragment.Pattrens.DoctorDetailsAdapter;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.SearchFragment.Controller.Search_Fragment;
import com.besolutions.drbookingOriginal.Scenario.ScenarioHome.Pattrens.IFOnBackPressed;
import com.besolutions.drbookingOriginal.Scenario.ScenarioMapDoctorLocation.Controller.map_doctor_location;
import com.besolutions.drbookingOriginal.Scenario.ScenarioSignIn.Model.Model_Failed;
import com.besolutions.drbookingOriginal.local_data.saved_data;
import com.besolutions.drbookingOriginal.local_data.send_data;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;


public class Doctor_Details_Fragment extends Fragment implements NetworkInterface, IFOnBackPressed {

    private View view;
    TextView title, descripition;
    ImageView fav, doctorDetailsImg, back, map;
    String doctor_id, user_id;
    RatingBar ratingBar;
    ImageView rating;
    Boolean isfav = false;
    TextView name, fees, address, speclist, jobtitle;
    ProgressBar pg;
    ViewSwitcher viewSwitcher;
    int x = 0;
    ModelDate[] modelDates;
    ArrayList<ModelDate> dateArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.doctor_details_fragment, container, false);


        map = view.findViewById(R.id.map);
        map.setImageResource(R.drawable.seemap);
        doctor_id = getArguments().getString("id");
        Log.e("iddoctor", doctor_id);
        name = view.findViewById(R.id.name);
        descripition = view.findViewById(R.id.descripition);
        fees = view.findViewById(R.id.fees);
        address = view.findViewById(R.id.address);
        speclist = view.findViewById(R.id.speclist);
        jobtitle = view.findViewById(R.id.jobtitle);
        back = view.findViewById(R.id.back);
        doctorDetailsImg = view.findViewById(R.id.doctorDetailsImg);
        title = view.findViewById(R.id.products);
        ratingBar = view.findViewById(R.id.ratings);
        rating = view.findViewById(R.id.star);
        pg = view.findViewById(R.id.progressbar);
        viewSwitcher = view.findViewById(R.id.viewswitch);
        title.setText("بيانات الدكتور");
        user_id = saved_data.get_user_id(getContext());
        x = 1;
        new Apicalls(getContext(), Doctor_Details_Fragment.this).doctor_details(doctor_id, user_id);
        pg.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saved_data.get_check_appointment(getContext())) {

                    startActivity(new Intent(getContext(), Appointment.class));
                    send_data.Check_doctor_Appointment(getContext(),false);

                } else {
                    FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new Search_Fragment());
                    fr.commit();
                }
            }
        });
        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(getContext(), R.layout.rating);
            }
        });

        fav = (ImageView) view.findViewById(R.id.fav);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isfav == false) {
                    x = 2;
                    fav.setImageResource(R.drawable.redfavourite);
                    new Apicalls(getContext(), Doctor_Details_Fragment.this).add_favorate(user_id, doctor_id);
                    Dialog_Anim dialog_anim = new Dialog_Anim();
                    dialog_anim.dialog(getContext(), R.layout.like_dialog, .90);
                    isfav = true;
                } else {
                    x = 2;
                    fav.setImageResource(R.drawable.fav);
                    new Apicalls(getContext(), Doctor_Details_Fragment.this).delete_favorate(user_id, doctor_id);
                    Dialog_Anim dialog_anim = new Dialog_Anim();
                    dialog_anim.dialog(getContext(), R.layout.unlike_dialog, .90);
                    isfav = false;
                }
            }
        });

        return view;
    }

    public void dialog(Context context, int resource) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.setContentView(resource);
        int width = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        RatingBar ratingBar = dialog.findViewById(R.id.rating);
        final Button rate = dialog.findViewById(R.id.rate);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, final float rating, boolean fromUser) {
                rate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        x = 3;
                        new Apicalls(getContext(), Doctor_Details_Fragment.this).rate_doctors(user_id, doctor_id, (Math.round(rating)));

                        dialog.dismiss();

                    }
                });
            }
        });
        dialog.show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        pg.setVisibility(View.GONE);

        if (x == 1) {
            Gson gson = new Gson();
            ModelDoctor_Details details = gson.fromJson(model.getResponse(), ModelDoctor_Details.class);
            if (details.getStatus() == 1) {

                viewSwitcher.showNext();
                name.setText(details.getDoctor().getName());
                speclist.setText(details.getDoctor().getJobTitle());
                fees.setText(details.getDoctor().getPrice());
                address.setText(details.getDoctor().getAddress());
                jobtitle.setText(details.getDoctor().getJobTitle());
                ratingBar.setRating(details.getDoctor().getRating());
                descripition.setText(details.getDoctor().getDescription());
                Glide.with(getActivity())
                        .load(details.getDoctor().getImage())
                        .placeholder(R.drawable.drpicture)
                        .into(doctorDetailsImg);
                goToMap(details.getDoctor().getLatitude(), details.getDoctor().getLongitude(), details.getDoctor().getName());
                if (details.getDoctor().getFavorite() == 1) {

                    fav.setImageResource(R.drawable.redfavourite);
                    isfav = true;
                }
                if (details.getDates() != null) {
                    modelDates = details.getDates();
                    for (int i = 0; i < modelDates.length; i++) {

                        ModelDate dates = new ModelDate();
                        dates.setReservationDate(modelDates[i].getReservationDate());
                        dates.setFromTime(modelDates[i].getFromTime());
                        dates.setToTime(modelDates[i].getToTime());
                        dates.setId(modelDates[i].getId());
                        dates.setIdUser(modelDates[i].getIdUser());
                        dateArrayList.add(dates);
                    }
                    RecyclerView recyclerView = view.findViewById(R.id.reservationList);
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                    DoctorDetailsAdapter adabter = new DoctorDetailsAdapter(dateArrayList, getActivity());
                    recyclerView.setAdapter(adabter);

                }


            } else if (details.getStatus() == 2) {
                Gson gson1 = new Gson();
                Model_Failed failed = gson1.fromJson(model.getResponse(), Model_Failed.class);
                Toasty.error(getContext(), "" + failed.getMessage(), Toast.LENGTH_LONG).show();

            } else {
                Gson gson2 = new Gson();
                Model_Failed failed = gson2.fromJson(model.getResponse(), Model_Failed.class);
                Toasty.error(getContext(), "" + failed.getMessage(), Toast.LENGTH_LONG).show();

            }
        } else if (x == 2) {

            Gson gson3 = new Gson();
            Model_Failed fav = gson3.fromJson(model.getResponse(), Model_Failed.class);

            if (fav.getStatus() == 1) {

                Toasty.success(getContext(), "" + fav.getMessage(), Toast.LENGTH_LONG).show();

            } else if (fav.getStatus() == 2) {

                Toasty.error(getContext(), "" + fav.getMessage(), Toast.LENGTH_LONG).show();

            } else {
                Toasty.error(getContext(), "" + fav.getMessage(), Toast.LENGTH_LONG).show();
            }

        } else if (x == 3) {

            Gson gson4 = new Gson();
            Model_Failed rate = gson4.fromJson(model.getResponse(), Model_Failed.class);

            if (rate.getStatus() == 1) {

                Dialog_Anim dialog_anim = new Dialog_Anim();
                dialog_anim.dialog(getContext(), R.layout.rate_dialog, .90);
                Toasty.success(getContext(), "" + rate.getMessage(), Toast.LENGTH_LONG).show();

            } else if (rate.getStatus() == 2) {

                Toasty.error(getContext(), "" + rate.getMessage(), Toast.LENGTH_LONG).show();

            } else {
                Toasty.error(getContext(), "" + rate.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void OnError(VolleyError error) {
        pg.setVisibility(View.GONE);
        Log.e("erroroor", "" + error.toString());
        Toasty.error(getContext(), "Somthing Went Wrong", Toast.LENGTH_LONG).show();

    }


    private void goToMap(final String lat, final String longt, final String name) {
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), map_doctor_location.class);
                intent.putExtra("lat", lat);
                intent.putExtra("longt", longt);
                intent.putExtra("placeName", name);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onBackPressed() {

        if (saved_data.get_check_appointment(getContext())) {

            startActivity(new Intent(getContext(), Appointment.class));
            send_data.Check_doctor_Appointment(getContext(),false);

        } else {
            FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Search_Fragment());
            fr.commit();
        }
        return true;
    }
}
