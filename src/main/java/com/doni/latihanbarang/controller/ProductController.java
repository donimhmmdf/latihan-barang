package com.doni.latihanbarang.controller;

import com.doni.latihanbarang.model.request.CreateProductRequest;
import com.doni.latihanbarang.model.request.UpdateProductRequest;
import com.doni.latihanbarang.model.response.ProductResponse;
import com.doni.latihanbarang.model.response.WebResponse;
import com.doni.latihanbarang.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  ProductService productService;

  @PostMapping(
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<ProductResponse> create(
    @RequestBody CreateProductRequest request
  ) {
    ProductResponse productResponse = productService.create(request);
    return WebResponse.<ProductResponse>builder().data(productResponse).build();
  }

  @GetMapping
  public WebResponse<List<ProductResponse>> getAll() {
    List<ProductResponse> productResponse = productService.getAll();
    return WebResponse
      .<List<ProductResponse>>builder()
      .data(productResponse)
      .build();
  }

  @GetMapping(path = "/{id}")
  public WebResponse<ProductResponse> getOne(@PathVariable("id") String id) {
    ProductResponse product = productService.getOne(id);
    return WebResponse.<ProductResponse>builder().data(product).build();
  }

  @PutMapping(path = "/{id}")
  public WebResponse<ProductResponse> updateOne(
    @PathVariable("id") String id,
    @RequestBody UpdateProductRequest request
  ) {
    request.setId(id);
    ProductResponse productResponse = productService.updateOne(request);
    return WebResponse.<ProductResponse>builder().data(productResponse).build();
  }
  @DeleteMapping(path = "/{id}")
  public WebResponse<String> deleteOne(@PathVariable("id") String id){
    productService.deleteOne(id);
    return WebResponse.<String>builder().data("OK").build();
  }
}
