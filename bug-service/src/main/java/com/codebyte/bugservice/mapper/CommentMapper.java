package com.codebyte.bugservice.mapper;

import com.codebyte.bugservice.dto.CommentRequestDTO;
import com.codebyte.bugservice.dto.CommentResponseDTO;
import com.codebyte.bugservice.entity.Comment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapper {

    public Comment commentDTOToComment(CommentRequestDTO commentRequestDTO){
        return Comment.builder()
                .description(commentRequestDTO.getDescription())
                .bugId(commentRequestDTO.getBugId())
                .build();
    }

    public CommentResponseDTO commentToCommentDTO(Comment comment){
        return CommentResponseDTO.builder()
                .description(comment.getDescription())
                .bugId(comment.getBugId())
                .commentId(comment.getCommentUlid())
                .build();
    }

    public List<CommentResponseDTO> commentsToCommentDTOs(List<Comment> comments) {
        List<CommentResponseDTO> commentResponseDTOs = new ArrayList<>();

        for(Comment comment:comments){
            commentResponseDTOs.add(commentToCommentDTO(comment));
        }
        return commentResponseDTOs;
    }
}
