package com.codetroopers.materialAndroidBootstrap.core;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.robotium.solo.Solo;

import java.io.Serializable;

@SuppressWarnings("UnusedDeclaration")
@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public abstract class RobotiumTest<T extends Activity> extends ActivityInstrumentationTestCase2<T> {
    protected Solo solo;

    public enum Direction {
        LEFT, RIGHT
    }

    public RobotiumTest(final Class<T> activityClass) {
        super(activityClass);
    }

    @Override
    public void tearDown() throws Exception {
        solo.assertMemoryNotLow();
        solo.finishOpenedActivities();
    }

    protected final void waitForFragmentVisible(final String tag) {
        assertTrue(solo.waitForFragmentByTag(tag));
    }

    protected final Button assertButtonVisible(final int stringId) {
        final String stringValue = solo.getString(stringId);
        assertTrue(solo.searchButton(stringValue));
        final Button button = solo.getButton(stringValue);
        assertViewVisibleOnScreen(button);
        return button;
    }

    protected final void assertTextViewVisibleOnScreen(final int textId) {
        final String text = solo.getString(textId);
        // FIXME replace with a call to Strings utility class (which is not yet merged on this branch)
        assertTrue(isNotBlank(text));
        assertTextViewVisibleOnScreen(text);
    }

    protected final void assertTextViewVisibleOnScreen(final String text) {
        assertTrue(solo.searchText(text));
        final TextView textView = solo.getText(text, true);
        assertNotNull(textView);
        assertViewVisibleOnScreen(textView);
    }

    protected final void assertViewVisibleOnScreen(final View view) {
        assertTrue(view.isShown());
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        assertTrue("Expected view is not inside the screen", location[0] >= 0 && location[1] >= 0);
    }

    protected final View getView(final int id) {
        assertTrue(solo.waitForView(id));
        return solo.getView(id);
    }

    protected final Solo startActivity() {
        solo = new Solo(getInstrumentation(), getActivity());
        return solo;
    }

    protected final void assertFinishCalled() {
        solo.getActivityMonitor().waitForActivityWithTimeout(1000);
        assertTrue(solo.getCurrentActivity().isFinishing());
    }

    protected final void swipe(final Direction direction) {
        Point size = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(size);
        int width = size.x;
        float xStart = ((direction == Direction.RIGHT) ? (width - 10) : 10);
        float xEnd = ((direction == Direction.RIGHT) ? 10 : (width - 10));

        // The value for y doesn't change, as we want to swipe straight across
        solo.drag(xStart, xEnd, size.y / 2, size.y / 2, 1);
    }

    protected final Solo startActivityWithExtra(final String extraKey, final Serializable serializable) {
        Intent i = new Intent();
        i.putExtra(extraKey, serializable);
        setActivityIntent(i);
        return startActivity();
    }

    @Deprecated
    private static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    @Deprecated
    private static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
