package com.codetroopers.materialAndroidBootstrap.ui.activity;

import android.app.Activity;
import android.content.Context;

import com.codetroopers.materialAndroidBootstrap.core.RobotiumMockingTest;
import com.codetroopers.materialAndroidBootstrap.core.components.ActivityScope;
import com.codetroopers.materialAndroidBootstrap.core.components.ApplicationComponent;
import com.codetroopers.materialAndroidBootstrap.core.components.ComponentsFactory;
import com.codetroopers.materialAndroidBootstrap.core.components.HomeActivityComponent;
import com.codetroopers.materialAndroidBootstrap.core.modules.AndroidModule;
import com.codetroopers.materialAndroidBootstrap.core.modules.ApplicationModule;
import com.codetroopers.materialAndroidBootstrap.core.modules.ForApplication;
import com.codetroopers.materialAndroidBootstrap.core.modules.HomeActivityModule;
import com.codetroopers.materialAndroidBootstrap.example.DummyContentFactory;
import com.robotium.solo.Solo;

import java.sql.SQLException;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;

import static com.codetroopers.materialAndroidBootstrap.core.SingletonMockFactory.mock;
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


    private static class ComponentsTestFactory extends ComponentsFactory {

        @Override
        public ApplicationComponent buildApplicationComponent(Context applicationContext) {
            return DaggerHomeActivityTest_ApplicationTestComponent
                    .builder()
                    .applicationModule(new ApplicationModule(applicationContext))
                    .build();
        }

        @Override
        public HomeActivityComponent buildHomeActivityComponent(ApplicationComponent applicationComponent, HomeActivity homeActivity) {
            return ((ApplicationTestComponent) applicationComponent)
                    .homeActivityTestComponent(new HomeActivityTestModule(homeActivity));
        }
    }

    /****************************************
     * Custom Component for test injections *
     ****************************************/

    @Singleton
    @Component(
            modules = {
                    ApplicationModule.class,
                    AndroidModule.class
            }
    )
    public interface ApplicationTestComponent extends ApplicationComponent {
        HomeActivityTestComponent homeActivityTestComponent(HomeActivityModule homeActivityModule);
    }

    @ActivityScope
    @Subcomponent(
            modules = {
                    HomeActivityModule.class
            }
    )
    public interface HomeActivityTestComponent extends HomeActivityComponent {
    }

    /********************
     * Mocking Modules  *
     ********************/

    public static class HomeActivityTestModule extends HomeActivityModule {
        public HomeActivityTestModule(Activity activity) {
            super(activity);
        }

        @Override
        protected DummyContentFactory provideDummyContentFactory(@ForApplication Context context) {
            return mock(DummyContentFactory.class);
        }
    }
}
