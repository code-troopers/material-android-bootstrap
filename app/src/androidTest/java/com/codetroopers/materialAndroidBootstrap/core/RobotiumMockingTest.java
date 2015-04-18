package com.codetroopers.materialAndroidBootstrap.core;

import android.app.Activity;
import android.content.Context;

import com.codetroopers.materialAndroidBootstrap.core.modules.AndroidBootstrapModule;
import com.codetroopers.materialAndroidBootstrap.core.modules.AndroidModule;

import org.mockito.internal.util.reflection.Whitebox;

public abstract class RobotiumMockingTest<T extends Activity> extends RobotiumTest<T> {

    protected MockInjector mockInjector;

    public RobotiumMockingTest(Class<T> activityClass) {
        super(activityClass);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        final Context context = getInstrumentation().getTargetContext();
        System.setProperty("dexmaker.dexcache", context.getCacheDir().getPath());

        // init for DAGGER
        mockInjector = DaggerMockInjector.builder()
                .androidBootstrapModule(getMockAndroidBootstrapModule())
                .androidModule(getMockAndroidModule())
                .build();

        Whitebox.setInternalState(AndroidBootstrapApplication.getInstance(), "injector", mockInjector);
    }

    protected AndroidModule getMockAndroidModule() {
        return new AndroidModule();
    }

    protected AndroidBootstrapModule getMockAndroidBootstrapModule() {
        return new AndroidBootstrapModule();
    }
}
