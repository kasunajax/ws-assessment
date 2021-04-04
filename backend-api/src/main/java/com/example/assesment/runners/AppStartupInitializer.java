package com.example.assesment.runners;

import com.example.assesment.models.Product;
import com.example.assesment.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Initializes the App with sample data at startup to have some
 * data in the database in order to perform calculations.
 */
@Component
public class AppStartupInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppStartupInitializer.class);

    private final ProductRepository productRepository;

    @Autowired
    public AppStartupInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String...args) throws Exception {
        logger.info("Injecting test product items ...");

        Product productPenguinEars = new Product(1, "Penguin Ears", 175.0, 20);
        Product productHorseShoe = new Product(2, "Horse Shoe", 825.0, 5);
        List<Product> productList = Arrays.asList(productPenguinEars, productHorseShoe);
        productRepository.saveAll(productList);

        logger.info("Test product items injected successfully.");
    }
}
