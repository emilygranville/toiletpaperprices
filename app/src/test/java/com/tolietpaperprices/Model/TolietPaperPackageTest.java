package com.tolietpaperprices.Model;

import static org.junit.jupiter.api.Assertions.*;
import android.util.Log;
import org.junit.jupiter.api.Test;

class TolietPaperPackageTest {
    TolietPaperPackage firstPackage = new TolietPaperPackage("Charmin",
            TolietPaperPackage.Style.SOFT, 5, 10, 6,
            "Target", "Town 1");
    TolietPaperPackage secondPackage = new TolietPaperPackage("Charmin",
            TolietPaperPackage.Style.STRONG, 3, 9, 8,
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