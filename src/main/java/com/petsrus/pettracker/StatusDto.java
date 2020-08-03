package com.petsrus.pettracker;

import org.hibernate.annotations.Immutable;


@Immutable
public class StatusDto {
    String status;
    String message;

    public StatusDto(String status) {
        this.status = status;
    }

    public StatusDto(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
