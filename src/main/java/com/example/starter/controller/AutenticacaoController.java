package com.example.starter.controller;

import com.example.starter.config.TokenService;
import com.example.starter.dto.TokenDTO;
import com.example.starter.dto.UsuarioDTO;
import com.example.starter.form.LoginFORM;
import com.example.starter.form.ValidateTokenFORM;
import com.example.starter.model.Usuario;
import com.example.starter.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginFORM loginFORM) {
        UsernamePasswordAuthenticationToken authenticationToken = loginFORM.convert();
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            String token = tokenService.createToken(authentication);
            return ResponseEntity.ok(new TokenDTO(token,"Bearer"));
        } catch (AuthenticationException authenticationException) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/validate/{id}")
    @Transactional
    public ResponseEntity<UsuarioDTO> validateUser(@PathVariable Long id,
                                                   @RequestBody @Valid ValidateTokenFORM validateTokenFORM) {
        Usuario usuario = usuarioService.validate(id,validateTokenFORM);
        if(usuario != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
            return ResponseEntity.ok(usuarioDTO);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
