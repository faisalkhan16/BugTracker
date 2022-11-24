package com.codebyte.userservice.service;

import com.codebyte.userservice.dto.UserDTO;
import com.codebyte.userservice.entity.User;
import com.codebyte.userservice.mapper.UserMapper;
import com.codebyte.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public User saveUser(User user){
        log.info("Inside UserService saveUser in UserService");
        return  userRepository.save(user);
    }

    public List<UserDTO> findByUserIds(List<String> userIds){
        log.info("Inside UserService findByUserId in UserService");
/*        try {
            log.info("Wait Started");
            Thread.sleep(10000);
            log.info("Wait End");

        } catch(InterruptedException e){
            log.error("Inside UserService findByUserId Thread Failure");
            e.printStackTrace();
        }*/
        return userMapper.usersToUserDTOs(userRepository.findByUserUlidIn(userIds));
    }

}
