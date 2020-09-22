package com.example.starter.config;

import com.example.starter.model.Usuario;
import com.example.starter.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Usuario> usuario = usuarioRepository.findByEmail(username);
        if(usuario.size()>0) {
            return usuario.get(0);
        }
        throw new UsernameNotFoundException("Dados inválidos!");
    }
}
