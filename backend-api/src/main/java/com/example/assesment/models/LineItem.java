package com.example.assesment.models;

public class LineItem {

    private Product product;
    private int quantity;

    private int numberOfCartons;
    private int numberOfUnits;

    private boolean discounted;

    private double lineItemTotal;

    public LineItem() {
    }

    public LineItem(Product product, int numberOfCartons, int numberOfUnits) {
        this.product = product;
        this.numberOfCartons = numberOfCartons;
        this.numberOfUnits = numberOfUnits;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getNumberOfCartons() {
        return numberOfCartons;
    }

    public void setNumberOfCartons(int numberOfCartons) {
        this.numberOfCartons = numberOfCartons;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public double getLineItemTotal() {
        return lineItemTotal;
    }

    public void setLineItemTotal(double lineItemTotal) {
        this.lineItemTotal = lineItemTotal;
    }

    public boolean isDiscounted() {
        return discounted;
    }

    public void setDiscounted(boolean discounted) {
        this.discounted = discounted;
    }
}
