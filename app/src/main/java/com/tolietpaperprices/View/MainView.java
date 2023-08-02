package com.tolietpaperprices.View;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.tolietpaperprices.R;
import com.tolietpaperprices.databinding.ActivityMainBinding;

public class MainView implements IMainView {
    FragmentManager fragmentManager;
    ActivityMainBinding binding;

    /**
     * MainView constructor
     * @param activity android activity
     */
    public MainView(FragmentActivity activity) {
        this.fragmentManager = activity.getSupportFragmentManager();
        this.binding = ActivityMainBinding.inflate(activity.getLayoutInflater());
    }
}
