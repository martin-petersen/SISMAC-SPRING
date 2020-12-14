package com.example.starter.model;

import com.example.starter.form.UpdateUsuarioFORM;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    private String senha;
    private boolean validate = false;
    private String validateCode;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_role",
            joinColumns = {@JoinColumn(name = "usuario_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> perfis = new ArrayList<>();

    @Nullable
    @ManyToOne
    private Cliente cliente;

    public Usuario() {

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Role> getPerfil() {
        return perfis;
    }

    public void setPerfil(Role role) {
        this.perfis.add(role);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setPaciente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public void setPerfis(List<Role> perfis) {
        this.perfis = perfis;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode() {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder code = new StringBuilder();
        int index = -1;
        Random random = new Random();
        for (int i=0; i<6; ++i) {
            index = random.nextInt(chars.length());
            code.append(chars.charAt(index));
        }
        this.validateCode = code.toString();
    }
}
