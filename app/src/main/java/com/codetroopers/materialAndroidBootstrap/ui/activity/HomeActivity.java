package com.codetroopers.materialAndroidBootstrap.ui.activity;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.codetroopers.materialAndroidBootstrap.R;
import com.codetroopers.materialAndroidBootstrap.core.AndroidBootstrapApplication;
import com.codetroopers.materialAndroidBootstrap.example.DummyContentFactory;
import com.codetroopers.materialAndroidBootstrap.ui.activity.core.BaseActionBarActivity;
import com.codetroopers.materialAndroidBootstrap.util.Strings;
import com.codetroopers.materialAndroidBootstrap.util.UIUtils;

import javax.inject.Inject;

import butterknife.InjectView;
import timber.log.Timber;

public class HomeActivity extends BaseActionBarActivity {

    @InjectView(R.id.drawer)
    DrawerLayout mDrawer;
    @InjectView(R.id.navigation_view)
    NavigationView mNavigationView;

    @InjectView(R.id.content)
    TextView tvContent;

    @Inject
    DummyContentFactory dummyContentFactory;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AndroidBootstrapApplication) getApplication()).injector().inject(this);

        if (UIUtils.isTablet(this)) {
            Timber.d("Creating activity for a tablet context...");
        } else {
            Timber.d("Creating activity for a phone context...");
        }
        /**
         * examples of {@link #com.codetroopers.materialAndroidBootstrap.util.Strings} utilities methods
         */
        Timber.d(Strings.joinAnd(", ", " and ",
                Strings.namedFormat("Android SDK = $sdkVersion ($release)",
                        "sdkVersion", Build.VERSION.SDK_INT,
                        "release", Build.VERSION.RELEASE),
                Strings.namedFormat("CODENAME = $codeName",
                        "codeName", Build.VERSION.CODENAME),
                Strings.namedFormat("INCREMENTAL = $incremental",
                        "incremental", Build.VERSION.INCREMENTAL)));

        setupDrawer();
        tvContent.setText(dummyContentFactory.getDummyContent());
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, getToolbar(), R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawer.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_home;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawer.isDrawerOpen(mNavigationView);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(Gravity.START);
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
