package com.tolietpaperprices.View;

public interface IAddTPView {

    interface Listener {

        /**
         * Creates a new TPPackage based on given info,
         * adds it to the list, and exits page
         */
        public void onTPAddDoneButton();
    }
}
