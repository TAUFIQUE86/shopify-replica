package com.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "image")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "url",length = 2000)
    private  String url;

    // Mapping brands
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

}
