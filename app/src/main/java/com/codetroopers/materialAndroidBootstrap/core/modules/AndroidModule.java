package com.codetroopers.materialAndroidBootstrap.core.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.preference.PreferenceManager;

import com.codetroopers.materialAndroidBootstrap.core.AndroidBootstrapApplication;

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
    /**
     * Allow the application context to be injected but require that it be annotated with
     * {@link ForApplication @Annotation} to explicitly differentiate it from an activity context.
     */
    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext() {
        return AndroidBootstrapApplication.getInstance();
    }

    @Provides
    @Singleton
    LocationManager provideLocationManager(@ForApplication final Context context) {
        return (LocationManager) context.getSystemService(LOCATION_SERVICE);
    }

    @Provides
    SharedPreferences provideDefaultSharedPreferences(@ForApplication final Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}