package edu.upc.dsa.models;

public class Product {

    String name;
    double price;
    private int numSells;
    public Product() {
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.numSells = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setNumSells(int numSells) {
        this.numSells = numSells;
    }

    public int getNumSells() {
        this.numSells = numSells;
        return numSells;
    }

    public void addNumSells(int sells){
        this.numSells =+ sells;
    }
}