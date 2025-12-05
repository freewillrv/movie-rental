package com.rahul.verma.movierental.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Entity;

import java.time.Instant;

@Data
@Entity
public class CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant modifiedAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private Instant createdAt;

}
