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

/**
 * Controller for the app
 * Acts as a bridge between the list and the infromation and the display
 *
 * @author Emily
 */
public class MainActivity extends AppCompatActivity implements IMainView.Listener, IAddTPView.Listener, IDisplayTPView.Listener {
    public static String TPP = "tpp";
    private IMainView mainView;
    private PackageOrganizer packageOrganizer;

    /**
     * Sets up the app
     * @param savedInstanceState
     */
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
        Fragment disaplayPackage = new DisplayTPView(this, this.packageOrganizer.getListOfPackages());
        this.mainView.displayFragment(disaplayPackage, false, "display");
    }

    /**
     * Method to switch to Add page
     */
    @Override
    public void onAddMenuButton() {
        Fragment addPackage = new AddTPView(this);
        this.mainView.displayFragment(addPackage, true, "add");
    }

    /**
     * Creates a new TPPackage based on given info,
     * adds it to the list, and exits page
     */
    @Override
    public void onTPAddDoneButton(TPPackage tpPackage, boolean isEditedPackage, int index) {
        if (isEditedPackage) {
            this.packageOrganizer.editPackage(tpPackage, index);
        } else {
            this.packageOrganizer.addPackage(tpPackage);
        }
        Fragment disaplayPackage = new DisplayTPView(this, this.packageOrganizer.getListOfPackages());
        this.mainView.displayFragment(disaplayPackage, true, "display");
    }

    /**
     * Functionality for editing package information
     * @param index
     */
    @Override
    public void editPackageButton(int index) {
        Fragment editPackage = new AddTPView(this,
                this.packageOrganizer.getListOfPackages().get(index), index);
        this.mainView.displayFragment(editPackage, false, "add");
    }

    /**
     * Functionality for deleting a package
     * @param index
     */
    @Override
    public void deletePackageButton(int index) {
        this.packageOrganizer.deletePackage(index);
        Fragment disaplayPackage = new DisplayTPView(this, this.packageOrganizer.getListOfPackages());
        this.mainView.displayFragment(disaplayPackage, true, "display");
    }
}