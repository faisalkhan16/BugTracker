package com.codebyte.bugservice.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Product {

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;
}
