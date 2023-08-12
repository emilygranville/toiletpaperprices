package com.toiletpaperprices.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class containing information for one package of toilet paper
 *
 * @author Emily
 */
public class TPPackage implements Serializable, Comparable<TPPackage> {

    /**
     * Enum for the different toilet paper styles
     */
    public enum Style{
        SOFT, STRONG, NORMAL
    }

    private String brand;
    private Style style;
    private double price;
    private int numRolls;
    private int numSquaresPerRoll;
    private double pricePerSquare;
    private String storeName;
    private String storeTownName;
    private LocalDateTime dateOfEntry;

    /**
     * Default constructor
     * @param brand
     * @param style
     * @param price
     * @param numRolls
     * @param numSquaresPerRoll
     * @param storeName
     * @param storeTownName
     */
    public TPPackage(String brand, Style style, double price, int numRolls,
                     int numSquaresPerRoll, String storeName, String storeTownName) {
        this.brand = brand;
        this.style = style;
        this.price = price;
        this.numRolls = numRolls;
        this.numSquaresPerRoll = numSquaresPerRoll;
        this.storeName = storeName;
        this.storeTownName = storeTownName;
        this.dateOfEntry = LocalDateTime.now();
        this.pricePerSquare = this.price / (this.numSquaresPerRoll * this.numRolls);
    }

    /**
     * Getters and setters for ToiletPaperPackage
     */
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        calculatePricePerSquare();
    }

    public int getNumRolls() {
        return numRolls;
    }

    public void setNumRolls(int numRolls) {
        this.numRolls = numRolls;
        calculatePricePerSquare();
    }

    public int getNumSquaresPerRoll() {
        return numSquaresPerRoll;
    }

    public void setNumSquaresPerRoll(int numSquaresPerRoll) {
        this.numSquaresPerRoll = numSquaresPerRoll;
        calculatePricePerSquare();
    }

    public double getPricePerSquare() {
        return pricePerSquare;
    }

    public void calculatePricePerSquare() {
        this.pricePerSquare = this.price / (this.numSquaresPerRoll * this.numRolls);
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreTownName() {
        return storeTownName;
    }

    public void setStoreTownName(String storeTownName) {
        this.storeTownName = storeTownName;
    }

    public LocalDateTime getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(LocalDateTime dateOfEntry) {
        this.dateOfEntry = LocalDateTime.now();
    }

    /**
     * Compares the packages based price per square
     * @param o
     * @return 1 when the first object is more expensive; -1 when less; 0 when equal
     */
    @Override
    public int compareTo(TPPackage o) {
        if (this.pricePerSquare > o.getPricePerSquare()) {
            return 1;
        }
        if (this.pricePerSquare < o.getPricePerSquare()) {
            return -1;
        }
        return 0;
    }

    /**
     * Makes a string of the local date time
     * @return
     */
    public String dateToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d uuuu");
        return this.dateOfEntry.format(formatter);
    }

    /**
     * Makes a string of the class
     * @return
     */
    @Override
    public String toString() {
        return String.format("Brand: %s, Style: %s, Price: $%.2f, Num Rolls: %d, Squares: %d, " +
                        "Price per square: $%f, Store: %s, Town: %s, Date: %s",
                this.brand, this.style.toString(), this.price, this.numRolls,
                this.numSquaresPerRoll, this.pricePerSquare, this.storeName,
                this.storeTownName, dateToString());
    }
}
