package it.unicam.Hackathon.dto;

import lombok.Data;

@Data
public class AggiornaSottomissioneRequest {
    private Long teamId;
    private Long hackathonId;
    private String contenuto;
}