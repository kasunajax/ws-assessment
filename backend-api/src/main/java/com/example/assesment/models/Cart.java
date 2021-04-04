package com.example.assesment.models;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class holds the list of cart items aka line items and
 * the netTotal of all the cartItems added
 */
public class Cart {

    private List<LineItem> lineItems;
    private double netTotal = 0;

    public Cart() {
    }

    /**
     * Can be used to initialize with a list of line items
     * @param lineItems list of line items aka cart items
     */
    public Cart(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(double netTotal) {
        this.netTotal = netTotal;
    }

    public void addLineItem(LineItem newItem) {
        this.lineItems.add(newItem);
    }

    /**
     * Checks whether the product is in the cart already
     * @param product the product that needs to be checked whether it is in the cart
     * @return whether the product is in the cart already
     */
    public boolean isProductInTheCart(Product product) {
        List<Product> list = lineItems.stream().map(lineItem -> lineItem.getProduct()).collect(Collectors.toList());
        return list.stream().anyMatch(product::equals);
    }

    /**
     * Finds the line item that corresponds to the product in the cart
     * @param product the product that is in the cart
     * @return the line item that corresponds to the product in the cart
     */
    public LineItem getLineItemFromCart(Product product) {
        return lineItems.stream().filter(lineItem -> lineItem.getProduct().equals(product)).findFirst().get();
    }

    public void calculateNetTotal() {
        lineItems.forEach(lineItem -> this.netTotal += lineItem.getLineItemTotal());
    }
}
