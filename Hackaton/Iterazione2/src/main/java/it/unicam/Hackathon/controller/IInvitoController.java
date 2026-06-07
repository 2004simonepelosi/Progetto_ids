package it.unicam.Hackathon.controller;

import it.unicam.Hackathon.dto.InvitoRequest;
import it.unicam.Hackathon.entity.Invito;
import it.unicam.Hackathon.service.GestoreInvito;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/inviti")
@RequiredArgsConstructor
public class IInvitoController {

    private final GestoreInvito gestoreInvito;

    @PostMapping
    public ResponseEntity<Map<String, Object>> invita(@RequestBody InvitoRequest dto) {
        Invito i = gestoreInvito.creaInvito(dto);
        return ResponseEntity.ok(Map.of(
                "messaggio", "Invito Consegnato!",
                "id", i.getId(),
                "team", i.getTeam().getNome(),
                "utente", i.getUtente().getNome() + " " + i.getUtente().getCognome(),
                "stato", i.getStato()
        ));
    }

    @PostMapping("/{id}/accetta")
    public ResponseEntity<Map<String, Object>> accetta(@PathVariable Long id) {
        Invito i = gestoreInvito.accettaInvito(id);
        return ResponseEntity.ok(Map.of(
                "messaggio", "Invito Accettato!",
                "utente", i.getUtente().getNome() + " " + i.getUtente().getCognome(),
                "team", i.getTeam().getNome(),
                "ruolo", i.getUtente().getRuolo(),
                "stato", i.getStato()
        ));
    }

    @PostMapping("/{id}/rifiuta")
    public ResponseEntity<Map<String, Object>> rifiuta(@PathVariable Long id) {
        Invito i = gestoreInvito.rifiutaInvito(id);
        return ResponseEntity.ok(Map.of(
                "messaggio", "Invito Rifiutato!",
                "stato", i.getStato()
        ));
    }
}