package com.rahul.verma.movierental.controller;

import com.rahul.verma.movierental.dto.*;
import com.rahul.verma.movierental.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDto<UserDto>> createUser(@RequestBody UserDto userDto) {
        ResponseDto<UserDto> response = userService.addUser(userDto);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<UserDto>> updateUser(@PathVariable Integer id, @RequestBody @Valid  UserDto userDto) {
        userDto.setId(id);  // ensure path ID is used
        ResponseDto<UserDto> response = userService.updateUser(userDto);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<UserDto>> deleteUser(@PathVariable Integer id) {
        ResponseDto<UserDto> response = userService.deleteUser(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<UserDto>> getUserById(@PathVariable Integer id) {
        ResponseDto<UserDto> response = userService.getUserById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping
    public PaginatedResponseDto<UserDto> getAllUsers(@ModelAttribute PageableInput input) {
        return userService.getAllUsers(input);
    }

}
