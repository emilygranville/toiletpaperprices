package com.tolietpaperprices.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tolietpaperprices.R;
import com.tolietpaperprices.View.IMainView;
import com.tolietpaperprices.View.MainView;

public class MainActivity extends AppCompatActivity {
    IMainView mainView;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportFragmentManager().setFragmentFactory(new TPPFragmentFactory(this));
        super.onCreate(savedInstanceState);

        this.mainView = new MainView(this);
        setContentView(R.layout.activity_main);
    }
}