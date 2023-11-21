package com.springboot.advanced_jpa.data.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.advanced_jpa.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void sortingAndPagingTest(){
        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(1000);
        product1.setStock(1000);
        product1.setCreateAt(LocalDateTime.now());
        product1.setUpdateAt(LocalDateTime.now());

        Product product2 = new Product();
        product2.setName("펜");
        product2.setPrice(5000);
        product2.setStock(300);
        product2.setCreateAt(LocalDateTime.now());
        product2.setUpdateAt(LocalDateTime.now());

        Product product3 = new Product();
        product3.setName("펜");
        product3.setPrice(500);
        product3.setStock(50);
        product3.setCreateAt(LocalDateTime.now());
        product3.setUpdateAt(LocalDateTime.now());

        Product savedProduct1 = productRepository.save(product1);
        Product savedProduct2 = productRepository.save(product2);
        Product savedProduct3 = productRepository.save(product3);

        productRepository.findByName("펜", Sort.by(Order.asc("price")));
        productRepository.findByName("펜", Sort.by(Order.asc("price"), Order.desc("stock")));

        System.out.println(productRepository.findByName("펜", getSort()));

        Page<Product> productPage  = productRepository.findByName("펜", PageRequest.of(0,2));
        System.out.println(productPage.getContent());
    }

    private Sort getSort(){
        return Sort.by(
                Order.asc("price"),
                Order.desc("stock")
        );
    }

    @PersistenceContext
    EntityManager entityManager

    @Test
    void queryDslTest(){
        JPAQuery<Product> query = new JPAQuery<>(entityManager);
        QProcut qProcut = QProduct.procut();

        List<Product> productList = query
                .from(qProcut)
                .where(qProcut.name.eq("펜"))
                .orderBy(qProcut.price.asc())
                .fetch();

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
    void queryDslTest2(){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProcut qProcut = QProduct.procut();

        List<Product> productList = jpaQueryFactory.selectFrom(qProcut)
                .where(qProcut.name.eq("펜"))
                .orderBy(qProcut.price.asc())
                .fetch();

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
    void queryDslTest3(){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProcut qProcut = QProduct.procut();

        List<String> productList = jpaQueryFactory
                .select(qProcut.name)
                .from(qProcut)
                .where(qProcut.name.eq("펜"))
                .orderBy(qProcut.price.asc())
                .fetch();

        for(Product product: productList){
            System.out.println("-------------------------");
            System.out.println("Product Name : " + product.getName());
            System.out.println("-------------------------");
        }

        List<Tuple> tupleList = jpaQueryFactory
                .select(qProcut.name, qProcut.price)
                .from(qProcut)
                .where(qProcut.name.eq("펜"))
                .orderBy(qProcut.price.asc())
                .fetch();

        for(Product product: productList){
            System.out.println("-------------------------");
            System.out.println("Product Name : " + product.getName());
            System.out.println("Product Price : " + product.getPrice());
            System.out.println("-------------------------");
        }
    }

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    void queryDslTest3(){
        QProcut qProcut = QProduct.procut();

        List<String> productList = jpaQueryFactory
                .select(qProcut.name)
                .from(qProcut)
                .where(qProcut.name.eq("펜"))
                .orderBy(qProcut.price.asc())
                .fetch();

        for(Product product: productList){
            System.out.println("-------------------------");
            System.out.println("Product Name : " + product.getName());
            System.out.println("-------------------------");
        }
    }
}
