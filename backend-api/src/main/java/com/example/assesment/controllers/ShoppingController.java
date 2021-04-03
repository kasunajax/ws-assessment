package com.example.assesment.controllers;

import com.example.assesment.models.Cart;
import com.example.assesment.models.LineItem;
import com.example.assesment.models.Product;
import com.example.assesment.services.PriceEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/api/shopping")
public class ShoppingController {

    private PriceEngine priceEngine;

    @Autowired
    public ShoppingController(PriceEngine priceEngine) {
        this.priceEngine = priceEngine;
    }

    @GetMapping("/products")
    @CrossOrigin(origins = {"http://localhost:4200"})
    public ResponseEntity getProducts() {

        List<Product> productList = this.priceEngine.getAll();
        List<ProductDto> list = productList.stream().map(product -> new ProductDto(product)).collect(Collectors.toList());

        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/summary")
    @CrossOrigin(origins = {"http://localhost:4200"})
    public ResponseEntity getSummary(@RequestBody AddLineItemRequestDto request) {


        Cart cart = new Cart();
        cart.setLineItems(request.getLineItems());


        return new ResponseEntity(this.priceEngine.calculatePrice(cart, request.getNewLineItem()), HttpStatus.OK);
    }

    public static class AddLineItemRequestDto {
        private List<LineItem> lineItems;
        private LineItem newLineItem;

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

    public static class ProductDto {

        private int id;
        private String name;
        private double price;
        private int unitsPerCarton;

        public ProductDto(Product product) {
            this.id = product.getId();
            this.name = product.getName();
            this.price = product.getPrice();
            this.unitsPerCarton = product.getUnitsPerCarton();
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

    }

}
