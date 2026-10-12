package com.demo.sec9.service;

import com.demo.sec9.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO addUser(UserDTO userDTO);
    UserDTO getUser(Long id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(UserDTO userDTO,Long id);
    void deleteUser(Long id);
}
