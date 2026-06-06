package it.unicam.Hackathon.service;

import it.unicam.Hackathon.entity.*;
import it.unicam.Hackathon.exception.BusinessException;
import it.unicam.Hackathon.exception.ResourceNotFoundException;
import it.unicam.Hackathon.repository.RepositoryTeam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final RepositoryTeam repositoryTeam;

    @Transactional
    public Team salvaTeam(String nome, Utente capoGruppo, Hackathon hackathon) {
        if (repositoryTeam.existsByNomeAndHackathonId(nome, hackathon.getId()))
            throw new BusinessException("Nome team già usato in questo hackathon");
        Team t = Team.builder()
                .nome(nome).capoGruppo(capoGruppo).hackathon(hackathon).build();
        return repositoryTeam.save(t);
    }

    public Team findById(Long id) {
        return repositoryTeam.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team non trovato: " + id));
    }
}