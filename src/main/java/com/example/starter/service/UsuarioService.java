package com.example.starter.service;

import com.example.starter.form.UpdateUsuarioFORM;
import com.example.starter.form.UsuarioFORM;
import com.example.starter.form.ValidateTokenFORM;
import com.example.starter.model.Usuario;
import com.example.starter.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailSender emailSender;

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> buscarPorEmail(String email) {
        if(usuarioRepository.findByEmail(email).size()>0) {
            return usuarioRepository.findByEmail(email);
        } else {
            throw new NullPointerException();
        }
    }

    public List<Usuario> buscarPorNome(String nome) {
        if(usuarioRepository.findByNome(nome) != null) {
            return usuarioRepository.findByNome(nome);
        } else {
            throw new NullPointerException();
        }
    }

    public Usuario salvar(UsuarioFORM usuarioFORM) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioFORM.getNome());
        usuario.setEmail(usuarioFORM.getEmail());
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioFORM.getSenha()));
        usuario.setValidateCode();

        usuarioRepository.save(usuario);
        emailSender.send(usuario);

        return usuario;
    }

    public Usuario alterarUser(Long id, UpdateUsuarioFORM updateUsuarioFORM) {
        Usuario usuario = usuarioRepository.findById(id).get();
        if(!updateUsuarioFORM.getNome().equals(usuario.getNome())) {
            usuario.setNome(updateUsuarioFORM.getNome());
        } else if(!updateUsuarioFORM.getEmail().equals(usuario.getEmail())) {
            usuario.setEmail(updateUsuarioFORM.getEmail());
        } else if(updateUsuarioFORM.getSenha() != null) {
            String password = usuario.getPassword();
            String newPassword = new BCryptPasswordEncoder().encode(updateUsuarioFORM.getSenha());
            if(!password.equals(newPassword)) {
                usuario.setSenha(new BCryptPasswordEncoder().encode(updateUsuarioFORM.getSenha()));
            }
        }
        usuarioRepository.save(usuario);
        return usuario;
    }

    public boolean remover(Long id) {
        try {
            usuarioRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Usuario validate(Long id, ValidateTokenFORM validateTokenFORM) {
        Usuario usuario = usuarioRepository.findById(id).get();
        if(usuario.getValidateCode().equals(validateTokenFORM.getToken())) {
            usuario.setValidate(true);
            usuarioRepository.save(usuario);
            return usuario;
        } else {
            return null;
        }
    }
}
