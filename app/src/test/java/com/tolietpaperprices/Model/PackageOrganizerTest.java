package com.tolietpaperprices.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PackageOrganizerTest {

    TolietPaperPackage firstPackage = new TolietPaperPackage("Charmin",
            TolietPaperPackage.Style.SOFT, 5, 10, 6,
            "Target", "Town 1");
    TolietPaperPackage secondPackage = new TolietPaperPackage("Charmin",
            TolietPaperPackage.Style.STRONG, 3, 9, 8,
            "Target", "Town 2");
    TolietPaperPackage thirdPackage = new TolietPaperPackage("Charmin",
            TolietPaperPackage.Style.NORMAL, 10, 6, 11,
            "Target", "Town 3");

    PackageOrganizer organizer = new PackageOrganizer();

    @Test
    void addPackage() {
        assertEquals(0, organizer.getListOfPackages().size());
        organizer.addPackage(firstPackage);
        assertEquals(1, organizer.getListOfPackages().size());
        organizer.addPackage(secondPackage);
        organizer.addPackage(thirdPackage);
        assertEquals(3, organizer.getListOfPackages().size());
    }

    @Test
    void editPackage() {
    }

    @Test
    void deletePackage() {
    }

    @Test
    void sortPackagesPrice() {
    }

    @Test
    void sortPackagesStyle() {
    }

    @Test
    void softPackageList() {
    }

    @Test
    void strongPackageList() {
    }

    @Test
    void normalPackageList() {
    }
}