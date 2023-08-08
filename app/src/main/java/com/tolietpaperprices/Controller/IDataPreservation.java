package com.tolietpaperprices.Controller;

import android.content.Context;

import com.tolietpaperprices.Model.PackageOrganizer;
import com.tolietpaperprices.Model.TPPackage;

/**
 * Interface for preserving data
 *
 * @author Emily
 */
public interface IDataPreservation {

    public void savePackageOrganizer(Context context, PackageOrganizer packageOrganizer);

    public PackageOrganizer loadPackageOrganizer(Context context);
}
