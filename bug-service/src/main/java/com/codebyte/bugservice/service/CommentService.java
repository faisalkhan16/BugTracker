package com.codebyte.bugservice.service;

import com.codebyte.bugservice.dto.CommentRequestDTO;
import com.codebyte.bugservice.dto.CommentResponseDTO;
import com.codebyte.bugservice.entity.Comment;
import com.codebyte.bugservice.mapper.CommentMapper;
import com.codebyte.bugservice.repository.CommentRepository;
import com.codebyte.bugservice.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public List<CommentResponseDTO> getCommentsByBugId(Long bugId) {
        log.info("Inside CommentService getCommentsByBugUlid() Bug_Id: {}",bugId);
        List<Comment> comments = commentRepository.findByBugId(bugId);
        List<CommentResponseDTO> commentDTOs = commentMapper.commentsToCommentDTOs(comments);
        return commentDTOs;
    }

    public void saveComment(CommentRequestDTO commentRequestDTO,String createdBy) {
        log.info("Inside CommentService saveComment()");
        Comment comment = commentMapper.commentDTOToComment(commentRequestDTO);
        comment.setCommentUlid(Util.getUlid());
        comment.setCreatedDate(LocalDate.now());
        comment.setCreatedBy(createdBy);
        commentRepository.save(comment);
    }

    public void deleteComment(String commentUliid) {
        log.info("Inside CommentService deleteComment CommentUlid: {}",commentUliid);
        commentRepository.deleteByCommentUlid(commentUliid);
    }

    public void updateComment(CommentRequestDTO commentRequestDTO,String commentUliid,String updatedBy) {
        log.info("Inside CommentService updateComment CommentUlid: {}",commentUliid);
        Comment comment = commentRepository.findByCommentUlid(commentUliid);
        Comment updateComment = getUpdateComment(comment, commentRequestDTO);
        updateComment.setCommentUlid(commentUliid);
        updateComment.setUpdatedBy(updatedBy);
        commentRepository.save(updateComment);
    }

    private Comment getUpdateComment(Comment commentToUpdate, CommentRequestDTO commentRequestDTO){
        Comment comment = commentMapper.commentDTOToComment(commentRequestDTO);
        commentToUpdate.setDescription(comment.getDescription());
        commentToUpdate.setCreatedDate(comment.getCreatedDate());
        commentToUpdate.setCreatedBy(comment.getCreatedBy());
        commentToUpdate.setUpdatedDate(LocalDate.now());
        return commentToUpdate;
    }
}
