package com.example.assesment.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This class is JPA entity and it is used to store product information
 */
@Entity
public class Product {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private double price;
    private int unitsPerCarton;

    public Product() {
    }

    /**
     * Used to initialise a product object with its id
     * @param id the id of the product
     */
    public Product(int id) {
        this.id = id;
    }

    /**
     * Used to initialise with all params
     * @param id the id of the product
     * @param name the name of the product
     * @param price the carton price of the product
     * @param unitsPerCarton the no: of pieces in a carton
     */
    public Product(int id, String name, double price, int unitsPerCarton) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unitsPerCarton = unitsPerCarton;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getUnitsPerCarton() {
        return unitsPerCarton;
    }

    public void setUnitsPerCarton(int unitsPerCarton) {
        this.unitsPerCarton = unitsPerCarton;
    }

    /**
     * Check equality of two Product objects
     * @param product the product that needs to be checked for equality
     * @return whether the products are equal
     */
    public boolean equals(Product product) {
        return product.getId() == id;
    }
}
