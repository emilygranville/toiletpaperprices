package com.tolietpaperprices.Controller;

import com.tolietpaperprices.Model.TPPackage;

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
    private List<TPPackage> listOfPackages;

    /**
     * Default constructor of PackageOrganizer
     */
    public PackageOrganizer() {
        listOfPackages = new LinkedList<TPPackage>();
    }

    /**
     * Getter for listOfPackages
     * @return
     */
    public List<TPPackage> getListOfPackages() {
        return listOfPackages;
    }

    /**
     * Adds new packages to the lists
     * Sorts the packages as it adds them
     * @param tPackage
     */
    public void addPackage(TPPackage tPackage) {
        double pricePerSquare = tPackage.getPricePerSquare();
        Iterator<TPPackage> iterator = listOfPackages.iterator();
        int index = 0;

        while (iterator.hasNext()) {
            TPPackage next = iterator.next();
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
    public void editPackage(TPPackage newPackage, int index) {
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
    public List<TPPackage> sortPackagesStyle() {
        List<TPPackage> total = new LinkedList<>();
        total.addAll(softPackageList());
        total.addAll(strongPackageList());
        total.addAll(normalPackageList());

        return total;
    }

    /**
     * Returns a list of packages with soft style
     * @return
     */
    public List<TPPackage> softPackageList() {
        List<TPPackage> softRolls = new LinkedList<>();
        for (TPPackage roll : this.listOfPackages) {
            if (roll.getStyle() == TPPackage.Style.SOFT) {
                softRolls.add(roll);
            }
        }

        return softRolls;
    }

    /**
     * Returns a list of packages with strong style
     * @return
     */
    public List<TPPackage> strongPackageList() {
        List<TPPackage> strongRolls = new LinkedList<>();
        for (TPPackage roll : this.listOfPackages) {
            if (roll.getStyle() == TPPackage.Style.STRONG) {
                strongRolls.add(roll);
            }
        }

        return strongRolls;
    }

    /**
     * Returns a list of packages with normal style
     * @return
     */
    public List<TPPackage> normalPackageList() {
        List<TPPackage> normalRolls = new LinkedList<>();
        for (TPPackage roll : this.listOfPackages) {
            if (roll.getStyle() == TPPackage.Style.NORMAL) {
                normalRolls.add(roll);
            }
        }

        return normalRolls;
    }
}
