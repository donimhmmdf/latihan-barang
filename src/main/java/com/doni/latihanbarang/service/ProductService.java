package com.doni.latihanbarang.service;

import com.doni.latihanbarang.entity.Category;
import com.doni.latihanbarang.entity.Product;
import com.doni.latihanbarang.model.request.CreateProductRequest;
import com.doni.latihanbarang.model.request.UpdateProductRequest;
import com.doni.latihanbarang.model.response.ProductResponse;
import com.doni.latihanbarang.repository.CategoryRepository;
import com.doni.latihanbarang.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  CategoryRepository categoryRepository;

  @Autowired
  ValidationService validationService;

  public ProductResponse create(CreateProductRequest request) {
    validationService.validate(request);

    Category category = categoryRepository
      .findById(request.getCategory().getId())
      .orElseThrow(() -> new EntityNotFoundException("Category not found."));

    Product product = new Product();
    product.setId(UUID.randomUUID().toString());
    product.setName(request.getName());
    product.setDescription(request.getDescription());
    product.setImage(request.getImage());
    product.setPrice(request.getPrice());
    product.setStock(request.getStock());
    product.setCategory(category);

    productRepository.save(product);

    return toProductResponse(product);
  }

  @Transactional
  private ProductResponse toProductResponse(Product product) {
    return ProductResponse
      .builder()
      .id(product.getId())
      .name(product.getName())
      .description(product.getDescription())
      .image(product.getImage())
      .price(product.getPrice())
      .stock(product.getStock())
      .category(product.getCategory())
      .build();
  }

  @Transactional(readOnly = true)
  public List<ProductResponse> getAll() {
    List<Product> products = productRepository.findAll();
    return products
      .stream()
      .map(this::toProductResponse)
      .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public ProductResponse getOne(String id) {
    Product product = productRepository
      .findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Product with id " + id + " not found."
        )
      );
    return toProductResponse(product);
  }

  public void deleteOne(String id) {
    Product product = productRepository
      .findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Product with id" + id + " not found."
        )
      );
    productRepository.delete(product);
  }

  public ProductResponse updateOne(UpdateProductRequest request) {
    validationService.validate(request);

    Product product = productRepository
      .findById(request.getId())
      .orElseThrow(() ->
        new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Product with id " + request.getId() + " not found"
        )
      );
      product.setName(request.getName());
      product.setDescription(request.getDescription());
      product.setImage(request.getImage());
      product.setPrice(request.getPrice());
      product.setStock(request.getStock());
      product.setCategory(request.getCategory());

      productRepository.save(product);
      return toProductResponse(product);
  }
}
