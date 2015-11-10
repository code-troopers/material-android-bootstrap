package com.codetroopers.materialAndroidBootstrap.ui.activity.core;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.codetroopers.materialAndroidBootstrap.R;
import com.codetroopers.materialAndroidBootstrap.core.AndroidBootstrapApplication;
import com.codetroopers.materialAndroidBootstrap.core.components.ApplicationComponent;

import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class BaseActionBarActivity extends ActionBarActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);
        if (mToolbar != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mToolbar.setElevation(getResources().getDimensionPixelSize(R.dimen.action_bar_elevation));
            }
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected final ApplicationComponent getApplicationComponent() {
        return ((AndroidBootstrapApplication) getApplication()).getComponent();
    }

    protected abstract int getLayoutResource();

    protected void setActionBarIcon(int iconRes) {
        mToolbar.setNavigationIcon(iconRes);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }
}
