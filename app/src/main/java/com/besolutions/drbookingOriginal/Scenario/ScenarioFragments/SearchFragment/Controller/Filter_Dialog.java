package com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.SearchFragment.Controller;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

public class Filter_Dialog {

    public void dialog(final Context context, int resource, double widthh, String type) {
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        dialog.show();


    }
}
