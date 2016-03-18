package com.codetroopers.materialAndroidBootstrap;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.PowerManager;
import android.support.test.InstrumentationRegistry;

import org.junit.rules.ExternalResource;

public class WakeLockRule extends ExternalResource {
    private PowerManager.WakeLock mWakeLock;

    @SuppressWarnings("deprecation")
    @Override
    protected void before() throws Throwable {
        Context app = InstrumentationRegistry.getInstrumentation().getTargetContext();
        // The API docs recommend using LayoutParams.FLAG_TURN_SCREEN_ON, etc, but by the time we
        // have a chance to set them Activity.onCreate() has already been called and the flags will
        // not have an effect on the activity (they must be set prior to calling

        // Unlock the screen
        KeyguardManager keyguard = (KeyguardManager) app.getSystemService(Context.KEYGUARD_SERVICE);
        keyguard.newKeyguardLock(getClass().getSimpleName()).disableKeyguard();

        // Start a wake lock
        PowerManager power = (PowerManager) app.getSystemService(Context.POWER_SERVICE);
        mWakeLock = power.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, getClass().getSimpleName());
        mWakeLock.acquire();
    }

    @Override
    protected void after() {
        if (mWakeLock != null && mWakeLock.isHeld()) {
            mWakeLock.release();
        }
    }
}
