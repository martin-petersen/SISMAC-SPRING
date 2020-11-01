package com.example.starter.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class ValidateTokenFORM {
    @NotNull @Length(max = 6)
    private String token;

    public String getToken() {
        return token;
    }
}
