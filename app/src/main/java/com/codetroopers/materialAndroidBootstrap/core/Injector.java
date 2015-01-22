package com.codetroopers.materialAndroidBootstrap.core;

import com.google.common.annotations.VisibleForTesting;

import dagger.ObjectGraph;

@VisibleForTesting
public class Injector {

    public static ObjectGraph objectGraph = null;

    public static void init(Object... modules) {
        if (objectGraph == null) {
            objectGraph = ObjectGraph.create(modules);
        } else {
            objectGraph = objectGraph.plus(modules);
        }
    }

    public static void inject(Object object) {
        objectGraph.inject(object);
    }
}
