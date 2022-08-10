package com.example.vuductue.seeder;

import com.example.vuductue.entity.Product;
import com.example.vuductue.repository.ProductRepository;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Slf4j
public class ApplicationSeeder implements CommandLineRunner {
    boolean createSeedData = false;
    private static Logger logger = Logger.getLogger(ApplicationSeeder.class .getSimpleName());
    int numberOfProduct = 500;
    final ProductRepository productRepository;
    Faker faker;

    public ApplicationSeeder(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.faker = new Faker();
    }

    @Override
    public void run(String... args) throws Exception {
        if(createSeedData){
            logger.log(Level.SEVERE, "Start seeding");
            seedProduct();
        }
    }

    private void seedProduct() {
        List<Product> listProduct = new ArrayList<>();
        logger.log(Level.SEVERE, "---------------- Start seeding Product ----------------");
        for (int i = 0; i < numberOfProduct; i++) {
            System.out.println(i + 1);
            Product product = new Product();
            product.setName(faker.name().title());
            product.setPrice(BigDecimal.valueOf(faker.number().numberBetween(100, 200) * 10000));
            product.setQuantity(faker.number().numberBetween(10, 100));
            listProduct.add(product);
            System.out.println(product.toString());
        }
        logger.log(Level.SEVERE, "---------------- End seeding Product ----------------");
        productRepository.saveAll(listProduct);
    }

}
