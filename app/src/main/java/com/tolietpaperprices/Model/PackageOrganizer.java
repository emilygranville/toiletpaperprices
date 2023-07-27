package com.tolietpaperprices.Model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that organized the packages
 *
 * @author Emily
 */
public class PackageOrganizer implements Serializable {
    List<TolietPaperPackage> listOfPackages;

    public PackageOrganizer() {
        listOfPackages = new LinkedList<TolietPaperPackage>();
    }

    public void addPackage(TolietPaperPackage tPackage) {
        double pricePerSquare = tPackage.getPricePerSquare();
        Iterator<TolietPaperPackage> iterator = listOfPackages.iterator();
        int index = 0;

        while (iterator.hasNext()) {
            TolietPaperPackage next = iterator.next();
            if (next.getPricePerSquare() >= pricePerSquare) {
                break;
            }
            index++;
        }
        listOfPackages.add(index, tPackage);
    }
}
