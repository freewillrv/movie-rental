package com.rahul.verma.movierental.service;

import com.rahul.verma.movierental.dto.*;
import org.springframework.data.domain.Pageable;

public interface UserService {
    ResponseDto<UserDto> addUser(UserDto userDto);
    ResponseDto<UserDto> updateUser(UserDto userDto);
    ResponseDto<UserDto> deleteUser(Integer id);
    ResponseDto<UserDto> getUserById(Integer id);
    PaginatedResponseDto<UserDto> getAllUsers(PageableInput input);
}
