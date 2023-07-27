package com.tolietpaperprices.Model;

import java.time.LocalDateTime;

/**
 * Class containing information for one package of toliet paper
 *
 * @author Emily
 */
public class TolietPaperPackage {

    String brand;
    String style; //soft, heavy duty, normal, etc
    double price;
    int numRolls;
    int numSquaresPerRoll;
    int pricePerSquare;
    String storeName;
    String storeTown;
    LocalDateTime dateOfEntry;


}
