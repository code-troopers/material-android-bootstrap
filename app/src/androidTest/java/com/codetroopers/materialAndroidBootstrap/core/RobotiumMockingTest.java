package com.codetroopers.materialAndroidBootstrap.core;

import android.app.Activity;
import android.content.Context;

import dagger.ObjectGraph;

public abstract class RobotiumMockingTest<T extends Activity> extends RobotiumTest<T> {
    public RobotiumMockingTest(Class<T> activityClass) {
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
