package com.example.starter.service;

import com.example.starter.form.RecuperarUsuarioFORM;
import com.example.starter.form.UpdateUsuarioFORM;
import com.example.starter.form.UsuarioFORM;
import com.example.starter.form.ValidateTokenFORM;
import com.example.starter.model.Paciente;
import com.example.starter.model.Role;
import com.example.starter.model.Usuario;
import com.example.starter.repository.PacienteRepository;
import com.example.starter.repository.RoleRepository;
import com.example.starter.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private RoleRepository roleRepository;

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
        usuario.setNome(usuarioFORM.getNome().toUpperCase());
        usuario.setEmail(usuarioFORM.getEmail());
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioFORM.getSenha()));
        usuario.setValidateCode();
        Long id = usuarioFORM.getRole();
        if(roleRepository.findById(id).isPresent()) {
            Role role = roleRepository.findById(id).get();
            usuario.setPerfil(role);
        }
        usuarioRepository.save(usuario);
        emailSender.send(usuario);
        return usuario;
    }

    public Usuario alterarUser(Long id, UpdateUsuarioFORM updateUsuarioFORM) {
        Usuario usuario = usuarioRepository.findById(id).get();
        if(!updateUsuarioFORM.getNome().equals(usuario.getNome())) {
            usuario.setNome(updateUsuarioFORM.getNome().toUpperCase());
        }
        if(!updateUsuarioFORM.getEmail().equals(usuario.getEmail())) {
            usuario.setEmail(updateUsuarioFORM.getEmail());
        }
        if(updateUsuarioFORM.getSenha() != null) {
            String password = usuario.getPassword();
            String newPassword = updateUsuarioFORM.getSenha();
            boolean isMatch = new BCryptPasswordEncoder().matches(newPassword,password);
            if(!isMatch) {
                usuario.setSenha(new BCryptPasswordEncoder().encode(newPassword));
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

    public boolean authenticated(Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal();
        if(logado.isValidate())
            return true;
        else
            return false;
    }

    public Usuario recuperarSenha(RecuperarUsuarioFORM recuperarUsuarioFORM) {
        List<Usuario> usuarios = usuarioRepository.findByEmail(recuperarUsuarioFORM.getEmail());
        Usuario usuario = usuarios.get(0);
        if(usuario.getNome().equals(recuperarUsuarioFORM.getNome().toUpperCase())) {
            String newPassword = new StringBuffer(recuperarUsuarioFORM.getEmail()).reverse().toString();
            usuario.setSenha(new BCryptPasswordEncoder().encode(newPassword));
            usuarioRepository.save(usuario);
            emailSender.recoverPassword(usuario,newPassword);
        } else {
            return null;
        }
        return usuario;
    }

    public Long getID(Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal();
        return logado.getId();
    }

    public Usuario criarVinculo(Long id, String cpf) {
        Usuario usuario = usuarioRepository.findById(id).get();
        Paciente paciente = pacienteRepository.findByCpf(cpf);
        usuario.setPaciente(paciente);
        return usuario;
    }

    public String getRole(Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal();
        return logado.getPerfil().get(0).getPerfil();
    }
}
