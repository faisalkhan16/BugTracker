package com.codebyte.bugservice.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class Severity {

    @JsonProperty(value = "code")
    private int code;

    @JsonProperty(value = "name")
    private String name;
}
