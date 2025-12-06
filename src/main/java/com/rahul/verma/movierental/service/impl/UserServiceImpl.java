package com.rahul.verma.movierental.service.impl;

import com.rahul.verma.movierental.dto.MessageDto;
import com.rahul.verma.movierental.dto.ResponseDto;
import com.rahul.verma.movierental.dto.ResponseListDto;
import com.rahul.verma.movierental.dto.UserDto;
import com.rahul.verma.movierental.entity.User;
import com.rahul.verma.movierental.exception.NotFoundException;
import com.rahul.verma.movierental.repository.UserRepository;
import com.rahul.verma.movierental.service.AbstractCommonService;
import com.rahul.verma.movierental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl extends AbstractCommonService<User> implements UserService {

    private final UserRepository userRepository;
    private final CryptoServiceImpl cryptoService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CryptoServiceImpl cryptoService) {
        super(userRepository, "User", Set.of("id", "userName", "email"));
        this.userRepository = userRepository;
        this.cryptoService = cryptoService;
    }

    @Override
    public ResponseDto<UserDto> addUser(UserDto userDto) {
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setModifiedAt(LocalDateTime.now());
        userDto.setLastLogin(LocalDateTime.now());
        User entity = mapToEntity(userDto);
        entity.setPassword(
                cryptoService.hashPassword(userDto.getPassword().toCharArray())
        );
        User saved = create(entity);
        return response(
                mapToDto(saved),
                List.of(new MessageDto("User created successfully", 201)),
                201
        );
    }

    @Override
    public ResponseDto<UserDto> updateUser(UserDto userDto) {

        User existing = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        existing.setEmailId(userDto.getEmailId());
        existing.setGivenName(userDto.getGivenName());
        existing.setLastName(userDto.getLastName());
        existing.setModifiedAt(LocalDateTime.now());

        User updated = update(existing);

        return response(
                mapToDto(updated),
                List.of(new MessageDto("User updated successfully", 200)),
                200
        );
    }

    @Override
    public ResponseDto<UserDto> deleteUser(Integer id) {
        delete(id);
        return response(
                null,
                List.of(new MessageDto("User deleted successfully", 204)),
                204
        );
    }

    @Override
    public ResponseDto<UserDto> getUserById(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return response(
                mapToDto(user),
                List.of(new MessageDto("User fetched successfully", 200)),
                200
        );
    }

    @Override
    public ResponseListDto<UserDto> getAllUsers() {

        List<User> users = findAll();
        List<UserDto> dtos = users.stream()
                .map(this::mapToDto)
                .toList();
        return listResponse(
                dtos,
                List.of(new MessageDto("Users fetched successfully", 200)),
                200
        );
    }

    private User mapToEntity(UserDto dto) {
        User u = new User();
        u.setUserName(dto.getUserName());
        u.setEmailId(dto.getEmailId());
        u.setGivenName(dto.getGivenName());
        u.setLastLogin(dto.getLastLogin());
        u.setLastName(dto.getLastName());
        u.setCreatedAt(dto.getCreatedAt());
        u.setLastLogin(dto.getLastLogin());
        u.setModifiedAt(dto.getModifiedAt());
        return u;
    }

    private UserDto mapToDto(User user) {
        UserDto dto = new UserDto(user.getUserName());
        dto.setEmailId(user.getEmailId());
        dto.setGivenName(user.getGivenName());
        dto.setLastName(user.getLastName());
        return dto;
    }

    public  ResponseDto<UserDto> response(UserDto data, List<MessageDto> messages, int status) {
        return new ResponseDto<>(data, messages, status);
    }

    public ResponseListDto<UserDto> listResponse(List<UserDto> data, List<MessageDto> messages, int status) {
        return new ResponseListDto<>(data, messages, status);
    }
}

