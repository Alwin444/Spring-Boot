package com.demo.sec9.controller;

import com.demo.sec9.dto.UserDTO;
import com.demo.sec9.entity.User;
import com.demo.sec9.repository.UserRepository;
import com.demo.sec9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        UserDTO userDTO1 = userService.addUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO1);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOList = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userDTOList);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
        UserDTO userDTO = userService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO,@PathVariable Long id) {
        UserDTO userDTO1 = userService.updateUser(userDTO,id);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO1);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.status(204).body("User Deleted");

    }

}
