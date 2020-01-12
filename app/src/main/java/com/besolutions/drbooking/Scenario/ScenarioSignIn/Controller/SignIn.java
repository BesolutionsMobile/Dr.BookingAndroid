package com.besolutions.drbooking.Scenario.ScenarioSignIn.Controller;

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
import com.besolutions.drbooking.Scenario.Edit_Profile_Dialog;
import com.besolutions.drbooking.Scenario.ScenarioEditProfile.Controller.Edit_Profile;
import com.besolutions.drbooking.Scenario.ScenarioHome.Controller.MainActivity;
import com.besolutions.drbooking.Scenario.ScenarioSignIn.Model.Model_Failed;
import com.besolutions.drbooking.Scenario.ScenarioSignIn.Model.Model_SignIn;
import com.besolutions.drbooking.Scenario.ScenarioSignUp.Controller.SignUp;
import com.besolutions.drbooking.Utils.progressdialog;
import com.besolutions.drbooking.local_data.saved_data;
import com.besolutions.drbooking.local_data.send_data;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;

import es.dmoral.toasty.Toasty;

public class SignIn extends AppCompatActivity implements NetworkInterface {
    TextView goToSignUp;
    String id;
    EditText Email, Password;
    Button signIn;
    TextView forgetPassword;
    progressdialog progressdialog;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        forgetPassword = (TextView) findViewById(R.id.forgetpass);
        signIn = (Button) findViewById(R.id.signin);
        goToSignUp = (TextView) findViewById(R.id.gotosignup);

        goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, SignUp.class));

            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, ForgetPassword.class));
            }
        });

        Email = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Email.getText().toString().equals(""))
                {
                    Email.setError("من فضلك ادخل البريد الاكتروني!");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.username));

                    Toasty.error(SignIn.this, "من فضلك ادخل البريد الاكتروني!", Toast.LENGTH_SHORT).show();

                }
                else if(Password.getText().toString().equals(""))
                {
                    Password.setError("من فضلك ادخل الرقم السري!");
                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.password));

                    Toasty.error(SignIn.this, "من فضلك ادخل الرقم السري!", Toast.LENGTH_SHORT).show();

                }
                else {

                    pd = new ProgressDialog(SignIn.this);
                    pd.setMessage("جاري التحميل....");
                    pd.show();
                    new Apicalls(SignIn.this,SignIn.this).loginUser(Email.getText().toString(),Password.getText().toString());

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
        Model_SignIn sign = gson.fromJson(model.getResponse(),Model_SignIn.class);

        send_data send_data = new send_data();
        send_data.userId_check(this,true);

        if (sign.getStatus()== 1 ) {
            Toasty.success(SignIn.this, "successful login", Toast.LENGTH_SHORT).show();
            id = sign.getUserData().getId();

            send_data.SET_USER_ID(this,id);

            login_dialog loginDialog = new login_dialog();
            loginDialog.dialog(this,R.layout.welcome_dialog,.90);


        } else if (sign.getStatus()== 2) {
            Gson gson1 = new Gson();
            Model_Failed failed = gson1.fromJson(model.getResponse(),Model_Failed.class);
            Toasty.error(SignIn.this,""+failed.getMessage(), Toast.LENGTH_SHORT).show();
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
            Toasty.error(SignIn.this,""+failed.getMessage(), Toast.LENGTH_SHORT).show();
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
        pd.dismiss();
        Toasty.error(SignIn.this,""+error.toString(),Toast.LENGTH_LONG).show();

    }
}
