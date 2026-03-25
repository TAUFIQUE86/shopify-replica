package com.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "brands")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "price")
    private BigDecimal price;


    // mapping products
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    // Mapping size
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Size> sizes = new LinkedHashSet<>();


  // Mapping Image
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Image> images = new LinkedHashSet<>();

}
