package com.doni.latihanbarang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doni.latihanbarang.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
    
}
