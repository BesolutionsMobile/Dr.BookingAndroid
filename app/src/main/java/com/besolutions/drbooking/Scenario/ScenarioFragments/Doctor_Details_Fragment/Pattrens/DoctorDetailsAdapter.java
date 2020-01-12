package com.besolutions.drbooking.Scenario.ScenarioFragments.Doctor_Details_Fragment.Pattrens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.besolutions.drbooking.NetworkLayer.Apicalls;
import com.besolutions.drbooking.NetworkLayer.NetworkInterface;
import com.besolutions.drbooking.NetworkLayer.ResponseModel;
import com.besolutions.drbooking.R;
import com.besolutions.drbooking.Scenario.ScenarioFragments.Doctor_Details_Fragment.Model.ModelDate;
import com.besolutions.drbooking.Scenario.ScenarioSignIn.Model.Model_Failed;
import com.besolutions.drbooking.Scenario.ScenarioFragments.Doctor_Details_Fragment.Controller.reservation_dialog;
import com.besolutions.drbooking.local_data.saved_data;
import com.google.gson.Gson;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;


public class DoctorDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>implements NetworkInterface {

    ArrayList<ModelDate> mylist;
    Context mContext;


    public DoctorDetailsAdapter(ArrayList<ModelDate> mylist, Context context) {
        this.mylist = mylist;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_item, parent, false);
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
        final ModelDate date = mylist.get(i);


        MainItemHolder mainHolder = (MainItemHolder) holder;


        mainHolder.date.setText(date.getReservationDate());
        mainHolder.from.setText(date.getFromTime());
        mainHolder.to.setText(mylist.get(i).getToTime());

        mainHolder.reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_id = saved_data.get_user_id(mContext);
                String id = date.getId();
                String doctor_id = date.getIdUser();

                new Apicalls(mContext,DoctorDetailsAdapter.this).confirm_reservation(user_id,doctor_id,id);

                /*Bundle b = new Bundle();
                Intent intent = new Intent(mContext, Confirm_Reservation.class);
                b.putString("id", String.valueOf(id));
                b.putString("doctor_id", String.valueOf(doctor_id));
                intent.putExtras(b);
                v.getContext().startActivity(intent);*/
            }
        });


    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }


    public class MainItemHolder extends RecyclerView.ViewHolder {
        TextView date, from, to, reservation;

        public MainItemHolder(@NonNull View itemView) {
            super(itemView);


            date = (TextView) itemView.findViewById(R.id.date);
            from = (TextView) itemView.findViewById(R.id.from);
            to = (TextView) itemView.findViewById(R.id.to);
            reservation = (TextView) itemView.findViewById(R.id.reserve);


        }


    }
    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        Gson gson = new Gson();
        Model_Failed reserve = gson.fromJson(model.getResponse(),Model_Failed.class);
        if (reserve.getStatus() == 1) {

            Toasty.success(mContext, "" + reserve.getMessage(), Toast.LENGTH_LONG).show();
            reservation_dialog reservation_dialog = new reservation_dialog();
            reservation_dialog.dialog(mContext,R.layout.reservation_dialog,.90);

        } else if (reserve.getStatus() == 2) {
            Toasty.error(mContext, "" + reserve.getMessage(), Toast.LENGTH_LONG).show();

        } else {
            Toasty.error(mContext, "" + reserve.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void OnError(VolleyError error) {

    }


}
