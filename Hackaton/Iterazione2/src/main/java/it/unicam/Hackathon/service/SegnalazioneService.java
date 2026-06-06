package it.unicam.Hackathon.service;

import it.unicam.Hackathon.entity.*;
import it.unicam.Hackathon.entity.enums.RuoloUtente;
import it.unicam.Hackathon.exception.BusinessException;
import it.unicam.Hackathon.exception.ResourceNotFoundException;
import it.unicam.Hackathon.repository.RepositorySegnalazione;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SegnalazioneService {

    private final RepositorySegnalazione repositorySegnalazione;

    @Transactional
    public Segnalazione creaSegnalazione(Utente mentore, Team team, Hackathon hackathon, String descrizione) {
        if (mentore.getRuolo() != RuoloUtente.MENTORE)
            throw new BusinessException("Solo un Mentore può segnalare un team!");
        Segnalazione s = Segnalazione.builder()
                .mentore(mentore).team(team).hackathon(hackathon)
                .descrizione(descrizione).build();
        return repositorySegnalazione.save(s);
    }

    public Segnalazione findById(Long id) {
        return repositorySegnalazione.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Segnalazione non trovata: " + id));
    }

    public List<Segnalazione> getAllSegnalazioni(Long teamId) {
        return repositorySegnalazione.findByTeamId(teamId);
    }
}