package com.codetroopers.materialAndroidBootstrap.core;

import android.app.Application;
import android.util.Log;

import com.codetroopers.materialAndroidBootstrap.BuildConfig;
import com.codetroopers.materialAndroidBootstrap.core.components.ApplicationComponent;
import com.codetroopers.materialAndroidBootstrap.core.components.DaggerApplicationComponent;
import com.codetroopers.materialAndroidBootstrap.core.modules.ApplicationModule;

import timber.log.Timber;

public class AndroidBootstrapApplication extends Application implements HasComponent<ApplicationComponent> {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //Uncomment to add crashlytics
        //Fabric.with(this, new Crashlytics());

        initLoggers();
        applicationComponent = createInjector();
    }

    @Override
    public ApplicationComponent getComponent() {
        return applicationComponent;
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
    private ApplicationComponent createInjector() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}