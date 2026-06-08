package it.unicam.Hackathon.dto;

import lombok.Data;

@Data
public class PrenotaSlotRequest {
    private Long callId;
    private String slotConfermato;
}