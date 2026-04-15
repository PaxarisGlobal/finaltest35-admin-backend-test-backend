package com.generated.ecommercewebsite.mapper;

import com.generated.ecommercewebsite.dto.ProductDto;
import com.generated.ecommercewebsite.entity.Product;

public class ProductMapper {
    private ProductMapper() {}

    public static ProductDto toDto(Product entity) {
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setCompany(entity.getCompany());
        return dto;
    }

    public static Product toEntity(ProductDto dto) {
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setCompany(dto.getCompany());
        return entity;
    }
}
