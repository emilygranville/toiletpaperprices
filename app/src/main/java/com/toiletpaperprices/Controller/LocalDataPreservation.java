package com.toiletpaperprices.Controller;

import android.content.Context;
import android.util.Log;

import com.toiletpaperprices.Model.PackageOrganizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class for saving data locally
 *
 * @author Emily
 */
public class LocalDataPreservation implements IDataPreservation {

    public static final String PACKAGE_ORGANIZER_FILE_NAME = "package organizer file name";

    @Override
    public void savePackageOrganizer(Context context, PackageOrganizer packageOrganizer) {
        File outFile = new File(context.getFilesDir(), this.PACKAGE_ORGANIZER_FILE_NAME);
        try {
            FileOutputStream fileOutStream = new FileOutputStream(outFile);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutStream);
            objectOutStream.writeObject(packageOrganizer);
        } catch (IOException e) {
            final String emsg = String.format("I/O error writing to %s", outFile);
            Log.e(MainActivity.TPP, emsg);
            e.printStackTrace();
        }
    }

    @Override
    public PackageOrganizer loadPackageOrganizer(Context context) {
        File inFile = new File(context.getFilesDir(), this.PACKAGE_ORGANIZER_FILE_NAME);
        if (inFile.isFile()) {
            try {
                FileInputStream fileInStream = new FileInputStream(inFile);
                ObjectInputStream objectInStream = new ObjectInputStream(fileInStream);
                return (PackageOrganizer) objectInStream.readObject();
            } catch (IOException e) {
                final String emsg = String.format("I/O error reading from %s", inFile);
                Log.e(MainActivity.TPP, emsg);
                e.printStackTrace();
                return new PackageOrganizer();
            } catch (ClassNotFoundException e) {
                final String emsg = String.format("Can't find class of object from", inFile);
                Log.e(MainActivity.TPP, emsg);
                e.printStackTrace();
                return new PackageOrganizer();
            }
        }
        return new PackageOrganizer();
    }
}
