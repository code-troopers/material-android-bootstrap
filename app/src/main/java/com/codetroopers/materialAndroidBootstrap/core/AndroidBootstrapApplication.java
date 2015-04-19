package com.codetroopers.materialAndroidBootstrap.core;

import android.app.Application;

import com.codetroopers.materialAndroidBootstrap.BuildConfig;

import timber.log.Timber;

public class AndroidBootstrapApplication extends android.app.Application {
    private static Application instance;
    private Injector injector;

    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Uncomment to add crashlytics
        //Fabric.with(this, new Crashlytics());
        instance = this;

        initLoggers();
        injector = createInjector();
    }

    public Injector injector() {
        return injector;
    }

    /**
     * Timber init
     */
    private void initLoggers() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } /** else {
         // A tree which logs important information for crash reporting
         // custom implementations can be inserted by extending HollowTree
         Timber.plant(new Timber.HollowTree() {...});
         } **/
    }

    /**
     * Dagger component init
     */
    private Injector createInjector() {
        return DaggerInjector.create();
    }
}