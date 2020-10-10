package com.example.starter.controller;

import com.example.starter.config.TokenService;
import com.example.starter.dto.TokenDTO;
import com.example.starter.dto.UsuarioDTO;
import com.example.starter.form.LoginFORM;
import com.example.starter.form.RecuperarUsuarioFORM;
import com.example.starter.form.UsuarioFORM;
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
import java.util.List;

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
            boolean validate = false;
            if(usuarioService.authenticated(authentication)) {
                validate = true;
            }
            String token = tokenService.createToken(authentication);
            return ResponseEntity.ok(new TokenDTO(token,"Bearer", validate,usuarioService.getID(authentication)));
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
            usuario.setValidate(true);
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
            return ResponseEntity.ok(usuarioDTO);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/recoverPassword")
    @Transactional
    public ResponseEntity<Boolean> recover(@RequestBody @Valid RecuperarUsuarioFORM recuperarUsuarioFORM) {
        try {
            Usuario usuarios = usuarioService.recuperarSenha(recuperarUsuarioFORM);
            return ResponseEntity.ok(Boolean.TRUE);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
