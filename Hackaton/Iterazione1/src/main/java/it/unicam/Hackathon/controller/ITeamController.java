package it.unicam.Hackathon.controller;

import it.unicam.Hackathon.dto.CreaTeamRequest;
import it.unicam.Hackathon.entity.Team;
import it.unicam.Hackathon.service.GestoreTeam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
                "messaggio", "Team creato con successo!",
                "id", t.getId(),
                "nomeTeam", t.getNome(),
                "capoGruppo", t.getCapoGruppo().getNome() + " " + t.getCapoGruppo().getCognome(),
                "ruoloCapoGruppo", t.getCapoGruppo().getRuolo(),
                "hackathon", t.getHackathon().getNome()
        ));
    }
}