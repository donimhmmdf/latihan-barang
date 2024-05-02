package com.doni.latihanbarang.model.request;

import java.math.BigDecimal;

import com.doni.latihanbarang.entity.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductRequest {
    
    @NotBlank
    @Size(max = 100)
    private String name;
    
    @Size(max = 100)
    private String description;

    private String image;

    private BigDecimal price;

    private int stock;

    private Category category;
}
