package com.example.assesment.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private double price;

    private int unitsPerCarton;

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
}
