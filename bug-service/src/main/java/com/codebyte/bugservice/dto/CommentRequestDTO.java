package com.codebyte.bugservice.dto;

import com.codebyte.bugservice.validation.CommentRequestConstraint;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@CommentRequestConstraint
@EqualsAndHashCode
public class CommentRequestDTO {

    @JsonProperty(value = "bug_id")
    private Long bugId;

    @JsonProperty(value = "description")
    private String description;

}
