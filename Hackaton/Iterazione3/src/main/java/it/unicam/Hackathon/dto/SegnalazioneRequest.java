package it.unicam.Hackathon.dto;

import lombok.Data;

@Data
public class SegnalazioneRequest {
    private Long mentoreId;
    private Long teamId;
    private Long hackathonId;
    private String descrizione;
}