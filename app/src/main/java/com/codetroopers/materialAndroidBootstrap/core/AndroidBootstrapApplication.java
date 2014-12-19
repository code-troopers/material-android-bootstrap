package com.codetroopers.materialAndroidBootstrap.core;

import android.app.Application;

import java.util.Arrays;
import java.util.List;

public class AndroidBootstrapApplication extends android.app.Application {
    private static Application instance;

    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Injector.init(getModules().toArray());
    }

    public void inject(Object object) {
        Injector.inject(object);
    }

    protected List<Object> getModules() {
        return Arrays.asList(
                new AndroidModule(),
                new AndroidBootstrapModule()
        );
    }
}