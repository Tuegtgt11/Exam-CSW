package com.example.vuductue.repository;

import com.example.vuductue.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
