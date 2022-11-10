package com.codebyte.bugservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class CommentResponseDTO {

    @JsonProperty(value = "comment_id")
    private String commentId;

    @JsonProperty(value = "bug_id")
    private Long bugId;

    @JsonProperty(value = "description")
    private String description;
}
