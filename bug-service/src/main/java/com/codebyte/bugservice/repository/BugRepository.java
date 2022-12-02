package com.codebyte.bugservice.repository;

import com.codebyte.bugservice.entity.Bug;
import com.codebyte.bugservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepository extends JpaRepository<Bug,Long> {

    Bug findByName(String Name);

}
