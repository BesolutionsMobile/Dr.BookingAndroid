package com.besolutions.drbookingOriginal.Scenario;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.Window;

public class Edit_Profile_Dialog {

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

                dialog.dismiss();
                ((Activity) context).finish();
            }
        }, 4000);


    }
}
