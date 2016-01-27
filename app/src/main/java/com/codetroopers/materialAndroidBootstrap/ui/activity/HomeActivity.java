package com.codetroopers.materialAndroidBootstrap.ui.activity;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.codetroopers.materialAndroidBootstrap.R;
import com.codetroopers.materialAndroidBootstrap.core.HasComponent;
import com.codetroopers.materialAndroidBootstrap.core.components.ComponentsFactory;
import com.codetroopers.materialAndroidBootstrap.core.components.HomeActivityComponent;
import com.codetroopers.materialAndroidBootstrap.example.DummyContent;
import com.codetroopers.materialAndroidBootstrap.example.DummyContentFactory;
import com.codetroopers.materialAndroidBootstrap.ui.activity.core.BaseActionBarActivity;
import com.codetroopers.materialAndroidBootstrap.util.MenuItemUtil;
import com.codetroopers.materialAndroidBootstrap.util.Strings;
import com.codetroopers.materialAndroidBootstrap.util.UIUtils;

import javax.inject.Inject;

import butterknife.Bind;
import hugo.weaving.DebugLog;
import icepick.State;
import timber.log.Timber;

import static java.lang.String.format;

@DebugLog
public class HomeActivity extends BaseActionBarActivity implements
        DrawerLayout.DrawerListener,
        NavigationView.OnNavigationItemSelectedListener, HasComponent<HomeActivityComponent> {

    private HomeActivityComponent component;

    @Bind(R.id.drawer)
    DrawerLayout mDrawer;
    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;
    @Bind(R.id.content)
    TextView tvContent;

    @Inject
    DummyContentFactory dummyContentFactory;

    @State
    DummyContent dummyContent;
    @State
    @IdRes
    int mCurrentMenuItem;
    @State
    boolean mCurrentMenuItemChanged;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        component = ComponentsFactory.get().buildHomeActivityComponent(getApplicationComponent(), this);
        component.injectActivity(this);

        final String device = UIUtils.isTablet(this) ? "tablet" : "phone";
        Timber.d("Creating activity for a %s context...", device);
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

        if (savedInstanceState == null) {
            mCurrentMenuItem = R.id.nav_drawer_menu_1;
        }
        setupDrawer();
        if (dummyContent == null) {
            dummyContent = dummyContentFactory.getDummyContent();
        }
        tvContent.setText(format("[%s] %s", dummyContent.creationDate(), dummyContent.content()));
    }

    private void setupDrawer() {
        mDrawer.setDrawerListener(this);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.drawer_open, R.string.drawer_close);

        mDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);

        MenuItem firstItem = mNavigationView.getMenu().findItem(mCurrentMenuItem);
        firstItem.setCheckable(true);
        firstItem.setChecked(true);

        MenuItemUtil.setItemCounter(mNavigationView, R.id.nav_drawer_menu_1, 12);
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
    public HomeActivityComponent getComponent() {
        return component;
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        mDrawerToggle.onDrawerOpened(drawerView);
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        mDrawerToggle.onDrawerClosed(drawerView);

        if (mCurrentMenuItemChanged) {
            switch (mCurrentMenuItem) {
                //Do fragment replacement
                default:
            }
            mCurrentMenuItemChanged = false;
        }
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        mDrawerToggle.onDrawerStateChanged(newState);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        @IdRes int id = menuItem.getItemId();
        if (id == mCurrentMenuItem) {
            mCurrentMenuItemChanged = false;
        } else {
            selectNewMenuItem(menuItem);
        }
        mDrawer.closeDrawers();
        return false;
    }

    private void selectNewMenuItem(MenuItem menuItem) {
        mCurrentMenuItemChanged = true;
        menuItem.setCheckable(true);
        menuItem.setChecked(true);
        mNavigationView.getMenu().findItem(mCurrentMenuItem).setChecked(false);
        mCurrentMenuItem = menuItem.getItemId();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(mNavigationView)) {
            mDrawer.closeDrawers();
        } else {
            finish();
        }
    }
}
