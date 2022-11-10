package com.codebyte.productservice.service;

import com.codebyte.productservice.dto.ProductDTO;
import com.codebyte.productservice.entity.Product;
import com.codebyte.productservice.mapper.ProductMapper;
import com.codebyte.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public Product saveProduct(Product product){
        log.info("Inside saveProduct in ProductService");
        return  productRepository.save(product);
    }

    public List<ProductDTO> findByProductByCodes(List<String> productCodes){
        log.info("Inside findByProductByCodes in ProductService");
        return productMapper.productsToProductDTOs(productRepository.findByProductUlidIn(productCodes));
    }

}
