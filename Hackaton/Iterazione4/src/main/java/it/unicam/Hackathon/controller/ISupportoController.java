package it.unicam.Hackathon.controller;

import it.unicam.Hackathon.dto.SupportoRequest;
import it.unicam.Hackathon.entity.Supporto;
import it.unicam.Hackathon.service.GestoreSupporto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/supporto")
@RequiredArgsConstructor
public class ISupportoController {

    private final GestoreSupporto gestoreSupporto;

    @PostMapping
    public ResponseEntity<Map<String, Object>> invia(@RequestBody SupportoRequest dto) {
        Supporto s = gestoreSupporto.richiestaSupporto(dto);
        return ResponseEntity.ok(Map.of(
                "messaggio", "Richiesta supporto inviata!",
                "id", s.getId(),
                "descrizione", s.getDescrizione()
        ));
    }

    @GetMapping("/hackathon/{hackathonId}")
    public ResponseEntity<List<Supporto>> visualizza(@PathVariable Long hackathonId) {
        return ResponseEntity.ok(gestoreSupporto.getRichieste(hackathonId));
    }
}