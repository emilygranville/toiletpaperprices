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

    /**
     * Returns a list of packages sorted by price and style
     * @return
     */
    public List<TolietPaperPackage> sortPackagesStyle() {
        List<TolietPaperPackage> total = new LinkedList<>();
        total.addAll(softPackageList());
        total.addAll(strongPackageList());
        total.addAll(normalPackageList());

        return total;
    }

    /**
     * Returns a list of packages with soft style
     * @return
     */
    public List<TolietPaperPackage> softPackageList() {
        List<TolietPaperPackage> softRolls = new LinkedList<>();
        for (TolietPaperPackage roll : this.listOfPackages) {
            if (roll.style == TolietPaperPackage.Style.SOFT) {
                softRolls.add(roll);
            }
        }

        return softRolls;
    }

    /**
     * Returns a list of packages with strong style
     * @return
     */
    public List<TolietPaperPackage> strongPackageList() {
        List<TolietPaperPackage> strongRolls = new LinkedList<>();
        for (TolietPaperPackage roll : this.listOfPackages) {
            if (roll.style == TolietPaperPackage.Style.STRONG) {
                strongRolls.add(roll);
            }
        }

        return strongRolls;
    }

    /**
     * Returns a list of packages with normal style
     * @return
     */
    public List<TolietPaperPackage> normalPackageList() {
        List<TolietPaperPackage> normalRolls = new LinkedList<>();
        for (TolietPaperPackage roll : this.listOfPackages) {
            if (roll.style == TolietPaperPackage.Style.NORMAL) {
                normalRolls.add(roll);
            }
        }

        return normalRolls;
    }
}
