package com.tolietpaperprices.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.tolietpaperprices.R;
import com.tolietpaperprices.View.IMainView;
import com.tolietpaperprices.View.MainView;

public class MainActivity extends AppCompatActivity implements IMainView.Listener {
    IMainView mainView;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportFragmentManager().setFragmentFactory(new TPPFragmentFactory(this));
        super.onCreate(savedInstanceState);

        this.mainView = new MainView(this);
        setContentView(R.layout.activity_main);
    }

    /**
     * Method to switch to About page
     */
    @Override
    public void onAboutMenuButton() {
        Log.i("tpp", "about");
    }

    /**
     * Method to switch to Display page
     */
    @Override
    public void onDisplayMenuButton() {
        Log.i("tpp", "about");
    }

    /**
     * Method to switch to Add page
     */
    @Override
    public void onAddMenuButton() {
        Log.i("tpp", "about");
    }
}