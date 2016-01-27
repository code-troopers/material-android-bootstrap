package com.codetroopers.materialAndroidBootstrap.util;

import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codetroopers.materialAndroidBootstrap.R;

import timber.log.Timber;

public class MenuItemCounterUtil {

    public static void setItemCounter(NavigationView mNavigationView, int itemId, int counter) {

        MenuItem menuItem = mNavigationView.getMenu().findItem(itemId);

        if (menuItem != null) {
            try {
                LinearLayout menuItemCounter = (LinearLayout) menuItem.getActionView();
                if (menuItemCounter != null) {
                    TextView counterTextView = (TextView) menuItemCounter.findViewById(R.id.counter);
                    if (counter == 0) {
                        menuItemCounter.setVisibility(View.GONE);
                    } else {
                        menuItemCounter.setVisibility(View.VISIBLE);
                        counterTextView.setText(Integer.toString(counter));
                    }
                }
            } catch (ClassCastException e) {
                Timber.e(e, "Action view is not a CounterView for this item");
            }
        }
    }

}
