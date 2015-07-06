package com.codetroopers.materialAndroidBootstrap.core.components;

import com.codetroopers.materialAndroidBootstrap.core.modules.HomeActivityModule;
import com.codetroopers.materialAndroidBootstrap.ui.activity.HomeActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(
        modules = {
                HomeActivityModule.class
        }
)
public interface HomeActivityComponent {
    void injectActivity(HomeActivity homeActivity);
}
