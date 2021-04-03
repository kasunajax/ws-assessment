package com.example.assesment.services;

import com.example.assesment.models.Cart;
import com.example.assesment.models.LineItem;
import com.example.assesment.models.Product;
import com.example.assesment.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PriceEngineTests {

    private final ProductRepository mockProductRepository = mock(ProductRepository.class);

    /*
     * Initializes the mock repository with sample data for testing
     */
    @BeforeEach
    public void initMockProductRepository() {
        Product productPenguinEars = new Product(1, "Penguin Ears", 175.0, 20);
        Product productHorseShoe = new Product(2, "Horse Shoe", 825.0, 5);
        List<Product> productList = Arrays.asList(productPenguinEars, productHorseShoe);
        when(mockProductRepository.findAll()).thenReturn(productList);
        when(mockProductRepository.findById(1)).thenReturn(Optional.of(productPenguinEars));
        when(mockProductRepository.findById(2)).thenReturn(Optional.of(productHorseShoe));
    }

    /*
     * Text Case 01 
     * Expects 175.0 when 1 carton and 0 pieces of product id = 1 (Penguin Ears) ears were purchased 
     */
    @Test
    @DisplayName("Text Case 01")
    public void calculatePrice1() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(1);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 1, 0);
        priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertEquals(175.0, testCart.getNetTotal());
    }

    /*
     * Text Case 02 
     * Expects 175.0 x 2 when 2 cartons and 0 pieces of product id = 1 (Penguin Ears) were purchased 
     */
    @Test
    @DisplayName("Text Case 02")
    public void calculatePrice2() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(1);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 2, 0);
        priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertEquals(175.0 * 2, testCart.getNetTotal());
    }

    /*
     * Text Case 03 
     * (Must apply 10% discount on carton price 175.0) when 3 cartons and 0 pieces of product id = 1 (Penguin Ears) were purchased 
     * Expects (175.0 x 0.9) x 3 = 472.5 
     */
    @Test
    @DisplayName("Text Case 03")
    public void calculatePrice3() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(1);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 3, 0);
        priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertEquals((175 * 0.9) * 3, testCart.getNetTotal());
    }

    /*
     * Text Case 04 
     * (Must add the discount)  when 3 cartons and 0 pieces of product id = 1 (Penguin Ears) were purchased 
     * Doesn't expect 175.0 x 3 = 525.0 
     */
    @Test
    @DisplayName("Text Case 04")
    public void calculatePrice4() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(1);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 3, 0);
        priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertNotEquals(175.0 * 3, testCart.getNetTotal());
    }

    /*
     * Text Case 05 
     * (Must Include the Labour Cost Only) when 1 carton and 10 pieces of product id = 1 (Penguin Ears) ears were purchased 
     * Expects 175.0 x 1 + (175.0 / 20 x 1.3 x 10) 
     */
    @Test
    @DisplayName("Text Case 05")
    public void calculatePrice5() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(1);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 1, 10);
        priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertEquals(175.0 * 1 + ((175.0 / 20) * 1.3) * 10, testCart.getNetTotal());
    }

    /*
     * Text Case 06 
     * (Must apply 10% discount on carton price 175.0 + Labour Cost) 
     * when 3 carton and 10 pieces of product id = 1 (Penguin Ears) ears were purchased 
     * Expects (175.0 x 0.9) x 3 + ((175.0 x 0.9) / 20 x 1.3) discount 
     */
    @Test
    @DisplayName("Text Case 06")
    public void calculatePrice6() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(1);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 3, 10);
        priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertEquals((175.0 * 0.9) * 3 + ((175.0 * 0.9 / 20) * 1.3 * 10) , testCart.getNetTotal());
    }

    /*
     * Text Case 07 
     * Expects 825.0 when 1 carton and 0 pieces of product id = 2 (Horse Shoe) ears were purchased 
     */
    @Test
    @DisplayName("Text Case 07")
    public void calculatePrice7() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(2);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 1, 0);
        priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertEquals(825.0, testCart.getNetTotal());
    }

    /*
     * Text Case 08 
     * Expects 825.0 x 2 when 2 cartons and 0 pieces of product id = 2 (Horse Shoe) were purchased 
     */
    @Test
    @DisplayName("Text Case 08")
    public void calculatePrice8() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(2);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 2, 0);
        priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertEquals(825.0 * 2, testCart.getNetTotal());
    }

    /*
     * Text Case 09 
     * (Must apply 10% discount on carton price 825.0) when 3 cartons and 0 pieces of product id = 2 (Horse Shoe) were purchased 
     * Expects (825.0 x 0.9) x 3 = 2227.5 
     */
    @Test
    @DisplayName("Text Case 09")
    public void calculatePrice9() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(2);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 3, 0);
        priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertEquals((825.0 * 0.9) * 3, testCart.getNetTotal());
    }

    /*
     * Text Case 10 
     * (Must add the discount)  when 3 cartons and 0 pieces of product id = 2 (Horse Shoe) were purchased 
     * Doesn't expect 825.0 x 3 = 2475.0 
     */
    @Test
    @DisplayName("Text Case 10")
    public void calculatePrice10() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(2);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 3, 0);
        priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertNotEquals(825.0 * 3, testCart.getNetTotal());
    }

    /*
     * Text Case 11 
     * (Must Include the Labour Cost Only) when 1 carton and 2 pieces of product id = 2 (Horse Shoe) were purchased 
     * Expects 825.0 x 1 + (825.0 / 5 x 1.3) x 2 
     */
    @Test
    @DisplayName("Text Case 11")
    public void calculatePrice11() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(2);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 1, 2);
        priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertEquals(825.0 * 1 + ((825.0 / 5) * 1.3) * 2, testCart.getNetTotal());
    }

    /*
     * Text Case 12 
     * (Must apply 10% discount on carton price 825.0 + Labour Cost) 
     * when 3 carton and 3 pieces of product id = 2 (Horse Shoe) were purchased 
     * Expects (825.0 x 0.9) x 3 + ((825.0 x 0.9) / 5 x 1.3) x 3 discount 
     */
    @Test
    @DisplayName("Text Case 12")
    public void calculatePrice12() {
        PriceEngine priceEngine = new PriceEngine(mockProductRepository);
        Cart testCart = new Cart(new ArrayList<>());
        Product testProduct1 = new Product(2);
        LineItem newTestLineItem1 = new LineItem(testProduct1, 3, 3);
        priceEngine.calculatePrice(testCart, newTestLineItem1);
        assertEquals((825.0 * 0.9) * 3 + ((825.0 * 0.9 / 5) * 1.3 * 3) , testCart.getNetTotal());
    }

    /*
     * Test Case 13 
     * Cart contains two line items ie. Penguin Ears, and HorseShoe 
     * Line Item 1 - Penguin Ears (id = 1, cartons = 1, pieces = 0, lineTotal1 = 175.0 * 1) 
     * Line Item 2 - Horse Shoe (id = 2, cartons = 1, pieces = 0, lineTotal2 = 825.0 * 1) 
     * Net Total = lineTotal1 + lineTotal2 = 175.0 + 825.0 = 1000.0 
     * Expected Output = 1000.0 
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

        priceEngine.calculatePrice(testCart, newTestLineItem2);
        assertEquals((175.0 * 1) + (825.0 * 1) , testCart.getNetTotal());
    }

    /*
     * Test Case 14 (Ultimate)
     * Cart contains two line items ie. Penguin Ears, and HorseShoe 
     *
     * Line Item 1 - Penguin Ears (id = 1, cartons = 3, pieces = 10, lineTotal1 = ?) 
     *             - 1. Apply 10% discount (3 or more cartons) => newCartonPrice = 175.0 * 0.9 = 157.5 
     *             - 2. Units per carton = 20 
     *             - 3. New price per piece = 157.5 / 20 = 7.875 
     *             - 4. Apply 1.3% increment (Buying pieces also) => newPricePerPiece = 7.875 * 1.3 = 10.2375 
     *             - 5. Total cost of cartons = 3 * 157.5 = 472.5 
     *             - 6. Total cost of pieces = 10 * 10.2375 = 102.375 
     *             - 7. Line total = 102.375 + 472.5 = 574.875 
     * 
     * Line Item 2 - Horse Shoe (id = 2, cartons = 3, pieces = 10, lineTotal2 = ?) 
     *             - No: of pieces are 10 (Not optimised because Units per carton = 5) 
     *             - Before calculating the total the cost is optimised 
     *             - Optimised selection => Horse Shoe (id = 2, cartons = 5, lineTotal2= ?) 
     *             - Calculating Line Total:- 
     *             - Calculating Line Total:- 
     *             - 1. Apply 10% discount (3 or more cartons) => newCartonPrice = 825.0 * 0.9 = 742.5 
     *             - 2. Units per carton = 5 
     *             - 4. NO 1.3 % Increment since only cartons are purchased 
     *             - 5. Total cost of cartons = 5 * 742.5 = 3712.5 
     *             - 7. Line total = 3712.5 
     * 
     * Net Total = lineTotal1 + lineTotal2 = 574.875 + 3712.5 = 4287.375 
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

        priceEngine.calculatePrice(testCart, newTestLineItem2);
        assertEquals(4287.375, testCart.getNetTotal());
    }
}
