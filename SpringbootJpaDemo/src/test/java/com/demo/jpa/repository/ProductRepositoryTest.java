package com.demo.jpa.repository;

import com.demo.jpa.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest // It will load all the beans from our application at test phase
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    Product product;

    @BeforeEach
    void setUp() {
        product = Product.builder().name("Apple")
                    .description("Apple description")
                    .sku("100ABC")
                    .price(new BigDecimal(100))
                    .active(true)
                    .imageUrl("apple.png")
    //                .dateCreated("2015 ") // not needed as using hibernate annotation
    //                .lastUpdated("2015 ") // not needed as using hibernate annotation
                    .build();
    }

    // Save method - EntityManager will use entityManager.persist() to persist,
    // else if row already exists (identified by the primary key) then entityManager.merge()
    // to update that column)
    @Test
    void saveMethod() {
        final Product saved = productRepository.save(product);
        System.out.println(saved);
        System.out.println(saved.getId());
    }

    // Save method - EntityManager will use entityManager.persist() to persist,
    // else if row already exists (identified by the primary key) then entityManager.merge()
    // to update that column)
    @Test
    void updateUsingSaveMethod() {
        // find or retrieve  an entity by id
        Long id = 1L;
        final Optional<Product> productOptional = productRepository.findById(id);
        final Product product = productOptional.orElseThrow();

        // update entity information
        product.setName("updated rproduct 1");
        product.setDescription("updated rproduct 1 description");

        // save updated entity
        productRepository.save(product);
    }

    @Test
    void findByIdMethod() {
        try {
            Long id = 2L;
            final Product product = productRepository.findById(id).orElseThrow();
            System.out.println(product);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}