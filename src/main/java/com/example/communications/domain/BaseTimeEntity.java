package com.example.communications.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime signDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

}