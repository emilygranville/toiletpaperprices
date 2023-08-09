package com.toiletpaperprices.View;

import com.toiletpaperprices.Model.TPPackage;

/**
 * Interface for add view
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
        public void onTPAddDoneButton(TPPackage tpPackage, boolean isEditedPackage, int index);
    }
}
