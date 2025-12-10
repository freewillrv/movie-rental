package com.rahul.verma.movierental.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name="user")
@Entity
@Getter
@Setter
public class User extends CommonEntity {

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "email_id", nullable = false, unique = true)
    private String emailId;

    @Column(nullable = false)
    private String password;

    @Column(name = "given_name")
    private String givenName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

}
