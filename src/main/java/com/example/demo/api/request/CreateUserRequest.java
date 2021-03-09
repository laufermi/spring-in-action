package com.example.demo.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private static final int MAX_LENGTH = 255;

    @NotBlank
    @Size(max = MAX_LENGTH)
    private String name;

    @NotBlank
    @Email
    @Size(max = MAX_LENGTH)
    private String email;

    @NotBlank
    @Size(max = MAX_LENGTH)
    private String phone;

    public String getName() {
        return name.toLowerCase(Locale.CHINA);
    }

    public void setName(String name) {
        this.name = name.toLowerCase(Locale.CHINA);
    }
}
