package com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.Doctor_Details_Fragment.Controller;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.besolutions.drbookingOriginal.R;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.SearchFragment.Controller.Search_Fragment;

public class reservation_dialog {

    public void dialog(final Context context, int resource, double widthh) {
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {


                FragmentTransaction fr = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new Search_Fragment());
                fr.commit();

                dialog.dismiss();
            }
        }, 4000);


    }
}
