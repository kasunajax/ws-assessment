package com.example.assesment.services;

import com.example.assesment.models.Cart;
import com.example.assesment.models.LineItem;
import com.example.assesment.models.Product;
import com.example.assesment.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A service class that contains the business logic related
 * to price calculation
 */
@Service
public class PriceEngine {

    private static final double LABOUR_INCREMENT_PERCENTAGE = 1.3; // Rate applied for units that are taken in addition to cartons
    private static final double PURCHASE_DISCOUNT_PERCENTAGE = 0.9; // Rate applied for carton price when 3 or more cartons are in the cart
    private static final int NO_OF_CARTONS_REQUIRED_FOR_DISCOUNT = 3; // Minimum no: of cartons required to receive the discounted rate

    private ProductRepository productRepository;

    @Autowired
    public PriceEngine(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public void optimizeLineItem(LineItem lineItem) {

        /*
        Here, the product object that corresponds to the line item is directly fetched from the database
        for calculations by its id.
         */
        Product lineProduct = productRepository.findById(lineItem.getProduct().getId()).get();

        int totalQuantity = lineItem.getNumberOfCartons() * lineProduct.getUnitsPerCarton() + lineItem.getNumberOfUnits();
        int newNumberOfCartons = totalQuantity / lineProduct.getUnitsPerCarton();
        int newNumberOfUnits = totalQuantity % lineProduct.getUnitsPerCarton();

        lineItem.setNumberOfCartons(newNumberOfCartons);
        lineItem.setNumberOfUnits(newNumberOfUnits);
    }

    public void optimizeCart(Cart cart) {
        cart.getLineItems().forEach(this::optimizeLineItem);
    }

    public void calculateNetTotal(Cart cart) {
        cart.getLineItems().forEach(this::calculateLineTotal);
        cart.calculateNetTotal();
    }

    public void calculateLineTotal(LineItem item) {

        /*
        Here, the product object that corresponds to the line item is directly fetched from the database
        for calculations by its id.
         */
        Product lineProduct = productRepository.findById(item.getProduct().getId()).get();

        double pricePerCarton = lineProduct.getPrice();
        item.setDiscounted(false);

        // Check if carton price should be discounted
        if(item.getNumberOfCartons() >= NO_OF_CARTONS_REQUIRED_FOR_DISCOUNT) {
            pricePerCarton = pricePerCarton * PURCHASE_DISCOUNT_PERCENTAGE;
            item.setDiscounted(true);
        }

        // Calculate total cost of purchased cartons
        double unitsPerCarton = lineProduct.getUnitsPerCarton();
        double cartonPriceTotal = item.getNumberOfCartons() * pricePerCarton;

        // Calculate total cost of purchased units
        double pricePerUnit = pricePerCarton / unitsPerCarton * LABOUR_INCREMENT_PERCENTAGE;
        double unitPriceTotal = item.getNumberOfUnits() * pricePerUnit;

        // Calculate line total due to cartons and units purchased
        double lineTotal = cartonPriceTotal + unitPriceTotal;
        item.setLineItemTotal(lineTotal);
    }

    /**
     * Calculates the Price
     * @param cart the existing cart
     * @param newItem the line item that needs to be processed (ie. Add/Remove/Update in the cart)
     */
    public void calculatePrice(Cart cart, LineItem newItem) {

        // Checks if Product is already in the cart.
        if(cart.isProductInTheCart(newItem.getProduct())) {

            if(newItem.getNumberOfCartons() == 0 && newItem.getNumberOfUnits() == 0) {
                // If newItem contains 0 cartons and 0 pieces, then find and remove it from the cart
                cart.getLineItems().removeIf(lineItem -> lineItem.getProduct().getId() == newItem.getProduct().getId());
            } else {
                // Else get the existing line item and update
                LineItem item = cart.getLineItemFromCart(newItem.getProduct());
                item.setNumberOfCartons(newItem.getNumberOfCartons());
                item.setNumberOfUnits(newItem.getNumberOfUnits());
            }

        } else {
            // Else add the line Item as a new LineItem to the cart
            cart.addLineItem(newItem);
        }

        // Optimizing the carton and pieces selection by the user
        optimizeCart(cart);

        // Calculate the net total by summing up all the line totals
        calculateNetTotal(cart);

    }


}
