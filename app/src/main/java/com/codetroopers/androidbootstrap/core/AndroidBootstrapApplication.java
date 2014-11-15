package com.codetroopers.androidbootstrap.core;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

public class AndroidBootstrapApplication extends android.app.Application {
    private ObjectGraph graph;

    @Override
    public void onCreate() {
        super.onCreate();

        graph = ObjectGraph.create(getModules().toArray());
    }

    protected List<Object> getModules() {
        return Arrays.asList(
                new AndroidModule(this),
                new AndroidBootstrapModule()
        );
    }

    public void inject(Object object) {
        graph.inject(object);
    }
}