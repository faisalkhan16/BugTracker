package com.codebyte.userservice.mapper;

import com.codebyte.userservice.dto.UserDTO;
import com.codebyte.userservice.repository.UserInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public UserDTO userToUserDTO(UserInfo user){
        return UserDTO.builder().id(user.getUserUlid()).name(user.getName()).build();
    }

    public List<UserDTO> usersToUserDTOs(List<UserInfo> users){
        List<UserDTO> userDTOs = new ArrayList<>();
        for(UserInfo user:users){
            userDTOs.add(userToUserDTO(user));
        }

        return userDTOs;
    }
}
