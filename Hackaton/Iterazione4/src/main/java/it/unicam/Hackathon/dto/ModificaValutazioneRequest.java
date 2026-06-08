package it.unicam.Hackathon.dto;

import lombok.Data;

@Data
public class ModificaValutazioneRequest {
    private Long giudiceId;
    private int voto;
    private String testo;
}