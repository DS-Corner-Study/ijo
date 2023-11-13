package com.springboot.test.data.repository;

import com.springboot.test.data.dto.ProductDto;
import com.springboot.test.data.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ProductRepositoryTest2 {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void basicCRUDTest(){
        // give
        Product givenProduct = Product.builder()
                .name("노트")
                .price(1000)
                .stock(500)
                .build();

        // when
        Product savedProduct = productRepository.save(givenProduct);

        // then
        assertThat(savedProduct.getName()).isEqualTo(givenProduct.getName());
        assertThat(savedProduct.getNumber()).isEqualTo(givenProduct.getNumber());
        assertThat(savedProduct.getPrice()).isEqualTo(givenProduct.getPrice());
        assertThat(savedProduct.getStock()).isEqualTo(givenProduct.getStock());

        // when
        Product selectedProduct = productRepository.findById(savedProduct.getNumber())
                .orElseThrow(RuntimeException::new);

        // then
        assertThat(selectedProduct.getName()).isEqualTo(givenProduct.getName());
        assertThat(selectedProduct.getNumber()).isEqualTo(givenProduct.getNumber());
        assertThat(selectedProduct.getPrice()).isEqualTo(givenProduct.getPrice());
        assertThat(selectedProduct.getStock()).isEqualTo(givenProduct.getStock());

        // when
        Product foundProduct = productRepository.findById(selectedProduct.getNumber())
                .orElseThrow(RuntimeException::new);

        foundProduct.setName("장난감");

        Product updatedProduct = productRepository.save(foundProduct);

        // then
        assertEquals(updatedProduct.getName(), "장난감");

        // when
        productRepository.delete(updatedProduct);

        // then
        assertFalse(productRepository.findById(selectedProduct.getNumber()).isPresent());

    }
}
