package it.unicam.Hackathon.controller;

import it.unicam.Hackathon.dto.*;
import it.unicam.Hackathon.entity.Utente;
import it.unicam.Hackathon.service.GestoreUtente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/utenti")
@RequiredArgsConstructor
public class IUserController {

    private final GestoreUtente gestoreUtente;

    @PostMapping("/registrazione")
    public ResponseEntity<Map<String, Object>> registrazione(@RequestBody RegistrazioneRequest dto) {
        Utente u = gestoreUtente.registrazioneUtente(dto);
        return ResponseEntity.ok(Map.of(
                "messaggio", "Benvenuto " + u.getNome() + "!",
                "id", u.getId(),
                "utente", u.getNome() + " " + u.getCognome(),
                "ruolo", u.getRuolo()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest dto) {
        Utente u = gestoreUtente.loginUtente(dto);
        return ResponseEntity.ok(Map.of(
                "messaggio", "Benvenuto " + u.getNome() + "!",
                "id", u.getId(),
                "utente", u.getNome() + " " + u.getCognome(),
                "ruolo", u.getRuolo()
        ));
    }
}