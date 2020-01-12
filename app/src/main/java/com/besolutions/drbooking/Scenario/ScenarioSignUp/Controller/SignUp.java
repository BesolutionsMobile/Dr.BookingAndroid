package com.besolutions.drbooking.Scenario.ScenarioSignUp.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.drbooking.NetworkLayer.Apicalls;
import com.besolutions.drbooking.NetworkLayer.NetworkInterface;
import com.besolutions.drbooking.NetworkLayer.ResponseModel;
import com.besolutions.drbooking.R;
import com.besolutions.drbooking.Scenario.ScenarioSignIn.Controller.SignIn;
import com.besolutions.drbooking.Scenario.ScenarioSignIn.Model.Model_Failed;
import com.besolutions.drbooking.Scenario.ScenarioSignUp.Model.Model_SignUp;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;

import es.dmoral.toasty.Toasty;

public class SignUp extends AppCompatActivity implements NetworkInterface {

    TextView goToLogin;
    Button Signup;
    EditText userName, Email, Password, phone;
    ProgressDialog pg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Signup = (Button) findViewById(R.id.signup);
        userName = (EditText) findViewById(R.id.username);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);
        phone = (EditText) findViewById(R.id.phone);

        goToLogin = (TextView) findViewById(R.id.goToLogin);
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, SignIn.class));
            }
        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userName.getText().toString().equals("")) {
                    userName.setError("من فضلك ادخل الاسم !");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.username));

                    Toasty.error(SignUp.this, "من فضلك ادخل الاسم !", Toast.LENGTH_SHORT).show();


                } else if (Email.getText().toString().equals("")) {

                    Email.setError("من فضلك ادخل االبريد الالكتروني !");
                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.email));

                    Toasty.error(SignUp.this, "من فضلك ادخل االبريد الالكتروني !", Toast.LENGTH_SHORT).show();


                } else if (Password.getText().toString().equals("")) {
                    Password.setError("من فضلك ادخل الرقم السري !");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.password));

                    Toasty.error(SignUp.this, "من فضلك ادخل الرقم السري !", Toast.LENGTH_SHORT).show();


                } else if (phone.getText().toString().equals("")) {

                    phone.setError("من فضلك ادخل رقم الهاتف !");
                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.phone));

                    Toasty.error(SignUp.this, "من فضلك ادخل رقم الهاتف !", Toast.LENGTH_SHORT).show();


                } else {

                    pg = new ProgressDialog(SignUp.this);
                    pg.setMessage("جاري التحميل....");
                    pg.show();

                    new Apicalls(SignUp.this, SignUp.this).registerUser(userName.getText().toString(), Email.getText().toString(), phone.getText().toString(), Password.getText().toString());


                }

            }
        });
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        pg.dismiss();
        Gson gson = new Gson();
        Model_SignUp sign = gson.fromJson(model.getResponse(),Model_SignUp.class);


        if (sign.getStatus()== 1 ) {

            Toast.makeText(SignUp.this, ""+sign.getMessage(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignUp.this, SignIn.class));
            finish();


        } else if (sign.getStatus()== 2) {

            Gson gson1 = new Gson();
            Model_Failed failed = gson1.fromJson(model.getResponse(),Model_Failed.class);
            Toasty.error(SignUp.this,""+failed.getMessage(), Toast.LENGTH_SHORT).show();
            YoYo.with(Techniques.Flash)
                    .duration(800)
                    .repeat(1)
                    .playOn(findViewById(R.id.username));

            YoYo.with(Techniques.Flash)
                    .duration(800)
                    .repeat(1)
                    .playOn(findViewById(R.id.password));

            Email.setError("من فضلك راجع البريد الاكتروني الخاصة بك");

            Password.setError("من فضلك راجع كلمة المرور الخاصة بك");



        } else if (sign.getStatus()== 3) {

            Gson gson2 = new Gson();
            Model_Failed failed = gson2.fromJson(model.getResponse(),Model_Failed.class);
            Toasty.error(SignUp.this,""+failed.getMessage(), Toast.LENGTH_SHORT).show();
            YoYo.with(Techniques.Flash)
                    .duration(800)
                    .repeat(1)
                    .playOn(findViewById(R.id.username));

            YoYo.with(Techniques.Flash)
                    .duration(800)
                    .repeat(1)
                    .playOn(findViewById(R.id.password));

            Email.setError("من فضلك راجع البريد الاكتروني الخاصة بك");

            Password.setError("من فضلك راجع كلمة المرور الخاصة بك");


        }

    }

    @Override
    public void OnError(VolleyError error) {
        pg.dismiss();
        Toasty.error(SignUp.this,"No Internet Connection",Toast.LENGTH_LONG).show();

    }
}
