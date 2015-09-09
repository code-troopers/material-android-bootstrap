package com.codetroopers.materialAndroidBootstrap.core.components;

import com.codetroopers.materialAndroidBootstrap.core.modules.HomeActivityModule;
import com.codetroopers.materialAndroidBootstrap.ui.activity.HomeActivityTest;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(
        modules = {
                HomeActivityModule.class
        }
)
public interface MockHomeActivityComponent extends HomeActivityComponent {
    void injectInTest(HomeActivityTest homeActivityTest);
}
