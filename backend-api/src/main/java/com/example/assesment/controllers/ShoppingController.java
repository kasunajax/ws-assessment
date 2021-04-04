package com.example.assesment.controllers;

import com.example.assesment.models.AddLineItemRequestDto;
import com.example.assesment.models.Cart;
import com.example.assesment.models.Product;
import com.example.assesment.models.ProductDto;
import com.example.assesment.services.PriceEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller handles incoming Http requests and process
 * Contains two endpoints
 */
@RestController()
@RequestMapping("/api/shopping")
@CrossOrigin(origins = {"${cors.origin.frontend}"})
public class ShoppingController {

    private PriceEngine priceEngine;

    @Autowired
    public ShoppingController(PriceEngine priceEngine) {
        this.priceEngine = priceEngine;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<Product> productList = this.priceEngine.getAll();
        List<ProductDto> list = productList.stream().map(ProductDto::from).collect(Collectors.toList());
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/summary")
    public ResponseEntity<Cart> getSummary(@RequestBody AddLineItemRequestDto addLineItemRequestDto) {
        Cart cart = new Cart();
        cart.setLineItems(addLineItemRequestDto.getLineItems());
        priceEngine.calculatePrice(cart, addLineItemRequestDto.getNewLineItem());
        return new ResponseEntity(cart, HttpStatus.OK);
    }

}
