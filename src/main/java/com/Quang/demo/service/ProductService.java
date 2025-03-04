package com.Quang.demo.service;

import org.springframework.stereotype.Service;

import com.Quang.demo.domain.Product;
import com.Quang.demo.repository.ProductRepository;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product handleSaveProduct(Product newProduct) {
    Product product = this.productRepository.save(newProduct);
    return product;
  }

}
