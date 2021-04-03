package com.example.assesment.models;

/**
 * This class is a DTO (Data Transfer Object) and used to
 * expose the fields in the Product Entity to the Http Client
 */
public class ProductDto {

    private int id;
    private String name;
    private double price;
    private int unitsPerCarton;

    public ProductDto() {
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

    public static ProductDto from(Product product) {
        ProductDto dto = new ProductDto();
        dto.id = product.getId();
        dto.name = product.getName();
        dto.price = product.getPrice();
        dto.unitsPerCarton = product.getUnitsPerCarton();
        return dto;
    }

}
