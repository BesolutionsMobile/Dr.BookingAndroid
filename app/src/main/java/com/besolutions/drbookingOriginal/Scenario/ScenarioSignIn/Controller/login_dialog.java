package com.besolutions.drbookingOriginal.Scenario.ScenarioSignIn.Controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.Window;

import com.besolutions.drbookingOriginal.Scenario.ScenarioHome.Controller.MainActivity;
import com.besolutions.drbookingOriginal.local_data.saved_data;

public class login_dialog {
    public void dialog(final Context context, int resource, double widthh) {
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        dialog.setCanceledOnTouchOutside(false);
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        dialog.show();



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                String id = saved_data.get_user_id(context);
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("id", id);
                context.startActivity(intent);
                ((Activity) context).finish();
                dialog.dismiss();
            }
        }, 4000);


    }
}
