package com.example.starter.config;

import com.example.starter.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private final String expiration = "1800000";
    private final String secret = "62%Z3nLm&fxyjenB47soU*%S7ltiCvAA4&YWM#Q@Syu5t5Ocm*%yc$WLLPCneLqjMekTbae59zxn&E8X9n8XeA$x5Pb6i@lIaAHz$^Wb2R@@@liu1yoRhc$yZXvHYLWbJ8U4&uBC$ecAAqCaUip6CqvUKbCy3swjEYr9aXNIM%z1Jo9iSrhDP55KL&&Bi6k6H6#9$iAwt6Gz7w@2ThZxK&x5TLGa%%6Pkm5YAw4D%aoImq2KCqoUcpC8l5aDyoV$2U3TTAYUcT2qw0K*^2RRYoK%!a8cdRLO3IHDx#kiTlk!";



    public String createToken(Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API do Fórum da Alura")
                .setSubject(logado.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try{
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (Exception e) {
            return false;
        }

    }

    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
