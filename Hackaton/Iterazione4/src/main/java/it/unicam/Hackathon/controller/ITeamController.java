package it.unicam.Hackathon.controller;

import it.unicam.Hackathon.dto.*;
import it.unicam.Hackathon.entity.*;
import it.unicam.Hackathon.service.GestoreTeam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
public class ITeamController {

    private final GestoreTeam gestoreTeam;

    @PostMapping
    public ResponseEntity<Map<String, Object>> creaTeam(@RequestBody CreaTeamRequest dto) {
        Team t = gestoreTeam.creaTeam(dto);
        return ResponseEntity.ok(Map.of(
                "messaggio", "Team creato con successo!", "id", t.getId(),
                "nomeTeam", t.getNome(),
                "capoGruppo", t.getCapoGruppo().getNome() + " " + t.getCapoGruppo().getCognome(),
                "ruoloCapoGruppo", t.getCapoGruppo().getRuolo(),
                "hackathon", t.getHackathon().getNome()
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> modificaTeam(
            @PathVariable Long id, @RequestBody ModificaTeamRequest dto) {
        Team t = gestoreTeam.modificaTeam(id, dto);
        return ResponseEntity.ok(Map.of(
                "messaggio", "Team modificato con successo!",
                "id", t.getId(), "nomeTeam", t.getNome()
        ));
    }

    @GetMapping("/{id}/membri")
    public ResponseEntity<List<Membro>> getMembri(@PathVariable Long id) {
        return ResponseEntity.ok(gestoreTeam.getMembri(id));
    }

    @DeleteMapping("/{teamId}/membri/{membroId}")
    public ResponseEntity<Map<String, Object>> rimuoviMembro(
            @PathVariable Long teamId, @PathVariable Long membroId) {
        gestoreTeam.rimuoviMembro(membroId);
        return ResponseEntity.ok(Map.of("messaggio", "Membro Rimosso!"));
    }
}