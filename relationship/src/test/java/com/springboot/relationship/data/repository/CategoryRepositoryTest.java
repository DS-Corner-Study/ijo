package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.Category;
import com.springboot.relationship.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    ProducerRepositoryTest productRepository;

    @Autowired
    CategoryRepository categoryRepository;


    @Test
    void relationshipTest(){
        Product product = new Product();
        product.setName("스프링부트 JPA");
        product.setPrice(5000);
        product.setStock(500);
        productRepository.save(product);

        Category category = new Category();
        category.setCode("S1");
        category.setName("도서");
        category.getProducts().add(product);

        List<Product> productList = categoryRepository.findById(1L).get().getProducts();

        for(Product foundProduct:productList){
            System.out.println(product);
        }
    }

}
