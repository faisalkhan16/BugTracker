package com.codebyte.userservice.repository;

import com.codebyte.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<UserInfo> findByUserUlidIn(List<String> userUlids);
}
