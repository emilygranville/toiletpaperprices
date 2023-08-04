package com.tolietpaperprices.View;

import android.view.View;

import androidx.fragment.app.Fragment;

public interface IMainView {

    View getRootView();

    void displayFragment(Fragment fragment, boolean allowBack, String name);

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
