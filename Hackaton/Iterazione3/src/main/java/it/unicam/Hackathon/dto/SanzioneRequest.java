package it.unicam.Hackathon.dto;

import lombok.Data;

@Data
public class SanzioneRequest {
    private Long organizzatoreId;
    private Long teamId;
    private Long hackathonId;
    private Long segnalazioneId;
    private String sanzione;
}