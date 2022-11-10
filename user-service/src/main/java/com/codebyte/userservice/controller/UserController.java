package com.codebyte.userservice.controller;

import com.codebyte.userservice.dto.UserDTO;
import com.codebyte.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDTO>> getUsers(@RequestParam List<String> id){
        log.info("request: getUsers(): {}", id);
        return ResponseEntity.ok().body( userService.findByUserIds(id));
    }

}
