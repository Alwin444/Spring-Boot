package com.demo.sec9.service.impl;

import com.demo.sec9.dto.UserDTO;
import com.demo.sec9.entity.User;
import com.demo.sec9.exception.ResourceNotFoundException;
import com.demo.sec9.repository.UserRepository;
import com.demo.sec9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceIMPL implements UserService{
    private UserRepository userRepository;

    @Autowired
    public UserServiceIMPL(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirst_name(userDTO.getFirst_name());
        user.setLast_name(userDTO.getLast_name());
        user.setEmail(userDTO.getEmail());
        User obj =  userRepository.save(user);
        UserDTO addUserDTO = new UserDTO();
        addUserDTO.setId(obj.getId());
        addUserDTO.setFirst_name(obj.getFirst_name());
        addUserDTO.setLast_name(obj.getLast_name());
        addUserDTO.setEmail(obj.getEmail());
        return addUserDTO;
    }

    @Override
    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirst_name(user.getFirst_name());
        userDTO.setLast_name(user.getLast_name());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setFirst_name(user.getFirst_name());
            userDTO.setLast_name(user.getLast_name());
            userDTO.setEmail(user.getEmail());
            return userDTO;
        }).toList();
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO,Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        user.setFirst_name(userDTO.getFirst_name());
        user.setLast_name(userDTO.getLast_name());
        user.setEmail(userDTO.getEmail());
        User obj =  userRepository.save(user);
        UserDTO updateUserDTO = new UserDTO();
        updateUserDTO.setId(obj.getId());
        updateUserDTO.setFirst_name(obj.getFirst_name());
        updateUserDTO.setLast_name(obj.getLast_name());
        updateUserDTO.setEmail(obj.getEmail());
        return updateUserDTO;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
    }
}
