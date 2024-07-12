package com.adminpanel.miniproject.service;

import com.adminpanel.miniproject.dto.UserDto;
import com.adminpanel.miniproject.entity.User;

import java.util.List;


public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    UserDto findById(int id);

    List<UserDto> findAllByRole(String role);

    User updateUser(UserDto user);

    void deleteUserById(int id);
    List<UserDto> searchUser(String role,String name);


}
