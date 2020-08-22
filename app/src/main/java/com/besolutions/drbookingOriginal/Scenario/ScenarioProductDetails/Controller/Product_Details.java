package com.besolutions.drbookingOriginal.Scenario.ScenarioProductDetails.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.drbookingOriginal.NetworkLayer.Apicalls;
import com.besolutions.drbookingOriginal.NetworkLayer.NetworkInterface;
import com.besolutions.drbookingOriginal.NetworkLayer.ResponseModel;
import com.besolutions.drbookingOriginal.R;
import com.besolutions.drbookingOriginal.Scenario.ScenarioProductDetails.Model.ModelProductDetails;
import com.besolutions.drbookingOriginal.Scenario.ScenarioSignIn.Model.Model_Failed;
import com.besolutions.drbookingOriginal.local_data.saved_data;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import es.dmoral.toasty.Toasty;

public class Product_Details extends AppCompatActivity implements NetworkInterface {

    String id, user_id;
    TextView productDetails, name, price, used, description, titleName, usedName;
    ImageView back, imgBack;
    int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Bundle b = getIntent().getExtras();
        id = b.getString("id");
        name = (TextView) findViewById(R.id.title);
        price = (TextView) findViewById(R.id.price);
        used = (TextView) findViewById(R.id.used);
        description = (TextView) findViewById(R.id.description);
        titleName = (TextView) findViewById(R.id.def);
        usedName = (TextView) findViewById(R.id.useful);
        imgBack = (ImageView) findViewById(R.id.viewpager);
        user_id = saved_data.get_user_id(this);
        x = 1;
        new Apicalls(this, Product_Details.this).product_details(id);
        productDetails = (TextView) findViewById(R.id.products);
        productDetails.setText("تفاصيل المنتج");
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button buy = (Button) findViewById(R.id.buy);

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = 2;
                new Apicalls(Product_Details.this, Product_Details.this).make_order(user_id, id);
                Dialoag_success dialoag_success = new Dialoag_success();
                dialoag_success.dialog(Product_Details.this, R.layout.order_success_dialog, .90);


            }
        });
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        if (x == 1) {

            Gson gson = new Gson();
            ModelProductDetails productDetails = gson.fromJson(model.getResponse(), ModelProductDetails.class);
            if (productDetails.getStatus() == 1) {

                name.setText(productDetails.getProduct().getName());
                price.setText(productDetails.getProduct().getPrice());
                used.setText(productDetails.getProduct().getDescription());
                description.setText(productDetails.getProduct().getDescription());
                titleName.setText(productDetails.getProduct().getName());
                usedName.setText(productDetails.getProduct().getName());
                Glide.with(this)
                        .load(productDetails.getProduct().getImage())
                        .placeholder(R.drawable.drpicture)
                        .into(imgBack);
            } else if (productDetails.getStatus() == 2) {

                Gson gson1 = new Gson();
                Model_Failed failed = gson1.fromJson(model.getResponse(), Model_Failed.class);
                Toasty.error(this, "" + failed.getMessage(), Toast.LENGTH_LONG).show();


            } else if (productDetails.getStatus() == 3) {

                Gson gson2 = new Gson();
                Model_Failed failed = gson2.fromJson(model.getResponse(), Model_Failed.class);
                Toasty.error(this, "" + failed.getMessage(), Toast.LENGTH_LONG).show();

            }


        } else if (x == 2) {
            Gson gson = new Gson();
            Model_Failed order = gson.fromJson(model.getResponse(), Model_Failed.class);
            if (order.getStatus() == 1) {

                Toasty.success(this, "" + order.getMessage(), Toast.LENGTH_LONG).show();

            } else if (order.getStatus() == 2) {

                Toasty.error(this, "" + order.getMessage(), Toast.LENGTH_LONG).show();

            } else if (order.getStatus() == 3) {

                Toasty.error(this, "" + order.getMessage(), Toast.LENGTH_LONG).show();

            }

        }

    }

    @Override
    public void OnError(VolleyError error) {

    }
}
