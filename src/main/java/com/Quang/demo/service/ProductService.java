package com.Quang.demo.service;

import java.util.List;

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

  public List<Product> handleGetProducts() {
    List<Product> products = this.productRepository.findAll();
    return products;

  }

  public Product handleGetByID(long id) {
    Product product = this.productRepository.findById(id);
    return product;
  }

  public void handleDeleteProduct(long id) {
    this.productRepository.deleteById(id);
  }
}
