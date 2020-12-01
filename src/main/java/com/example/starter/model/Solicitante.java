package com.example.starter.model;
import javax.persistence.*;

public abstract class Solicitante {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}