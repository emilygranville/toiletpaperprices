package com.tolietpaperprices.Controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.tolietpaperprices.Model.TPPackage;
import com.tolietpaperprices.View.AddTPView;
import com.tolietpaperprices.View.DisplayTPView;
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
public class MainActivity extends AppCompatActivity implements IMainView.Listener, IAddTPView.Listener, IDisplayTPView.Listener {
    public static final String IN_PROGRESS = "in progress";
    public static final String LIST_OF_PACKAGES_KEY = "list of packages key";
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
     * @param outPersistentState
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putBoolean(IN_PROGRESS, true);
    }

    /**
     * Restores instance when it was destroyed
     * @param savedInstanceState
     * @param persistentState
     */
    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
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
        Bundle fragArgs = new Bundle();
        fragArgs.putSerializable(LIST_OF_PACKAGES_KEY, (Serializable) this.packageOrganizer.getListOfPackages());
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

        Bundle fragArgs = new Bundle();
        fragArgs.putSerializable(LIST_OF_PACKAGES_KEY, (Serializable) this.packageOrganizer.getListOfPackages());
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
        Fragment addPackage = new AddTPView(this,
                this.packageOrganizer.getListOfPackages().get(index), index);
        this.mainView.displayFragment(addPackage, false, "add");
    }

    /**
     * Functionality for deleting a package
     * @param index
     */
    @Override
    public void deletePackageButton(int index) {
        this.packageOrganizer.deletePackage(index);
        Bundle fragArgs = new Bundle();
        fragArgs.putSerializable(LIST_OF_PACKAGES_KEY, (Serializable) this.packageOrganizer.getListOfPackages());
        Fragment displayPackage = new DisplayTPView(this);
        displayPackage.setArguments(fragArgs);
        this.mainView.displayFragment(displayPackage, true, "display");
    }
}