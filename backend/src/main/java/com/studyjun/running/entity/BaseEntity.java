package com.studyjun.running.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public abstract class BaseEntity {
    @Id
    private String id;

    @CreatedDate
    private String createDate;

    @LastModifiedDate
    private String updateDate;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public BaseEntity() {
        this.createDate = LocalDateTime.now().format(FORMATTER);
        this.updateDate = LocalDateTime.now().format(FORMATTER);
    }

    public void updateTimestamps() {
        this.updateDate = LocalDateTime.now().format(FORMATTER);
    }
}