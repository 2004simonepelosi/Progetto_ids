package it.unicam.Hackathon.controller;

import it.unicam.Hackathon.dto.*;
import it.unicam.Hackathon.entity.Sottomissione;
import it.unicam.Hackathon.service.GestoreHackathon;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sottomissioni")
@RequiredArgsConstructor
public class ISottomissioneController {

    private final GestoreHackathon gestoreHackathon;

    @PostMapping
    public ResponseEntity<Map<String, Object>> inviaSottomissione(@RequestBody SottomissioneRequest dto) {
        Sottomissione s = gestoreHackathon.salvaSottomissione(dto);
        return ResponseEntity.ok(Map.of(
                "messaggio", "Sottomissione Salvata!", "id", s.getId(),
                "team", s.getTeam().getNome(), "hackathon", s.getHackathon().getNome()
        ));
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> aggiornaSottomissione(@RequestBody AggiornaSottomissioneRequest dto) {
        Sottomissione s = gestoreHackathon.aggiornaSottomissione(dto);
        return ResponseEntity.ok(Map.of(
                "messaggio", "Sottomissione Salvata!", "id", s.getId(),
                "team", s.getTeam().getNome(), "hackathon", s.getHackathon().getNome()
        ));
    }

    @GetMapping("/hackathon/{hackathonId}")
    public ResponseEntity<List<Sottomissione>> visualizza(@PathVariable Long hackathonId) {
        return ResponseEntity.ok(gestoreHackathon.getSottomissioni(hackathonId));
    }
}