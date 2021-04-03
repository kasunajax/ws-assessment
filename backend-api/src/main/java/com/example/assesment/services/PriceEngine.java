package com.example.assesment.services;

import com.example.assesment.models.Cart;
import com.example.assesment.models.LineItem;
import com.example.assesment.models.Product;
import com.example.assesment.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceEngine {

    private static final double LABOUR_INCREMENT_PERCENTAGE = 1.3;
    private static final double PURCHASE_DISCOUNT_PERCENTAGE = 0.9;
    private static final int NO_OF_CARTONS_REQUIRED_FOR_DISCOUNT = 3;

    private ProductRepository productRepository;

    @Autowired
    public PriceEngine(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll(){
        List<Product> list = new ArrayList<>();
        Product p1 = new Product();
        p1.setId(1);
        p1.setPrice(175);
        p1.setUnitsPerCarton(20);
        p1.setName("Penguin Ears");

        Product p2 = new Product();
        p2.setId(2);
        p2.setPrice(825);
        p2.setUnitsPerCarton(5);
        p2.setName("Horse Shoe");

        list.add(p1);
        list.add(p2);

        return list;
    }

    public boolean isPresent(Cart cart, LineItem newItem) {
        List<Product> list = cart.getLineItems().stream().map(lineItem -> lineItem.getProduct()).collect(Collectors.toList());
        return list.stream().anyMatch(product -> product.getId() == newItem.getProduct().getId());
    }

    private Product getProduct(LineItem item) {
        return getAll().stream().filter(product -> product.getId() == item.getProduct().getId()).findFirst().get();
    }

    public LineItem findLineItem(Cart cart, LineItem newItem) {
        LineItem item = cart.getLineItems().stream().filter(product -> product.getProduct().getId() == newItem.getProduct().getId()).collect(Collectors.toList()).get(0);
        return item;
    }

    public void optimizePrice(LineItem item) {
        Product lineProduct = getProduct(item);

        int totalQuantity = item.getNumberOfCartons() * lineProduct.getUnitsPerCarton() + item.getNumberOfUnits();

        int newNumberOfCartons = totalQuantity / lineProduct.getUnitsPerCarton();
        int newNumberOfUnits = totalQuantity % lineProduct.getUnitsPerCarton();

        item.setNumberOfCartons(newNumberOfCartons);
        item.setNumberOfUnits(newNumberOfUnits);

    }

    private double getUnitPrice(double pricePerCarton, double unitsPerCarton) {
        return pricePerCarton / unitsPerCarton * LABOUR_INCREMENT_PERCENTAGE;
    }

    public double calculateLineTotal(LineItem item) {
        double lineTotal = 0;
        Product lineProduct = getProduct(item);

        double pricePerCarton = lineProduct.getPrice();
        item.setDiscounted(false);

        if(item.getNumberOfCartons() >= NO_OF_CARTONS_REQUIRED_FOR_DISCOUNT) {
            pricePerCarton = pricePerCarton * PURCHASE_DISCOUNT_PERCENTAGE;
            item.setDiscounted(true);
        }

        double pricePerUnit = getUnitPrice(pricePerCarton, lineProduct.getUnitsPerCarton());

        double cartonPriceTotal = item.getNumberOfCartons() * pricePerCarton;
        double unitPriceTotal = item.getNumberOfUnits() * pricePerUnit;

        lineTotal = cartonPriceTotal + unitPriceTotal;
        item.setLineItemTotal(lineTotal);

        return lineTotal;
    }

    public Cart calculatePrice(Cart cart, LineItem newItem) {

        double total = 0;


        if(this.isPresent(cart, newItem)) {

            if(newItem.getNumberOfCartons() == 0 && newItem.getNumberOfUnits() == 0) {
                cart.getLineItems().removeIf(lineItem -> lineItem.getProduct().getId() == newItem.getProduct().getId());
            } else {
                LineItem item = findLineItem(cart, newItem);
                item.setNumberOfCartons(newItem.getNumberOfCartons());
                item.setNumberOfUnits(newItem.getNumberOfUnits());

            }

        } else {
            cart.addLineItem(newItem);
        }

        for(LineItem lineItem: cart.getLineItems()) {
            optimizePrice(lineItem);
            total += calculateLineTotal(lineItem);
        }

        cart.setNetTotal(total);
        return cart;
    }


}
