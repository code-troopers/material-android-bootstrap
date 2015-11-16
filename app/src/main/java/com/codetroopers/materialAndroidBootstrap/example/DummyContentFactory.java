package com.codetroopers.materialAndroidBootstrap.example;

import android.content.Context;

import com.codetroopers.materialAndroidBootstrap.R;

import hugo.weaving.DebugLog;

public class DummyContentFactory {

    private final Context context;

    public DummyContentFactory(Context context) {
        this.context = context;
    }

    @DebugLog
    public String getDummyContent() {
        return context.getString(R.string.hello_world);
    }
}
