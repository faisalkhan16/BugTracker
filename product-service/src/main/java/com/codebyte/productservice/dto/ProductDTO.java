package com.codebyte.productservice.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private String code;
    private String name;
}
