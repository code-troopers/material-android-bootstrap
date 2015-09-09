package com.codetroopers.materialAndroidBootstrap.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;

import com.codetroopers.materialAndroidBootstrap.core.RobotiumMockingTest;
import com.codetroopers.materialAndroidBootstrap.core.components.DaggerMockApplicationComponent;
import com.codetroopers.materialAndroidBootstrap.core.components.MockHomeActivityComponent;
import com.codetroopers.materialAndroidBootstrap.core.modules.AndroidModule;
import com.codetroopers.materialAndroidBootstrap.core.modules.ApplicationModule;
import com.codetroopers.materialAndroidBootstrap.core.modules.HomeActivityModule;
import com.codetroopers.materialAndroidBootstrap.example.DummyContentFactory;
import com.robotium.solo.Solo;

import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import java.sql.SQLException;

import javax.inject.Inject;

import static org.mockito.Mockito.doReturn;
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

        final HomeActivity activity = getActivity();

        final AndroidModule mockAndroidModule = Mockito.spy(new AndroidModule());
        doReturn(mock(SharedPreferences.class))
                .when(mockAndroidModule)
                .provideDefaultSharedPreferences(Matchers.<Context>anyObject());

        final HomeActivityModule mockHomeActivityModule = Mockito.spy(new HomeActivityModule(activity));
        doReturn(mock(DummyContentFactory.class))
                .when(mockHomeActivityModule)
                .provideDummyContentFactory(Matchers.<Context>anyObject());

        final MockHomeActivityComponent mockHomeActivityComponent =
                DaggerMockApplicationComponent.builder()
                        .applicationModule(new ApplicationModule(activity.getApplication()))
                        .androidModule(mockAndroidModule)
                        .build()
                        .mockHomeActivityComponent(mockHomeActivityModule);

        mockHomeActivityComponent.injectInTest(this);

        Whitebox.setInternalState(activity, "component", mockHomeActivityComponent);

        reset(mockSharedPreferences, mockDummyContentFactory);
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
}
