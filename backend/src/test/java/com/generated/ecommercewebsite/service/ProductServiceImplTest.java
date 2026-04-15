package com.generated.ecommercewebsite.service;

import com.generated.ecommercewebsite.dto.ProductDto;
import com.generated.ecommercewebsite.entity.Product;
import com.generated.ecommercewebsite.exception.ResourceNotFoundException;
import com.generated.ecommercewebsite.mapper.ProductMapper;
import com.generated.ecommercewebsite.repository.ProductRepository;
import com.generated.ecommercewebsite.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private UUID productId;
    private Product product;
    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        productId = UUID.randomUUID();
        
        product = new Product();
        product.setId(productId);
        product.setName("John Doe");
        product.setEmail("john@example.com");
        product.setCompany("ACME Corp");

        productDto = new ProductDto();
        productDto.setId(productId);
        productDto.setName("John Doe");
        productDto.setEmail("john@example.com");
        productDto.setCompany("ACME Corp");
    }

    @Test
    void testFindAll() {
        List<Product> products = List.of(product);
        when(productRepository.findAll()).thenReturn(products);

        List<ProductDto> result = productService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        ProductDto result = productService.findById(productId);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testFindByIdNotFound() {
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.findById(productId));
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testCreate() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDto result = productService.create(productDto);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testUpdate() {
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDto updated = new ProductDto();
        updated.setId(productId);
        updated.setName("Jane Doe");
        updated.setEmail("jane@example.com");
        updated.setCompany("ACME Inc");

        ProductDto result = productService.update(productId, updated);

        assertNotNull(result);
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testDelete() {
        when(productRepository.existsById(productId)).thenReturn(true);

        productService.delete(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    void testDeleteNotFound() {
        when(productRepository.existsById(productId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> productService.delete(productId));
    }
}
