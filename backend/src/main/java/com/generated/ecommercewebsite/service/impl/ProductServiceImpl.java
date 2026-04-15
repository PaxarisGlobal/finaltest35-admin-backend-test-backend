package com.generated.ecommercewebsite.service.impl;

import com.generated.ecommercewebsite.dto.ProductDto;
import com.generated.ecommercewebsite.entity.Product;
import com.generated.ecommercewebsite.exception.ResourceNotFoundException;
import com.generated.ecommercewebsite.mapper.ProductMapper;
import com.generated.ecommercewebsite.repository.ProductRepository;
import com.generated.ecommercewebsite.service.ProductService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(ProductMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(UUID id) {
        Product product = productRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
        return ProductMapper.toDto(product);
    }

    @Override
    public ProductDto create(ProductDto dto) {
        Product product = ProductMapper.toEntity(dto);
        product.setId(null);
        return ProductMapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductDto update(UUID id, ProductDto dto) {
        Product existing = productRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setCompany(dto.getCompany());
        return ProductMapper.toDto(productRepository.save(existing));
    }

    @Override
    public void delete(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found: " + id);
        }
        productRepository.deleteById(id);
    }
}
