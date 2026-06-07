package it.unicam.Hackathon.service;

import it.unicam.Hackathon.entity.*;
import it.unicam.Hackathon.entity.enums.StatoInvito;
import it.unicam.Hackathon.exception.BusinessException;
import it.unicam.Hackathon.exception.ResourceNotFoundException;
import it.unicam.Hackathon.repository.RepositoryInvito;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InvitoService {

    private final RepositoryInvito repositoryInvito;

    @Transactional
    public Invito creaInvito(Team team, Utente utente, String descrizione) {
        if (repositoryInvito.findByTeamIdAndUtenteId(team.getId(), utente.getId()).isPresent())
            throw new BusinessException("Invito già inviato a questo utente!");
        Invito invito = Invito.builder()
                .team(team).utente(utente).descrizione(descrizione)
                .stato(StatoInvito.IN_ATTESA).build();
        return repositoryInvito.save(invito);
    }

    public Invito getInvito(Long id) {
        return repositoryInvito.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invito non trovato: " + id));
    }

    @Transactional
    public Invito aggiornaInvito(Invito invito, StatoInvito stato) {
        invito.setStato(stato);
        return repositoryInvito.save(invito);
    }
}