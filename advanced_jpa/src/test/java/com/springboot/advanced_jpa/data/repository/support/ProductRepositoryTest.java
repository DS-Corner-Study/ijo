package com.springboot.advanced_jpa.data.repository.support;

import com.springboot.advanced_jpa.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void findByNameTest(){
        List<Product> productList = productRepository.findByNAme("펜");

        for(Product product: productList){
            System.out.println("-------------------------");
            System.out.println();
            System.out.println("Product Number : " + product.getNumber());
            System.out.println("Product Name : " + product.getName());
            System.out.println("Product Price" + product.getPrice());
            System.out.println("Product Stock" + product.getStock());
            System.out.println();
            System.out.println("-------------------------");
        }
    }

    @Test
    public void auditingTest(){
        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(1000);
        product1.setStock(1000);

        Product savedProduct = productRepository.save(product1);

        System.out.println(savedProduct.getName());
        System.out.println(savedProduct.getCreateAt());
    }
}
