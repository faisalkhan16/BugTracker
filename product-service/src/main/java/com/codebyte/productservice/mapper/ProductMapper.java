package com.codebyte.productservice.mapper;

import com.codebyte.productservice.dto.ProductDTO;
import com.codebyte.productservice.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    public ProductDTO productToProductDTO(Product product){
        return ProductDTO.builder().code(product.getProductUlid()).name(product.getName()).build();
    }

    public List<ProductDTO> productsToProductDTOs(List<Product> products){
        List<ProductDTO> productDTOs = new ArrayList<>();
        for(Product product:products){
            productDTOs.add(productToProductDTO(product));
        }

        return productDTOs;
    }
}
