package com.ajaz.productserviceproject.thirdpartyclients;

import com.ajaz.productserviceproject.dtos.FakeStoreProductDto;
import com.ajaz.productserviceproject.dtos.GenericProductDto;
import com.ajaz.productserviceproject.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductServiceClient{

    private RestTemplateBuilder restTemplateBuilder;
    private String specificProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String productRequestsBaseUrl = "https://fakestoreapi.com/products";

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException{

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if(fakeStoreProductDto == null){
            throw new NotFoundException("Product with id: " + id + " does not exist");
        }

        return fakeStoreProductDto;
    }


    public FakeStoreProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(productRequestsBaseUrl, genericProductDto, FakeStoreProductDto.class);

        return response.getBody();
    }


    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestsBaseUrl, FakeStoreProductDto[].class);

        List<GenericProductDto> products = new ArrayList<>();

//        for(FakeStoreProductDto fakeStoreProductDto : response.getBody()){
//            products.add(convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto));
//        }

        return Arrays.stream(response.getBody()).toList();
    }



    public FakeStoreProductDto deleteProductById(Long id) throws NotFoundException{

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> getResponse = restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);

        if(getResponse.getBody() == null){
            throw new NotFoundException("Product to be deleted with id: " + id + " not found");
        }

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);


        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);


        return response.getBody();
    }


    public FakeStoreProductDto updateProductById(Long id, GenericProductDto genericProductDto) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
//        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
//        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
//
//

        ResponseEntity<FakeStoreProductDto> getResponse = restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);

        if(getResponse.getBody() == null){
            throw new NotFoundException("Product to be updated with id: " + id + " not found");
        }

        HttpEntity<GenericProductDto> request = new HttpEntity<GenericProductDto>(
                genericProductDto);
        restTemplate.exchange(specificProductRequestUrl, HttpMethod.PUT, request, FakeStoreProductDto.class, id);

//        restTemplate.put(specificProductRequestUrl, genericProductDto, id);
//        genericProductDto.setId(id);

        return getResponse.getBody();
//        return convertFakeStoreProductDtoToGenericProductDto(response.getBody());
    }
}
