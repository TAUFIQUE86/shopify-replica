package com.product.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "name" ,length = 45)
    private  String name;

    // Sub-category mapping
    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;


    // Mapping brands
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Brand> brands = new LinkedHashSet<>();

}
