package com.tolietpaperprices.View;

import android.view.View;

import androidx.fragment.app.Fragment;

/**
 * Interface for main view
 *
 * @author
 */
public interface IMainView {

    /**
     * Returns highest root view of the screen
     */
    View getRootView();

    /**
     * Displays the fragment
     * @param fragment
     * @param allowBack whether back button can return to fragment
     * @param name
     */
    void displayFragment(Fragment fragment, boolean allowBack, String name);

    /**
     * Listener so that controller classes are altered to edits
     */
    interface Listener {
        /**
         * Method to switch to About page
         */
        public void onAboutMenuButton();

        /**
         * Method to switch to Display page
         */
        public void onDisplayMenuButton();

        /**
         * Method to switch to Add page
         */
        public void onAddMenuButton();
    }
}
