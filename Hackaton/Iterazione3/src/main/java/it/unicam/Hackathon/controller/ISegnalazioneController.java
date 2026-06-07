package it.unicam.Hackathon.controller;

import it.unicam.Hackathon.dto.SegnalazioneRequest;
import it.unicam.Hackathon.entity.Segnalazione;
import it.unicam.Hackathon.service.GestoreSegnalazione;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/segnalazioni")
@RequiredArgsConstructor
public class ISegnalazioneController {

    private final GestoreSegnalazione gestoreSegnalazione;

    @PostMapping
    public ResponseEntity<Map<String, Object>> segnala(@RequestBody SegnalazioneRequest dto) {
        Segnalazione s = gestoreSegnalazione.inviaSegnalazione(dto);
        return ResponseEntity.ok(Map.of(
                "messaggio", "Segnalazione registrata!",
                "id", s.getId(),
                "team", s.getTeam().getNome(),
                "mentore", s.getMentore().getNome() + " " + s.getMentore().getCognome()
        ));
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<Segnalazione>> visualizza(@PathVariable Long teamId) {
        return ResponseEntity.ok(gestoreSegnalazione.visualizzaSegnalazioni(teamId));
    }
}