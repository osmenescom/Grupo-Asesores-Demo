package com.asgeirr.grupoasesorestest.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.asgeirr.grupoasesorestest.R;
import com.google.gson.GsonBuilder;

import java.text.NumberFormat;

public class CommonUtils {
    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static String formatNumber(double number, int fractions) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(number % 1 == 0 ? 0 : fractions);
        return numberFormat.format(number);
    }

    public static <T> String toJson(T object) {
        return object == null ? null : new GsonBuilder().create().toJson(object, object.getClass());
    }

    public static <T> T toObject(String json, Class clazz) {
        return json == null ? null : (T) new GsonBuilder().create().fromJson(json, clazz);
    }
}
