package com.Quang.demo.service.specification;

import org.springframework.data.jpa.domain.Specification;

import com.Quang.demo.domain.Product;
import com.Quang.demo.domain.Product_;

public class ProductSpec {
    public static Specification<Product> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Product_.NAME), "%" + name + "%");
    }
}
