package com.codetroopers.materialAndroidBootstrap.core;

import android.app.Activity;
import android.content.Context;

public abstract class RobotiumMockingTest<T extends Activity> extends RobotiumTest<T> {

    public RobotiumMockingTest(Class<T> activityClass) {
        super(activityClass);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        final Context context = getInstrumentation().getTargetContext();
        System.setProperty("dexmaker.dexcache", context.getCacheDir().getPath());
    }
}
