package com.codetroopers.materialAndroidBootstrap.core.modules;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.codetroopers.materialAndroidBootstrap.core.components.ActivityScope;
import com.codetroopers.materialAndroidBootstrap.example.DummyContentFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeActivityModule {
    private final Activity activity;

    public HomeActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    public Activity provideActivity() {
        return activity;
    }

    // TODO put your application-specific providers here!

    @ActivityScope
    @Provides
    DummyContentFactory provideDummyContentFactory(@ForApplication Context context) {
        return getDummyContentFactory(context);
    }

    /**
     * Visible for testing
     */
    @NonNull
    protected DummyContentFactory getDummyContentFactory(@ForApplication Context context) {
        return new DummyContentFactory(context);
    }
}
