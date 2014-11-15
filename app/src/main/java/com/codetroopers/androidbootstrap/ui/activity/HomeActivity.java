package com.codetroopers.androidbootstrap.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.codetroopers.androidbootstrap.R;
import com.codetroopers.androidbootstrap.ui.activity.core.BaseActionBarActivity;

import butterknife.InjectView;

public class HomeActivity extends BaseActionBarActivity {

    @InjectView(R.id.drawer)
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarIcon(R.drawable.ic_drawer);

        drawer.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);
        drawer.setStatusBarBackground(R.color.statusBarTransparentColor);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(Gravity.START);
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
