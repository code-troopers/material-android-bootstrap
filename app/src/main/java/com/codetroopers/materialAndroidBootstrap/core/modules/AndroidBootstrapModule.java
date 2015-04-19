package com.codetroopers.materialAndroidBootstrap.core.modules;

import android.content.Context;

import com.codetroopers.materialAndroidBootstrap.example.DummyContentFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidBootstrapModule {
    // TODO put your application-specific providers here!

    @Singleton
    @Provides
    DummyContentFactory provideDummyContentFactory(@ForApplication Context context) {
        return new DummyContentFactory(context);
    }
}
