package com.example.starter;

import com.example.starter.model.Usuario;
import com.example.starter.service.EmailSender;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class StarterApplicationTests {

    @Autowired
    private EmailSender emailSender;

    @Test
    public void enviarEmail() {
        Usuario usuario = new Usuario();
        usuario.setValidateCode();
        usuario.setEmail("martinrpetersen171@gmail.com");

        emailSender.send(usuario);
    }

}
