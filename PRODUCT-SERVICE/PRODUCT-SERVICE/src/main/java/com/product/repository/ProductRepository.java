package com.product.repository;

import com.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // jpql query to optimized our data bases

/*
    @Query("""
    SELECT DISTINCT p FROM Product p
    LEFT JOIN FETCH p.brands b
    LEFT JOIN FETCH b.sizes
    LEFT JOIN FETCH b.images
    LEFT JOIN FETCH p.subCategory
    WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(p.subCategory.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(b.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
    List<Product> searchProducts(@Param("keyword") String keyword);

 */



    @Query("""
    SELECT DISTINCT p FROM Product p
    LEFT JOIN p.brands b
    WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(p.subCategory.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(b.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
    List<Product> searchProducts(@Param("keyword") String keyword);





}