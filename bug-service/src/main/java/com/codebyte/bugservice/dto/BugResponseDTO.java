package com.codebyte.bugservice.dto;

import com.codebyte.bugservice.VO.Product;
import com.codebyte.bugservice.VO.Severity;
import com.codebyte.bugservice.VO.Status;
import com.codebyte.bugservice.VO.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class BugResponseDTO {

    @JsonProperty(value = "bug_id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "product")
    private Product product;

    @JsonProperty(value = "assign_by_user")
    private User assignByUser;

    @JsonProperty(value = "assign_to_user")
    private User assignToUser;

    @JsonProperty(value = "severity")
    private Severity severity;

    @JsonProperty(value = "status")
    private Status status;

    @JsonProperty(value = "comments")
    private List<CommentResponseDTO> commentResponseDTO;

}
