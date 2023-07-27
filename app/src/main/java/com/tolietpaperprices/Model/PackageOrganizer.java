package com.tolietpaperprices.Model;

import java.io.Serializable;
import java.util.Collections;
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

    /**
     * Adds new packages to the lists
     * Sorts the packages as it adds them
     * @param tPackage
     */
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

    /**
     * Replaces existing package with the new package with updated information
     * @param newPackage
     * @param index
     */
    public void editPackage(TolietPaperPackage newPackage, int index) {
        listOfPackages.remove(index);
        listOfPackages.set(index, newPackage);
    }

    /**
     * Removes package at index
     * @param index
     */
    public void deletePackage(int index) {
        listOfPackages.remove(index);
    }

    public void sortPackagesPrice() {
        Collections.sort(listOfPackages);
    }

    public List<TolietPaperPackage> sortPackagesStyle() {
        List<TolietPaperPackage> softRolls = new LinkedList<>();
        List<TolietPaperPackage> strongRolls = new LinkedList<>();
        List<TolietPaperPackage> normalRolls = new LinkedList<>();

        for (TolietPaperPackage roll : this.listOfPackages) {
            if (roll.style == TolietPaperPackage.Style.SOFT) {
                softRolls.add(roll);
            } else if (roll.style == TolietPaperPackage.Style.STRONG) {
                strongRolls.add(roll);
            } else {
                normalRolls.add(roll);
            }
        }

        List<TolietPaperPackage> total = new LinkedList<>();
        total.addAll(softRolls);
        total.addAll(strongRolls);
        total.addAll(normalRolls);

        return total;
    }
}
