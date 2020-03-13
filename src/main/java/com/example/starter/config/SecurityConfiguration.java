package com.example.starter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/pacientes").permitAll()
                .antMatchers(HttpMethod.GET,"/pacientes/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/atualizarCadastro").permitAll()
                .antMatchers(HttpMethod.DELETE, "/deletarCadastro").permitAll()
                .antMatchers(HttpMethod.POST, "/h2-console/**").permitAll()
                .antMatchers(HttpMethod.GET,"/h2-console/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/h2-console/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }
}