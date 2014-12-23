package com.codetroopers.materialAndroidBootstrap.util;

import android.content.Context;
import android.content.res.Configuration;

@SuppressWarnings("UnusedDeclaration")
public class UIUtils {

    /**
     * Helps determine if the app is running in a Tablet context.
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
