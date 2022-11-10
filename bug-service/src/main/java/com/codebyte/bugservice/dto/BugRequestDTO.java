package com.codebyte.bugservice.dto;

import com.codebyte.bugservice.validation.BugRequestContraint;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@BugRequestContraint
@EqualsAndHashCode
public class BugRequestDTO {


    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "severity_code")
    private int severity;

    @JsonProperty(value = "status_code")
    private int status;

    @JsonProperty(value = "product_id")
    private String productId;

    @JsonProperty(value = "assign_by_user_id")
    private String assignByUserId;

    @JsonProperty(value = "assign_to_user_id")
    private String assignToUserId;



}
