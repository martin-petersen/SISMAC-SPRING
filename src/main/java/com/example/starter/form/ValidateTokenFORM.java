package com.example.starter.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class ValidateTokenFORM {
@NotNull(message = "token não pode ser vazio") @Length(message="token não tem tamanho maior que 6 caracteres",max = 6)
    private String token;

    public String getToken() {
        return token;
    }
}
