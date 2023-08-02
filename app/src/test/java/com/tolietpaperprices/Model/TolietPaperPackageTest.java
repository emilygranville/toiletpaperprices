package com.tolietpaperprices.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TolietPaperPackageTest {
    TPPackage firstPackage = new TPPackage("Charmin",
            TPPackage.Style.SOFT, 5, 10, 6,
            "Target", "Town 1");
    TPPackage secondPackage = new TPPackage("Charmin",
            TPPackage.Style.STRONG, 3, 9, 8,
            "Target", "Town 2");

    @Test
    void compareTo() {
        System.out.println("first price " + String.valueOf(firstPackage.getPricePerSquare()));
        System.out.println("second price " + String.valueOf(secondPackage.getPricePerSquare()));
        
        assertEquals(1, firstPackage.compareTo(secondPackage));
        assertEquals(0, secondPackage.compareTo(secondPackage));
        assertEquals(-1, secondPackage.compareTo(firstPackage));
    }
}