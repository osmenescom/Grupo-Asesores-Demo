package com.asgeirr.grupoasesorestest.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

public class ScreenUtils {

    private ScreenUtils() {
        // This utility class is not publicly instantiable
    }

    public static float convertDpToPx(Context context, int dp) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                context.getResources().getDisplayMetrics());
    }
}
