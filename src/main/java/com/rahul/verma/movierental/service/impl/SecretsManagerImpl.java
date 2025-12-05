package com.rahul.verma.movierental.service.impl;

import com.rahul.verma.movierental.service.SecretsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class SecretsManagerImpl implements SecretsManager {

    private final Environment environment;

    @Autowired
    public SecretsManagerImpl(final Environment environment) {
        this.environment = environment;
    }

}
