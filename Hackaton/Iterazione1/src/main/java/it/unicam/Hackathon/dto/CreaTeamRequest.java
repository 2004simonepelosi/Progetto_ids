package it.unicam.Hackathon.dto;

import lombok.Data;

@Data
public class CreaTeamRequest {
    private Long capoGruppoId;
    private Long hackathonId;
    private String nome;
}