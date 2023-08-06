package com.tolietpaperprices.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.tolietpaperprices.Model.TPPackage;
import com.tolietpaperprices.View.AddTPView;
import com.tolietpaperprices.View.DisplayTPView;
import com.tolietpaperprices.View.IAddTPView;
import com.tolietpaperprices.View.IMainView;
import com.tolietpaperprices.View.MainView;

public class MainActivity extends AppCompatActivity implements IMainView.Listener, IAddTPView.Listener {
    public String TPP = "tpp";
    private IMainView mainView;
    private PackageOrganizer packageOrganizer;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportFragmentManager().setFragmentFactory(new TPPFragmentFactory(this));
        super.onCreate(savedInstanceState);
        //Log.i("tpp", "hello");
        this.mainView = new MainView(this);
        setContentView(this.mainView.getRootView());

        //Log.i("tpp", "on create");

        this.packageOrganizer = new PackageOrganizer();

        if (this.packageOrganizer.getListOfPackages() == null || this.packageOrganizer.getListOfPackages().isEmpty()) {
            Fragment addPackage = new AddTPView(this);
            this.mainView.displayFragment(addPackage, false, "add");
        } else {
            Fragment disaplayPackage = new DisplayTPView();
            this.mainView.displayFragment(disaplayPackage, false, "display");
        }
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

    /**
     * Creates a new TPPackage based on given info,
     * adds it to the list, and exits page
     */
    @Override
    public void onTPAddDoneButton(TPPackage tpPackage) {
        Log.i(TPP, "on add button done: ");
        Log.i(TPP, tpPackage.toString());
    }
}