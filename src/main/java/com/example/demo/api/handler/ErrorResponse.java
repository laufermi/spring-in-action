package com.example.demo.api.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String message;

    public static ErrorResponse of(String message) {
        return ErrorResponse.builder().message(message).build();
    }
}
