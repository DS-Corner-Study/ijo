package com.springboot.advanced_jpa.data.repository.support;

import com.springboot.advanced_jpa.data.entity.Product;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProductRepositoryCustomImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {

    public ProductRepositoryCustomImpl(){
        super(Product.class);
    }

    @Override
    public List<Product> findByName(String name){
        QProcut qProcut = QProduct.procut();

        List<Product> productList = jpaQueryFactory.selectFrom(qProcut)
                .where(qProcut.name.eq(name))
                .orderBy(qProcut.price.asc())
                .fetch();

        return productList;
    }
}
