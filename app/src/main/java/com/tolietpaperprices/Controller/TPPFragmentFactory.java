package com.tolietpaperprices.Controller;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TPPFragmentFactory extends FragmentFactory {
    private static final String PACKAGE_NAME = "com.tolietpaperprices.View";
    private final MainActivity controller;

    public TPPFragmentFactory(MainActivity controller) {
        this.controller = controller;
    }

    @NonNull
    @Override
    public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {

        Class<? extends Fragment> fragmentClass = loadFragmentClass(classLoader, className);

        if (fragmentClass.getPackage().getName().equals(PACKAGE_NAME)) {
            try {
                Constructor<?>[] constructors = fragmentClass.getConstructors();
                return (Fragment) constructors[0].newInstance(controller);
            } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        return super.instantiate(classLoader, className);
    }
}
