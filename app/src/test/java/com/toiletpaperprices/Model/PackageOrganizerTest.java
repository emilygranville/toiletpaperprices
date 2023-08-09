package com.toiletpaperprices.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.List;

class PackageOrganizerTest {

    TPPackage firstPackage = new TPPackage("Charmin",
            TPPackage.Style.SOFT, 5, 10, 6,
            "Target", "Town 1");
    TPPackage secondPackage = new TPPackage("Charmin",
            TPPackage.Style.STRONG, 3, 9, 8,
            "Target", "Town 2");
    TPPackage thirdPackage = new TPPackage("Charmin",
            TPPackage.Style.NORMAL, 10, 6, 11,
            "Target", "Town 3");

    PackageOrganizer organizer = new PackageOrganizer();

    void startOrganizer() {
        organizer.addPackage(firstPackage);
        organizer.addPackage(secondPackage);
        organizer.addPackage(thirdPackage);
    }
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
        startOrganizer();
        TPPackage newPackage = new TPPackage("Charmin",
                TPPackage.Style.STRONG, 3, 9, 8,
                "Target", "Town 5");
        organizer.editPackage(newPackage, 1);
        assertEquals("Town 5", organizer.getListOfPackages().get(1).getStoreTownName());
    }

    @Test
    void deletePackage() {
        startOrganizer();
        organizer.deletePackage(0);
        assertEquals(2, organizer.getListOfPackages().size());
        assertEquals("Town 1", organizer.getListOfPackages().get(0).getStoreTownName());
        assertEquals("Town 3", organizer.getListOfPackages().get(1).getStoreTownName());
    }

    @Test
    void sortPackagesPrice() {
    }

    @Test
    void sortPackagesStyle() {
    }

    @Test
    void softPackageList() {
        startOrganizer();
        List<TPPackage> softRolls = organizer.softPackageList();
        assertEquals(1, softRolls.size());
        assertEquals("Town 1", softRolls.get(0).getStoreTownName());
    }

    @Test
    void strongPackageList() {
        startOrganizer();
        List<TPPackage> strongRolls = organizer.strongPackageList();
        assertEquals(1, strongRolls.size());
        assertEquals("Town 2", strongRolls.get(0).getStoreTownName());
    }

    @Test
    void normalPackageList() {
        startOrganizer();
        List<TPPackage> normalRolls = organizer.normalPackageList();
        assertEquals(1, normalRolls.size());
        assertEquals("Town 3", normalRolls.get(0).getStoreTownName());
    }
}