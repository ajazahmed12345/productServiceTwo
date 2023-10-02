package com.ajaz.productserviceproject.services;

import com.ajaz.productserviceproject.dtos.FakeStoreProductDto;
import com.ajaz.productserviceproject.dtos.GenericProductDto;
import com.ajaz.productserviceproject.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) throws NotFoundException {
        return null;
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto) throws NotFoundException {
        return null;
    }
}
