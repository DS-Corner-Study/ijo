package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.Producer;
import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.Provider;
import jakarta.transaction.Transactional;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProviderRepositoryTest {
    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    ProducerRepositoryTest productRepository;

    @Test
    void relationshipTest1(){
        Provider provider = new Provider();
        provider.setName("corner 물산");

        providerRepository.save(provider);

        Product product = new Product();
        product.setName("가위");
        product.setPrice(5000);
        product.setStock(500);
        productRepository.save(product);

        System.out.println("product: " + productRepository.findById(1L)
                .orElseThrow(RuntimeException::new));

        System.out.println("provider: " + productRepository.findById(1L)
                .orElseThrow(RuntimeException::new).getProvider());

    }


    @Test
    void relationshipTest(){
        Provider provider = new Provider();
        provider.setName("corner 물산");

        providerRepository.save(provider);


        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(1000);
        product1.setStock(1000);
        product1.setProvider(provider);

        Product product2 = new Product();
        product2.setName("가방");
        product2.setPrice(5000);
        product2.setStock(300);
        product2.setProvider(provider);


        Product product3 = new Product();
        product3.setName("모자");
        product3.setPrice(500);
        product3.setStock(50);
        product3.setProvider(provider);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);


        List<Product> productList = providerRepository.findById(provider.getId()).get().getProductList();

        for(Product product: productList){
            System.out.println(product);
        }
    }

    @Test
    void cascadeTest(){
        Provider provider = saveProvider("new provider");

        Product product1 = saveProduct("동글펜", 400, 1000);
        Product product2 = saveProduct("네모 공책", 200, 200);
        Product product3 = saveProduct("지우개", 300, 1400);

        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));

        providerRepository.save(provider);

    }

    @Test
    @Transactional
    void orphanRemovalTest(){
        Provider provider = saveProvider("new provider");

        Product product1 = saveProduct("동글펜", 400, 1000);
        Product product2 = saveProduct("네모 공책", 200, 200);
        Product product3 = saveProduct("지우개", 300, 1400);

        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));

        providerRepository.saveAndFlush(provider);

        providerRepository.findAll().forEach(System.out::println);
        productRepository.findAll().forEach(System.out::println);

        Provider foundProvider = providerRepository.findById(1L).get();
        foundProvider.getProductList().remove(0);

        providerRepository.findAll().forEach(System.out::println);
        productRepository.findAll().forEach(System.out::println);

    }

    private Product saveProduct(String name, Integer price, Integer stock){

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return  product;
    }

    private Provider saveProvider(String name){

        Provider provider = new Provider();

        provider.setName(name);

        return  provider;
    }
}
