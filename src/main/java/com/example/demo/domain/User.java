package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String name;
    private String email;
    private String phone;

    public String getName() {
        return name.toLowerCase(Locale.CHINA);
    }

    public void setName(String name) {
        this.name = name.toLowerCase(Locale.CHINA);
    }
}
