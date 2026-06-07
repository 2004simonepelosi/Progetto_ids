package it.unicam.Hackathon.service;

import it.unicam.Hackathon.dto.CreaTeamRequest;
import it.unicam.Hackathon.dto.ModificaTeamRequest;
import it.unicam.Hackathon.entity.*;
import it.unicam.Hackathon.entity.enums.RuoloUtente;
import it.unicam.Hackathon.validator.ValidatorTeam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public Team modificaTeam(Long teamId, ModificaTeamRequest dto) {
        return teamService.modificaTeam(teamId, dto.getNome());
    }

    public List<Membro> getMembri(Long teamId) {
        return teamService.getMembri(teamId);
    }

    public void rimuoviMembro(Long membroId) {
        teamService.rimuoviMembro(membroId);
    }
}