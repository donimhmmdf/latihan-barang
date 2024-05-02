package com.doni.latihanbarang.service;

import com.doni.latihanbarang.entity.Category;
import com.doni.latihanbarang.model.request.CreateCategoryRequest;
import com.doni.latihanbarang.model.response.CategoryResponse;
import com.doni.latihanbarang.repository.CategoryRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategoryService {

  @Autowired
  CategoryRepository categoryRepository;

  @Autowired
  ValidationService validationService;

  public CategoryResponse create(CreateCategoryRequest request) {
    validationService.validate(request);
    if (categoryRepository.existsByName(request.getName())) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "Category already exists."
      );
    }
    Category category = new Category();
    category.setId(UUID.randomUUID().toString());
    category.setName(request.getName());

    categoryRepository.save(category);
    return CategoryResponse
      .builder()
      .id(category.getId())
      .name(category.getName())
      .build();
  }

  public List<CategoryResponse> getAll() {
    List<Category> categories = categoryRepository.findAll();
    return categories
      .stream()
      .map(this::toCategoryResponse)
      .collect(Collectors.toList());
  }

  private CategoryResponse toCategoryResponse(Category category) {
    return CategoryResponse
      .builder()
      .id(category.getId())
      .name(category.getName())
      .build();
  }
}
