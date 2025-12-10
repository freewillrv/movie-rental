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
        super(userRepository, "User", Set.of("id", "createdAt", "modifiedAt"));
        this.userRepository = userRepository;
        this.cryptoService = cryptoService;
    }

    @Override
    protected User preCreateTransform(final User user) {
        user.setPassword(
                cryptoService.hashPassword(user.getPassword().toCharArray())
        );
        return user;
    }

}

