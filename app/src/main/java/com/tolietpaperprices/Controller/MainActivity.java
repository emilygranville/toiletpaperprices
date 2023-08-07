package com.tolietpaperprices.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.tolietpaperprices.Model.TPPackage;
import com.tolietpaperprices.View.AddTPView;
import com.tolietpaperprices.View.DisplayTPView;
import com.tolietpaperprices.View.IAddTPView;
import com.tolietpaperprices.View.IDisplayTPView;
import com.tolietpaperprices.View.IMainView;
import com.tolietpaperprices.View.MainView;

public class MainActivity extends AppCompatActivity implements IMainView.Listener, IAddTPView.Listener, IDisplayTPView.Listener {
    public static String TPP = "tpp";
    private IMainView mainView;
    private PackageOrganizer packageOrganizer;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportFragmentManager().setFragmentFactory(new TPPFragmentFactory(this));
        super.onCreate(savedInstanceState);

        this.mainView = new MainView(this,this);
        setContentView(this.mainView.getRootView());

        this.packageOrganizer = new PackageOrganizer();

        if (this.packageOrganizer.getListOfPackages() == null || this.packageOrganizer.getListOfPackages().isEmpty()) {
            Fragment addPackage = new AddTPView(this);
            this.mainView.displayFragment(addPackage, false, "add");
        } else {
            Fragment disaplayPackage = new DisplayTPView(this, this.packageOrganizer.getListOfPackages());
            this.mainView.displayFragment(disaplayPackage, false, "display");
        }

        /* test
        TPPackage firstPackage = new TPPackage("Charmin",
                    TPPackage.Style.SOFT, 5, 10, 6,
                    "Target", "Town 1");
        TPPackage secondPackage = new TPPackage("Charmin",
                    TPPackage.Style.STRONG, 3, 9, 8,
                    "Target", "Town 2");
        packageOrganizer.addPackage(firstPackage);
        packageOrganizer.addPackage(secondPackage);
        
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
        Fragment disaplayPackage = new DisplayTPView(this, this.packageOrganizer.getListOfPackages());
        this.mainView.displayFragment(disaplayPackage, false, "display");
    }

    /**
     * Method to switch to Add page
     */
    @Override
    public void onAddMenuButton() {
        Log.i(TPP, "add menu pressed");
        Fragment addPackage = new AddTPView(this);
        this.mainView.displayFragment(addPackage, true, "add");
    }

    /**
     * Creates a new TPPackage based on given info,
     * adds it to the list, and exits page
     */
    @Override
    public void onTPAddDoneButton(TPPackage tpPackage) {
        Log.i(TPP, "on add button done: ");
        Log.i(TPP, tpPackage.toString());
        packageOrganizer.addPackage(tpPackage);
        Fragment disaplayPackage = new DisplayTPView(this, this.packageOrganizer.getListOfPackages());
        this.mainView.displayFragment(disaplayPackage, true, "display");
    }

    @Override
    public void editPackageButton(int index) {
        Log.i(TPP, "hi edit! index: " + String.valueOf(index));
    }

    @Override
    public void deletePackageButton(int index) {
        Log.i(TPP, "hi delete! index: " + String.valueOf(index));
    }
}