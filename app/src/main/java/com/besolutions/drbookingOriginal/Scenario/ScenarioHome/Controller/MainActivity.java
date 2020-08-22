package com.besolutions.drbookingOriginal.Scenario.ScenarioHome.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.besolutions.drbookingOriginal.R;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.Doctor_Details_Fragment.Controller.Doctor_Details_Fragment;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.ProductFragment.Controller.Product_Fragment;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.ProfileFragment.Controller.Profile_Fragment;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.SearchFragment.Controller.Search_Fragment;
import com.besolutions.drbookingOriginal.Scenario.ScenarioHome.Pattrens.IFOnBackPressed;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static BottomNavigationView navigation;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = findViewById(R.id.navigation);
        fragmentManager = getSupportFragmentManager();
        if (getIntent().getStringExtra("doctor_string_id") != null) {


            String doctor_id = getIntent().getStringExtra("doctor_string_id");

            //FragmentTransaction fr = getSupportFragmentManager().beginTransaction();

            Bundle b = new Bundle();

            Doctor_Details_Fragment detailsFragment = new Doctor_Details_Fragment();
            detailsFragment.setArguments(b);

            b.putString("id", doctor_id);

            loadFragment(detailsFragment);

            navigation.setSelectedItemId(R.id.Search);

        }else {
            navigation.setSelectedItemId(R.id.Search);
            Search_Fragment search_fragment = new Search_Fragment();
            loadFragment(search_fragment);
        }

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.Products:

                        Product_Fragment product_fragment = new Product_Fragment();
                        loadFragment(product_fragment);

                        return true;


                    case R.id.Search:
                        Search_Fragment search_fragment = new Search_Fragment();
                        loadFragment(search_fragment);
                        return true;

                    case R.id.Profile:

                        Profile_Fragment profile_fragment = new Profile_Fragment();
                        loadFragment(profile_fragment);


                        return true;


                    default:
                        return true;

                }


            }
        });
    }


    private void loadFragment(Fragment fragment) {

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (!(fragment instanceof IFOnBackPressed) || !((IFOnBackPressed) fragment).onBackPressed()) {
            super.onBackPressed();
        }
    }
}
