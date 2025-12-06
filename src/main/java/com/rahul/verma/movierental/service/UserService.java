package com.rahul.verma.movierental.service;

import com.rahul.verma.movierental.dto.ResponseDto;
import com.rahul.verma.movierental.dto.ResponseListDto;
import com.rahul.verma.movierental.dto.UserDto;

public interface UserService {
    ResponseDto<UserDto> addUser(UserDto userDto);
    ResponseDto<UserDto> updateUser(UserDto userDto);
    ResponseDto<UserDto> deleteUser(Integer id);
    ResponseDto<UserDto> getUserById(Integer id);
    ResponseListDto<UserDto> getAllUsers();
}
