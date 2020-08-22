package com.besolutions.drbookingOriginal.Scenario.ScenarioSignIn.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.drbookingOriginal.NetworkLayer.Apicalls;
import com.besolutions.drbookingOriginal.NetworkLayer.NetworkInterface;
import com.besolutions.drbookingOriginal.NetworkLayer.ResponseModel;
import com.besolutions.drbookingOriginal.R;
import com.besolutions.drbookingOriginal.Scenario.ScenarioSignIn.Model.Model_Failed;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;

import es.dmoral.toasty.Toasty;

public class ForgetPassword extends AppCompatActivity implements NetworkInterface {

    Button send;
    EditText email;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        send = findViewById(R.id.send);
        email = findViewById(R.id.email);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("")) {
                    email.setError("من فضلك ادخل البريد الاكتروني!");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.email));

                    Toasty.error(ForgetPassword.this, "من فضلك ادخل البريد الاكتروني!", Toast.LENGTH_SHORT).show();

                } else {
                    pd = new ProgressDialog(ForgetPassword.this);
                    pd.setMessage("جاري التحميل....");
                    pd.show();
                    new Apicalls(ForgetPassword.this,ForgetPassword.this).forget_password(email.getText().toString());

                }
            }
        });
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        pd.dismiss();
        Gson gson = new Gson();
        Model_Failed forget = gson.fromJson(model.getResponse(),Model_Failed.class);

        if (forget.getStatus()== 1 ) {

            Toast.makeText(ForgetPassword.this, ""+forget.getMessage(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ForgetPassword.this, SignIn.class));

        } else if (forget.getStatus()== 2) {

            Toasty.error(ForgetPassword.this,""+forget.getMessage(), Toast.LENGTH_SHORT).show();
            YoYo.with(Techniques.Flash)
                    .duration(800)
                    .repeat(1)
                    .playOn(findViewById(R.id.email));


            email.setError("من فضلك راجع البريد الاكتروني الخاصة بك");


        } else if (forget.getStatus()== 3) {

            Toasty.error(ForgetPassword.this,""+forget.getMessage(), Toast.LENGTH_SHORT).show();
            YoYo.with(Techniques.Flash)
                    .duration(800)
                    .repeat(1)
                    .playOn(findViewById(R.id.email));


            email.setError("من فضلك راجع البريد الاكتروني الخاصة بك");

        }


    }

    @Override
    public void OnError(VolleyError error) {

        pd.dismiss();
        Toasty.error(ForgetPassword.this,"No Connection",Toast.LENGTH_LONG).show();

    }
}
