package com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.SearchFragment.Pattrens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.besolutions.drbookingOriginal.NetworkLayer.Apicalls;
import com.besolutions.drbookingOriginal.NetworkLayer.NetworkInterface;
import com.besolutions.drbookingOriginal.NetworkLayer.ResponseModel;
import com.besolutions.drbookingOriginal.R;
import com.besolutions.drbookingOriginal.Scenario.Dialog_Anim;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.Doctor_Details_Fragment.Controller.Doctor_Details_Fragment;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.SearchFragment.Model.Doctor;
import com.besolutions.drbookingOriginal.Scenario.ScenarioSignIn.Model.Model_Failed;
import com.besolutions.drbookingOriginal.Utils.TinyDB;
import com.besolutions.drbookingOriginal.local_data.saved_data;
import com.besolutions.drbookingOriginal.local_data.send_data;
import com.besolutions.drbookingOriginal.Scenario.ScenarioMapDoctorLocation.Controller.map_doctor_location;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class SAF_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements NetworkInterface {

    int resource;
    ArrayList<Doctor> mylist;
    Context mContext;
    public int x = 0;
    TinyDB tinyDB;

    public SAF_Adapter(Context context, ArrayList<Doctor> mylist, int resource) {
        this.mylist = mylist;
        this.mContext = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View ads = LayoutInflater.from(mContext).inflate(resource, parent, false);
        MainItemHolder mainHolder = new MainItemHolder(ads);


        return mainHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int i) {

        int viewType = getItemViewType(i);
        final Doctor doctor = mylist.get(i);


        final MainItemHolder mainHolder = (MainItemHolder) holder;

        mainHolder.name.setText(doctor.getName().toString());
        mainHolder.spechlist.setText(doctor.getJobTitle().toString());
        mainHolder.fees.setText(doctor.getPrice().toString());
        mainHolder.address.setText(doctor.getAddress().toString());
        mainHolder.ratings.setRating(doctor.getRating());
        Glide.with(mContext)
                .load(doctor.getImage())
                .placeholder(R.drawable.drpicture)
                .into(mainHolder.profileImg);


        mainHolder.viewmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, map_doctor_location.class);
                intent.putExtra("lat", doctor.getLatitude().toString());
                intent.putExtra("longt", doctor.getLongitude().toString());
                intent.putExtra("placeName", doctor.getName().toString());
                v.getContext().startActivity(intent);
            }
        });

        mainHolder.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr = ((AppCompatActivity) mContext).getSupportFragmentManager().beginTransaction();

                Bundle b = new Bundle();
                String name = mainHolder.name.getText().toString();
                String fees = mainHolder.fees.getText().toString();
                String address = mainHolder.address.getText().toString();
                String id = mylist.get(i).getId();

                Doctor_Details_Fragment detailsFragment = new Doctor_Details_Fragment();

                b.putString("fees", fees);
                b.putString("name", name);
                b.putString("address", address);
                b.putString("id", id);
                b.putBoolean("isFav",doctor.getIsFav());
                detailsFragment.setArguments(b);
                send_data.SET_DOCTOR_ID(mContext, String.valueOf(id));

                fr.replace(R.id.fragment_container, detailsFragment);
                fr.commit();
            }
        });
        mainHolder.searchitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr = ((AppCompatActivity) mContext).getSupportFragmentManager().beginTransaction();

                Bundle b = new Bundle();
                String name = mainHolder.name.getText().toString();
                String fees = mainHolder.fees.getText().toString();
                String address = mainHolder.address.getText().toString();
                String id = mylist.get(i).getId();

                Doctor_Details_Fragment detailsFragment = new Doctor_Details_Fragment();
                detailsFragment.setArguments(b);
//                b.putString("fees", fees);
//                b.putString("name", name);
//                b.putString("address", address);
                b.putString("id", id);
//                b.putBoolean("isFav",doctor.getIsFav());


                send_data.SET_DOCTOR_ID(mContext, String.valueOf(id));

                fr.replace(R.id.fragment_container, detailsFragment);
                fr.commit();
            }
        });


        if (doctor.getIsFav() == true){
            mainHolder.fav.setImageResource(R.drawable.redfavourite);
            doctor.setIsFav(true);
        }else if (doctor.getIsFav() == false)
        {
            mainHolder.fav.setImageResource(R.drawable.fav);

        }

        mainHolder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saved_data saved_data = new saved_data();
                if (doctor.getIsFav() == false) {
                    x = 1;
                    final String doctor_id = doctor.getId();
                    mainHolder.fav.setImageResource(R.drawable.redfavourite);
                    String user_id = saved_data.get_user_id(mContext);
                    new Apicalls(mContext, SAF_Adapter.this).add_favorate(user_id, doctor_id);
                    doctor.setIsFav(true);
                    notifyItemChanged(i);
                    Dialog_Anim dialog_anim = new Dialog_Anim();
                    dialog_anim.dialog(mContext,R.layout.like_dialog,.90);


                } else if (doctor.getIsFav() == true){
                    x = 2;
                    final String doctorId = doctor.getId();
                    mainHolder.fav.setImageResource(R.drawable.fav);
                    String user_id = saved_data.get_user_id(mContext);
                    new Apicalls(mContext, SAF_Adapter.this).delete_favorate(user_id, doctorId);
                    doctor.setIsFav(false);
                    notifyItemChanged(i);
                    Dialog_Anim dialog_anim = new Dialog_Anim();
                    dialog_anim.dialog(mContext,R.layout.unlike_dialog,.90);


                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        Gson gson = new Gson();
        Model_Failed fav = gson.fromJson(model.getResponse(), Model_Failed.class);

        if (fav.getStatus() == 1) {

            Toasty.success(mContext, "" + fav.getMessage(), Toast.LENGTH_LONG).show();

        } else if (fav.getStatus() == 2) {

            Toasty.error(mContext, "" + fav.getMessage(), Toast.LENGTH_LONG).show();

        } else {
            Toasty.error(mContext, "" + fav.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void OnError(VolleyError error) {
        Toast.makeText(mContext, ""+error.toString(), Toast.LENGTH_SHORT).show();
    }


    public class MainItemHolder extends RecyclerView.ViewHolder {
        TextView name, address, fees, spechlist;
        ImageView profileImg, fav;
        LinearLayout searchitem, viewmap;
        RatingBar ratings;
        Button profile;

        public MainItemHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.doctorName);
            address = (TextView) itemView.findViewById(R.id.locaation);
            fees = (TextView) itemView.findViewById(R.id.fees);
            spechlist = (TextView) itemView.findViewById(R.id.speclist);
            profileImg = (ImageView) itemView.findViewById(R.id.prfileImage);
            searchitem = (LinearLayout) itemView.findViewById(R.id.searchitem);
            fav = (ImageView) itemView.findViewById(R.id.fav);
            profile = (Button) itemView.findViewById(R.id.profile);
            ratings = (RatingBar) itemView.findViewById(R.id.ratings);
            viewmap = (LinearLayout) itemView.findViewById(R.id.viewmap);

        }


    }

}
