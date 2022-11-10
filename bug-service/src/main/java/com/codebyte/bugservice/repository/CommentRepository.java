package com.codebyte.bugservice.repository;

import com.codebyte.bugservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    void deleteByCommentUlid(String commentUlid);

    Comment findByCommentUlid(String commentUlid);

    List<Comment> findByBugId(Long bugId);
}
