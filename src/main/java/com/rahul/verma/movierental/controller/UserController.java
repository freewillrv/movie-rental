package com.rahul.verma.movierental.controller;

import com.rahul.verma.movierental.assembler.UserAssembler;
import com.rahul.verma.movierental.dto.MessageDto;
import com.rahul.verma.movierental.dto.UserDto;
import com.rahul.verma.movierental.dto.*;
import com.rahul.verma.movierental.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserAssembler userAssembler;

    @Autowired
    public UserController(final UserService userService, final UserAssembler userAssembler) {
        this.userService = userService;
        this.userAssembler = userAssembler;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        final UserDto responseUserDto = userAssembler.mapToDto(userService.create(userAssembler.mapToEntity(userDto)));
        responseUserDto.setMessages(List.of(new MessageDto("Created user successfully", HttpStatus.OK.value())));
        return new ResponseEntity<>(responseUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer id, @RequestBody @Valid UserDto userDto) {
        userDto.setId(id);  // ensure path ID is used
        final UserDto responseUserDto = userAssembler.mapToDto(userService.update(userAssembler.mapToEntity(userDto)));
        responseUserDto.setMessages(List.of(new MessageDto("Updated user successfully", HttpStatus.OK.value())));
        return new ResponseEntity<>(responseUserDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        final UserDto user = userAssembler.mapToDto(userService.getById(id));
        user.setMessages(List.of(new MessageDto("Fetched Successfully", HttpStatus.OK.value())));
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Validated
    @GetMapping
    public ResponseEntity<ResponseListDto> getAllUsers(@ModelAttribute PageableInput input) {
        Page<UserDto> page = userService.findAll(input).map(userAssembler::mapToDto);
        return ResponseEntity.status(HttpStatus.OK)
                .header("X-Page-No", String.valueOf(page.getNumber()))
                .header("X-Page-Size", String.valueOf(page.getSize()))
                .header("X-Total-Pages", String.valueOf(page.getTotalPages()))
                .header("X-Total-Items", String.valueOf(page.getTotalElements()))
                .body(ResponseListDto.builder()
                        .data(page.getContent())
                        .messages(List.of(
                                new MessageDto("Fetched Successfully", HttpStatus.OK.value())
                        )).build()
                );
    }
}