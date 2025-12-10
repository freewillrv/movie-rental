package com.rahul.verma.movierental.assembler;

import com.rahul.verma.movierental.dto.UserDto;
import com.rahul.verma.movierental.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserAssembler {

    public User mapToEntity(final UserDto dto) {
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

    public UserDto mapToDto(final User user) {
        UserDto dto = new UserDto(user.getUserName());
        dto.setEmailId(user.getEmailId());
        dto.setGivenName(user.getGivenName());
        dto.setLastName(user.getLastName());
        return dto;
    }
}
