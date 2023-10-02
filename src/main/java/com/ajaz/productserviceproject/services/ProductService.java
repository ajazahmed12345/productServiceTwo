package com.ajaz.productserviceproject.services;

import com.ajaz.productserviceproject.dtos.FakeStoreProductDto;
import com.ajaz.productserviceproject.dtos.GenericProductDto;
import com.ajaz.productserviceproject.exceptions.NotFoundException;
import com.ajaz.productserviceproject.models.Product;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(Long id) throws NotFoundException;
    GenericProductDto createProduct(GenericProductDto genericProductDto);

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProductById(Long id) throws NotFoundException;

    GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto) throws NotFoundException;
}
