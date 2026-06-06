package it.unicam.Hackathon.service;

import it.unicam.Hackathon.dto.CreaTeamRequest;
import it.unicam.Hackathon.entity.*;
import it.unicam.Hackathon.entity.enums.RuoloUtente;
import it.unicam.Hackathon.validator.ValidatorTeam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GestoreTeam {

    private final ValidatorTeam validatorTeam;
    private final TeamService teamService;
    private final UtenteService utenteService;
    private final HackathonService hackathonService;

    public Team creaTeam(CreaTeamRequest dto) {
        validatorTeam.validate(dto);
        Utente capo = utenteService.findById(dto.getCapoGruppoId());
        Hackathon hackathon = hackathonService.findById(dto.getHackathonId());
        Team team = teamService.salvaTeam(dto.getNome(), capo, hackathon);
        utenteService.aggiornaRuolo(capo.getId(), RuoloUtente.CAPOGRUPPO);
        return team;
    }
}
