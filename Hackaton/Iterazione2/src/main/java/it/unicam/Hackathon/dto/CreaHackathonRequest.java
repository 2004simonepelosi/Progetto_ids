package it.unicam.Hackathon.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CreaHackathonRequest {
    private Long organizzatoreId;
    private String nome;
    private String descrizione;
    private String luogo;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private String dataIscrizione;
    private int numeroMassimoPersone;
    private int numeroMinimoPersone;
    private float premio;
}