package com.codetroopers.materialAndroidBootstrap.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;

import com.codetroopers.materialAndroidBootstrap.core.ForApplication;
import com.codetroopers.materialAndroidBootstrap.core.MockingActivityTest;
import com.codetroopers.materialAndroidBootstrap.core.TestRootModule;
import com.codetroopers.materialAndroidBootstrap.example.DummyContentFactory;

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

public class HomeActivityTest extends MockingActivityTest<HomeActivity> {
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
        reset(mockSharedPreferences, mockDummyContentFactory);
    }

    @Override
    protected Object getTestModule() {
        return new TestModule();
    }

    public void testHomeActivity_example() throws SQLException {
        when(mockDummyContentFactory.getDummyContent()).thenReturn("Hello World from test!");

        // start the activity...
        getActivity();

        // we check that non mocked objects are still injectables
        assertNotNull(locationManager);

        verify(mockDummyContentFactory).getDummyContent();
        verifyNoMoreInteractions(mockDummyContentFactory);
        verifyNoMoreInteractions(mockSharedPreferences);
    }

    /**
     * DAGGER injection for the mocks
     */
    @Module(
            includes = TestRootModule.class,
            injects = HomeActivityTest.class,
            overrides = true
    )
    static class TestModule {
        @SuppressWarnings("UnusedParameters")
        @Provides
        @Singleton
        DummyContentFactory provideDummyContentFactory(@ForApplication final Context context) {
            return mock(DummyContentFactory.class);
        }

        @Provides
        @Singleton
        SharedPreferences provideSharedPreferences() {
            return mock(SharedPreferences.class);
        }
    }
}
