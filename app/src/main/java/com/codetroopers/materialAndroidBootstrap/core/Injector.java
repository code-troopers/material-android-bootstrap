package com.codetroopers.materialAndroidBootstrap.core;

import com.codetroopers.materialAndroidBootstrap.core.modules.AndroidBootstrapModule;
import com.codetroopers.materialAndroidBootstrap.core.modules.AndroidModule;
import com.codetroopers.materialAndroidBootstrap.ui.activity.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AndroidModule.class,
                AndroidBootstrapModule.class
        }
)
public interface Injector {
    void inject(HomeActivity activity);
}
