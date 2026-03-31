package com.product.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "name" ,length = 45)
    private  String name;

    // Sub-category mapping
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;


    // Mapping brands
    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Brand> brands = new LinkedHashSet<>();

}
