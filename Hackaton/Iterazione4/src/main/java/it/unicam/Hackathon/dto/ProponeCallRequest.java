package it.unicam.Hackathon.dto;

import lombok.Data;

@Data
public class ProponeCallRequest {
    private Long mentoreId;
    private Long supportoId;
    private String data;
    private String ora;
    private int durata;
    private String note;
}