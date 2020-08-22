package com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.ProfileFragment.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.VolleyError;
import com.besolutions.drbookingOriginal.NetworkLayer.Apicalls;
import com.besolutions.drbookingOriginal.NetworkLayer.NetworkInterface;
import com.besolutions.drbookingOriginal.NetworkLayer.ResponseModel;
import com.besolutions.drbookingOriginal.R;
import com.besolutions.drbookingOriginal.Scenario.ScenarioAppointment.Controller.Appointment;
import com.besolutions.drbookingOriginal.Scenario.ScenarioEditProfile.Controller.Edit_Profile;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.ProfileFragment.Model.ModelEditProfile;
import com.besolutions.drbookingOriginal.Scenario.ScenarioMyFavorit.Controller.My_Favorite;
import com.besolutions.drbookingOriginal.Scenario.ScenarioMyProduct.Controller.My_Product;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.SearchFragment.Controller.Search_Fragment;
import com.besolutions.drbookingOriginal.Scenario.ScenarioHome.Controller.MainActivity;
import com.besolutions.drbookingOriginal.Scenario.ScenarioHome.Pattrens.IFOnBackPressed;
import com.besolutions.drbookingOriginal.Scenario.ScenarioSignIn.Controller.SignIn;
import com.besolutions.drbookingOriginal.Scenario.ScenarioSignIn.Model.Model_Failed;
import com.besolutions.drbookingOriginal.local_data.saved_data;
import com.besolutions.drbookingOriginal.local_data.send_data;
import com.google.gson.Gson;

import es.dmoral.toasty.Toasty;

public class Profile_Fragment extends Fragment implements NetworkInterface, IFOnBackPressed {

    private View view;
    LinearLayout myproducts,myappoienments,editData,mywishlist;
    Button logout;
    TextView title;
    ImageView back;
    ImageView userImage;
    TextView userName,userNumber;
    String user_id;
    ProgressBar pg;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.profile_fragment, container, false);

        title=(TextView)view.findViewById(R.id.products);
        title.setText("الصفحه الشخصيه");
        back=(ImageView)view.findViewById(R.id.back);
        userNumber=(TextView)view.findViewById(R.id.usernumber);
        userName=(TextView)view.findViewById(R.id.username);
        userImage=(ImageView) view.findViewById(R.id.userimage);
        user_id = saved_data.get_user_id(getContext());
        pg = view.findViewById(R.id.progressbar);
        onclick();
        pg.setVisibility(View.VISIBLE);
        new Apicalls(getContext(),Profile_Fragment.this).user_profile(user_id);

        return view;
    }


    public void onclick()
    {

        myproducts=(LinearLayout)view.findViewById(R.id.myproducts);
        myproducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), My_Product.class));
            }
        });
        logout=(Button)view.findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_data.userId_check(getContext(),false);
                startActivity(new Intent(getContext(), SignIn.class));

            }
        });
        myappoienments=(LinearLayout)view.findViewById(R.id.myappoienments);
        myappoienments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Appointment.class));
            }
        });
        editData=(LinearLayout)view.findViewById(R.id.editdata);
        editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Edit_Profile.class));
            }
        });
        mywishlist=(LinearLayout)view.findViewById(R.id.mywishlist);
        mywishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), My_Favorite.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Search_Fragment());
                fr.commit();
                MainActivity.navigation.setSelectedItemId(R.id.Search);
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        pg.setVisibility(View.GONE);
        Gson gson = new Gson();
        ModelEditProfile profile = gson.fromJson(model.getResponse(),ModelEditProfile.class);
        if (profile.getStatus() == 1){

            userName.setText(profile.getUser().getName());
            userNumber.setText(profile.getUser().getPhone());

        }else if (profile.getStatus() == 2){

            Gson gson1 = new Gson();
            Model_Failed failed = gson1.fromJson(model.getResponse(), Model_Failed.class);
            Toasty.error(getContext(), "" + failed.getMessage(), Toast.LENGTH_LONG).show();

        }else if (profile.getStatus() == 3){

            Gson gson3 = new Gson();
            Model_Failed failed1 = gson3.fromJson(model.getResponse(), Model_Failed.class);
            Toasty.error(getContext(), "" + failed1.getMessage(), Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void OnError(VolleyError error) {
        pg.setVisibility(View.GONE);
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
