package com.codetroopers.materialAndroidBootstrap.core.components;

import com.codetroopers.materialAndroidBootstrap.core.modules.AndroidModule;
import com.codetroopers.materialAndroidBootstrap.core.modules.ApplicationModule;
import com.codetroopers.materialAndroidBootstrap.core.modules.HomeActivityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                AndroidModule.class
        }
)
public interface MockApplicationComponent extends ApplicationComponent {
        MockHomeActivityComponent mockHomeActivityComponent(HomeActivityModule mockHomeActivityModule);
}
