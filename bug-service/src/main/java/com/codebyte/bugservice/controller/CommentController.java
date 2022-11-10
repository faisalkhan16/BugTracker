package com.codebyte.bugservice.controller;

import com.codebyte.bugservice.dto.CommentRequestDTO;
import com.codebyte.bugservice.dto.CommentResponseDTO;
import com.codebyte.bugservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping(value = "/comments")
    public ResponseEntity create(@Valid @RequestBody CommentRequestDTO commentRequestDTO)
    {
        log.info("request: create(): {}", commentRequestDTO);
        String createdBy = "01GH3QB3T70HWTFX4CRGCT1HXT";
        commentService.saveComment(commentRequestDTO,createdBy);
        log.info("response: create(): {}", HttpStatus.CREATED);
        return new  ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/bugs/{bug_id}/comments")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByBugId(@PathVariable @NotBlank(message = "invalid bug_id") Long bug_id)
    {
        log.info("request: getCommentsByBugUlId BugId(): {}", bug_id);
        List<CommentResponseDTO> commentResponseDTOs = commentService.getCommentsByBugId(bug_id);
        return ResponseEntity.ok().body(commentResponseDTOs);
    }

    @DeleteMapping(value = "/comment/{comment_id}")
    public ResponseEntity delete(@PathVariable @NotBlank(message = "invalid comment_id") String comment_id)
    {
        log.info("request: delete(): {}", comment_id);
        commentService.deleteComment(comment_id);
        log.info("response: delete(): {}", HttpStatus.NO_CONTENT);
        return new  ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @PatchMapping(value = "/comment/{comment_id}")
    public ResponseEntity update(@Valid @RequestBody CommentRequestDTO commentRequestDTO,@PathVariable @NotBlank(message = "invalid comment_id") String comment_id)
    {
        String updatedBy = "01GH3QB3T70HWTFX4CRGCT1HXT";
        log.info("request: update(): {}", comment_id);
        commentService.updateComment(commentRequestDTO,comment_id,updatedBy);
        log.info("response: update(): {}", HttpStatus.NO_CONTENT);

        return new  ResponseEntity(HttpStatus.NO_CONTENT);

    }
}
