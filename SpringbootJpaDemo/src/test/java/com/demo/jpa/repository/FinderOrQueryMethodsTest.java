package com.demo.jpa.repository;

import com.demo.jpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//TODO Analyse all the hibernate generated methods later on
@SpringBootTest
public class FinderOrQueryMethodsTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameMethod() {
        final Product banana = productRepository.findByName("Banana").orElseThrow();
        System.out.println(banana);

    }

    @Test
    void findByIdMethod() {
        Long id = 1L;
        final Product product = productRepository.findById(id).orElseThrow();
        System.out.println(product);
    }

    @Test
    void findByNameorDescriptionMethod() {
        final List<Product> productList = productRepository.findByNameOrDescription("Banana", "Ice cream description");
        productList.forEach(product ->
                {
                    System.out.println(product);
                });
    }

    @Test
    void findByNameAndDescriptionMethod() {
        final List<Product> productList = productRepository.findByNameAndDescription("Banana", "Banana description");
        productList.forEach(product ->
        {
            System.out.println(product);
        });
    }
}
