package it.unicam.Hackathon.controller;

import it.unicam.Hackathon.dto.*;
import it.unicam.Hackathon.entity.Hackathon;
import it.unicam.Hackathon.service.GestoreHackathon;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hackathon")
@RequiredArgsConstructor
public class IHackathonController {

    private final GestoreHackathon gestoreHackathon;

    @GetMapping
    public ResponseEntity<List<Hackathon>> listaTutti() {
        return ResponseEntity.ok(gestoreHackathon.getAllHackathon());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hackathon> getDettagli(@PathVariable Long id) {
        return ResponseEntity.ok(gestoreHackathon.getDettagli(id));
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> creaHackathon(@RequestBody CreaHackathonRequest dto) {
        Hackathon h = gestoreHackathon.creaHackathon(dto);
        return ResponseEntity.ok(Map.of(
                "messaggio", "Hackathon creato con successo!",
                "id", h.getId(), "nome", h.getNome(),
                "organizzatore", h.getOrganizzatore().getNome() + " " + h.getOrganizzatore().getCognome(),
                "ruoloOrganizzatore", h.getOrganizzatore().getRuolo(), "stato", h.getStato()
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> modificaHackathon(
            @PathVariable Long id, @RequestBody ModificaHackathonRequest dto) {
        Hackathon h = gestoreHackathon.modificaHackathon(id, dto);
        return ResponseEntity.ok(Map.of(
                "messaggio", "Hackathon modificato con successo!",
                "id", h.getId(), "nome", h.getNome(), "stato", h.getStato()
        ));
    }

    @PostMapping("/{id}/giudice")
    public ResponseEntity<Map<String, Object>> nominaGiudice(@PathVariable Long id, @RequestParam Long utenteId) {
        Hackathon h = gestoreHackathon.nominaGiudice(id, utenteId);
        return ResponseEntity.ok(Map.of(
                "messaggio", "Giudice nominato con successo!",
                "giudice", h.getGiudice().getNome() + " " + h.getGiudice().getCognome(),
                "ruolo", h.getGiudice().getRuolo(), "hackathon", h.getNome()
        ));
    }

    @PostMapping("/{id}/mentore")
    public ResponseEntity<Map<String, Object>> nominaMentore(@PathVariable Long id, @RequestParam Long utenteId) {
        Hackathon h = gestoreHackathon.nominaMentore(id, utenteId);
        return ResponseEntity.ok(Map.of(
                "messaggio", "Mentore nominato con successo!",
                "hackathon", h.getNome(), "numeroMentori", h.getMentori().size()
        ));
    }

    @PostMapping("/{id}/vincitore")
    public ResponseEntity<Map<String, Object>> proclamaVincitore(@PathVariable Long id, @RequestParam Long teamId) {
        Hackathon h = gestoreHackathon.proclamaVincitore(id, teamId);
        return ResponseEntity.ok(Map.of(
                "messaggio", "Team proclamato vincitore!",
                "teamVincitore", h.getVincitore().getNome(), "stato", h.getStato()
        ));
    }
}