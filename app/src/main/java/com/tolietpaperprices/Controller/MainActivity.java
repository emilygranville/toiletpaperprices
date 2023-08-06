package com.tolietpaperprices.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.snackbar.Snackbar;
import com.tolietpaperprices.R;
import com.tolietpaperprices.View.AddTPView;
import com.tolietpaperprices.View.DisplayTPView;
import com.tolietpaperprices.View.IMainView;
import com.tolietpaperprices.View.MainView;

import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements IMainView.Listener {
    public String TPP = "tpp";
    IMainView mainView;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportFragmentManager().setFragmentFactory(new TPPFragmentFactory(this));
        super.onCreate(savedInstanceState);
        //Log.i("tpp", "hello");
        this.mainView = new MainView(this);
        setContentView(R.layout.activity_main);

        //Log.i("tpp", "on create");

        /*
        PackageOrganizer packageOrganizer = new PackageOrganizer();

        if (packageOrganizer.getListOfPackages() == null || packageOrganizer.getListOfPackages().isEmpty()) {
            Fragment addPackage = new AddTPView();
            this.mainView.displayFragment(addPackage, false, "add");
        } else {
            Fragment disaplayPackage = new DisplayTPView();
            this.mainView.displayFragment(disaplayPackage, false, "display");
        }

        */
    }

    /**
     * Method to switch to About page
     */
    @Override
    public void onAboutMenuButton() {
        Log.i(TPP, "about menu pressed");
    }

    /**
     * Method to switch to Display page
     */
    @Override
    public void onDisplayMenuButton() {
        Log.i(TPP, "display menu pressed");
    }

    /**
     * Method to switch to Add page
     */
    @Override
    public void onAddMenuButton() {
        Log.i(TPP, "add menu pressed");
    }

    public String toString() {
        return "Main activity";
    }
}