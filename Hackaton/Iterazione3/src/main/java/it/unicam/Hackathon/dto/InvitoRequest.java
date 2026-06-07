package it.unicam.Hackathon.dto;

import lombok.Data;

@Data
public class InvitoRequest {
    private Long teamId;
    private Long utenteId;
    private String descrizione;
}