package com.codetroopers.materialAndroidBootstrap.core.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.LOCATION_SERVICE;

/**
 * A module for Android-specific dependencies which require a {@link Context} or
 * {@link android.app.Application} to create.
 */
@Module
public class AndroidModule {
    @Provides
    @Singleton
    LocationManager provideLocationManager(@ForApplication final Context context) {
        return (LocationManager) context.getSystemService(LOCATION_SERVICE);
    }

    @Provides
    SharedPreferences provideDefaultSharedPreferences(@ForApplication final Context context) {
        return getDefaultSharedPreferences(context);
    }

    /**
     * Visible for testing
     */
    protected SharedPreferences getDefaultSharedPreferences(@ForApplication Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}