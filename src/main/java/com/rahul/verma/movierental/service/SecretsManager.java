package com.rahul.verma.movierental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class SecretsManager {

    private final Environment environment;

    @Autowired
    public SecretsManager(final Environment environment) {
        this.environment = environment;
    }

}
