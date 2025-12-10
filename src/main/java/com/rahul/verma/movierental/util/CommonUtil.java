package com.rahul.verma.movierental.util;

import com.rahul.verma.movierental.dto.MessageDto;
import com.rahul.verma.movierental.service.SecretsManager;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Service
public class CommonUtil {

    private final Environment environment;

    @Autowired
    public CommonUtil(final Environment environment, final SecretsManager secretsManager) {
        this.environment = environment;
    }


}
