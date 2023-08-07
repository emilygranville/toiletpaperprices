package com.tolietpaperprices.View;

import com.tolietpaperprices.Model.TPPackage;

/**
 * Intferface for add view
 *
 * @author Emily
 */
public interface IAddTPView {

    /**
     * Listener so that controller classes are alerted to edits
     */
    interface Listener {

        /**
         * Creates a new TPPackage based on given info,
         * adds it to the list, and exits page
         */
        public void onTPAddDoneButton(TPPackage tpPackage);
    }
}
