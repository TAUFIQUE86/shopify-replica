package com.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "brands")
@Getter
@Setter
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
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    // Mapping size
    @JsonManagedReference
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Size> sizes = new LinkedHashSet<>();


  // Mapping Image
    @JsonManagedReference
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Image> images = new LinkedHashSet<>();

}
