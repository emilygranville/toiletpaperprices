package com.tolietpaperprices.View;

public interface IMainView {

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
