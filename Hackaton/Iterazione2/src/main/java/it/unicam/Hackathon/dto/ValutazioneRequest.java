package it.unicam.Hackathon.dto;

import lombok.Data;

@Data
public class ValutazioneRequest {
    private Long giudiceId;
    private Long sottomissioneId;
    private int voto;
    private String testo;
}