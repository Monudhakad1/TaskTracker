package com.tracker.newtracker.models.dtos;

public record ErrorResponse(
        int status ,
        String message,
        String details
){

}
