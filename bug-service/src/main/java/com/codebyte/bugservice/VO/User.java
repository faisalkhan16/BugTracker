package com.codebyte.bugservice.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class User {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;
}
