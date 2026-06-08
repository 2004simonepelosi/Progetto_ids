package it.unicam.Hackathon.service;

import it.unicam.Hackathon.dto.InvitoRequest;
import it.unicam.Hackathon.entity.*;
import it.unicam.Hackathon.entity.enums.RuoloUtente;
import it.unicam.Hackathon.entity.enums.StatoInvito;
import it.unicam.Hackathon.repository.RepositoryMembro;
import it.unicam.Hackathon.validator.InvitoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GestoreInvito {

    private final InvitoValidator invitoValidator;
    private final InvitoService invitoService;
    private final UtenteService utenteService;
    private final TeamService teamService;
    private final RepositoryMembro repositoryMembro;

    public Invito creaInvito(InvitoRequest dto) {
        invitoValidator.validate(dto);
        Team team = teamService.findById(dto.getTeamId());
        Utente utente = utenteService.findById(dto.getUtenteId());
        return invitoService.creaInvito(team, utente, dto.getDescrizione());
    }

    @Transactional
    public Invito accettaInvito(Long invitoId) {
        Invito invito = invitoService.getInvito(invitoId);
        invito = invitoService.aggiornaInvito(invito, StatoInvito.ACCETTATO);
        utenteService.aggiornaRuolo(invito.getUtente().getId(), RuoloUtente.MEMBRO);
        Membro membro = Membro.builder()
                .utente(invito.getUtente())
                .team(invito.getTeam())
                .build();
        repositoryMembro.save(membro);
        return invito;
    }

    public Invito rifiutaInvito(Long invitoId) {
        Invito invito = invitoService.getInvito(invitoId);
        return invitoService.aggiornaInvito(invito, StatoInvito.RIFIUTATO);
    }
}