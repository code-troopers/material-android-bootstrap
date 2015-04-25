package com.codetroopers.materialAndroidBootstrap.core;

import android.app.Application;
import android.util.Log;

import com.codetroopers.materialAndroidBootstrap.BuildConfig;

import timber.log.Timber;

public class AndroidBootstrapApplication extends Application {
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
        } else {
            // only log INFO+ with no tag tracing the calling class
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    if (priority != Log.VERBOSE && priority != Log.DEBUG) {
                        Log.println(priority, tag, message);
                    }
                }
            });
        }
    }

    /**
     * Dagger component init
     */
    private Injector createInjector() {
        return DaggerInjector.create();
    }
}