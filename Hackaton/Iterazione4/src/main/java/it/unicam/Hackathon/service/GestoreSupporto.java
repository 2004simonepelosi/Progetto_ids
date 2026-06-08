package it.unicam.Hackathon.service;

import it.unicam.Hackathon.dto.SupportoRequest;
import it.unicam.Hackathon.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Design Pattern: FACADE
 */
@Service
@RequiredArgsConstructor
public class GestoreSupporto {

    private final SupportoService supportoService;
    private final TeamService teamService;
    private final HackathonService hackathonService;

    public Supporto richiestaSupporto(SupportoRequest dto) {
        Team team = teamService.findById(dto.getTeamId());
        Hackathon hackathon = hackathonService.findById(dto.getHackathonId());
        return supportoService.creaRichiesta(team, hackathon, dto.getDescrizione());
    }

    public List<Supporto> getRichieste(Long hackathonId) {
        return supportoService.getRichieste(hackathonId);
    }
}