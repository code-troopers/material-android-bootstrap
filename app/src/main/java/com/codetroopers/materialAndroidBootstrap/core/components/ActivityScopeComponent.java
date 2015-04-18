package com.codetroopers.materialAndroidBootstrap.core.components;

import com.codetroopers.materialAndroidBootstrap.core.modules.AndroidBootstrapModule;
import com.codetroopers.materialAndroidBootstrap.ui.activity.HomeActivity;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = SingletonComponent.class,
        modules = AndroidBootstrapModule.class
)
public interface ActivityScopeComponent {
    void inject(HomeActivity activity);
}
