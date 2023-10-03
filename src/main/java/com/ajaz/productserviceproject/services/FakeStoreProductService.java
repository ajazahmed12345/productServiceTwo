package com.ajaz.productserviceproject.services;

import com.ajaz.productserviceproject.dtos.FakeStoreProductDto;
import com.ajaz.productserviceproject.dtos.GenericProductDto;
import com.ajaz.productserviceproject.exceptions.NotFoundException;
import com.ajaz.productserviceproject.thirdpartyclients.FakeStoreProductServiceClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

   private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException{

        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.getProductById(id));
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {

        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.createProduct(genericProductDto));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<GenericProductDto> ans = new ArrayList<>();

        fakeStoreProductServiceClient.getAllProducts().forEach(
                product -> ans.add(convertFakeStoreProductDtoToGenericProductDto(product))
        );


        return ans;
    }

    public GenericProductDto convertFakeStoreProductDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setCategory(fakeStoreProductDto.getCategory());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setImage(fakeStoreProductDto.getImage());
        product.setPrice(fakeStoreProductDto.getPrice());


        return product;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) throws NotFoundException{

        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.deleteProductById(id));
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto) throws NotFoundException {

        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.updateProductById(id, genericProductDto));

    }
}
