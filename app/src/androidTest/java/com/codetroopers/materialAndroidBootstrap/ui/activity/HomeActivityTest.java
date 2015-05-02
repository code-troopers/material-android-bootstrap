package com.codetroopers.materialAndroidBootstrap.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;

import com.codetroopers.materialAndroidBootstrap.core.RobotiumMockingTest;
import com.codetroopers.materialAndroidBootstrap.core.modules.AndroidBootstrapModule;
import com.codetroopers.materialAndroidBootstrap.core.modules.AndroidModule;
import com.codetroopers.materialAndroidBootstrap.core.modules.ForApplication;
import com.codetroopers.materialAndroidBootstrap.example.DummyContentFactory;
import com.robotium.solo.Solo;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class HomeActivityTest extends RobotiumMockingTest<HomeActivity> {
    @Inject
    DummyContentFactory mockDummyContentFactory;
    @Inject
    SharedPreferences mockSharedPreferences;
    @Inject
    LocationManager locationManager;

    public HomeActivityTest() {
        super(HomeActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mockInjector.inject(this);
        reset(mockSharedPreferences, mockDummyContentFactory);
    }

    @Override
    protected AndroidModule getMockAndroidModule() {
        return new AndroidTestModule();
    }

    @Override
    protected AndroidBootstrapModule getMockAndroidBootstrapModule() {
        return new AndroidBootstrapTestModule();
    }

    public void testHomeActivity_example() throws SQLException {
        when(mockDummyContentFactory.getDummyContent()).thenReturn("Hello World from test!");

        final Solo solo = startActivity();
        solo.assertCurrentActivity("Current activity must be HomeActivity", HomeActivity.class, true);
        assertTextViewVisibleOnScreen("Hello World from test!");

        // we check that non mocked objects are still injectables
        assertNotNull(locationManager);

        verify(mockDummyContentFactory).getDummyContent();
        verifyNoMoreInteractions(mockDummyContentFactory, mockSharedPreferences);
    }

    /**
     * DAGGER injection for the mocks
     */

    @Module
    static class AndroidBootstrapTestModule extends AndroidBootstrapModule {
        @Provides
        @Singleton
        DummyContentFactory provideDummyContentFactory(@ForApplication final Context context) {
            return mock(DummyContentFactory.class);
        }
    }

    @Module
    static class AndroidTestModule extends AndroidModule {
        @Provides
        @Singleton
        SharedPreferences provideDefaultSharedPreferences(@ForApplication Context context) {
            return mock(SharedPreferences.class);
        }
    }
}
