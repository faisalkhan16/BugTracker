package com.codebyte.productservice.controller;

import com.codebyte.productservice.dto.ProductDTO;
import com.codebyte.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductDTO>> getProducts(@RequestParam List<String> code){
        log.info("request: getProducts(): {}", code);
        return ResponseEntity.ok().body( productService.findByProductByCodes(code));
    }

}
