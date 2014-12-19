package com.codetroopers.materialAndroidBootstrap.core;

import android.app.Activity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

import dagger.ObjectGraph;

public abstract class MockingActivityTest<T extends Activity> extends ActivityInstrumentationTestCase2<T> {
    public MockingActivityTest(Class<T> activityClass) {
        super(activityClass);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        final Context context = getInstrumentation().getTargetContext();
        System.setProperty("dexmaker.dexcache", context.getCacheDir().getPath());

        // init for DAGGER
        Injector.objectGraph = ObjectGraph.create(getTestModule());
        Injector.inject(this);
    }

    protected abstract Object getTestModule();
}
