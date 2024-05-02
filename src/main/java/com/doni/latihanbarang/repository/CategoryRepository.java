package com.doni.latihanbarang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doni.latihanbarang.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

    boolean existsByName(String name);

    
}