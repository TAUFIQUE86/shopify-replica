package com.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "sub_category")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "name", length = 45)
    private  String name;

    // Category mapping

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


// product mapping
    @JsonManagedReference
    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products = new LinkedHashSet<>();

}
