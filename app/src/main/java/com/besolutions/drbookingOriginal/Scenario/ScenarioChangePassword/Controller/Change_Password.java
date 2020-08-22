package com.besolutions.drbookingOriginal.Scenario.ScenarioChangePassword.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.drbookingOriginal.NetworkLayer.Apicalls;
import com.besolutions.drbookingOriginal.NetworkLayer.NetworkInterface;
import com.besolutions.drbookingOriginal.NetworkLayer.ResponseModel;
import com.besolutions.drbookingOriginal.R;
import com.besolutions.drbookingOriginal.Scenario.Edit_Profile_Dialog;
import com.besolutions.drbookingOriginal.Scenario.ScenarioSignIn.Model.Model_Failed;
import com.besolutions.drbookingOriginal.local_data.saved_data;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;

import es.dmoral.toasty.Toasty;

public class Change_Password extends AppCompatActivity implements NetworkInterface {
    EditText password, newPassword, reTypePassword;
    Button edit;
    ProgressBar pg;
    String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        password = (EditText) findViewById(R.id.password);
        newPassword = (EditText) findViewById(R.id.newpassword);
        reTypePassword = (EditText) findViewById(R.id.retypepassword);
        edit = (Button) findViewById(R.id.edit);
        pg = findViewById(R.id.progressbar);
        user_id = saved_data.get_user_id(this);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().equals("")) {
                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.password));

                    password.setError("من فضلك ادخل كلمة المرور القديمة!");
                    Toasty.error(Change_Password.this, "من فضلك ادخل كلمة المرور القديمة!", Toast.LENGTH_SHORT).show();

                } else if (newPassword.getText().toString().equals("")) {
                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.newpassword));

                    newPassword.setError("من فضلك ادخل كلمة المرور الجديدة!");

                    Toasty.error(Change_Password.this, "من فضلك ادخل كلمة المرور الجديدة!", Toast.LENGTH_SHORT).show();


                } else if (newPassword.getText().toString().length() <= 5) {

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.newpassword));

                    newPassword.setError("كلمة السر قصيرة!");
                    Toasty.error(Change_Password.this, "لا يمكن ان تكون كلمة السر اقل من 6 حروف او ارقام!", Toast.LENGTH_SHORT).show();


                } else if (reTypePassword.getText().toString().equals("")) {

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.retypepassword));

                    reTypePassword.setError("من فضلك اعد ادخال كلمة السر!");
                    Toasty.error(Change_Password.this, "من فضلك اعد ادخال كلمة السر!", Toast.LENGTH_SHORT).show();


                } else if (!newPassword.getText().toString().equals(reTypePassword.getText().toString())) {
                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.newpassword));

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.retypepassword));

                    newPassword.setError("كلمة السر غير متطابقه!");
                    reTypePassword.setError("كلمة السر غير متطابقه!");

                    Toasty.error(Change_Password.this, "كلمة السر غير متطابقه!", Toast.LENGTH_SHORT).show();

                } else {

                    pg.setVisibility(View.VISIBLE);
                    new Apicalls(Change_Password.this,Change_Password.this).change_pasword(user_id,password.getText().toString(),newPassword.getText().toString(),reTypePassword.getText().toString());

                }

            }
        });
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        pg.setVisibility(View.GONE);

        Gson gson = new Gson();
        Model_Failed change_password = gson.fromJson(model.getResponse(), Model_Failed.class);

        if (change_password.getStatus() == 1) {

            Toasty.success(this, "" + change_password.getMessage(), Toast.LENGTH_LONG).show();
            Edit_Profile_Dialog profileDialog = new Edit_Profile_Dialog();
            profileDialog.dialog(this,R.layout.success_editprofile,.90);

        } else if (change_password.getStatus() == 2) {

            Toasty.error(this, "" + change_password.getMessage(), Toast.LENGTH_LONG).show();
            Edit_Profile_Dialog profileDialog = new Edit_Profile_Dialog();
            profileDialog.dialog(this,R.layout.faild_editprofile,.90);

        } else {
            Toasty.error(this, "" + change_password.getMessage(), Toast.LENGTH_LONG).show();
            Edit_Profile_Dialog profileDialog = new Edit_Profile_Dialog();
            profileDialog.dialog(this,R.layout.faild_editprofile,.90);
        }

    }

    @Override
    public void OnError(VolleyError error) {

        pg.setVisibility(View.GONE);
        Toasty.error(this, "" +error.toString(), Toast.LENGTH_LONG).show();

    }
}
