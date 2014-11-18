package com.codetroopers.androidbootstrap.ui.activity.core;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.codetroopers.androidbootstrap.R;
import com.codetroopers.androidbootstrap.core.AndroidBootstrapApplication;

import butterknife.ButterKnife;
import butterknife.InjectView;

public abstract class BaseActionBarActivity extends ActionBarActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ((AndroidBootstrapApplication) getApplication()).inject(this);
        ButterKnife.inject(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                toolbar.setElevation(getResources().getDimensionPixelSize(R.dimen.action_bar_elevation));
            }
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected abstract int getLayoutResource();

    protected void setActionBarIcon(int iconRes) {
        toolbar.setNavigationIcon(iconRes);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }
}
