package com.tolietpaperprices.View;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class MainView implements IMainView {
    FragmentManager fragmentManager;

    /**
     * MainView constructor
     * @param activity android activity
     */
    public MainView(FragmentActivity activity) {
        this.fragmentManager = activity.getSupportFragmentManager();
    }
}
