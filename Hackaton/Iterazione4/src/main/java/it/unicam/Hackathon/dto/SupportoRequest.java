package it.unicam.Hackathon.dto;

import lombok.Data;

@Data
public class SupportoRequest {
    private Long teamId;
    private Long hackathonId;
    private String descrizione;
}