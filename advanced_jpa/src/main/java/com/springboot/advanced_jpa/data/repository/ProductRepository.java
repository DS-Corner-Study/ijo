package com.springboot.advanced_jpa.data.repository;

import com.springboot.advanced_jpa.data.entity.Product;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // find .. By

    @Override
    Optional<Product> findById(Long aLong);
    List<Product> findAllByName(String name);
    Product queryByNumber(Long number);

    // exists .. By
    boolean existsByNumber(Long number);

    // count .. By
    long countByName(String name);

    // delete .. By
    void deleteByNumber(Long number);
    long removeByName(String name);

    List<Product> findFirstByName(String name);
    List<Product> findTop10ByName(String name);

    Product findByNameIs(Long number);
    Product findByNumberIs(Long number);
    Product findByNameIsNot(Long number);
    Product findByNumberNot(Long number);

    List<Product> findByUpdatedAtNull();
    List<Product> findByUpdatedAtIsNull();
    List<Product> findByUpdatedAtNotNull();
    List<Product> findByUpdatedAtIsNotNull();

    Product findByisActiveTrue();
    Product findByisActiveIsTrue();
    Product findByisActiveFalse();
    Product findByisActiveisFlase();

    Product findByNumberAndName(Long number, String name);
    Product findByNumberOrName(Long number ,String name);

    List<Product> findByPriceIsGreaterThan(Long price);
    List<Product> findByPriceGreaterThan(Long price);
    List<Product> findByPriceGreaterThanEqual(Long price);
    List<Product> findByPriceIsLessThan(Long price);
    List<Product> findByPriceLessThan(Long price);
    List<Product> findByPriceLessThanEqual(Long price);
    List<Product> findByPriceIsBetween(Long lowPrice, Long highPrice);
    List<Product> findByPriceBetween(Long lowPrice, Long highPrice);

    List<Product> findByNameLike(String name);
    List<Product> findByNameIsLike(String name);
    List<Product> findByNameContains(String name);
    List<Product> findByNameContaining(String name);
    List<Product> findByNameIsContaining(String name);
    List<Product> findByNameStartsWith(String name);
    List<Product> findByNameStartingWith(String name);
    List<Product> findByNameIsStartingWith(String name);
    List<Product> findByNameEndsWith(String name);
    List<Product> findByNameEndingWith(String name);
    List<Product> findByNameIsEndingWith(String name);

    List<Product> findByNameOrderByNumberAsc(String name);
    List<Product> findByNameOrderByNumberDesc(String name);
    List<Product> findByNameOrderByPriceAscStockDesc(String name);

    List<Product> findByName(String name, TypeCache.Sort sort);


    boolean findByName(String name, Sort sort);

    Page<Product> findByName(String name, Pageable pageable);

//    @Query("SELECT p FROM Product AS p WHERE p.name =?1")
//    List<Product> findByName(String name);

//    @Query("SELECT p FROM Product AS p WHERE p.name =:name")
//    List<Product> findByName(@Param("name") String name);

    @Query("SELECT p.name, p.price, p.stock FROM Product AS p WHERE p.name =:name")
    List<Product> findByName(@Param("name") String name);
}
