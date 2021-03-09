package com.example.demo.application.command;

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
public class DeleteUserCommand {
    private String name;

    public static DeleteUserCommand of(String name) {
        return DeleteUserCommand.builder()
                .name(name)
                .build();
    }
}
