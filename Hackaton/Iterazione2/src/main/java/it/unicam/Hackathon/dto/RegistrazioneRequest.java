package it.unicam.Hackathon.dto;

import lombok.Data;

@Data
public class RegistrazioneRequest {
    private String nome;
    private String cognome;
    private String email;
    private String password;
}