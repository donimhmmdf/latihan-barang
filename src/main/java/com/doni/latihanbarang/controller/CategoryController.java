package com.doni.latihanbarang.controller;

import com.doni.latihanbarang.model.request.CreateCategoryRequest;
import com.doni.latihanbarang.model.response.CategoryResponse;
import com.doni.latihanbarang.model.response.WebResponse;
import com.doni.latihanbarang.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

  @Autowired
  CategoryService categoryService;

  @PostMapping(
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<CategoryResponse> create(
    @RequestBody CreateCategoryRequest request
  ) {
    CategoryResponse categoryResponse = categoryService.create(request);
    return WebResponse
      .<CategoryResponse>builder()
      .data(categoryResponse)
      .build();
  }

  @GetMapping
  public WebResponse<List<CategoryResponse>> getAll() {
    List<CategoryResponse> categoryResponses = categoryService.getAll();
    return WebResponse
      .<List<CategoryResponse>>builder()
      .data(categoryResponses)
      .build();
  }
}
