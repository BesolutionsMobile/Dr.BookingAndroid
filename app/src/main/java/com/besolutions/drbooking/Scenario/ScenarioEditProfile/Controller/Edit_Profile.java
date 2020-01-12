package com.besolutions.drbooking.Scenario.ScenarioEditProfile.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.drbooking.NetworkLayer.Apicalls;
import com.besolutions.drbooking.NetworkLayer.NetworkInterface;
import com.besolutions.drbooking.NetworkLayer.ResponseModel;
import com.besolutions.drbooking.R;
import com.besolutions.drbooking.Scenario.Dialog_Anim;
import com.besolutions.drbooking.Scenario.Edit_Profile_Dialog;
import com.besolutions.drbooking.Scenario.ScenarioChangePassword.Controller.Change_Password;
import com.besolutions.drbooking.Scenario.ScenarioFragments.ProfileFragment.Model.ModelEditProfile;
import com.besolutions.drbooking.Scenario.ScenarioSignIn.Model.Model_Failed;
import com.besolutions.drbooking.local_data.saved_data;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class Edit_Profile extends AppCompatActivity implements NetworkInterface {

    CircleImageView circleImageView;
    Bitmap SelectedPhoto;
    Button edit;
    EditText userName, phone, email;
    TextView changepassword;
    ProgressBar pg;
    String user_id;
    int x = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        userName = (EditText) findViewById(R.id.username);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        pg = findViewById(R.id.progressbar);
        circleImageView = (CircleImageView) findViewById(R.id.editimg);
        user_id = saved_data.get_user_id(this);
        x = 1;
        pg.setVisibility(View.VISIBLE);
        new Apicalls(this, Edit_Profile.this).user_profile(user_id);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(i, "Select Your Photo"), 1);
            }
        });

        edit = (Button) findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userName.getText().toString().equals("")) {
                    userName.setError("من فضلك ادخل الاسم !");
                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.username));

                    Toasty.error(Edit_Profile.this, "من فضلك ادخل الاسم!", Toast.LENGTH_SHORT).show();

                } else if (email.getText().toString().equals("")) {

                    email.setError("من فضلك ادخل البريد الالكتروني !");
                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.email));

                    Toasty.error(Edit_Profile.this, "من فضلك ادخل البريد الالكتروني!", Toast.LENGTH_SHORT).show();


                } else if (phone.getText().toString().equals("")) {
                    phone.setError("من فضلك ادخل رقم الهاتف !");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.phone));

                    Toasty.error(Edit_Profile.this, "من فضلك ادخل رقم الهاتف!", Toast.LENGTH_SHORT).show();

                } else {
                    x = 2;
                    String username = userName.getText().toString();
                    username = username.replaceAll(" " , "%20");
                    new Apicalls(Edit_Profile.this, Edit_Profile.this).edit_profile(user_id, username, email.getText().toString(), phone.getText().toString());

                }

            }
        });
        changepassword = (TextView) findViewById(R.id.changepassword);
        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Edit_Profile.this, Change_Password.class));
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
// if(resultCode == RESULT_OK) تعني ان كان قد تم الحصول على البيانات بدون مشاكل
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Uri selectedImage = data.getData();
                InputStream imageStream = null;
                try {
                    imageStream = getContentResolver().openInputStream(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                SelectedPhoto = BitmapFactory.decodeStream(imageStream);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                SelectedPhoto.compress(Bitmap.CompressFormat.PNG, 100, os);
                circleImageView.setImageBitmap(SelectedPhoto);
            }
        }
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        pg.setVisibility(View.GONE);
        if (x == 1) {

            Gson gson = new Gson();
            ModelEditProfile profile = gson.fromJson(model.getResponse(), ModelEditProfile.class);
            if (profile.getStatus() == 1) {

                userName.setText(profile.getUser().getName());
                phone.setText(profile.getUser().getPhone());
                email.setText(profile.getUser().getMail());

            } else if (profile.getStatus() == 2) {

                Gson gson1 = new Gson();
                Model_Failed failed = gson1.fromJson(model.getResponse(), Model_Failed.class);
                Toasty.error(this, "" + failed.getMessage(), Toast.LENGTH_LONG).show();

            } else if (profile.getStatus() == 3) {

                Gson gson3 = new Gson();
                Model_Failed failed1 = gson3.fromJson(model.getResponse(), Model_Failed.class);
                Toasty.error(this, "" + failed1.getMessage(), Toast.LENGTH_LONG).show();


            }

        } else if (x == 2) {
            Gson gson = new Gson();
            Model_Failed profile_edit = gson.fromJson(model.getResponse(), Model_Failed.class);
            if (profile_edit.getStatus() == 1) {
                Toasty.success(this, "" + profile_edit.getMessage(), Toast.LENGTH_LONG).show();
                Edit_Profile_Dialog profileDialog = new Edit_Profile_Dialog();
                profileDialog.dialog(this, R.layout.success_editprofile, .90);
            } else if (profile_edit.getStatus() == 2) {

                Toasty.error(this, "" + profile_edit.getMessage(), Toast.LENGTH_LONG).show();
                Edit_Profile_Dialog profileDialog = new Edit_Profile_Dialog();
                profileDialog.dialog(this, R.layout.faild_editprofile, .90);
            } else if (profile_edit.getStatus() == 3) {

                Toasty.error(this, "" + profile_edit.getMessage(), Toast.LENGTH_LONG).show();
                Edit_Profile_Dialog profileDialog = new Edit_Profile_Dialog();
                profileDialog.dialog(this, R.layout.faild_editprofile, .90);
            }

        }
    }

    @Override
    public void OnError(VolleyError error) {
        pg.setVisibility(View.GONE);
        Toast.makeText(this, ""+error.toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
