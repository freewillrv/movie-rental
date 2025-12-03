package com.rahul.verma.movierental.util;

import com.rahul.verma.movierental.service.SecretsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class CommonUtil {

    private final Environment environment;

    @Autowired
    public CommonUtil(final Environment environment, final SecretsManager secretsManager) {
        this.environment = environment;
    }

}
