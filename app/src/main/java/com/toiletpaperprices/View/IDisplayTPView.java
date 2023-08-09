package com.toiletpaperprices.View;

/**
 * Interface for display view
 *
 * @author Emily
 */
public interface IDisplayTPView {

    /**
     * Listener so that controller classes are altered to edits
     */
    interface Listener {
        /**
         * Functionality for editing package information
         * @param index
         */
        public void editPackageButton(int index);

        /**
         * Functionality for deleting a package
         * @param index
         */
        public void deletePackageButton(int index);
    }
}
