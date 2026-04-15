package com.generated.ecommercewebsite.repository;

import com.generated.ecommercewebsite.entity.Product;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
