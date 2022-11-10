package com.codebyte.bugservice.mapper;

import com.codebyte.bugservice.VO.Product;
import com.codebyte.bugservice.VO.Severity;
import com.codebyte.bugservice.VO.Status;
import com.codebyte.bugservice.VO.User;
import com.codebyte.bugservice.constant.SEVERITY;
import com.codebyte.bugservice.constant.STATUS;
import com.codebyte.bugservice.dto.BugRequestDTO;
import com.codebyte.bugservice.dto.BugResponseDTO;
import com.codebyte.bugservice.entity.Bug;
import com.codebyte.bugservice.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BugMapper {

    private final CommentMapper commentMapper;

    public Bug bugDTOToBug(BugRequestDTO bugRequestDTO){
        return Bug.builder()
                .name(bugRequestDTO.getName())
                .description(bugRequestDTO.getDescription())
                .severity(bugRequestDTO.getSeverity())
                .status(bugRequestDTO.getStatus())
                .productUlid(bugRequestDTO.getProductId())
                .assignByUlid(bugRequestDTO.getAssignByUserId())
                .assignToUlid(bugRequestDTO.getAssignToUserId())
                .build();
    }

    public BugResponseDTO bugToBugResponseDTO(Bug bug,User assignBy, User assignTo, Product product){
        return BugResponseDTO.builder()
                .id(bug.getId())
                .name(bug.getName())
                .description(bug.getDescription())
                .severity(new Severity(bug.getSeverity(), String.valueOf(SEVERITY.values()[bug.getSeverity()])))
                .status(new Status(bug.getStatus(), String.valueOf(STATUS.values()[bug.getStatus()])))
                .product(product)
                .assignByUser(assignBy)
                .assignToUser(assignTo)
                .commentResponseDTO(commentMapper.commentsToCommentDTOs(bug.getComments()))
                .build();
    }

    public List<BugResponseDTO> bugsToBugResponseDTOs(List<Bug> bugs, User[] arrayAssignBy, User[] arrayAssignTo, Product[] arrayProducts) {
        List<BugResponseDTO> bugResponseDTOs = new ArrayList<>();

        for(Bug bug:bugs){
            User assignBy = Arrays.stream(arrayAssignBy).filter(user -> user.getId().equalsIgnoreCase(bug.getAssignByUlid())).findFirst().orElse(new User());
            User assignTo = Arrays.stream(arrayAssignTo).filter(user -> user.getId().equalsIgnoreCase(bug.getAssignToUlid())).findFirst().orElse(new User());
            Product product = Arrays.stream(arrayProducts).filter(prod -> prod.getCode().equalsIgnoreCase(bug.getProductUlid())).findFirst().orElse(new Product());
            bugResponseDTOs.add(bugToBugResponseDTO(bug,assignBy,assignTo,product));
        }

        return bugResponseDTOs;
    }

}
