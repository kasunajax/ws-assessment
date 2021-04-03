package com.example.assesment.services;

import com.example.assesment.models.Cart;
import com.example.assesment.models.LineItem;
import com.example.assesment.models.Product;
import com.example.assesment.repositories.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

public class PriceEngineTests {

    private final ProductRepository mockProductRepository = mock(ProductRepository.class);

    /**
     * Text Case 01 <br>
     * Expects 175.0 when 1 carton and 0 pieces of product id = 1 (Penguin Ears) ears were purchased <br>
     */
    @Test
    @DisplayName("Text Case 01")
    public void calculatePrice1() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(1);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 1, 0);
        testCart = priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertEquals(175.0, testCart.getNetTotal());
    }

    /**
     * Text Case 02 <br>
     * Expects 175.0 x 2 when 2 cartons and 0 pieces of product id = 1 (Penguin Ears) were purchased <br>
     */
    @Test
    @DisplayName("Text Case 02")
    public void calculatePrice2() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(1);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 2, 0);
        testCart = priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertEquals(175.0 * 2, testCart.getNetTotal());
    }

    /**
     * Text Case 03 <br>
     * (Must apply 10% discount on carton price 175.0) when 3 cartons and 0 pieces of product id = 1 (Penguin Ears) were purchased <br>
     * Expects (175.0 x 0.9) x 3 = 472.5 <br>
     */
    @Test
    @DisplayName("Text Case 03")
    public void calculatePrice3() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(1);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 3, 0);
        testCart = priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertEquals((175 * 0.9) * 3, testCart.getNetTotal());
    }

    /**
     * Text Case 04 <br>
     * (Must add the discount)  when 3 cartons and 0 pieces of product id = 1 (Penguin Ears) were purchased <br>
     * Doesn't expect 175.0 x 3 = 525.0 <br>
     */
    @Test
    @DisplayName("Text Case 04")
    public void calculatePrice4() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(1);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 3, 0);
        testCart = priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertNotEquals(175.0 * 3, testCart.getNetTotal());
    }

    /**
     * Text Case 05 <br>
     * (Must Include the Labour Cost Only) when 1 carton and 10 pieces of product id = 1 (Penguin Ears) ears were purchased <br>
     * Expects 175.0 x 1 + (175.0 / 20 x 1.3 x 10) <br>
     */
    @Test
    @DisplayName("Text Case 05")
    public void calculatePrice5() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(1);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 1, 10);
        testCart = priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertEquals(175.0 * 1 + ((175.0 / 20) * 1.3) * 10, testCart.getNetTotal());
    }

    /**
     * Text Case 06 <br>
     * (Must apply 10% discount on carton price 175.0 + Labour Cost) <br>
     * when 3 carton and 10 pieces of product id = 1 (Penguin Ears) ears were purchased <br>
     * Expects (175.0 x 0.9) x 3 + ((175.0 x 0.9) / 20 x 1.3) discount <br>
     */
    @Test
    @DisplayName("Text Case 06")
    public void calculatePrice6() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(1);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 3, 10);
        testCart = priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertEquals((175.0 * 0.9) * 3 + ((175.0 * 0.9 / 20) * 1.3 * 10) , testCart.getNetTotal());
    }

    /**
     * Text Case 07 <br>
     * Expects 825.0 when 1 carton and 0 pieces of product id = 2 (Horse Shoe) ears were purchased <br>
     */
    @Test
    @DisplayName("Text Case 07")
    public void calculatePrice7() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(2);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 1, 0);
        testCart = priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertEquals(825.0, testCart.getNetTotal());
    }

    /**
     * Text Case 08 <br>
     * Expects 825.0 x 2 when 2 cartons and 0 pieces of product id = 2 (Horse Shoe) were purchased <br>
     */
    @Test
    @DisplayName("Text Case 08")
    public void calculatePrice8() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(2);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 2, 0);
        testCart = priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertEquals(825.0 * 2, testCart.getNetTotal());
    }

    /**
     * Text Case 09 <br>
     * (Must apply 10% discount on carton price 825.0) when 3 cartons and 0 pieces of product id = 2 (Horse Shoe) were purchased <br>
     * Expects (825.0 x 0.9) x 3 = 2227.5 <br>
     */
    @Test
    @DisplayName("Text Case 09")
    public void calculatePrice9() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(2);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 3, 0);
        testCart = priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertEquals((825.0 * 0.9) * 3, testCart.getNetTotal());
    }

    /**
     * Text Case 10 <br>
     * (Must add the discount)  when 3 cartons and 0 pieces of product id = 2 (Horse Shoe) were purchased <br>
     * Doesn't expect 825.0 x 3 = 2475.0 <br>
     */
    @Test
    @DisplayName("Text Case 10")
    public void calculatePrice10() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(2);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 3, 0);
        testCart = priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertNotEquals(825.0 * 3, testCart.getNetTotal());
    }

    /**
     * Text Case 11 <br>
     * (Must Include the Labour Cost Only) when 1 carton and 2 pieces of product id = 2 (Horse Shoe) were purchased <br>
     * Expects 825.0 x 1 + (825.0 / 5 x 1.3) x 2 <br>
     */
    @Test
    @DisplayName("Text Case 11")
    public void calculatePrice11() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(2);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 1, 2);
        testCart = priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertEquals(825.0 * 1 + ((825.0 / 5) * 1.3) * 2, testCart.getNetTotal());
    }

    /**
     * Text Case 12 <br>
     * (Must apply 10% discount on carton price 825.0 + Labour Cost) <br>
     * when 3 carton and 3 pieces of product id = 2 (Horse Shoe) were purchased <br>
     * Expects (825.0 x 0.9) x 3 + ((825.0 x 0.9) / 5 x 1.3) x 3 discount <br>
     */
    @Test
    @DisplayName("Text Case 12")
    public void calculatePrice12() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(2);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 3, 3);
        testCart = priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertEquals((825.0 * 0.9) * 3 + ((825.0 * 0.9 / 5) * 1.3 * 3) , testCart.getNetTotal());
    }

    /**
     * Test Case 13 <br>
     * Cart contains two line items ie. Penguin Ears, and HorseShoe <br>
     * Line Item 1 - Penguin Ears (id = 1, cartons = 1, pieces = 0, lineTotal1 = 175.0 * 1) <br>
     * Line Item 2 - Horse Shoe (id = 2, cartons = 1, pieces = 0, lineTotal2 = 825.0 * 1) <br>
     * Net Total = lineTotal1 + lineTotal2 = 175.0 + 825.0 = 1000.0 <br>
     * Expected Output = 1000.0 <br>
     */
    @Test
    @DisplayName("Test Case 13")
    public void calculatePrice13() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);

        Product testProduct1 = new Product(1);
        Cart testCart = new Cart(new ArrayList<>());
        testCart.addLineItem(new LineItem(testProduct1, 1, 0));

        Product testProduct2 = new Product(2);
        LineItem newTestLineItem2 = new LineItem(testProduct2, 1, 0);

        testCart = priceEngine.calculatePrice(testCart, newTestLineItem2);
        assertEquals((175.0 * 1) + (825.0 * 1) , testCart.getNetTotal());
    }

    /**
     * Test Case 14 (Ultimate)<br>
     * Cart contains two line items ie. Penguin Ears, and HorseShoe <br>
     *
     * Line Item 1 - Penguin Ears (id = 1, cartons = 3, pieces = 10, lineTotal1 = ?) <br>
     *             - 1. Apply 10% discount (3 or more cartons) => newCartonPrice = 175.0 * 0.9 = 157.5 <br>
     *             - 2. Units per carton = 20 <br>
     *             - 3. New price per piece = 157.5 / 20 = 7.875 <br>
     *             - 4. Apply 1.3% increment (Buying pieces also) => newPricePerPiece = 7.875 * 1.3 = 10.2375 <br>
     *             - 5. Total cost of cartons = 3 * 157.5 = 472.5 <br>
     *             - 6. Total cost of pieces = 10 * 10.2375 = 102.375 <br>
     *             - 7. Line total = 102.375 + 472.5 = 574.875 <br>
     * <br>
     * Line Item 2 - Horse Shoe (id = 2, cartons = 3, pieces = 10, lineTotal2 = ?) <br>
     *             - No: of pieces are 10 (Not optimised because Units per carton = 5) <br>
     *             - Before calculating the total the cost is optimised <br>
     *             - Optimised selection => Horse Shoe (id = 2, cartons = 5, lineTotal2= ?) <br>
     *             - Calculating Line Total:- <br>
     *             - Calculating Line Total:- <br>
     *             - 1. Apply 10% discount (3 or more cartons) => newCartonPrice = 825.0 * 0.9 = 742.5 <br>
     *             - 2. Units per carton = 5 <br>
     *             - 4. NO 1.3 % Increment since only cartons are purchased <br>
     *             - 5. Total cost of cartons = 5 * 742.5 = 3712.5 <br>
     *             - 7. Line total = 3712.5 <br>
     * <br>
     * Net Total = lineTotal1 + lineTotal2 = 574.875 + 3712.5 = 4287.375 <br>
     * Expected Output = 4287.375
     */
    @Test
    @DisplayName("Test Case 14 (Ultimate)")
    public void calculatePrice14() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);

        Product testProduct1 = new Product(1);
        Cart testCart = new Cart(new ArrayList<>());
        testCart.addLineItem(new LineItem(testProduct1, 3, 10));

        Product testProduct2 = new Product(2);
        LineItem newTestLineItem2 = new LineItem(testProduct2, 3, 10);

        testCart = priceEngine.calculatePrice(testCart, newTestLineItem2);
        assertEquals(4287.375, testCart.getNetTotal());
    }
}
