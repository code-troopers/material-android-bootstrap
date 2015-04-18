package com.codetroopers.materialAndroidBootstrap.core.components;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;

import com.codetroopers.materialAndroidBootstrap.core.modules.AndroidModule;
import com.codetroopers.materialAndroidBootstrap.core.modules.ForApplication;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AndroidModule.class)
public interface SingletonComponent {
    @ForApplication
    Context applicationContext();

    LocationManager locationManager();

    SharedPreferences defaultSharedPreferences();
}
