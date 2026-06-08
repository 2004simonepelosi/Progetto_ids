package it.unicam.Hackathon.controller;

import it.unicam.Hackathon.dto.*;
import it.unicam.Hackathon.entity.*;
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
                "id", s.getId(), "descrizione", s.getDescrizione()
        ));
    }

    @GetMapping("/hackathon/{hackathonId}")
    public ResponseEntity<List<Supporto>> visualizza(@PathVariable Long hackathonId) {
        return ResponseEntity.ok(gestoreSupporto.getRichieste(hackathonId));
    }

    @PostMapping("/call")
    public ResponseEntity<Map<String, Object>> proponeCall(@RequestBody ProponeCallRequest dto) {
        CallSupporto call = gestoreSupporto.proponeCall(dto);
        return ResponseEntity.ok(Map.of(
                "messaggio", "Call Prenotata!",
                "id", call.getId(),
                "ora", call.getOra(),
                "stato", call.getStato()
        ));
    }

    @PostMapping("/call/{id}/prenota")
    public ResponseEntity<Map<String, Object>> prenotaSlot(
            @PathVariable Long id, @RequestBody PrenotaSlotRequest dto) {
        dto.setCallId(id);
        CallSupporto call = gestoreSupporto.prenotaSlot(dto);
        return ResponseEntity.ok(Map.of(
                "messaggio", "Slot Prenotato!",
                "id", call.getId(),
                "slotConfermato", call.getOra(),
                "stato", call.getStato()
        ));
    }
}