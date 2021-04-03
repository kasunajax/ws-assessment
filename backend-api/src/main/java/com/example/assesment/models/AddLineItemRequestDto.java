package com.example.assesment.models;

import java.util.List;

/**
 * This is a DTO (Data Transfer Object) used to accept the HTTP
 * request sent by the client to add new Items to the cart
 */
public class AddLineItemRequestDto {

    private List<LineItem> lineItems; // Current cart items already in the cart
    private LineItem newLineItem; // New or updated cart item that needs to be processed

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public LineItem getNewLineItem() {
        return newLineItem;
    }

    public void setNewLineItem(LineItem newLineItem) {
        this.newLineItem = newLineItem;
    }
}
