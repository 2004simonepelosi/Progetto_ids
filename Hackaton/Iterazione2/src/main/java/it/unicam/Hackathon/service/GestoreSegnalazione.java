package it.unicam.Hackathon.service;

import it.unicam.Hackathon.dto.SegnalazioneRequest;
import it.unicam.Hackathon.entity.*;
import it.unicam.Hackathon.validator.SegnalazioneValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GestoreSegnalazione {

    private final SegnalazioneValidator segnalazioneValidator;
    private final SegnalazioneService segnalazioneService;
    private final UtenteService utenteService;
    private final TeamService teamService;
    private final HackathonService hackathonService;

    public Segnalazione inviaSegnalazione(SegnalazioneRequest dto) {
        segnalazioneValidator.validate(dto);
        Utente mentore = utenteService.findById(dto.getMentoreId());
        Team team = teamService.findById(dto.getTeamId());
        Hackathon hackathon = hackathonService.findById(dto.getHackathonId());
        return segnalazioneService.creaSegnalazione(mentore, team, hackathon, dto.getDescrizione());
    }

    public List<Segnalazione> visualizzaSegnalazioni(Long teamId) {
        return segnalazioneService.getAllSegnalazioni(teamId);
    }
}