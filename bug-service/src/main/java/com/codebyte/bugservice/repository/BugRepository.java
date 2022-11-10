package com.codebyte.bugservice.repository;

import com.codebyte.bugservice.entity.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepository extends JpaRepository<Bug,Long> {


}
