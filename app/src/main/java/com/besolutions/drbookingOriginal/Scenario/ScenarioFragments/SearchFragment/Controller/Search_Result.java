package com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.SearchFragment.Controller;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.besolutions.drbookingOriginal.NetworkLayer.Apicalls;
import com.besolutions.drbookingOriginal.NetworkLayer.NetworkInterface;
import com.besolutions.drbookingOriginal.NetworkLayer.ResponseModel;
import com.besolutions.drbookingOriginal.R;
import com.besolutions.drbookingOriginal.Scenario.ScenarioConnection.Controller.checkConnection;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.SearchFragment.Model.Doctor;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.SearchFragment.Model.Model_SAF;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.SearchFragment.Pattrens.SAF_Adapter;
import com.besolutions.drbookingOriginal.Scenario.ScenarioHome.Controller.MainActivity;
import com.besolutions.drbookingOriginal.Scenario.ScenarioHome.Pattrens.IFOnBackPressed;
import com.besolutions.drbookingOriginal.Scenario.ScenarioSignIn.Model.Model_Failed;
import com.besolutions.drbookingOriginal.local_data.saved_data;
import com.google.gson.Gson;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class Search_Result extends Fragment implements NetworkInterface, IFOnBackPressed {

    private View view;
    TextView tasneef;
    ViewSwitcher viewSwitcher;
    Button noConnection;
    checkConnection checkCon;
    RecyclerView doctorList;
    String search_text, id, skip;
    Doctor[] doctors;
    ProgressBar pg;
    ArrayList<Doctor> arrayList = new ArrayList<Doctor>();
    private int x = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_result, container, false);

        tasneef = view.findViewById(R.id.sortlist);
        viewSwitcher = view.findViewById(R.id.viewswitch);
        checkCon = new checkConnection();
        doctorList = view.findViewById(R.id.doctorlist);
        pg = view.findViewById(R.id.progressbar);
        id = saved_data.get_user_id(getContext());
        Log.e("User_id", id);

        if (saved_data.get_text_search(getContext()) != null) {

            if (saved_data.get_text_search(getContext()).equals("0")) {
                x = 2;
                skip = saved_data.get_text_search(getContext());
                Log.e("skiptext1", skip);
                new Apicalls(getContext(), Search_Result.this).get_all_doctor(id);
                pg.setVisibility(View.VISIBLE);
            } else {
                x = 1;
                search_text = saved_data.get_text_search(getContext());
                search_text = search_text.replaceAll(" " ,  "%20");
                pg.setVisibility(View.VISIBLE);
                new Apicalls(getContext(), Search_Result.this).search_result(search_text, "rate", id);
            }

        }
        tasneef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(getContext(), R.layout.filter);

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
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        dialog.show();
        Log.e("searchrtext", search_text);
        RadioButton mostRated = dialog.findViewById(R.id.mostrated);
        mostRated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = 1;
                pg.setVisibility(View.VISIBLE);
                new Apicalls(getContext(), Search_Result.this).search_result(search_text, "rate", id);
                dialog.dismiss();
            }
        });
        RadioButton lowestprice = dialog.findViewById(R.id.lowestprice);
        lowestprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = 1;
                pg.setVisibility(View.VISIBLE);
                new Apicalls(getContext(), Search_Result.this).search_result(search_text, "price_asc", id);
                dialog.dismiss();
            }
        });
        RadioButton highestprice = dialog.findViewById(R.id.highestprice);
        highestprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = 1;
                pg.setVisibility(View.VISIBLE);
                new Apicalls(getContext(), Search_Result.this).search_result(search_text, "price_desc", id);
                dialog.dismiss();

            }
        });
        Button close = dialog.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
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
            Model_Failed result = gson.fromJson(model.getResponse(), Model_Failed.class);
            if (result.getStatus() == 1) {
                Toasty.success(getContext(), "" + result.getMessage(), Toast.LENGTH_LONG).show();

            } else if (result.getStatus() == 2) {
                Toasty.error(getContext(), "" + result.getMessage(), Toast.LENGTH_LONG).show();

            } else {
                Toasty.error(getContext(), "" + result.getMessage(), Toast.LENGTH_LONG).show();
            }

        } else if (x == 2) {

            viewSwitcher.showNext();
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
                Toasty.error(getContext(), "" + failed.getMessage(), Toast.LENGTH_LONG).show();

            } else {

                Gson gson3 = new Gson();
                Model_Failed failed1 = gson3.fromJson(model.getResponse(), Model_Failed.class);
                Toasty.error(getContext(), "" + failed1.getMessage(), Toast.LENGTH_LONG).show();
            }


        }

        RecyclerView recyclerView = view.findViewById(R.id.doctorlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SAF_Adapter adabter = new SAF_Adapter(getContext(), arrayList, R.layout.search_item);
        recyclerView.setAdapter(adabter);


    }

    @Override
    public void OnError(VolleyError error) {
        pg.setVisibility(View.GONE);

        Toast.makeText(getContext(), "" + error.toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onBackPressed() {
        FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Search_Fragment());
        fr.commit();
        MainActivity.navigation.setSelectedItemId(R.id.Search);

        return true;
    }
}
