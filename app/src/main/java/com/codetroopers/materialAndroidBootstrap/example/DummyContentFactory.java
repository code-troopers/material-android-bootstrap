package com.codetroopers.materialAndroidBootstrap.example;

import android.content.Context;

import com.codetroopers.materialAndroidBootstrap.R;

public class DummyContentFactory {

    private final Context context;

    public DummyContentFactory(Context context) {
        this.context = context;
    }

    public String getDummyContent() {
        return context.getString(R.string.hello_world);
    }
}
