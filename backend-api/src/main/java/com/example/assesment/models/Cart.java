package com.example.assesment.models;

import java.util.List;

public class Cart {

    private List<LineItem> lineItems;
    private double netTotal;

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
}
