package com.tolietpaperprices.Controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.tolietpaperprices.Model.PackageOrganizer;
import com.tolietpaperprices.Model.TPPackage;
import com.tolietpaperprices.View.AboutPageView;
import com.tolietpaperprices.View.AddTPView;
import com.tolietpaperprices.View.DisplayTPView;
import com.tolietpaperprices.View.IAboutPageView;
import com.tolietpaperprices.View.IAddTPView;
import com.tolietpaperprices.View.IDisplayTPView;
import com.tolietpaperprices.View.IMainView;
import com.tolietpaperprices.View.MainView;

import java.io.Serializable;

/**
 * Controller for the app
 * Acts as a bridge between the list and the infromation and the display
 *
 * @author Emily
 */
public class MainActivity extends AppCompatActivity implements IMainView.Listener, IAddTPView.Listener, IDisplayTPView.Listener, IAboutPageView.Listener {
    public static final String IN_PROGRESS = "in progress";
    public static final String LIST_OF_PACKAGES_KEY = "list of packages key";
    public static final String PACKAGE_ORGANIZER_KEY = "package organizer key";
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

        IDataPreservation loadData = new LocalDataPreservation();
        this.packageOrganizer = loadData.loadPackageOrganizer(this);

        if (savedInstanceState != null) {
            this.packageOrganizer = (PackageOrganizer) savedInstanceState.getSerializable(PACKAGE_ORGANIZER_KEY);
        }

        this.mainView = new MainView(this,this);
        setContentView(this.mainView.getRootView());

        if (savedInstanceState == null) {
            if (this.packageOrganizer.getListOfPackages() == null || this.packageOrganizer.getListOfPackages().isEmpty()) {
                Fragment addPackage = new AddTPView(this);
                this.mainView.displayFragment(addPackage, false, "add");
            } else {
                Bundle fragArgs = new Bundle();
                fragArgs.putSerializable(LIST_OF_PACKAGES_KEY, (Serializable) this.packageOrganizer.getListOfPackages());
                Fragment displayPackage = new DisplayTPView(this);
                displayPackage.setArguments(fragArgs);
                this.mainView.displayFragment(displayPackage, false, "display");
            }
        }
    }

    /**
     * Saves in progress when resource constraints are destroyed
     * @param outState
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(this.IN_PROGRESS, true);
        outState.putSerializable(this.PACKAGE_ORGANIZER_KEY, this.packageOrganizer);
    }

    /**
     * Restores instance when it was destroyed
     * @param savedInstanceState
     */
    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            this.packageOrganizer = (PackageOrganizer) savedInstanceState.getSerializable(this.PACKAGE_ORGANIZER_KEY);
        }
    }

    /**
     * Method to switch to About page
     */
    @Override
    public void onAboutMenuButton() {
        Log.i(TPP, "about menu pressed");
        Fragment aboutPage = new AboutPageView(this);
        this.mainView.displayFragment(aboutPage, true, "about");
    }

    /**
     * Method to switch to Display page
     */
    @Override
    public void onDisplayMenuButton() {
        Bundle fragArgs = new Bundle();
        fragArgs.putSerializable(this.LIST_OF_PACKAGES_KEY, (Serializable) this.packageOrganizer.getListOfPackages());
        Fragment displayPackage = new DisplayTPView(this);
        displayPackage.setArguments(fragArgs);
        this.mainView.displayFragment(displayPackage, false, "display");
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

        IDataPreservation saveData = new LocalDataPreservation();
        saveData.savePackageOrganizer(this, this.packageOrganizer);

        Bundle fragArgs = new Bundle();
        fragArgs.putSerializable(this.LIST_OF_PACKAGES_KEY, (Serializable) this.packageOrganizer.getListOfPackages());
        Fragment displayPackage = new DisplayTPView(this);
        displayPackage.setArguments(fragArgs);
        this.mainView.displayFragment(displayPackage, true, "display");
    }

    /**
     * Functionality for editing package information
     * @param index
     */
    @Override
    public void editPackageButton(int index) {
        Bundle fragArgs = new Bundle();
        fragArgs.putSerializable(AddTPView.PACKAGE_KEY, this.packageOrganizer.getListOfPackages().get(index));
        fragArgs.putSerializable(AddTPView.INDEX_KEY, index);
        Fragment addPackage = new AddTPView(this);
        addPackage.setArguments(fragArgs);
        this.mainView.displayFragment(addPackage, false, "add");
    }

    /**
     * Functionality for deleting a package
     * @param index
     */
    @Override
    public void deletePackageButton(int index) {
        this.packageOrganizer.deletePackage(index);

        IDataPreservation saveData = new LocalDataPreservation();
        saveData.savePackageOrganizer(this, this.packageOrganizer);

        Bundle fragArgs = new Bundle();
        fragArgs.putSerializable(this.LIST_OF_PACKAGES_KEY, (Serializable) this.packageOrganizer.getListOfPackages());
        Fragment displayPackage = new DisplayTPView(this);
        displayPackage.setArguments(fragArgs);
        this.mainView.displayFragment(displayPackage, true, "display");
    }
}