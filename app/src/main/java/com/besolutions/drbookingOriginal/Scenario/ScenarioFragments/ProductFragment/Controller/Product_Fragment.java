package com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.ProductFragment.Controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

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
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.ProductFragment.Model.ModelMyProduct;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.ProductFragment.Model.ModelProduct;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.ProductFragment.Pattrens.ProductAdapter;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.SearchFragment.Controller.Search_Fragment;
import com.besolutions.drbookingOriginal.Scenario.ScenarioHome.Controller.MainActivity;
import com.besolutions.drbookingOriginal.Scenario.ScenarioHome.Pattrens.IFOnBackPressed;
import com.besolutions.drbookingOriginal.Scenario.ScenarioSignIn.Model.Model_Failed;
import com.google.gson.Gson;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class Product_Fragment extends Fragment implements NetworkInterface, IFOnBackPressed {

    private View view;
    RecyclerView productList;
    ImageView back;
    ProgressBar pg;
    ArrayList<ModelProduct> productArrayList = new ArrayList<>();
    ModelProduct[] products;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.product_fragment, container, false);

        productList = view.findViewById(R.id.productList);
        pg = view.findViewById(R.id.progressbar);

        pg.setVisibility(View.VISIBLE);
        new Apicalls(getContext(), Product_Fragment.this).all_product();

        back = view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Search_Fragment());
                fr.commit();
                MainActivity.navigation.setSelectedItemId(R.id.Search);

            }
        });
        return view;
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
        Gson gson = new Gson();
        ModelMyProduct modelProduct = gson.fromJson(model.getResponse(), ModelMyProduct.class);
        products = modelProduct.getProducts();
        if (modelProduct.getStatus() == 1) {

            for (int i = 0; i < products.length; i++) {

                ModelProduct product = new ModelProduct();
                product.setId(products[i].getId());
                product.setPrice(products[i].getPrice());
                product.setName(products[i].getName());
                product.setBenefits(products[i].getBenefits());
                product.setDescription(products[i].getDescription());
                product.setImage(products[i].getImage());

                productArrayList.add(product);
            }
            RecyclerView recyclerView = view.findViewById(R.id.productList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            ProductAdapter adabter = new ProductAdapter(productArrayList, getActivity());
            recyclerView.setAdapter(adabter);


        } else if (modelProduct.getStatus() == 2) {
            Gson gson1 = new Gson();
            Model_Failed failed = gson1.fromJson(model.getResponse(), Model_Failed.class);
            Toasty.error(getContext(), "" + failed.getMessage(), Toast.LENGTH_LONG).show();

        } else if (modelProduct.getStatus() == 3) {
            Gson gson2 = new Gson();
            Model_Failed failed = gson2.fromJson(model.getResponse(), Model_Failed.class);
            Toasty.error(getContext(), "" + failed.getMessage(), Toast.LENGTH_LONG).show();

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
