package com.toiletpaperprices.Controller;

import android.content.Context;

import com.toiletpaperprices.Model.PackageOrganizer;

/**
 * Interface for preserving data
 *
 * @author Emily
 */
public interface IDataPreservation {

    public void savePackageOrganizer(Context context, PackageOrganizer packageOrganizer);

    public PackageOrganizer loadPackageOrganizer(Context context);
}
