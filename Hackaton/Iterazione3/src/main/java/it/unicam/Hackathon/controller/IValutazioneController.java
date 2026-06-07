package it.unicam.Hackathon.controller;

import it.unicam.Hackathon.dto.ValutazioneRequest;
import it.unicam.Hackathon.entity.Valutazione;
import it.unicam.Hackathon.service.GestoreHackathon;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/valutazioni")
@RequiredArgsConstructor
public class IValutazioneController {

    private final GestoreHackathon gestoreHackathon;

    @PostMapping
    public ResponseEntity<Map<String, Object>> valuta(@RequestBody ValutazioneRequest dto) {
        Valutazione v = gestoreHackathon.salvaValutazione(dto);
        return ResponseEntity.ok(Map.of(
                "messaggio", "Valutazione salvata con successo!",
                "id", v.getId(),
                "voto", v.getVoto(),
                "giudizio", v.getTesto(),
                "giudice", v.getGiudice().getNome() + " " + v.getGiudice().getCognome()
        ));
    }
}