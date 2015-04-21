package com.codetroopers.materialAndroidBootstrap.ui.activity;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.codetroopers.materialAndroidBootstrap.R;
import com.codetroopers.materialAndroidBootstrap.core.AndroidBootstrapApplication;
import com.codetroopers.materialAndroidBootstrap.example.DummyContentFactory;
import com.codetroopers.materialAndroidBootstrap.ui.activity.core.BaseActionBarActivity;
import com.codetroopers.materialAndroidBootstrap.util.Strings;
import com.codetroopers.materialAndroidBootstrap.util.UIUtils;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import timber.log.Timber;

public class HomeActivity extends BaseActionBarActivity implements DrawerAdapter.OnItemClickListener {

    @InjectView(R.id.drawer)
    DrawerLayout mDrawer;
    @InjectView(R.id.left_drawer)
    RecyclerView mDrawerList;

    @InjectView(R.id.content)
    TextView tvContent;

    @Inject
    DummyContentFactory dummyContentFactory;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerAdapter mAdapter;

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

        setupDrawer(savedInstanceState);

        tvContent.setText(dummyContentFactory.getDummyContent());
    }

    private void setupDrawer(Bundle savedInstanceState) {
        mAdapter = new DrawerAdapter(this);

        mDrawerList.setAdapter(mAdapter);
        // improve performance by indicating the list if fixed size.
        mDrawerList.setHasFixedSize(true);
        mDrawerList.setLayoutManager(new LinearLayoutManager(this));
        mDrawer.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);
        mDrawer.setStatusBarBackground(R.color.statusBarTransparentColor);
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
        if (savedInstanceState == null) {
            selectItem(0);
        }
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
        boolean drawerOpen = mDrawer.isDrawerOpen(mDrawerList);
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

    @Override
    public void onClick(View view, int position) {
        selectItem(position);
    }

    @OnClick(R.id.content)
    public void sayHi(TextView textView) {
        textView.setText("Hello!");
    }

    @OnItemSelected(R.id.list_contact)
    void onItemSelected(int position) {
        // TODO ...
        ListView listView = new ListView(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void selectItem(int position) {
        mDrawer.closeDrawer(mDrawerList);
        mAdapter.setActive(position);
    }
}
