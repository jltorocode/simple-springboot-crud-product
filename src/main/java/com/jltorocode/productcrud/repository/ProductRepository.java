package com.jltorocode.productcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jltorocode.productcrud.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
