package com.doni.latihanbarang.model.response;

import java.math.BigDecimal;

import com.doni.latihanbarang.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private String id;

    private String name;

    private String description;
    
    private String image;
    
    private BigDecimal price;

    private int stock;

    private Category category;
    
}
