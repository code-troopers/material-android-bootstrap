package com.codetroopers.materialAndroidBootstrap.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.codetroopers.materialAndroidBootstrap.core.RobotiumMockingTest;
import com.codetroopers.materialAndroidBootstrap.core.components.ApplicationComponent;
import com.codetroopers.materialAndroidBootstrap.core.components.ComponentsFactory;
import com.codetroopers.materialAndroidBootstrap.core.components.DaggerApplicationComponent;
import com.codetroopers.materialAndroidBootstrap.core.components.HomeActivityComponent;
import com.codetroopers.materialAndroidBootstrap.core.modules.AndroidModule;
import com.codetroopers.materialAndroidBootstrap.core.modules.ApplicationModule;
import com.codetroopers.materialAndroidBootstrap.core.modules.ForApplication;
import com.codetroopers.materialAndroidBootstrap.core.modules.HomeActivityModule;
import com.codetroopers.materialAndroidBootstrap.example.DummyContentFactory;
import com.robotium.solo.Solo;

import java.sql.SQLException;

import dagger.Module;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class HomeActivityTest extends RobotiumMockingTest<HomeActivity> {

    DummyContentFactory mockDummyContentFactory;

    public HomeActivityTest() {
        super(HomeActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mockDummyContentFactory = mock(DummyContentFactory.class);
        ComponentsFactory.register(new ComponentsTestFactory());
    }

    public void testHomeActivity_example() throws SQLException {
        when(mockDummyContentFactory.getDummyContent()).thenReturn("Hello World from test!");

        final Solo solo = startActivity();
        solo.assertCurrentActivity("Current activity must be HomeActivity", HomeActivity.class, true);
        assertTextViewVisibleOnScreen("Hello World from test!");

        verify(mockDummyContentFactory).getDummyContent();
        verifyNoMoreInteractions(mockDummyContentFactory);
    }

    /**********************************************************************************************/


    private class ComponentsTestFactory extends ComponentsFactory {

        @Override
        public ApplicationComponent buildApplicationComponent(Context applicationContext) {
            return DaggerApplicationComponent
                    .builder()
                    .androidModule(new AndroidModule())
                    .applicationModule(new ApplicationModule(getInstrumentation().getContext()))
                    .build();
        }

        @Override
        public HomeActivityComponent buildHomeActivityComponent(ApplicationComponent applicationComponent, HomeActivity homeActivity) {
            return applicationComponent
                    .homeActivityComponent(new HomeActivityTestModule(homeActivity, mockDummyContentFactory));
        }
    }

    @Module
    public static class HomeActivityTestModule extends HomeActivityModule {
        private final DummyContentFactory mock;

        public HomeActivityTestModule(Activity activity, DummyContentFactory mock) {
            super(activity);
            this.mock = mock;
        }

        @NonNull
        @Override
        protected DummyContentFactory getDummyContentFactory(@ForApplication Context context) {
            return mock;
        }
    }
}
