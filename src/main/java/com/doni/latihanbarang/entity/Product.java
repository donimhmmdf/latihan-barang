package com.doni.latihanbarang.entity;

import java.math.BigDecimal;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    
    @Id
    private String id;

    @Column(length = 100)
    private String name;
    
    @Column(length = 100)
    private String description;
    
    private String image;
    
    private BigDecimal price;

    private int stock;

    @JoinColumn
    @ManyToOne
    private Category category;
    

}
