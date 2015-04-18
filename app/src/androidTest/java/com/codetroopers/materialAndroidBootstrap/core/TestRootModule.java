package com.codetroopers.materialAndroidBootstrap.core;

import com.codetroopers.materialAndroidBootstrap.core.modules.AndroidBootstrapModule;
import com.codetroopers.materialAndroidBootstrap.core.modules.AndroidModule;

import dagger.Module;

/**
 * Add all the other modules to this one.
 * It allows to override providers for mocking purposes without being confronted to dependency graph problems.
 */
@Module(includes = {AndroidModule.class, AndroidBootstrapModule.class})
public class TestRootModule {
}
