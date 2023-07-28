package com.tolietpaperprices.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.List;

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
        TolietPaperPackage newPackage = new TolietPaperPackage("Charmin",
                TolietPaperPackage.Style.STRONG, 3, 9, 8,
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
        List<TolietPaperPackage> softRolls = organizer.softPackageList();
        assertEquals(1, softRolls.size());
        assertEquals("Town 1", softRolls.get(0).getStoreTownName());
    }

    @Test
    void strongPackageList() {
        startOrganizer();
        List<TolietPaperPackage> strongRolls = organizer.strongPackageList();
        assertEquals(1, strongRolls.size());
        assertEquals("Town 2", strongRolls.get(0).getStoreTownName());
    }

    @Test
    void normalPackageList() {
        startOrganizer();
        List<TolietPaperPackage> normalRolls = organizer.normalPackageList();
        assertEquals(1, normalRolls.size());
        assertEquals("Town 3", normalRolls.get(0).getStoreTownName());
    }
}