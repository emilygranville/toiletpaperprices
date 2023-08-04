package com.tolietpaperprices.View;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View.OnClickListener;

import com.google.android.material.snackbar.Snackbar;
import com.tolietpaperprices.databinding.ActivityMainBinding;

public class MainView implements IMainView, IMainView.Listener{
    FragmentManager fragmentManager;
    ActivityMainBinding binding;
    Listener listener;

    /**
     * MainView constructor
     * @param activity android activity
     */
    public MainView(/*Listener listener,*/ FragmentActivity activity) {
        this.fragmentManager = activity.getSupportFragmentManager();
        this.binding = ActivityMainBinding.inflate(activity.getLayoutInflater());
        this.listener = (Listener) activity;

        Button aboutButton = this.binding.aboutMenuButton;
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainView.this.listener.onAboutMenuButton();
            }
        });

        Button displayButton = this.binding.displayMenuButton;
        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainView.this.listener.onDisplayMenuButton();
            }
        });

        Button addButton = this.binding.addMenuButton;
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainView.this.listener.onAddMenuButton();
            }
        });
    }

    @Override
    public View getRootView() {
        return this.binding.getRoot();
    }

    @Override
    public void displayFragment(Fragment fragment, boolean allowBack, String name) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.replace(this.binding.mainFragmentContainer.getId(), fragment);
        if (allowBack) {
            ft.addToBackStack(name);
        }
        ft.commit();
    }

    /**
     * Method to switch to About page
     */
    @Override
    public void onAboutMenuButton() {
        Log.i("tpp", "about work here?");
    }

    /**
     * Method to switch to Display page
     */
    @Override
    public void onDisplayMenuButton() {
        Log.i("tpp", "display work here?");
    }

    /**
     * Method to switch to Add page
     */
    @Override
    public void onAddMenuButton() {
        Log.i("tpp", "add work here?");
    }
}
