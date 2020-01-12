package com.besolutions.drbooking.Scenario.ScenarioMyProduct.Controller;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.besolutions.drbooking.NetworkLayer.Apicalls;
import com.besolutions.drbooking.NetworkLayer.NetworkInterface;
import com.besolutions.drbooking.NetworkLayer.ResponseModel;
import com.besolutions.drbooking.R;
import com.besolutions.drbooking.Scenario.ScenarioFragments.ProductFragment.Model.ModelProduct;
import com.besolutions.drbooking.Scenario.ScenarioMyProduct.Model.MyProducts;
import com.besolutions.drbooking.Scenario.ScenarioMyProduct.Model.Product;
import com.besolutions.drbooking.Scenario.ScenarioMyProduct.Pattrens.MyProductAdapter;
import com.besolutions.drbooking.Scenario.ScenarioMyProduct.Utils.Converter;
import com.besolutions.drbooking.Scenario.ScenarioSignIn.Model.Model_Failed;
import com.besolutions.drbooking.local_data.saved_data;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class My_Product extends AppCompatActivity implements NetworkInterface {

    ImageView back;
    String user_id;
    ProgressBar pg;
    ArrayList<Product> productArrayList = new ArrayList<>();
    ModelProduct[] products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__product);
        back=(ImageView)findViewById(R.id.back);
        pg =findViewById(R.id.progressbar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        user_id = saved_data.get_user_id(this);
        pg.setVisibility(View.VISIBLE);
        new Apicalls(this,My_Product.this).my_product(user_id);
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        pg.setVisibility(View.GONE);

//        try {
//            MyProducts data = Converter.fromJsonString(model.getResponse());
//
//            Map<String, Product> hashMap = data.getProducts();
//
//            for(int i = 0;i<hashMap.size();i++)
//            {
//                Product modelProduct = new Product();
//                modelProduct.setID(Objects.requireNonNull(hashMap.get(String.valueOf(i))).getID());
//                modelProduct.setName(Objects.requireNonNull(hashMap.get(String.valueOf(i))).getName());
//                modelProduct.setBenefits(Objects.requireNonNull(hashMap.get(String.valueOf(i))).getBenefits());
//                modelProduct.setDescription(Objects.requireNonNull(hashMap.get(String.valueOf(i))).getDescription());
//                modelProduct.setImage(Objects.requireNonNull(hashMap.get(String.valueOf(i))).getImage());
//                modelProduct.setPrice(Objects.requireNonNull(hashMap.get(String.valueOf(i))).getPrice());
//                productArrayList.add(modelProduct);
//            }
//
//            RecyclerView recyclerView = findViewById(R.id.myProductList);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            MyProductAdapter adabter = new MyProductAdapter(productArrayList, this);
//            recyclerView.setAdapter(adabter);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        Gson gson = new Gson();
        Model_Failed modelProduct = gson.fromJson(model.getResponse(), Model_Failed.class);
        //products = modelProduct.getProducts();
        if (modelProduct.getStatus() == 1) {

       /*     for (int i = 0; i < products.length; i++) {

                ModelProduct product = new ModelProduct();
                product.setId(products[i].getId());
                product.setPrice(products[i].getPrice());
                product.setName(products[i].getName());
                product.setBenefits(products[i].getBenefits());
                product.setDescription(products[i].getDescription());
                product.setImage(products[i].getImage());

                productArrayList.add(product);
            }
            RecyclerView recyclerView = findViewById(R.id.myProductList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            MyProductAdapter adabter = new MyProductAdapter(productArrayList, this);
            recyclerView.setAdapter(adabter);*/


        } else if (modelProduct.getStatus() == 2) {
            Gson gson1 = new Gson();
            Model_Failed failed = gson1.fromJson(model.getResponse(), Model_Failed.class);
            Toasty.error(this, "" + failed.getMessage(), Toast.LENGTH_LONG).show();

        } else if (modelProduct.getStatus() == 3) {
            Gson gson2 = new Gson();
            Model_Failed failed = gson2.fromJson(model.getResponse(), Model_Failed.class);
            Toasty.error(this, "" + failed.getMessage(), Toast.LENGTH_LONG).show();

        }

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
