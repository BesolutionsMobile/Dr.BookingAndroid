package com.besolutions.drbooking.Scenario.ScenarioAppointment.Pattrens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.besolutions.drbooking.NetworkLayer.Apicalls;
import com.besolutions.drbooking.NetworkLayer.NetworkInterface;
import com.besolutions.drbooking.NetworkLayer.ResponseModel;
import com.besolutions.drbooking.R;
import com.besolutions.drbooking.Scenario.ScenarioAppointment.Model.ModelReservation;
import com.besolutions.drbooking.Scenario.ScenarioFragments.Doctor_Details_Fragment.Controller.Doctor_Details_Fragment;
import com.besolutions.drbooking.Scenario.ScenarioFragments.Doctor_Details_Fragment.Controller.reservation_dialog;
import com.besolutions.drbooking.Scenario.ScenarioFragments.Doctor_Details_Fragment.Model.ModelDate;
import com.besolutions.drbooking.Scenario.ScenarioFragments.SearchFragment.Controller.Search_Result;
import com.besolutions.drbooking.Scenario.ScenarioHome.Controller.MainActivity;
import com.besolutions.drbooking.Scenario.ScenarioMapDoctorLocation.Controller.map_doctor_location;
import com.besolutions.drbooking.Scenario.ScenarioSignIn.Model.Model_Failed;
import com.besolutions.drbooking.local_data.saved_data;
import com.besolutions.drbooking.local_data.send_data;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;


public class ReservationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ModelReservation> mylist;
    Context mContext;


    public ReservationAdapter(ArrayList<ModelReservation> mylist, Context context) {
        this.mylist = mylist;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_item, parent, false);
        MainItemHolder mainHolder = new MainItemHolder(ads);


        return mainHolder;
    }


    public class MainHolder extends RecyclerView.ViewHolder {
        public MainHolder(View itemview) {
            super(itemview);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int i) {

        int viewType = getItemViewType(i);
        final ModelReservation reservation = mylist.get(i);


        MainItemHolder mainHolder = (MainItemHolder) holder;

        mainHolder.title.setText(reservation.getDoctor());
        mainHolder.specialist.setText(reservation.getJobTitle());
        mainHolder.from.setText(reservation.getTimeFrom());
        mainHolder.to.setText(reservation.getTimeTo());
        mainHolder.date.setText(reservation.getDate());
        mainHolder.price.setText(reservation.getPrice());
        mainHolder.Address.setText(reservation.getPlace());
        Glide.with(mContext)
                .load(reservation.getImage())
                .placeholder(R.drawable.drpicture)
                .into(mainHolder.circleImageView);

        mainHolder.searchitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, MainActivity.class);
                String id = mylist.get(i).getDoctorId();
                intent.putExtra("doctor_string_id", String.valueOf(id));
                intent.putExtras(intent);

                 mContext.startActivity(intent);






            }
        });

        mainHolder.viewmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, map_doctor_location.class);
                intent.putExtra("lat", reservation.getLatitude());
                intent.putExtra("longt", reservation.getLongitude());
                intent.putExtra("placeName", reservation.getDoctor());
                v.getContext().startActivity(intent);
            }
        });

        mainHolder.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MainActivity.class);
                String id = mylist.get(i).getDoctorId();
                intent.putExtra("doctor_string_id", String.valueOf(id));
                intent.putExtras(intent);

                mContext.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }


    public class MainItemHolder extends RecyclerView.ViewHolder {
        TextView date, from, to, price, Address, title, specialist;
        LinearLayout searchitem, viewmap;
        CircleImageView circleImageView;
        Button profile;

        public MainItemHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            from = itemView.findViewById(R.id.fromtime);
            specialist = itemView.findViewById(R.id.speclist);
            to = itemView.findViewById(R.id.totime);
            price = itemView.findViewById(R.id.fees);
            Address = itemView.findViewById(R.id.locaation);
            title = itemView.findViewById(R.id.doctorName);
            searchitem = itemView.findViewById(R.id.searchitem);
            viewmap = itemView.findViewById(R.id.viewmap);
            circleImageView = itemView.findViewById(R.id.prfileImage);
            profile = itemView.findViewById(R.id.profile);


        }


    }


}
