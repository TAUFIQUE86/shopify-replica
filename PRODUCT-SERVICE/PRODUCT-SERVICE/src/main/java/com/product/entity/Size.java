package com.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "size")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Size {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

     @Column(name = "size")
    private  String size;

    @Column(name = "quantity")
    private  String quantity;


    // Mapping brands
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

}
