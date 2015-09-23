package com.codetroopers.materialAndroidBootstrap.core;

import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

public class SingletonMockFactory {
    private static final Map<Class, Object> SINGLETONS = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T> T mock(Class<T> clazz) {
        if (!SINGLETONS.containsKey(clazz)) {
            SINGLETONS.put(clazz, Mockito.mock(clazz));
        }
        return (T) SINGLETONS.get(clazz);
    }
}
