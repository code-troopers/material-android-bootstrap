package com.codetroopers.materialAndroidBootstrap.core.components;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;

import com.codetroopers.materialAndroidBootstrap.core.AndroidBootstrapApplication;
import com.codetroopers.materialAndroidBootstrap.core.modules.AndroidModule;
import com.codetroopers.materialAndroidBootstrap.core.modules.ApplicationModule;
import com.codetroopers.materialAndroidBootstrap.core.modules.ForApplication;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                AndroidModule.class
        }
)
public interface ApplicationComponent {
    AndroidBootstrapApplication injectApplication(AndroidBootstrapApplication application);

    /*************
     * EXPOSED DEPENDENCIES
     ************/

    @ForApplication
    Context applicationContext();

    SharedPreferences sharedPreferences();

    LocationManager locationManager();
}