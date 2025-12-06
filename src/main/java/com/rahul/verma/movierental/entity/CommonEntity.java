package com.rahul.verma.movierental.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @UpdateTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
